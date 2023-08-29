package xyz.gmfatoom.common.requestik.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate
import xyz.gmfatoom.common.requestik.domain.model.RequestModel

interface RequestikDataSource {
    fun getDataListFlow(cureentDay: LocalDate): Flow<List<LocalDate>>
    /**
     * Request
     */


    fun getRequests(): Flow<List<RequestModel>>
    /*
   suspend fun insertRequest(request: RequestModel)
   suspend fun deleteRequest(id: Long)
   fun getRequestById(id: Long): Flow<RequestModel>
   */

    /**
     * Category
     */



    /**
     * Contacts
     */



    /**
     * Corp
     */


    /**
     * Job
     */


    /**
     * Material
     */


    /**
     * Messages
     */


    /**
     * Metric
     */


    /**
     * Objects
     */


    /**
     * Phone Contacts
     */



    /**
     * Reques Items
     */



    /**
     * Users
     */
}