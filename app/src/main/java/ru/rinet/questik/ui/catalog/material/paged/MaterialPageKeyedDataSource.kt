package ru.rinet.questik.ui.catalog.jobs.paged

import android.util.Log
import androidx.paging.PageKeyedDataSource
import ru.rinet.questik.models.MaterialModel
import ru.rinet.questik.ui.catalog.jobs.holders.MaterialChild
import ru.rinet.questik.utils.CATALOG_LIST_MATERIAL
import ru.rinet.questik.utils.CATALOG_SEARCHRD_MATERIALS

class MaterialPageKeyedDataSource(val materialId: Long?, val startAfter: Long) :
    PageKeyedDataSource<Long, MaterialChild>() {

    companion object {
        private val TAG = MaterialPageKeyedDataSource::class.java.simpleName
    }


    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, MaterialChild>) {
        // not used
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, MaterialChild>) {
/*        Log.e(TAG, "Load Messages After: ${params.key}")
        val jobs = getMessagesAfter(category, params.key)
        val nextLoadKey = jobs?.lastOrNull()?.id?.toLong()
        callback.onResult(jobs?.map { JobsChild(it) }!!, nextLoadKey)*/
    }

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, MaterialChild>
    ) {
        Log.e(TAG, "Load Initial Job After: $startAfter")
        val materials = getMaterialsAfter(materialId, startAfter)
        val nextLoadKey = materials?.lastOrNull()?.id?.toLong()
        callback.onResult(materials?.map { MaterialChild(it) }!!, null, nextLoadKey)
    }

    private fun getMaterialsAfter(materialId: Long?, startAfter: Long): List<MaterialModel>? {
        if (CATALOG_SEARCHRD_MATERIALS.isEmpty()) {
            CATALOG_SEARCHRD_MATERIALS.apply {
                for (mat in CATALOG_LIST_MATERIAL) {
                    add(mat)
                }
            }
        }
        return CATALOG_SEARCHRD_MATERIALS

    }
}