package ru.rinet.questik.ui.chern

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.rinet.questik.models.CommonModel
import ru.rinet.questik.repo.QuestikRepository
import ru.rinet.questik.repo.local.room.entity.CategoryEntity
import ru.rinet.questik.repo.local.room.entity.JobsEntity
import ru.rinet.questik.repo.local.room.entity.MaterialEntity
import ru.rinet.questik.ui.chern.epoxy.ChernContainer
import ru.rinet.questik.ui.chern.epoxy.ChernPerCategory
import ru.rinet.questik.ui.chern.epoxy.OnChernCategoryExpanded
import ru.rinet.questik.ui.chern.epoxy.OnChernItemClickes
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ChernViewModel @Inject constructor(
    private val repository: QuestikRepository
) : ViewModel() {
    val chernLiveData: LiveData<ChernContainer>
        get() = _liveData
    private val _liveData = MutableLiveData<ChernContainer>()

    private val onItemClicked: OnChernItemClickes = {
        Log.i("ChernFragmentViewMOdel", "${it.name} - is clicked")
    }
    private val onChernCategoryExpanded: OnChernCategoryExpanded = { category ->
        val oldContainer = _liveData.value
        if (oldContainer != null) {
            oldContainer.onCategoryExpanded
            val newCategories = oldContainer.categories.map {
                if (it.category == category) {
                    it.copy(
                        category = it.category.copy(
                            isExpand = !category
                                .isExpand
                        )
                    )

                } else {
                    it
                }
            }
            _liveData.value = oldContainer.copy(categories = newCategories)

        }
    }

    init {
        val chernPerCategory: MutableList<ChernPerCategory> = mutableListOf()
        CoroutineScope(Dispatchers.IO).launch {
            val catalog = initCatalogList()

            val categories = initCategoryList()
            categories.forEach { category ->
                chernPerCategory.apply {
                   val newList:MutableList<CommonModel> = mutableListOf()
                    catalog.map { item ->
                       if (item.category_id == category.id.toString())  {
                           newList.add(item)
                       }
                    }
                    if (newList.isNotEmpty()){
                        this.add(ChernPerCategory(category, newList, onItemClicked, categories))
                    }
                }

            }
            CoroutineScope(Dispatchers.Main).launch {
                _liveData.value =
                    ChernContainer(chernPerCategory, onChernCategoryExpanded)
            }
        }


    }

    fun initCatalogList(): MutableList<CommonModel> {
        val list = mutableListOf<CommonModel>()
        initJobsList().forEach { job ->
            val newjob = CommonModel(
                rowId = UUID.randomUUID().toString(),
                id = job.id.toString(),
                name = job.name.toString(),
                price = job.price.toString(),
                metrics_id = job.metrics_id.toString(),
                category_id = job.category_id.toString(),
                price_inzh = job.price_inzh.toString(),
                price_nalog_zp = job.price_nalog_zp.toString(),
                price_zp = job.price_zp.toString()
            )
            list.add(newjob)
        }
        initMaterialList().forEach { material ->
            val newMaterial = CommonModel(
                rowId = UUID.randomUUID().toString(),
                id = material.id.toString(),
                plu = material.plu.toString(),
                name = material.name.toString(),
                price = material.price.toString(),
                metrics_id = material.metrics_id.toString(),
                category_id = material.category_id.toString()
            )
            list.add(newMaterial)
        }
        return list
    }

    fun initMaterialList(): List<MaterialEntity> {
        return repository.getMaterials().toMutableList()
    }

    fun initJobsList(): List<JobsEntity> {
        return repository.getJobs()
    }

    fun initCategoryList(): List<CategoryEntity> {
        return repository.getCategories()
    }

    fun initCategoryById(id: Int): CategoryEntity {
        return repository.getCategoryById(id)
    }
}



