package xyz.gmfatoom.questik.repo.local.room

import androidx.paging.PagingSource
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import xyz.gmfatoom.questik.repo.local.room.entity.*

interface IDbHelper {
    /**
     * Category
     */
    fun insertCategory(category: CategoryEntity)
    fun updateCategory(category: CategoryEntity)
    fun deleteCategory(category: CategoryEntity)
    fun getCategoryById(id: Int): CategoryEntity
    fun getCategories(): List<CategoryEntity>
    fun getAllCategories(): PagingSource<Int, CategoryEntity>
    suspend fun insertAllCategories(items:List<CategoryEntity>)
    fun clearCategories()
    fun getCategoriesCount():Int
    fun getCategoriesCountFlow(): Flow<Int>


    /**
     * Jobs
     */
    fun getAllJobs(): PagingSource<Int, JobsEntity>
    suspend fun insertAllJobs(items:List<JobsEntity>)
    fun clearJobs()
    fun insertJob(job: JobsEntity)
    fun updateJob(job: JobsEntity)
    fun deleteJob(job: JobsEntity)
    fun getJobById(id: Int): List<JobsEntity>
    fun getJobs(): List<JobsEntity>
    fun getJobsCount():Int
    fun getJobsCountFlow(): Flow<Int>
    fun getJobByCategoryId(id: String): List<JobsEntity>

    /**
     * Material
     */
    fun insertMaterial(material: MaterialEntity)
    fun updateMaterial(material: MaterialEntity)
    fun deleteMaterial(material: MaterialEntity)
    fun getMaterialyById(id: Int): List<MaterialEntity>
    fun getMaterials(): List<MaterialEntity>
    fun getAllMaterials(): PagingSource<Int, MaterialEntity>
    suspend  fun insertAllMaterials(items:List<MaterialEntity>)
    fun clearMaterials()
    fun getMaterialsCount():Int
    fun getMaterialsCountFlow(): Flow<Int>
    fun getMaterialByCategoryId(id: String): List<MaterialEntity>


    /**
     * Metrics
     */
    fun insertMetrics(metrics: MetricsEntity)
    fun updateMetrics(metrics: MetricsEntity)
    fun deleteMetrics(metrics: MetricsEntity)
    fun getMetricsById(id: Int): MetricsEntity
    fun getMetrics(): List<MetricsEntity>
    fun getAllMetrics(): PagingSource<Int, MetricsEntity>
    suspend  fun insertAllMetrics(items:List<MetricsEntity>)
    fun clearMetrics()
    fun getMetricsCount():Int
    fun getMetricsCountFlow(): Flow<Int>
    /**
     * Users
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserEntity)
    fun updateUser(user: UserEntity)
    fun deleteUser(user: UserEntity)
    fun getUserById(id: Int): List<UserEntity>
    fun getUser(): List<UserEntity>
    fun geUsersCount():Int
    fun geUsersCountFlow(): Flow<Int>

    /**
     * @RequestItem
     */

    fun getRequestItemCount(): Int
    fun getRequestItemCountFlow(): Flow<Int>
    fun insertAllRequestItems(items:List<RequestItemEntity>)
    fun clearRequestItem()
    fun insertRequestItem(chernovik: RequestItemEntity)
    fun updateRequestItem(chernovik: RequestItemEntity)
    fun deleteRequestItem(chernovik: RequestItemEntity)
    fun getRequestItemById(id: Int): RequestItemEntity
    fun getRequestItem(): List<RequestItemEntity>
    fun getRequestItemByCategoryId(id: String): List<RequestItemEntity>
    fun searchRequestItem(search: String?, cat: String): List<RequestItemEntity>
    fun filteredRequestItemList(search: String?, check: Boolean, cat: String): List<RequestItemEntity>
    fun getRequestItemByItogShow(check: Boolean, cat: String): List<RequestItemEntity>
    fun getRequestItemByIdDiapazon(start: Int, end: Int): List<RequestItemEntity>
    fun getRequestItemCountByCategoryId(id: String): Int
    fun getRequestItemByCategoryAndIdDiapazon(categoryId:String, start: Int, end: Int): List<RequestItemEntity>

    fun getRequestItemsByProjectId(id: Int, categoryId:String): List<RequestItemEntity>
    /**
     * @Request
     */

    fun getRequestsCount(): Int
    fun getRequestsCountFlow(): Flow<Int>
    fun insertAllRequests(items:List<RequestsEntity>)
    /* fun clearChernovikItems()*/

    fun insertRequest(request: RequestsEntity):Long
    fun updateRequest(request: RequestsEntity)
    fun deleteRequest(request: RequestsEntity)

    fun getRequestById(id: Int): RequestsEntity
    fun getRequests(): List<RequestsEntity>

    /**
     * @Contacts
     */




    fun getContactsCount(): Int
    fun getContactsCountFlow(): Flow<Int>
/*    fun getAllContacts(): PagingSource<Int, ContactsEntity>*/
    fun insertAllContacts(items:List<ContactsEntity>)
    fun clearContacts()
    fun insertContacts(contacts: ContactsEntity):Long
    fun updateContacts(contacts: ContactsEntity)
    fun deleteContacts(contacts: ContactsEntity)
    fun getContactsById(id: Int): ContactsEntity
    fun getContacts(): List<ContactsEntity>

    /**
     * @Corp
     */

    fun getCorpCount(): Int
    fun getCorpCountFlow(): Flow<Int>
/*    fun getAllCorp(): PagingSource<Int, CorpEntity>*/
    fun insertAllCorp(items:List<CorpEntity>)
    fun clearCorp()
    fun insertCorp(corp: CorpEntity):Long
    fun updateCorp(corp: CorpEntity)
    fun deleteCorp(corp: CorpEntity)
    fun getCorpById(id: Int): CorpEntity
    fun getCorp(): List<CorpEntity>


    /**
     * @Object
     */

    fun getObjectsCount(): Int
    fun getObjectsCountFlow(): Flow<Int>
    fun insertAllObjects(items:List<ObjectsEntity>)
    fun clearObjects()
    fun insertObjects(objects: ObjectsEntity):Long
    fun updateObjects(objects: ObjectsEntity)
    fun deleteObjects(objects: ObjectsEntity)
    fun getObjectsById(id: Int): ObjectsEntity
    fun getObjects(): List<ObjectsEntity>


}