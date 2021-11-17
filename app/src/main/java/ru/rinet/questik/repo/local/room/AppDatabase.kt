package ru.rinet.questik.repo.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.rinet.questik.repo.local.room.dao.*
import ru.rinet.questik.repo.local.room.entity.*

@Database(entities = [CategoryEntity::class, JobsEntity::class, MaterialEntity::class, MetricsEntity::class, UserEntity::class,RemoteKeys::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun jobsDao(): JobsDao
    abstract fun materialDao(): MaterialDao
    abstract fun metricsDao(): MetricsDao
    abstract fun userDao(): UserDao
    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object{
        @Volatile private var instance:AppDatabase? = null
        fun getDatabase(context: Context):AppDatabase =
            instance?: synchronized(this){
                instance?: buildDatabase(context).apply { instance = this }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context,AppDatabase::class.java,"questik_database.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}