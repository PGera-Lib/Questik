package xyz.gmfatoom.questik.ui.screens.chernovik

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import xyz.gmfatoom.questik.repo.QuestikRepository
import xyz.gmfatoom.questik.repo.local.room.entity.CategoryEntity
import xyz.gmfatoom.questik.repo.local.room.entity.JobsEntity
import xyz.gmfatoom.questik.repo.local.room.entity.MaterialEntity
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class ChernovikVM @Inject constructor(
    private val repository: QuestikRepository
) : ViewModel() {
    private val _fullJobsCatalog =  MutableStateFlow(mutableMapOf<CategoryEntity, List<JobsEntity>>())
    val fullJobsCatalog: StateFlow<MutableMap<CategoryEntity, List<JobsEntity>>> get() = _fullJobsCatalog

    private val _fullMaterialsCatalog =  MutableStateFlow(mutableMapOf<CategoryEntity, List<MaterialEntity>>())
    val fullMaterialsCatalog: StateFlow<MutableMap<CategoryEntity, List<MaterialEntity>>> get() = _fullMaterialsCatalog



    private val _jobsCurrentItems = MutableStateFlow(mutableMapOf<CategoryEntity, List<JobsEntity>>())
    val jobsCurrentItems: StateFlow<MutableMap<CategoryEntity, List<JobsEntity>>> get() = _jobsCurrentItems

    private val _materialsCurrentItems = MutableStateFlow(mutableMapOf<CategoryEntity, List<MaterialEntity>>())
    val materialsCurrentItems: StateFlow<MutableMap<CategoryEntity, List<MaterialEntity>>> get() = _materialsCurrentItems

    private val _selectedJobsCatalog = MutableStateFlow(mutableMapOf<CategoryEntity, List<JobsEntity>>())
    val selectedJobsCatalog: StateFlow<MutableMap<CategoryEntity, List<JobsEntity>>> get() = _selectedJobsCatalog

    private val _selectedMaterialsCatalog = MutableStateFlow(mutableMapOf<CategoryEntity, List<MaterialEntity>>())
    val selectedMaterialsCatalog: StateFlow<MutableMap<CategoryEntity, List<MaterialEntity>>> get() = _selectedMaterialsCatalog





    /**
     * Dialog Data
     */
/*
    private val _dialogJobsCatalog =  MutableStateFlow(mutableMapOf<CategoryEntity, List<JobsEntity>>())
    val dialogJobsCatalog: StateFlow<MutableMap<CategoryEntity, List<JobsEntity>>> get() = _dialogJobsCatalog

    private val _dialogMaterialsCatalog =  MutableStateFlow(mutableMapOf<CategoryEntity, List<MaterialEntity>>())
    val dialogMaterialsCatalog: StateFlow<MutableMap<CategoryEntity, List<MaterialEntity>>> get() = _dialogMaterialsCatalog


*/






    val itogRabot = MutableStateFlow(0.0)
    val itogMaterial = MutableStateFlow(0.0)


    private var serched: String = ""

    private val _expandableCategoryList = MutableStateFlow(listOf<Int>())
    val expandableCategoryList: StateFlow<List<Int>> get() = _expandableCategoryList

    init {
        getJobsData()
        getMaterialsData()
    }


    private fun getJobsData() {
        viewModelScope.launch(Dispatchers.Default) {
            val sampleList = mutableMapOf<CategoryEntity, List<JobsEntity>>()
         repository.getCategories().forEach { category ->
             val itemsList = repository.getJobsByCategoryId(category.id.toString()).map {
                 it.copy(metrics_id = repository.getMetricsById(it.metrics_id.toInt()).name)
             }
             if (itemsList.isNotEmpty()) {
                 sampleList.put(category, itemsList)
             }
            }
            _fullJobsCatalog.emit(sampleList)
            _jobsCurrentItems.value = _fullJobsCatalog.value
        }
    }
    private fun getMaterialsData() {
        viewModelScope.launch(Dispatchers.Default) {
            val sampleList = mutableMapOf<CategoryEntity, List<MaterialEntity>>()
            repository.getCategories().forEach { category ->
                val itemsList = repository.getMaterialByCategoryId(category.id.toString()).map {
                    it.copy(metrics_id = repository.getMetricsById(it.metrics_id.toInt()).name)
                }
                if (itemsList.isNotEmpty()) {
                    sampleList.put(category, itemsList)
                }
            }
            _fullMaterialsCatalog.emit(sampleList)
            _materialsCurrentItems.value = _fullMaterialsCatalog.value
        }
    }


    fun serchedFilter(serchWord: String) {
        serched = serchWord
        viewModelScope.launch(Dispatchers.Default) {
        if (serchWord.length>=3){
            val filteredJobs = MutableStateFlow(mutableMapOf<CategoryEntity, List<JobsEntity>>())
            _fullJobsCatalog.value.forEach { category, list ->
                val newList = list.filter {
                    it.name.toLowerCase(Locale.getDefault()).contains(serchWord.toLowerCase(Locale.getDefault()))
                }
                if (newList.isNotEmpty()) {
                    filteredJobs.value.put(category, newList)
                }
            }
            updateSelectedJobsCatalog(filteredJobs.value)
            _jobsCurrentItems.value = filteredJobs.value
         //   _dialogJobsCatalog.value = filteredJobs.value

            val filteredMaterials = MutableStateFlow(mutableMapOf<CategoryEntity, List<MaterialEntity>>())
            _fullMaterialsCatalog.value.forEach { category, list ->
                val newList = list.filter {
                    it.name.toLowerCase(Locale.getDefault()).contains(serchWord.toLowerCase(Locale.getDefault()))
                }
                if (newList.isNotEmpty()) {
                    filteredMaterials.value.put(category, newList)
                }
            }
            _materialsCurrentItems.value = filteredMaterials.value
            updateSelectedMaterialsCatalog(filteredMaterials.value)
        //    _dialogMaterialsCatalog.value = filteredMaterials.value
        } else if (serchWord.isEmpty()|| serchWord.length<3) {
       //     getSampleData()
            _jobsCurrentItems.value = _fullJobsCatalog.value
            _materialsCurrentItems.value = _fullMaterialsCatalog.value
/*            _jobsCurrentItems.value = _fullJobsCatalog.value
            _materialsCurrentItems.value = _fullMaterialsCatalog.value*/
            updateSelectedMaterialsCatalog(_fullMaterialsCatalog.value)
            updateSelectedJobsCatalog(_fullJobsCatalog.value)
    //        _dialogJobsCatalog.value = _fullJobsCatalog.value
     //       _dialogMaterialsCatalog.value = _fullMaterialsCatalog.value
           }
        }
    }


    fun getSelectedJobsCatalog() {
        viewModelScope.launch(Dispatchers.Default) {
            var sum: Double = 0.0
            val selectedCatalogItems =
                MutableStateFlow(mutableMapOf<CategoryEntity, List<JobsEntity>>())
            _fullJobsCatalog.value.forEach { (category, list) ->
                val itemsList = arrayListOf<JobsEntity>().apply {
                    list.forEach {
                        if (it.isSelected) {
                            this += it
                            var count = it.count
                            if (count.length==0){
                                count = "0"
                            }
                           sum += it.price.toDouble() * count.toDouble()
                        }
                    }
                }
                if (itemsList.isNotEmpty()) {
                    selectedCatalogItems.value.put(category, itemsList)
                }
            }
            itogRabot.value = sum
            _selectedJobsCatalog.value = selectedCatalogItems.value
        }
    }
    fun updateSelectedJobsCatalog(jobsMap: MutableMap<CategoryEntity, List<JobsEntity>>) {
        viewModelScope.launch(Dispatchers.Default) {
            var sum: Double = 0.0
            val selectedCatalogItems =
                MutableStateFlow(mutableMapOf<CategoryEntity, List<JobsEntity>>())
               jobsMap.forEach { (category, list) ->
                val itemsList = arrayListOf<JobsEntity>().apply {
                    list.forEach {
                        if (it.isSelected) {
                            this += it
                            var count = it.count
                            if (count.length==0){
                                count = "0"
                            }
                            sum += it.price.toDouble() * count.toDouble()
                        }
                    }
                }
                if (itemsList.isNotEmpty()) {
                    selectedCatalogItems.value.put(category, itemsList)
                }
            }
            itogRabot.value = sum
            _selectedJobsCatalog.value = selectedCatalogItems.value
        }
    }

    fun updateSelectedMaterialsCatalog(materialsMap: MutableMap<CategoryEntity, List<MaterialEntity>>) {
        viewModelScope.launch(Dispatchers.Default) {
            var sum: Double = 0.0
            val selectedCatalogItems =
                MutableStateFlow(mutableMapOf<CategoryEntity, List<MaterialEntity>>())
            materialsMap.forEach { (category, list) ->
                val itemsList = arrayListOf<MaterialEntity>().apply {
                    list.forEach {
                        if (it.isSelected) {
                            this += it
                            var count = it.count
                            if (count.length==0){
                                count = "0"
                            }
                            sum += it.price.toDouble() * count.toDouble()
                        }
                    }
                }
                if (itemsList.isNotEmpty()) {
                    selectedCatalogItems.value.put(category, itemsList)
                }
            }
            itogMaterial.value = sum
            _selectedMaterialsCatalog.value = selectedCatalogItems.value
        }
    }



    fun getSelectedMaterialsCatalog() {
        viewModelScope.launch(Dispatchers.Default) {
            var sum: Double = 0.0
            val selectedCatalogItems =
                MutableStateFlow(mutableMapOf<CategoryEntity, List<MaterialEntity>>())
            _fullMaterialsCatalog.value.forEach { (category, list) ->
                val itemsList = arrayListOf<MaterialEntity>().apply {
                    list.forEach {
                        if (it.isSelected) {
                            this += it
                            var count = it.count
                            if (count.length==0){
                                count = "0"
                            }
                            sum += it.price.toDouble() * count.toDouble()
                        }
                    }
                }
                if (itemsList.isNotEmpty()) {
                    selectedCatalogItems.value.put(category, itemsList)
                }
            }
            itogMaterial.value = sum
            _selectedMaterialsCatalog.value = selectedCatalogItems.value
        }
    }




    fun selectJobsItem(item: JobsEntity) {
        println(" ChernovikViewModel item -  ${item.name}    is checked - ${item.isSelected}")
        viewModelScope.launch(Dispatchers.Default) {
            val filteredCatalog = mutableMapOf<CategoryEntity, List<JobsEntity>>()
            _fullJobsCatalog.value.forEach { (category, list) ->
                val newList = ArrayList<JobsEntity>()
                newList.apply {
                    list.forEach {
                        if (item.id.equals(it.id) ) {
                            println("chernviewmodel , added item is - ${item.isSelected}  ")
                            this +=item
                        } else {
                            this +=it
                        }
                    }
                }

                filteredCatalog.put(category, newList)
            }
            _fullJobsCatalog.value = filteredCatalog
            updateSelectedJobsCatalog(filteredCatalog)
            _jobsCurrentItems.value = _fullJobsCatalog.value
            if (serched.length>=3){
                serchedFilter(serched)}
        }

    }
    fun selectMaterialItem(item: MaterialEntity) {
        println(" ChernovikViewModel item -  ${item.name}    is checked - ${item.isSelected}")
        viewModelScope.launch(Dispatchers.Default) {
            val filteredCatalog = mutableMapOf<CategoryEntity, List<MaterialEntity>>()
            _fullMaterialsCatalog.value.forEach { (category, list) ->
                val newList = ArrayList<MaterialEntity>()
                newList.apply {
                    list.forEach {
                        if (item.id == it.id) {
                            println("chernviewmodel , added item is - ${item.isSelected}  ")
                            this +=item
                        } else {
                            this +=it
                        }
                    }
                }
                filteredCatalog.put(category, newList)
            }
            _fullMaterialsCatalog.value = filteredCatalog
            _materialsCurrentItems.value = _fullMaterialsCatalog.value
            updateSelectedMaterialsCatalog(filteredCatalog)

            if (serched.length>=3){
                serchedFilter(serched)}
        }

    }


    fun updateSelectJobsItem(item: JobsEntity) {
        println(" ChernovikViewModel item -  ${item.name}    is count is - ${item.count}")
        viewModelScope.launch(Dispatchers.Default) {
            val filteredCatalog = mutableMapOf<CategoryEntity, List<JobsEntity>>()
            _fullJobsCatalog.value.forEach { (category, list) ->
                val newList = ArrayList<JobsEntity>()
                newList.apply {
                    list.forEach {
                        if (item.id == it.id) {
                            println("chernviewmodel , changed count to - ${item.count}}  ")
                            this +=item
                        } else {
                            this +=it
                        }
                    }
                }
                filteredCatalog.put(category, newList)
            }
            _fullJobsCatalog.value = filteredCatalog
            _jobsCurrentItems.value = _fullJobsCatalog.value
            updateSelectedJobsCatalog(filteredCatalog)
            if (serched.length>=3){
                serchedFilter(serched)}
        }

    }


    fun updateSelectMaterialsItem(item: MaterialEntity) {
        println(" ChernovikViewModel item -  ${item.name}    is count is - ${item.count}")
        viewModelScope.launch(Dispatchers.Default) {
            val filteredCatalog = mutableMapOf<CategoryEntity, List<MaterialEntity>>()
            _fullMaterialsCatalog.value.forEach { (category, list) ->
                val newList = ArrayList<MaterialEntity>()
                newList.apply {
                    list.forEach {
                        if (item.id == it.id) {
                            println("chernviewmodel , changed count to - ${item.count}}  ")
                            this +=item
                        } else {
                            this +=it
                        }
                    }
                }
                filteredCatalog.put(category, newList)
            }
            _fullMaterialsCatalog.value = filteredCatalog
            _materialsCurrentItems.value = _fullMaterialsCatalog.value
            updateSelectedMaterialsCatalog(filteredCatalog)
            if (serched.length>=3){
                serchedFilter(serched)}
        }
    }

    fun cardArrowClick(cardId: Int) {
        _expandableCategoryList.value = _expandableCategoryList.value.toMutableList().also { list ->
            if (list.contains(cardId)) {
                list.remove(cardId)
            } else {
                list.add(cardId)
            }
        }
    }
}

