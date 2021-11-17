package ru.rinet.questik.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit
import ru.rinet.questik.paging.JobsPageSource
import ru.rinet.questik.paging.JobsRemoteMediator
import ru.rinet.questik.repo.QuestikRepository
import ru.rinet.questik.repo.local.room.AppDatabase
import ru.rinet.questik.repo.local.room.DbHelper
import ru.rinet.questik.repo.remote.HomeItemService
import ru.rinet.questik.repo.remote.ItemRemoteDataSource

@Module
@InstallIn(ActivityComponent::class)
object HomeModule {

    @Provides
    fun provideHomeItemService(retrofit: Retrofit): HomeItemService =
        retrofit.create(HomeItemService::class.java)

    @Provides
    fun provideJobsRemoteMediator(appDatabase: AppDatabase, homeItemService:HomeItemService) =
        JobsRemoteMediator(appDatabase,homeItemService)

    @Provides
    fun provideRemoteItemDataSource(appDatabase: AppDatabase,jobsRemoteMediator: JobsRemoteMediator) =
        ItemRemoteDataSource(appDatabase,jobsRemoteMediator)

    @Provides
    fun provideDbHelper(appDatabase: AppDatabase) = DbHelper(appDatabase)

    @Provides
    fun provideItemRepository(itemRemoteDataSource: ItemRemoteDataSource, dbHelper: DbHelper, appDatabase: AppDatabase) =
     QuestikRepository(itemRemoteDataSource, dbHelper, appDatabase)


    @Provides
    fun provideItemPageSource(homeItemService: HomeItemService) =
        JobsPageSource(homeItemService)

/*    @Provides
    fun provideHomePageAdapter() =
        HomePageAdapter(ItemComparator)*/
}