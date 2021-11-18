package ru.rinet.questik.repo.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import ru.rinet.questik.paging.JobsRemoteMediator
import ru.rinet.questik.repo.local.room.AppDatabase
import javax.inject.Inject

class ItemRemoteDataSource @Inject constructor(
    private val appDatabase: AppDatabase,
    private val jobsRemoteMediator: JobsRemoteMediator
) : BaseDataSource {

    @ExperimentalPagingApi
    fun getRemoteAndLocalFlow() = Pager(
        config = PagingConfig(5),
        remoteMediator = jobsRemoteMediator
    ) {
        appDatabase.jobsDao().getAllJobs()
    }.flow
}