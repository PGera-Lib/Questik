package xyz.gmfatoom.questik.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import xyz.gmfatoom.questik.repo.local.room.AppDatabase
import xyz.gmfatoom.questik.utils.Util
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit() = Util.provideRetrofit()

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context) =
        AppDatabase.getDatabase(context)

    @Provides
    @Singleton
    fun provideAuth(): FirebaseAuth = FirebaseAuth.getInstance()
}