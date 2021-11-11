package ru.rinet.questik.ui.catalog.jobs.paged

import androidx.paging.DataSource
import ru.rinet.questik.ui.catalog.jobs.holders.MaterialChild

class MaterialDataSourceFactory(materialId: Long?, startAfter: Long) : DataSource.Factory<Long, MaterialChild>() {

    val source = MaterialPageKeyedDataSource(materialId, startAfter)

    override fun create(): DataSource<Long, MaterialChild> {
        return source
    }
}