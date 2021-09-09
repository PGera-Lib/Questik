package xyz.gmfatoom.questik.repo

import androidx.paging.PagingSource
import kotlinx.coroutines.flow.Flow
import xyz.gmfatoom.questik.repo.local.room.AppDatabase
import xyz.gmfatoom.questik.repo.local.room.DbHelper
import xyz.gmfatoom.questik.repo.local.room.entity.*
import javax.inject.Inject

class QuestikRepository @Inject constructor(
    private val dbHelper: DbHelper,
    private val appDatabase: AppDatabase

):Repository{
    /**
     * Category
     */
    fun insertCategory(category: CategoryEntity) = dbHelper.insertCategory(category)
    fun updateCategory(category: CategoryEntity) = dbHelper.updateCategory(category)
    fun deleteCategory(category: CategoryEntity) = dbHelper.deleteCategory(category)
    fun getCategoryById(id: Int): CategoryEntity = dbHelper.getCategoryById(id)
    fun getCategories(): List<CategoryEntity> = dbHelper.getCategories()
    fun getAllCategories(): PagingSource<Int, CategoryEntity> = dbHelper.getAllCategories()
    suspend fun insertAllCategories(items:List<CategoryEntity>) = dbHelper.insertAllCategories(items)
    fun clearCategories() = dbHelper.clearCategories()
    fun getCategoriesCount(): Int = dbHelper.getCategoriesCount()
    fun getCategoriesCountFlow(): Flow<Int> = dbHelper.getCategoriesCountFlow()

    /**
     * Jobs
     */
    fun getAllJobs(): PagingSource<Int, JobsEntity> = dbHelper.getAllJobs()
    suspend fun installJobData(data: MutableList<JobsEntity>) =
        dbHelper.insertAllJobs(data)
    fun clearJobs()= dbHelper.clearJobs()
    fun insertJob(job: JobsEntity)= dbHelper.insertJob(job)
    fun updateJob(job: JobsEntity)= dbHelper.updateJob(job)
    fun deleteJob(job: JobsEntity)= dbHelper.deleteJob(job)
    fun getJobById(id: Int): List<JobsEntity> = dbHelper.getJobById(id)
    fun getJobs(): List<JobsEntity> = dbHelper.getJobs()
    fun getJobsCount(): Int = dbHelper.getJobsCount()
    fun getJobsCountFlow(): Flow<Int> = dbHelper.getJobsCountFlow()
    fun getJobsByCategoryId(id: String) : List<JobsEntity> = dbHelper.getJobByCategoryId(id)

    /**
     * Material
     */
    fun insertMaterial(material: MaterialEntity) = dbHelper.insertMaterial(material)
    fun updateMaterial(material: MaterialEntity) = dbHelper.updateMaterial(material)
    fun deleteMaterial(material: MaterialEntity)  = dbHelper.deleteMaterial(material)
    fun getMaterialyById(id: Int): List<MaterialEntity> = dbHelper.getMaterialyById(id)
    fun getMaterials(): List<MaterialEntity> = dbHelper.getMaterials()
    fun getAllMaterials(): PagingSource<Int, MaterialEntity> = dbHelper.getAllMaterials()
    suspend  fun insertAllMaterials(items:List<MaterialEntity>) = dbHelper.insertAllMaterials(items)
    fun clearMaterials() = dbHelper.clearMaterials()
    fun getMaterialsCount(): Int = dbHelper.getMaterialsCount()
    fun getMaterialsCountFlow(): Flow<Int> = dbHelper.getMaterialsCountFlow()
    fun getMaterialByCategoryId(id: String) : List<MaterialEntity> = dbHelper.getMaterialByCategoryId(id)

    /**
     * Metrics
     */
    fun insertMetrics(metrics: MetricsEntity) = dbHelper.insertMetrics(metrics)
    fun updateMetrics(metrics: MetricsEntity) = dbHelper.updateMetrics(metrics)
    fun deleteMetrics(metrics: MetricsEntity) = dbHelper.deleteMetrics(metrics)
    fun getMetricsById(id: Int): MetricsEntity = dbHelper.getMetricsById(id)
    fun getMetrics(): List<MetricsEntity> = dbHelper.getMetrics()
    fun getAllMetrics(): PagingSource<Int, MetricsEntity> = dbHelper.getAllMetrics()
    suspend  fun insertAllMetrics(items:List<MetricsEntity>) = dbHelper.insertAllMetrics(items)
    fun clearMetrics() = dbHelper.clearMetrics()
    fun getMetricsCount(): Int = dbHelper.getMetricsCount()
    fun getMetricsCountFlow(): Flow<Int> = dbHelper.getMetricsCountFlow()
    /**
     * Users
     */

    fun insertUser(user: UserEntity) = dbHelper.insertUser(user)
    fun updateUser(user: UserEntity) = dbHelper.updateUser(user)
    fun deleteUser(user: UserEntity) = dbHelper.deleteUser(user)
    fun getUserById(id: Int): List<UserEntity> = dbHelper.getUserById(id)
    fun getUser(): List<UserEntity> = dbHelper.getUser()
    fun getUsersCount(): Int = dbHelper.geUsersCount()
    fun getUsersCountFlow(): Flow<Int> = dbHelper.geUsersCountFlow()

    /**
     * @Chernovik
     */

    fun getChernovikCount(): Int = dbHelper.getRequestItemCount()
    fun getChernovikCountFlow(): Flow<Int> = dbHelper.getRequestItemCountFlow()
    fun insertAllChernovik(items:List<RequestItemEntity>) = dbHelper.insertAllRequestItems(items)
    fun clearChernovik()= dbHelper.clearRequestItem()
    fun insertRequestItem(chernovik: RequestItemEntity)= dbHelper.insertRequestItem(chernovik)
    fun updateChernovik(chernovik: RequestItemEntity)= dbHelper.updateRequestItem(chernovik)
    fun deleteChernovik(chernovik: RequestItemEntity)= dbHelper.deleteRequestItem(chernovik)
    fun getChernovikById(id: Int): RequestItemEntity = dbHelper.getRequestItemById(id)
    fun getChernovik(): List<RequestItemEntity> = dbHelper.getRequestItem()
    fun getChernovikByCategoryId(id: String): List<RequestItemEntity> = dbHelper.getRequestItemByCategoryId(id)
    fun searchChernovikItem(search: String?, cat: String): List<RequestItemEntity> = dbHelper.searchRequestItem(search, cat)
    fun filteredChernovikList(
        search: String?,
        check: Boolean, cat: String
    ): List<RequestItemEntity>  = dbHelper.filteredRequestItemList(search, check, cat)
    fun getChernovikByItogShow(check: Boolean, cat: String): List<RequestItemEntity>  = dbHelper.getRequestItemByItogShow(check, cat)
    fun getChernovikByIdDiapazon(start: Int, end: Int): List<RequestItemEntity> = dbHelper.getRequestItemByIdDiapazon(start, end)
    fun getChernovikCountByCategoryId(id: String): Int = dbHelper.getRequestItemCountByCategoryId(id)
    fun getChernovikByCategoryAndIdDiapazon(categoryId:String, start: Int, end: Int): List<RequestItemEntity> = dbHelper.getRequestItemByCategoryAndIdDiapazon(categoryId, start, end)
    fun getRequestItemsByProjectId(id: Int, categoryId: String): List<RequestItemEntity> = dbHelper.getRequestItemsByProjectId(id, categoryId)

    /**
     * @Requests
     */

    fun getRequestsCount(): Int = dbHelper.getRequestsCount()
    fun getRequestsCountFlow(): Flow<Int> = dbHelper.getRequestsCountFlow()
    fun insertAllRequests(items:List<RequestsEntity>) = dbHelper.insertAllRequests(items)
    /* fun clearChernovikItems()*/

    fun insertRequest(request: RequestsEntity):Long = dbHelper.insertRequest(request)
    fun updateRequest(request: RequestsEntity) = dbHelper.updateRequest(request)
    fun deleteRequest(request: RequestsEntity) = dbHelper.deleteRequest(request)

    fun getRequestById(id: Int): RequestsEntity = dbHelper.getRequestById(id)
    fun getRequests(): List<RequestsEntity> = dbHelper.getRequests()



    /**
     * @Contacts
     */

    fun getContactsCount(): Int = dbHelper.getContactsCount()
    fun getContactsCountFlow(): Flow<Int> = dbHelper.getContactsCountFlow()
/*    fun getAllContacts(): PagingSource<Int, ContactsEntity> = dbHelper.getAllContacts()*/
    fun insertAllContacts(items:List<ContactsEntity>)= dbHelper.insertAllContacts(items)
    fun clearContacts()= dbHelper.clearContacts()
    fun insertContacts(contacts: ContactsEntity):Long = dbHelper.insertContacts(contacts)
    fun updateContacts(contacts: ContactsEntity)= dbHelper.updateContacts(contacts)
    fun deleteContacts(contacts: ContactsEntity)= dbHelper.deleteContacts(contacts)
    fun getContactsById(id: Int): ContactsEntity = dbHelper.getContactsById(id)
    fun getContacts(): List<ContactsEntity> = dbHelper.getContacts()

    /**
     * @Corp
     */

    fun getCorpCount(): Int = dbHelper.getCorpCount()
    fun getCorpCountFlow(): Flow<Int> = dbHelper.getCorpCountFlow()
/*    fun getAllCorp(): PagingSource<Int, CorpEntity> = dbHelper.getAllCorp()*/
    fun insertAllCorp(items:List<CorpEntity>)= dbHelper.insertAllCorp(items)
    fun clearCorp()= dbHelper.clearCorp()
    fun insertCorp(corp: CorpEntity):Long = dbHelper.insertCorp(corp)
    fun updateCorp(corp: CorpEntity)= dbHelper.updateCorp(corp)
    fun deleteCorp(corp: CorpEntity)= dbHelper.deleteCorp(corp)
    fun getCorpById(id: Int): CorpEntity= dbHelper.getCorpById(id)
    fun getCorp(): List<CorpEntity> = dbHelper.getCorp()


    /**
     * @Object
     */

    fun getObjectsCount(): Int = dbHelper.getObjectsCount()
    fun getObjectsCountFlow(): Flow<Int> = dbHelper.getObjectsCountFlow()
    fun insertAllObjects(items:List<ObjectsEntity>) = dbHelper.insertAllObjects(items)
    fun clearObjects() = dbHelper.clearObjects()
    fun insertObjects(objects: ObjectsEntity):Long = dbHelper.insertObjects(objects)
    fun updateObjects(objects: ObjectsEntity) = dbHelper.updateObjects(objects)
    fun deleteObjects(objects: ObjectsEntity) = dbHelper.deleteObjects(objects)
    fun getObjectsById(id: Int): ObjectsEntity = dbHelper.getObjectsById(id)
    fun getObjects(): List<ObjectsEntity> = dbHelper.getObjects()

}