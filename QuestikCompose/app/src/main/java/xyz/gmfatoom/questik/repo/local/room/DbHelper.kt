package xyz.gmfatoom.questik.repo.local.room

import androidx.paging.PagingSource
import kotlinx.coroutines.flow.Flow
import xyz.gmfatoom.questik.repo.local.room.entity.*
import javax.inject.Inject

class DbHelper @Inject constructor(
               private val appDatabase: AppDatabase
): IDbHelper {
    /** **************************************************************************
     * Category
     ****************************************************************************/
    override fun insertCategory(category: CategoryEntity) =
        appDatabase.categoryDao().insertCategory(category)

    override fun updateCategory(category: CategoryEntity) =
        appDatabase.categoryDao().updateCategory(category)

    override fun deleteCategory(category: CategoryEntity) =
        appDatabase.categoryDao().deleteCategory(category)

    override fun getCategoryById(id: Int): CategoryEntity =
        appDatabase.categoryDao().getCategoryById(id)

    override fun getCategories(): List<CategoryEntity> = appDatabase.categoryDao().getCategories()
    override fun getAllCategories(): PagingSource<Int, CategoryEntity>  =
        appDatabase.categoryDao().getAllCategories()

    override suspend fun insertAllCategories(items: List<CategoryEntity>) =
        appDatabase.categoryDao().insertAllCategories(items)
    override fun clearCategories() =
        appDatabase.categoryDao().clearCategories()

    override fun getCategoriesCount(): Int =
        appDatabase.categoryDao().getCategoriesCount()

    override fun getCategoriesCountFlow(): Flow<Int> =
        appDatabase.categoryDao().getCategoriesCountFlow()



    /** **************************************************************************
     * Job
     ****************************************************************************/
    override fun insertJob(job: JobsEntity) = appDatabase.jobsDao().insertJob(job)
    override fun updateJob(job: JobsEntity) = appDatabase.jobsDao().updateJob(job)
    override fun deleteJob(job: JobsEntity) = appDatabase.jobsDao().deleteJob(job)
    override fun getJobById(id: Int): List<JobsEntity> = appDatabase.jobsDao().getJobById(id)
    override fun getJobs(): List<JobsEntity> = appDatabase.jobsDao().getJobs()
    override fun getJobsCount(): Int = appDatabase.jobsDao().getJobsCount()
    override fun getJobsCountFlow(): Flow<Int> = appDatabase.jobsDao().getJobsCountFlow()

    override fun getJobByCategoryId(id: String): List<JobsEntity> = appDatabase.jobsDao().getJobByCategoryId(id)
    override fun getAllJobs(): PagingSource<Int, JobsEntity> = appDatabase.jobsDao().getAllJobs()
    override suspend fun insertAllJobs(items: List<JobsEntity>) = appDatabase.jobsDao().insertAllJobs(items)
    override fun clearJobs() = appDatabase.jobsDao().clearJobs()

    /** **************************************************************************
     * Material
     ****************************************************************************/

    override fun insertMaterial(material: MaterialEntity) =
        appDatabase.materialDao().insertMaterial(material)

    override fun updateMaterial(material: MaterialEntity) =
        appDatabase.materialDao().updateMaterial(material)

    override fun deleteMaterial(material: MaterialEntity) =
        appDatabase.materialDao().deleteMaterial(material)

    override fun getMaterialyById(id: Int): List<MaterialEntity> =
        appDatabase.materialDao().getMaterialyById(id)

    override fun getMaterials(): List<MaterialEntity> = appDatabase.materialDao().getMaterials()
    override fun getAllMaterials(): PagingSource<Int, MaterialEntity>  = appDatabase.materialDao().getAllMaterials()

    override suspend fun insertAllMaterials(items: List<MaterialEntity>)   = appDatabase.materialDao().insertAllMaterials(items)
    override fun clearMaterials()  = appDatabase.materialDao().clearMaterials()
    override fun getMaterialsCount(): Int = appDatabase.materialDao().getMaterialsCount()
    override fun getMaterialsCountFlow(): Flow<Int> = appDatabase.materialDao().getMaterialsCountFlow()
    override fun getMaterialByCategoryId(id: String): List<MaterialEntity>  = appDatabase.materialDao().getMaterialByCategoryId(id)

    /** **************************************************************************
     * Metrics
     ****************************************************************************/

    override fun insertMetrics(metrics: MetricsEntity) =
        appDatabase.metricsDao().insertMetrics(metrics)

    override fun updateMetrics(metrics: MetricsEntity) =
        appDatabase.metricsDao().updateMetrics(metrics)

    override fun deleteMetrics(metrics: MetricsEntity) =
        appDatabase.metricsDao().deleteMetrics(metrics)

    override fun getMetricsById(id: Int): MetricsEntity =
        appDatabase.metricsDao().getMetricsById(id)

    override fun getMetrics(): List<MetricsEntity> = appDatabase.metricsDao().getMetrics()
    override fun getAllMetrics(): PagingSource<Int, MetricsEntity>  = appDatabase.metricsDao().getAllMetrics()
    override suspend fun insertAllMetrics(items: List<MetricsEntity>) = appDatabase.metricsDao().insertAllMetrics(items)
    override fun clearMetrics() = appDatabase.metricsDao().clearMetrics()
    override fun getMetricsCount(): Int = appDatabase.metricsDao().getMetricsCount()
    override fun getMetricsCountFlow(): Flow<Int> = appDatabase.metricsDao().getMetricsCountFlow()
    /** **************************************************************************
     * User
     ****************************************************************************/

    override fun insertUser(user: UserEntity) = appDatabase.userDao().insertUser(user)
    override fun updateUser(user: UserEntity) = appDatabase.userDao().updateUser(user)
    override fun deleteUser(user: UserEntity) = appDatabase.userDao().deleteUser(user)
    override fun getUserById(id: Int): List<UserEntity> = appDatabase.userDao().getUserById(id)
    override fun getUser(): List<UserEntity> = appDatabase.userDao().getUser()
    override fun geUsersCount(): Int = appDatabase.userDao().getUsersCount()
    override fun geUsersCountFlow(): Flow<Int> = appDatabase.userDao().getUsersCountFlow()

    /** **************************************************************************
     * RequestsItems
     ****************************************************************************/

    override fun getRequestItemCount(): Int = appDatabase.requestsItemsDao().getRequestItemsCount()
    override fun getRequestItemCountFlow(): Flow<Int> =appDatabase.requestsItemsDao().getRequestItemsCountFlow()
    override fun insertAllRequestItems(items: List<RequestItemEntity>) = appDatabase.requestsItemsDao().insertAllRequestItems(items)
    override fun clearRequestItem() = appDatabase.requestsItemsDao().clearRequestItems()
    override fun insertRequestItem(chernovik: RequestItemEntity) = appDatabase.requestsItemsDao().insertRequestItem(chernovik)
    override fun updateRequestItem(chernovik: RequestItemEntity) = appDatabase.requestsItemsDao().updateRequestItem(chernovik)
    override fun deleteRequestItem(chernovik: RequestItemEntity) = appDatabase.requestsItemsDao().deleteRequestItem(chernovik)
    override fun getRequestItemById(id: Int): RequestItemEntity = appDatabase.requestsItemsDao().getRequestItemById(id)
    override fun getRequestItem(): List<RequestItemEntity> = appDatabase.requestsItemsDao().getRequestItems()
    override fun getRequestItemByCategoryId(id: String): List<RequestItemEntity> = appDatabase.requestsItemsDao().getRequestItemsByCategoryId(id)
    override fun searchRequestItem(search: String?, cat: String): List<RequestItemEntity> = appDatabase.requestsItemsDao().searchRequestItem(search, cat)
    override fun filteredRequestItemList(
        search: String?,
        check: Boolean,
        cat: String
    ): List<RequestItemEntity>  = appDatabase.requestsItemsDao().filteredRequestItemsList(search, check, cat)

    override fun getRequestItemByItogShow(check: Boolean, cat: String): List<RequestItemEntity>  = appDatabase.requestsItemsDao().getRequestItemByItogShow(check, cat)
    override fun getRequestItemByIdDiapazon(start: Int, end: Int): List<RequestItemEntity>  = appDatabase.requestsItemsDao().getRequestItemsByIdDiapazon(start, end)
    override fun getRequestItemCountByCategoryId(id: String): Int = appDatabase.requestsItemsDao().getRequestItemsCountByCategoryId(id)
    override fun getRequestItemByCategoryAndIdDiapazon(categoryId:String, start: Int, end: Int): List<RequestItemEntity> = appDatabase.requestsItemsDao().getRequestItemsByCategoryAndIdDiapazon(categoryId, start, end)
    override fun getRequestItemsByProjectId(id: Int, categoryId:String): List<RequestItemEntity> = appDatabase.requestsItemsDao().getRequestItemsByProjectId(id, categoryId)


    /** **************************************************************************
     * Requests
     ****************************************************************************/


    override fun getRequestsCount(): Int = appDatabase.requestsDao().getRequestsCount()

    override fun getRequestsCountFlow(): Flow<Int> = appDatabase.requestsDao().getRequestsCountFlow()

    override fun insertAllRequests(items: List<RequestsEntity>) = appDatabase.requestsDao().insertAllRequests(items)

    override fun insertRequest(request: RequestsEntity):Long= appDatabase.requestsDao().insertRequest(request)

    override fun updateRequest(request: RequestsEntity) = appDatabase.requestsDao().updateRequest(request)

    override fun deleteRequest(request: RequestsEntity) = appDatabase.requestsDao().deleteRequest(request)

    override fun getRequestById(id: Int): RequestsEntity = appDatabase.requestsDao().getRequestById(id)

    override fun getRequests(): List<RequestsEntity> = appDatabase.requestsDao().getRequests()


    /** **************************************************************************
     * Contacts
     ****************************************************************************/


    override fun getContactsCount(): Int = appDatabase.contactsDao().getContactsCount()
    override fun getContactsCountFlow(): Flow<Int>  = appDatabase.contactsDao().getContactsCountFlow()
/*    override fun getAllContacts(): PagingSource<Int, ContactsEntity>  = appDatabase.contactsDao().getAllContacts()*/
    override fun insertAllContacts(items: List<ContactsEntity>)  = appDatabase.contactsDao().insertAllContacts(items)
    override fun clearContacts() = appDatabase.contactsDao().clearContacts()
    override fun insertContacts(contacts: ContactsEntity):Long = appDatabase.contactsDao().insertContacts(contacts)
    override fun updateContacts(contacts: ContactsEntity)  = appDatabase.contactsDao().updateContacts(contacts)
    override fun deleteContacts(contacts: ContactsEntity) = appDatabase.contactsDao().deleteContacts(contacts)
    override fun getContactsById(id: Int): ContactsEntity  = appDatabase.contactsDao().getContactsById(id)
    override fun getContacts(): List<ContactsEntity> = appDatabase.contactsDao().getContacts()


    /** **************************************************************************
     * Corp
     ****************************************************************************/


    override fun getCorpCount(): Int = appDatabase.corpDao().getCorpCount()
    override fun getCorpCountFlow(): Flow<Int> = appDatabase.corpDao().getCorpCountFlow()
/*    override fun getAllCorp(): PagingSource<Int, CorpEntity>  = appDatabase.corpDao().getAllCorp()*/
    override fun insertAllCorp(items: List<CorpEntity>)  = appDatabase.corpDao().insertAllCorp(items)
    override fun clearCorp()  = appDatabase.corpDao().clearCorp()
    override fun insertCorp(corp: CorpEntity):Long  = appDatabase.corpDao().insertCorp(corp)
    override fun updateCorp(corp: CorpEntity)  = appDatabase.corpDao().updateCorp(corp)
    override fun deleteCorp(corp: CorpEntity)  = appDatabase.corpDao().deleteCorp(corp)
    override fun getCorpById(id: Int): CorpEntity  = appDatabase.corpDao().getCorpById(id)
    override fun getCorp(): List<CorpEntity>  = appDatabase.corpDao().getCorp()

    /** **************************************************************************
     * Object
     ****************************************************************************/

    override fun getObjectsCount(): Int =appDatabase.objectsDao().getObjectsCount()
    override fun getObjectsCountFlow(): Flow<Int> =appDatabase.objectsDao().getObjectsCountFlow()
    override fun insertAllObjects(items: List<ObjectsEntity>)  =appDatabase.objectsDao().insertAllObjects(items)
    override fun clearObjects() = appDatabase.objectsDao().clearObjects()
    override fun insertObjects(objects: ObjectsEntity): Long  =appDatabase.objectsDao().insertObjects(objects)
    override fun updateObjects(objects: ObjectsEntity)  =appDatabase.objectsDao().updateObjects(objects)
    override fun deleteObjects(objects: ObjectsEntity)  =appDatabase.objectsDao().deleteObjects(objects)
    override fun getObjectsById(id: Int): ObjectsEntity  =appDatabase.objectsDao().getObjectsById(id)
    override fun getObjects(): List<ObjectsEntity>  =appDatabase.objectsDao().getObjects()



}