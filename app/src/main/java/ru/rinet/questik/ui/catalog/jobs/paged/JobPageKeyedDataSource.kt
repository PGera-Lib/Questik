package ru.rinet.questik.ui.catalog.jobs.paged

import android.util.Log
import androidx.paging.PageKeyedDataSource
import ru.rinet.questik.models.CategoryModel
import ru.rinet.questik.models.JobsModel
import ru.rinet.questik.ui.catalog.jobs.holders.JobsChild
import ru.rinet.questik.utils.JOBS_HASHMAP
import ru.rinet.questik.utils.JOBS_SEARCHED_MAP

class JobPageKeyedDataSource(val category: CategoryModel, val startAfter: Long) :
    PageKeyedDataSource<Long, JobsChild>() {

    companion object {
        private val TAG = JobPageKeyedDataSource::class.java.simpleName
    }


    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, JobsChild>) {
        // not used
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, JobsChild>) {
/*        Log.e(TAG, "Load Messages After: ${params.key}")
        val jobs = getMessagesAfter(category, params.key)
        val nextLoadKey = jobs?.lastOrNull()?.id?.toLong()
        callback.onResult(jobs?.map { JobsChild(it) }!!, nextLoadKey)*/
    }

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, JobsChild>
    ) {
        Log.e(TAG, "Load Initial Job After: $startAfter")
        val jobs = getJobsAfter(category, startAfter)
        val nextLoadKey = jobs?.lastOrNull()?.id?.toLong()
        callback.onResult(jobs?.map { JobsChild(it) }!!, null, nextLoadKey)
    }

    private fun getJobsAfter(category: CategoryModel, startAfter: Long): List<JobsModel>? {
        if (JOBS_SEARCHED_MAP.isEmpty()) {
            JOBS_SEARCHED_MAP.apply {
                for ((k, v) in JOBS_HASHMAP) {
                    put(k, v)
                }
            }
        }
        return JOBS_SEARCHED_MAP.get(category)?.toList()

    }
}
