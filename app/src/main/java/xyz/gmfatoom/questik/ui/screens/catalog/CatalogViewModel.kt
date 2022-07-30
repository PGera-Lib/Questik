package xyz.gmfatoom.questik.ui.screens.catalog

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
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val repository: QuestikRepository
) : ViewModel() {
    private val _categoryCard = MutableStateFlow(mutableMapOf<CategoryEntity, List<JobsEntity>>())
    val categoryCard: StateFlow<MutableMap<CategoryEntity, List<JobsEntity>>> get() = _categoryCard


    private val _expandableCategoryList = MutableStateFlow(listOf<Int>())
    val expandableCategoryList: StateFlow<List<Int>> get() = _expandableCategoryList
    init {
        getSampleData()
    }
    private fun getSampleData() {
        viewModelScope.launch(Dispatchers.Default) {
            val sampleList = mutableMapOf<CategoryEntity, List<JobsEntity>>()
            repository.getCategories().forEach{ category->
                   val itemsList =repository.getJobsByCategoryId(category.id.toString())
                sampleList.put(category, itemsList)
            }



            _categoryCard.emit(sampleList)
        }
    }
    fun getMetricsName(id:String) : String {

        var metricsName = " "
        viewModelScope.launch(Dispatchers.Default) {
            metricsName = repository.getMetricsById(id.toInt()).name
        }
        return metricsName
    }

    fun serchedFilter(serchWord: String) {
        if (serchWord.length>=3){
        val filteredCatalog = MutableStateFlow(mutableMapOf<CategoryEntity, List<JobsEntity>>())
        _categoryCard.value.forEach { category, list ->
            val newList = list.filter {
                it.name?.toLowerCase(Locale.getDefault())?.contains(serchWord.toLowerCase(Locale.getDefault())) == true
            }
            filteredCatalog.value.put(category, newList)

        }
        _categoryCard.value = filteredCatalog.value
    } else if (serchWord.isEmpty()) {
            getSampleData()
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

