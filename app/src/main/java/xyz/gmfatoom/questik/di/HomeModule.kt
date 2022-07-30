package xyz.gmfatoom.questik.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit
import xyz.gmfatoom.questik.repo.QuestikRepository
import xyz.gmfatoom.questik.repo.local.room.AppDatabase
import xyz.gmfatoom.questik.repo.local.room.DbHelper
import xyz.gmfatoom.questik.repo.remote.HomeItemService

@Module
@InstallIn(ActivityComponent::class)
object HomeModule {

/*
    @Provides
    fun provideHomeItemService(retrofit: Retrofit): HomeItemService =
        retrofit.create(HomeItemService::class.java)
*/

    @Provides
    fun provideDbHelper(appDatabase: AppDatabase) = DbHelper(appDatabase)

    @Provides
    fun provideItemRepository(dbHelper: DbHelper, appDatabase: AppDatabase) =
     QuestikRepository(dbHelper, appDatabase)

}