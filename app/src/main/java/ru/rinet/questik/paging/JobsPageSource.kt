package ru.rinet.questik.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.rinet.questik.repo.local.room.entity.JobsEntity
import ru.rinet.questik.repo.remote.HomeItemService

class JobsPageSource(
    private val homeItemService: HomeItemService
): PagingSource<Int, JobsEntity>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, JobsEntity> {
        try {
            val page = params.key?:1
            val response = homeItemService.getAllItems(page)

            val next = response.body()?.info?.next
            val nextPage = next?.split("page=")?.get(1)?.toIntOrNull()
            val prev:String? = response.body()?.info?.prev
            val prevPage = prev?.split("page=")?.get(1)?.toIntOrNull()
            val result:List<JobsEntity> = response.body()?.jobs.run { this as List<JobsEntity> }
            return LoadResult.Page(
                data = result,
                prevKey = prevPage,
                nextKey = nextPage
            )
        }catch (e:Throwable){
            Log.e("Error","Item Page Source",e)
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, JobsEntity>): Int? {
            return state.anchorPosition?.let { anchorPosition ->
                val anchorPageIndex = state.pages.indexOf(state.closestPageToPosition(anchorPosition))
                state.pages.getOrNull(anchorPageIndex + 1)?.prevKey ?: state.pages.getOrNull(anchorPageIndex - 1)?.nextKey
            }
    }
}