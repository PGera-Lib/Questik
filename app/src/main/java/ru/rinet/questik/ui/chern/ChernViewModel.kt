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
import ru.rinet.questik.repo.local.room.entity.MetricsEntity
import ru.rinet.questik.ui.chern.epoxy.*
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ChernViewModel @Inject constructor(
    private val repository: QuestikRepository
) : ViewModel() {
    val chernLiveData: LiveData<ChernContainer>
        get() = _liveData
    private val _liveData = MutableLiveData<ChernContainer>()

    private val fullLiveData = MutableLiveData<ChernContainer>()
    private var serchedItem: String = ""
    var showItogData: Boolean = false
/*    var itog: String = ""
    var itogRab: String = ""
    var itogMat: String = ""*/


    private fun updateData(model: CommonModel) {
        CoroutineScope(Dispatchers.IO).launch {
            val oldContainer = fullLiveData.value
            if (oldContainer != null) {
                var itog1: Double = 0.0
                var itog2: Double = 0.0
                var itog3: Double = 0.0
                val newCategories = oldContainer.categories.map { chernPerCat ->
                    val chekedItems: List<CommonModel> = chernPerCat.items.map { cm ->
                        if (cm.name.equals(model.name) && cm.id.equals(model.id)) {
/*                            if (model.isChecked) {
                                if (cm.count.length > 0 && cm.price.length > 0) {
                                    itog1 += (model.count.toDouble() * model.price.toDouble())
                                    if (model.isMaterial == true){
                                        itog3 += (model.count.toDouble() * model.price.toDouble())
                                    }else{ itog2 += (model.count.toDouble() * model.price.toDouble()) }
                                }
                            }*/
                            Log.i(
                                "ChernFragmentViewMOdel_UPDATE",
                                "${model.name} - count changed to ${model.count}  cheked is  ${model.isChecked}  Detail view is  ${model.isShow}  Материал? - ${model.isMaterial}"
                            )
                            model
                        } else {
                            cm
                        }
                    }
                    chernPerCat.copy(items = chekedItems)
                }
//                itog = itog1.toString()
//                itogRab = itog2.toString()
//                itogMat = itog3.toString()
                CoroutineScope(Dispatchers.Main).launch {
/*                    Log.i(
                        "ChernFragmentViewMOdelChekedItems",
                        " Итог is ${itog}   среди них работ на $itogRab и метриалов на $itogMat "
                    )*/
                    fullLiveData.value = oldContainer.copy(categories = newCategories)
                    //_liveData.value = oldContainer.copy(categories = newCategories)
                }
            }
            if (serchedItem.isNotEmpty()) {
                search(serchedItem)
                if (showItogData) {
                    showItog()
                }
            } else if (showItogData) {
                showItog()
            } else {
                fetchAllData()
            }
        }
    }

    private val onItemClicked: OnChernItemClickes = { commonModel ->
        Log.i("ChernFragmentViewMOdel", "${commonModel.name} - is clicked")

    }

    private val onItemUpdated: OnChernItemChanged = { commonModel ->
        //  Log.i("ChernFragmentViewMOdel", "${commonModel.name} - count changed to ${commonModel.count}  cheked is  ${commonModel.isChecked}  Detail view is  ${commonModel.isShow}  ")
        updateData(commonModel)
    }


    private val onItemCountChange: OnChernItemChangeCount = {
        Log.i("ChernFragmentViewMOdel", "${it} - in EdditBox event")

    }

    fun showItog() {
        if (showItogData) {
            CoroutineScope(Dispatchers.IO).launch {
                var chernPerCategory: MutableList<ChernPerCategory> = mutableListOf()
                val oldContainer = fullLiveData.value
                if (oldContainer != null) {

                    oldContainer.categories.map { chernPerCat ->
                        val itogItems: MutableList<CommonModel> = mutableListOf()
                        chernPerCat.items.forEach {
                            if (it.isChecked) {
                                itogItems.add(it)
                            }
                        }
                        if (itogItems.isNotEmpty()) {
                            Log.i(
                                "ChernFragmentViewMOdelChekedItems",
                                " chernpercat is ${chernPerCat.category.name}   СО СПИСКОМ" + itogItems.toString()
                            )
                            val newChernCat = chernPerCat.copy(items = itogItems)
                            chernPerCategory.add(newChernCat)
                        }
                    }

                    CoroutineScope(Dispatchers.Main).launch {

                        _liveData.value = oldContainer.copy(categories = chernPerCategory)
                    }
                }
            }
        }

    }

    private val onChernCategoryExpanded: OnChernCategoryExpanded = { category ->
        CoroutineScope(Dispatchers.IO).launch {
            val oldContainer = fullLiveData.value
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
                CoroutineScope(Dispatchers.Main).launch {
                    fullLiveData.value = oldContainer.copy(categories = newCategories)
                    //_liveData.value = oldContainer.copy(categories = newCategories)
                }
            }
            if (serchedItem.isNotEmpty()) {
                search(serchedItem)
                if (showItogData) {
                    showItog()
                }
            } else if (showItogData) {
                showItog()
            } else {
                fetchAllData()
            }
        }

    }

    init {
        CoroutineScope(Dispatchers.IO).launch {
            val chernPerCategory: MutableList<ChernPerCategory> = mutableListOf()
            val catalog = initCatalogList()
            val metrics = initMetricsList()
            val categories = initCategoryList()
            categories.forEach { category ->
                chernPerCategory.apply {
                    val newList: MutableList<CommonModel> = mutableListOf()
                    catalog.map { item ->
                        if (item.category_id == category.id.toString()) {
                            newList.add(item)
                        }
                    }
                    if (newList.isNotEmpty()) {
                        this.add(
                            ChernPerCategory(
                                category,
                                newList,
                                onItemClicked,
                                onItemCountChange,
                                onItemUpdated,
                                categories,
                                metrics
                            )
                        )
                    }
                }

            }
            CoroutineScope(Dispatchers.Main).launch {
                fullLiveData.value = ChernContainer(chernPerCategory, onChernCategoryExpanded)
                _liveData.value =
                    ChernContainer(chernPerCategory, onChernCategoryExpanded)
            }
        }


    }


    fun search(serched: String) {
        serchedItem = serched
        CoroutineScope(Dispatchers.IO).launch {
            var chernPerCategory: MutableList<ChernPerCategory> = mutableListOf()
            val oldContainer = fullLiveData.value
            if (oldContainer != null) {
                oldContainer.categories.map { chernPerCat ->
                    val serchedItems: MutableList<CommonModel> = mutableListOf()
                    chernPerCat.items.forEach {
                        if (it.name.lowercase(Locale.getDefault()).contains(serched)) {
                            serchedItems.add(it)
                        }
                    }
                    if (serchedItems.isNotEmpty()) {
                        Log.i(
                            "ChernFragmentViewMOdelSerchedItems",
                            " chernpercat is ${chernPerCat.category.name}     СО СПИСКОМ" + serchedItems.toString()
                        )
                        val newChernCat = chernPerCat.copy(items = serchedItems)
                        chernPerCategory.add(newChernCat)
                    }
                }
                CoroutineScope(Dispatchers.Main).launch {
                    _liveData.value = oldContainer.copy(categories = chernPerCategory)
                }
                if (showItogData) {
                    showItog()
                }
            }
        }


/*        val chernPerCategory: MutableList<ChernPerCategory> = mutableListOf()
        CoroutineScope(Dispatchers.IO).launch {
            val catalog = initCatalogList()

            val metrics = initMetricsList()
            val categories = initCategoryList()

            categories.forEach { category ->
                chernPerCategory.apply {
                    val newList:MutableList<CommonModel> = mutableListOf()
                    catalog.map { item ->
                        if (item.category_id == category.id.toString())  {
                            val filteredMat = item.name.toLowerCase(Locale.getDefault())
                            if (filteredMat.contains(serched) == true){
                                newList.add(item)
                            }
                        }
                    }
                    if (newList.isNotEmpty()){
                        this.add(ChernPerCategory(category, newList, onItemClicked, onItemCheked,onItemUpdated, categories, metrics))
                    }
                }

            }
            CoroutineScope(Dispatchers.Main).launch {
                _liveData.value =
                    ChernContainer(chernPerCategory, onChernCategoryExpanded)
            }
        }*/
    }

    fun fetchAllData() {
        CoroutineScope(Dispatchers.Main).launch {
            _liveData.value = fullLiveData.value
        }
/*        val chernPerCategory: MutableList<ChernPerCategory> = mutableListOf()
        CoroutineScope(Dispatchers.IO).launch {
            val catalog = initCatalogList()

            val metrics = initMetricsList()
            val categories = initCategoryList()

            categories.forEach { category ->
                chernPerCategory.apply {
                    val newList: MutableList<CommonModel> = mutableListOf()
                    catalog.map { item ->
                        if (item.category_id == category.id.toString()) {
                            newList.add(item)
                        }
                    }
                    if (newList.isNotEmpty()) {
                        this.add(
                            ChernPerCategory(
                                category,
                                newList,
                                onItemClicked,
                                onItemCheked,
                                onItemUpdated,
                                categories,
                                metrics
                            )
                        )
                    }
                }

            }
            CoroutineScope(Dispatchers.Main).launch {
                _liveData.value =
                    ChernContainer(chernPerCategory, onChernCategoryExpanded)
            }
        }*/
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
                price_zp = job.price_zp.toString(),
                isMaterial = false
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
                category_id = material.category_id.toString(),
                isMaterial = true
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

    fun initMetricsList(): List<MetricsEntity> {
        return repository.getMetrics()
    }

    fun initCategoryById(id: Int): CategoryEntity {
        return repository.getCategoryById(id)
    }


}



