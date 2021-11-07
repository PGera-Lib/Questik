package ru.rinet.questik.ui.catalog.jobs.paged

import androidx.paging.DataSource
import ru.rinet.questik.models.CategoryModel
import ru.rinet.questik.ui.catalog.jobs.holders.JobsChild

class JobsDataSourceFactory(category: CategoryModel, startAfter: Long) : DataSource.Factory<Long, JobsChild>() {

    val source = JobPageKeyedDataSource(category, startAfter)

    override fun create(): DataSource<Long, JobsChild> {
        return source
    }
}