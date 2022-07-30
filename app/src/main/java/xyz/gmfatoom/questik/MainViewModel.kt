package xyz.gmfatoom.questik

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import xyz.gmfatoom.questik.repo.remote.models.CategoryModel
import xyz.gmfatoom.questik.repo.remote.models.JobsModel
import xyz.gmfatoom.questik.repo.remote.models.MaterialModel
import xyz.gmfatoom.questik.repo.remote.models.MetricsModel
import xyz.gmfatoom.questik.repo.QuestikRepository
import xyz.gmfatoom.questik.repo.local.room.entity.CategoryEntity
import xyz.gmfatoom.questik.repo.local.room.entity.JobsEntity
import xyz.gmfatoom.questik.repo.local.room.entity.MaterialEntity
import xyz.gmfatoom.questik.repo.local.room.entity.MetricsEntity
import xyz.gmfatoom.questik.utils.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: QuestikRepository
) : ViewModel() {
    private val _progressStatus = MutableStateFlow("Инициализация")
    val progressStatus: StateFlow<String> get() = _progressStatus

    private val _progresIsShow = MutableStateFlow(false)
    val progresIsShow: StateFlow<Boolean> get() = _progresIsShow

    private val _databaseCount = MutableStateFlow(0)
    val databaseCount: StateFlow<Int> get() = _databaseCount

    private val _authIs = MutableStateFlow(false)
    val authIs: StateFlow<Boolean> get() = _authIs



    fun updateUserAuth() {
        if (AUTH.currentUser != null) {
            _authIs.value = true
            println("ПОЛЬЗОВАТЕЛЬ АВТОРИЗОВАН - АГА")
        } else {
            _authIs.value = false
            println("ПОЛЬЗОВАТЕЛЬ АВТОРИЗОВАН - НЕТ")
        }
    }

    fun getDatabaseCount() {
        CoroutineScope(Dispatchers.Default).launch {
            _databaseCount.value = repository.getCategoriesCount()
        }
    }

    fun initDatabaseCatalog() {
        if (authIs.value) {
            CoroutineScope(Dispatchers.Default).launch {
                if (repository.getMaterialsCount()==0){
                initJobs()
                }
            }
        }
    }

    private fun initCategories() {
        /**
         * Categories
         */
        var categoryCount = 0
        REF_DATABASE_ROOT.child(NODE_CATEGORY)
            .addListenerForSingleValueEvent(AppValueEventListener { snapshot3 ->
                categoryCount = snapshot3.childrenCount.toInt()

                println("initListCategoriesCount ------------------------$categoryCount")
                    loadCategoryToDatabase(categoryCount)
            })
    }

    private fun initMaterial() {
        /**
         * Material
         */
        var materialCount = 0
        REF_DATABASE_ROOT.child(NODE_MATERIALS)
            .addListenerForSingleValueEvent(AppValueEventListener { snapshot2 ->
                materialCount = snapshot2.childrenCount.toInt()
                println("initListMaterialsCount ------------------------$materialCount")
                    loadMaterialToDatabase(materialCount)

            })
    }

    private fun initJobs() {
        /**
         * Jobs
         */
        var jobsCount = 0
        REF_DATABASE_ROOT.child(NODE_JOBS)
            .addListenerForSingleValueEvent(AppValueEventListener { snapshot1 ->
                jobsCount = snapshot1.childrenCount.toInt()
                println("initListJobsCount ------------------------${snapshot1.childrenCount.toInt()}")
              loadJobsToDatabase(jobsCount)

            })
    }
    private fun initMetrics() {
        /**
         * Metrics
         */
        var metricsCount = 0
        REF_DATABASE_ROOT.child(NODE_METRICS)
            .addListenerForSingleValueEvent(AppValueEventListener { snapshot ->
                metricsCount = snapshot.childrenCount.toInt()
                println("initListMetricsCount ------------------------$metricsCount")
         loadMetricsToDatabase(metricsCount)
            })
    }


    private fun loadCategoryToDatabase(categoryCount: Int) {
        CoroutineScope(Dispatchers.Default).launch{
        val _currentCountLoaded = MutableStateFlow(0)
        if (repository.getCategoriesCount() != categoryCount) {
            _progresIsShow.value = true
            viewModelScope.launch {
               repository.getCategoriesCountFlow().collect {
                   _currentCountLoaded.value =it
               }
            println(" repository count ------------------------------------------------------${ _currentCountLoaded.value}")
            }

            _progressStatus.value =
                "Загрузка данных -  Категории: всего = $categoryCount"
                REF_DATABASE_ROOT.child(NODE_CATEGORY)
                    .addListenerForSingleValueEvent(AppValueEventListener { snapshot3 ->
                        for (child in snapshot3.children) {
                            var c = CategoryModel()
                            c = child.getCatModel()
                            val cat: CategoryEntity = CategoryEntity()
                            cat.id = c.id?.toInt()!!
                            cat.name = c.name
                            CoroutineScope(Dispatchers.Default).launch {
                                repository.insertCategory(
                                    cat
                                )
                            }
                        }
                        initMetrics()
                    })
                println("initListJob:FirebaseCount = $categoryCount  ROOM Count = ${_currentCountLoaded.value}")
                println("initListMetrics ------------------------1")
        } else {
            initMetrics()
        }}
    }

    private fun loadMaterialToDatabase(materialCount: Int) {
        CoroutineScope(Dispatchers.Default).launch {
            val _currentCountLoaded = MutableStateFlow(0)
        if (repository.getMaterialsCount() != materialCount) {
            _progresIsShow.value = true
            viewModelScope.launch {
                repository.getMaterialsCountFlow().collect() {
                    _currentCountLoaded.value = it
                }
            }
            val materials = mutableListOf<MaterialEntity>()
            _progressStatus.value =
                "Загрузка данных -  Мaтериалы: всего = $materialCount"
            REF_DATABASE_ROOT.child(NODE_MATERIALS)
                .addListenerForSingleValueEvent(AppValueEventListener { snapshot3 ->
                    //        println("mMaterialModel ------------------------1" )
                    for (child in snapshot3.children) {
                        var m = MaterialModel()
                        m = child.getMatModel()
                        val mat: MaterialEntity = MaterialEntity()
                        mat.id = m.id?.toInt()!!
                        mat.name = m.name.toString()
                        mat.price = m.price.toString()
                        mat.metrics_id = m.metrics_id.toString()
                        mat.category_id = m.category_id.toString()
                        mat.plu = m.plu
                        materials.add(mat)

                    }
                    CoroutineScope(Dispatchers.Default).launch { repository.insertAllMaterials(materials) }
                    initCategories()
                })
        } else {
            initCategories()
        }}
    }

    private fun loadJobsToDatabase(jobsCount: Int) {
        _progresIsShow.value = true
        CoroutineScope(Dispatchers.Default).launch {
            val _currentCountLoaded = MutableStateFlow(0)
            val count = repository.getJobsCount()
            if (jobsCount!=count) {
                viewModelScope.launch {
                    repository.getJobsCountFlow().collect() {
                        _currentCountLoaded.value = it
                    }
                }
                val jobs = mutableListOf<JobsEntity>()
                _progressStatus.value =
                    "Загрузка данных -  Работы: всего = $jobsCount"
                REF_DATABASE_ROOT.child(NODE_JOBS)
                    .addListenerForSingleValueEvent(AppValueEventListener { snapshot1 ->
                        //     println("initListJob ------------------------1" )
                        for (snap in snapshot1.children) {
                            val j: JobsModel = snap.getJobsModel()
                            val job: JobsEntity = JobsEntity()
                            job.id = j.id?.toInt()!!
                            job.name = j.name.toString()
                            job.price = j.price.toString()
                            job.metrics_id = j.metrics_id.toString()
                            job.category_id = j.category_id.toString()
                            job.price_inzh = j.price_inzh.toString()
                            job.price_nalog_zp = j.price_nalog_zp
                            job.price_zp = j.price_zp
                            jobs.add(job)
                        }
                        CoroutineScope(Dispatchers.Default).launch {repository.installJobData(jobs)}
                        initMaterial()
                    })
            } else {
                initMaterial()
            }
        }
    }

    private fun loadMetricsToDatabase(metricsCount: Int) {
        CoroutineScope(Dispatchers.Default).launch {
            val _currentCountLoaded = MutableStateFlow(0)
        if (repository.getMetricsCount() != metricsCount) {
            _progresIsShow.value = true
            viewModelScope.launch {
                repository.getMaterialsCountFlow().collect() {
                    _currentCountLoaded.value = it
                }
            }
            val metrics = mutableListOf<MetricsEntity>()
            _progressStatus.value =
                "Загрузка данных -  Метрика: всего = $metricsCount"
            REF_DATABASE_ROOT.child(NODE_METRICS)
                .addListenerForSingleValueEvent(AppValueEventListener { snapshot1 ->
                    for (snap in snapshot1.children) {
                        val mMetricsModel: MetricsModel = snap.getMetricsModel()
                        val met: MetricsEntity = MetricsEntity()
                        met.id = mMetricsModel.id.toInt()
                        met.name = mMetricsModel.name
                        metrics.add(met)

                    }
                    CoroutineScope(Dispatchers.Default).launch {
                        repository.insertAllMetrics(metrics)
                    }
                    _progresIsShow.value = false
                })
            println("initListJob:FirebaseCount = $metricsCount  ROOM Count = ${_currentCountLoaded.value}")
            println("initListMetrics ------------------------1")
        } else {
            _progresIsShow.value = false
        }
    }}
}