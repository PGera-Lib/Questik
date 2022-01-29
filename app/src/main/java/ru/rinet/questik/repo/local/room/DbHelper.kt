package ru.rinet.questik.repo.local.room

import androidx.paging.PagingSource
import ru.rinet.questik.repo.local.room.entity.*
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



    /** **************************************************************************
     * Job
     ****************************************************************************/
    override fun insertJob(job: JobsEntity) = appDatabase.jobsDao().insertJob(job)
    override fun updateJob(job: JobsEntity) = appDatabase.jobsDao().updateJob(job)
    override fun deleteJob(job: JobsEntity) = appDatabase.jobsDao().deleteJob(job)
    override fun getJobById(id: Int): List<JobsEntity> = appDatabase.jobsDao().getJobById(id)
    override fun getJobs(): List<JobsEntity> = appDatabase.jobsDao().getJobs()
    override fun getJobsCount(): Int = appDatabase.jobsDao().getJobsCount()
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
    /** **************************************************************************
     * Metrics
     ****************************************************************************/

    override fun insertMetrics(metrics: MetricsEntity) =
        appDatabase.metricsDao().insertMetrics(metrics)

    override fun updateMetrics(metrics: MetricsEntity) =
        appDatabase.metricsDao().updateMetrics(metrics)

    override fun deleteMetrics(metrics: MetricsEntity) =
        appDatabase.metricsDao().deleteMetrics(metrics)

    override fun getMetricsById(id: Int): List<MetricsEntity> =
        appDatabase.metricsDao().getMetricsById(id)

    override fun getMetrics(): List<MetricsEntity> = appDatabase.metricsDao().getMetrics()
    override fun getAllMetrics(): PagingSource<Int, MetricsEntity>  = appDatabase.metricsDao().getAllMetrics()
    override suspend fun insertAllMetrics(items: List<MetricsEntity>) = appDatabase.metricsDao().insertAllMetrics(items)
    override fun clearMetrics() = appDatabase.metricsDao().clearMetrics()
    override fun getMetricsCount(): Int = appDatabase.metricsDao().getMetricsCount()

    /** **************************************************************************
     * User
     ****************************************************************************/

    override fun insertUser(user: UserEntity) = appDatabase.userDao().insertUser(user)
    override fun updateUser(user: UserEntity) = appDatabase.userDao().updateUser(user)
    override fun deleteUser(user: UserEntity) = appDatabase.userDao().deleteUser(user)
    override fun getUserById(id: Int): List<UserEntity> = appDatabase.userDao().getUserById(id)
    override fun getUser(): List<UserEntity> = appDatabase.userDao().getUser()
    override fun geUsersCount(): Int = appDatabase.userDao().getUsersCount()

}