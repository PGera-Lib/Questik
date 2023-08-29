package xyz.gmfatoom.common.requestik.data.local

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.supervisorScope
import kotlinx.datetime.DateTimePeriod
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import xyz.gmfatoom.common.database.reQuestikDatabase
import xyz.gmfatoom.common.requestik.data.mappers.toRequestMoodel
import xyz.gmfatoom.common.requestik.domain.RequestikDataSource
import xyz.gmfatoom.common.requestik.domain.model.CorpModel
import xyz.gmfatoom.common.requestik.domain.model.ObjectsModel
import xyz.gmfatoom.common.requestik.domain.model.RequestModel
import xyz.gmfatoom.common.requestik.domain.model.UsersModel
import xyz.gmfatoom.common.utils.DateTimeUtil.getDataList

class SqlDelightRequestsDataSource(
    db: reQuestikDatabase
) : RequestikDataSource {
    private val requestsQueries = db.requestsQueries
    private val categoryQueries = db.categoryQueries
    override fun getDataListFlow(firstDay: LocalDate): Flow<List<LocalDate>> = flow {
        this.emit(getDataList(firstDay.minus(30, DateTimeUnit.DAY)))
    }.map { dataList->
        supervisorScope {
          dataList
              .map {
              it
          }
        }
    }

    override fun getRequests(): Flow<List<RequestModel>> {
        return requestsQueries
            .getRequestEntity()
            .asFlow()
            .mapToList()
            .map { requestEntities ->
                supervisorScope {
                    requestEntities
                        .map {
                            async { it.toRequestMoodel(
                                corpModel= CorpModel(),
                                objectsModel= ObjectsModel(),
                                contactList = emptyList(),
                                userCreator= UsersModel(),
                                userCurrent= UsersModel(),
                                requestItems= emptyList()
                            )}
                        }
                        .map { it.await() }
                }
            }
    }
/*'


    override suspend fun insertRequest(request: RequestModel) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteRequest(id: Long) {
        TODO("Not yet implemented")
    }

    override fun getRequestById(id: Long): Flow<RequestModel> {
        TODO("Not yet implemented")
    }
*/


}
