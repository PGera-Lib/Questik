package ru.rinet.questik.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import retrofit2.HttpException
import ru.rinet.questik.repo.local.room.AppDatabase
import ru.rinet.questik.repo.local.room.entity.JobsEntity
import ru.rinet.questik.repo.local.room.entity.RemoteKeys
import ru.rinet.questik.repo.remote.HomeItemService
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class JobsRemoteMediator(
    private val database: AppDatabase,
    private val homeItemService: HomeItemService
) : RemoteMediator<Int, JobsEntity>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, JobsEntity>):
            MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.next?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    remoteKeys?.prev
                        ?: return MediatorResult.Success(endOfPaginationReached = false)
                    remoteKeys.prev
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    remoteKeys?.next ?: return MediatorResult.Success(endOfPaginationReached = true)
                    remoteKeys.next
                }
            }
            val response = homeItemService.getAllItems(page)

            database.withTransaction{
                if(loadType == LoadType.REFRESH){
                    database.jobsDao().clearJobs()
                    database.remoteKeysDao().clearKeys()
                }
                val next = response.body()?.info?.next
                val nextPage = next?.split("page=")?.get(1)?.toIntOrNull()
                val prev:String? = response.body()?.info?.prev
                val prevPage = prev?.split("page=")?.get(1)?.toIntOrNull()
                val keys = response.body()?.jobs?.map {
                    RemoteKeys(id = it.id,next = nextPage,prev = prevPage)
                }
                response.body()?.jobs?.let { database.jobsDao().insertAllJobs(it) }
                keys?.let { database.remoteKeysDao().insertAll(it) }
            }

          //  val response = homeItemService.getFireBaseJobs(page)
/*            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.jobsDao().clearJobs()
                    database.remoteKeysDao().clearKeys()
                }
                fun pagePlus(page: Int) : Int{
                    val page = page+15
                    return  page
                }
                fun pageMinus(page: Int) : Int{
                    val page = page-15
                    return  page
                }
                val next = response.subList(page, pagePlus(page)).lastOrNull()?.id.toString()
                val nextPage = next?.split("page=")?.get(1)?.toIntOrNull()
                val prev:String? = response.subList(page, pageMinus(page)).firstOrNull()?.id.toString()
                val prevPage = prev?.split("page=")?.get(1)?.toIntOrNull()

                val keys = response.map {
                    RemoteKeys(id = it.id, next = nextPage, prev = prevPage)
                }
                response.let { database.jobsDao().insertAllJobs(it) }
                keys?.let { database.remoteKeysDao().insertAll(it) }
            }*/
            MediatorResult.Success(endOfPaginationReached = response.body()?.info?.count == null)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }


    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, JobsEntity>): RemoteKeys? {
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { item ->
                database.remoteKeysDao().remoteKeysId(item.id)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, JobsEntity>): RemoteKeys? {
        return state.pages.firstOrNull() { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { item ->
                database.remoteKeysDao().remoteKeysId(item.id)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, JobsEntity>
    ): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                database.remoteKeysDao().remoteKeysId(id)
            }
        }
    }
}