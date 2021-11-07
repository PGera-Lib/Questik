package ru.rinet.questik.ui.catalog.jobs.paged

import android.util.Log
import androidx.paging.PageKeyedDataSource
import ru.rinet.questik.models.CategoryModel
import ru.rinet.questik.models.JobsModel
import ru.rinet.questik.ui.catalog.jobs.holders.JobsChild
import ru.rinet.questik.utils.JOBS_HASHMAP

class JobPageKeyedDataSource (val category: CategoryModel, val startAfter: Long) : PageKeyedDataSource<Long, JobsChild>() {

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

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Long, JobsChild>) {
        Log.e(TAG, "Load Initial Messages After: $startAfter")

        val jobs = getMessagesAfter(category, startAfter)
        val nextLoadKey = jobs?.lastOrNull()?.id?.toLong()

        callback.onResult(jobs?.map { JobsChild(it) }!!, null, nextLoadKey)

    }
    private fun getMessagesAfter(category: CategoryModel, startAfter: Long): List<JobsModel>? {
        return JOBS_HASHMAP.get(category)?.toList()
    }
}