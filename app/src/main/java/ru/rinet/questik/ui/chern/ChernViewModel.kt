package ru.rinet.questik.ui.chern

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.rinet.questik.repo.QuestikRepository
import ru.rinet.questik.repo.local.room.entity.CategoryEntity
import ru.rinet.questik.repo.local.room.entity.ChernovikEntity
import ru.rinet.questik.repo.local.room.entity.MetricsEntity
import ru.rinet.questik.ui.chern.epoxy.*
import javax.inject.Inject

@HiltViewModel
class ChernViewModel @Inject constructor(
    private val repository: QuestikRepository
) : ViewModel() {
    val chernLiveData: LiveData<ChernContainer>
        get() = _liveData
    private val _liveData = MutableLiveData<ChernContainer>()

    //  private val fullLiveData = MutableLiveData<ChernContainer>()
    private var serchedItem: String = ""
    var showItogData: Boolean = false
    private var minList = 0
    private var maxList = 0

    init {

        CoroutineScope(Dispatchers.IO).launch {
            if (repository.getChernovikCount() == 0) {
                initChernovikDatabase()
            }
                val chernPerCategoryList: MutableList<ChernPerCategory> = mutableListOf()
                chernPerCategoryList.apply {
                    initCategoryList().forEach {
                        val itemListCount = repository.getChernovikCountByCategoryId(it.id.toString())
                       // val itemsList = repository.getChernovikByCategoryAndIdDiapazon(it.id.toString(), )
                        val itemsList = repository.getChernovikByCategoryId(it.id.toString())
                        //val listSize = itemsList.size
                        var minList = 0
                        if (itemListCount != 0) {
                            minList = 0
                        }
                        var maxList = 0
                        if (itemListCount != 0 && itemListCount <= 15) {
                            maxList = itemListCount
                        } else if (itemListCount == 0) {
                            maxList = 0
                        } else {
                            maxList = 15
                        }

                        //                itemsList.get(15).id ?:itemsList.get(listSize-1).id
                        /*     if (maxList>=listSize){
                                 val maxList = listSize
                             }*/
                        if (itemListCount!=0){
                            this.add(
                                ChernPerCategory(
                                    category = it,
                                    itemsSize = repository.getChernovikCountByCategoryId(it.id.toString()),
                                    minList,
                                    maxList,
                                    items = repository.getChernovikByCategoryId(it.id.toString()).subList(minList, maxList) as MutableList<ChernovikEntity>,
                                    onItemTouched,
                                    onItemCountChange,
                                    onItemUpdated(),
                                    initCategoryList(),
                                    initMetricsList()
                                )
                            )
                        }

                    }
                }
                CoroutineScope(Dispatchers.Main).launch {
                    // fullLiveData.value = ChernContainer(chernPerCategoryList, onChernCategoryExpanded)
                    _liveData.value =
                        ChernContainer(chernPerCategoryList, onChernCategoryExpanded)
                }
        }
    }

    private fun updateData(model: ChernovikEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            if (!model.equals(repository.getChernovikById(model.id))) {
                repository.updateChernovik(model)
                Log.i(
                    "TAG CHERN CHILD VIEW MODEL",
                    "разница ${model.name} -_________________________ОБНОВЛЕН_________________________"
                )
            }

            updateLiveData(model)
        }
    }

    /*         var chernPerCategory: MutableList<ChernPerCategory> = mutableListOf()
             val oldContainer = fullLiveData.value
             if (oldContainer != null) {
                 oldContainer.categories.map { chernPerCat ->
                     var udatedItems: MutableList<ChernovikEntity> = mutableListOf()
                     chernPerCat.items.forEach {
                         if (it.name == model.name && it.id == model.id) {
                             Log.i(
                                 "TAG CHERN CHILD VIEW MODEL",
                                 "разница ${model.name} - count ${it.itemCount}  changed to ${model.itemCount}  cheked is  ${it.isChecked}  changed to ${model.isChecked} Detail view is  ${it.isShow}  changed to ${model.isShow} "
                             )
                             udatedItems.add(model)
                         } else {
                             udatedItems.add(it)
                         }
                     }

                     if (udatedItems.isNotEmpty()) {
                         val newChernCat = chernPerCat.copy(items = udatedItems)
                         chernPerCategory.add(newChernCat)
                     }
                 }

                 CoroutineScope(Dispatchers.Main).launch {
                     fullLiveData.value = oldContainer.copy(categories = chernPerCategory)
                     _liveData.value = oldContainer.copy(categories = chernPerCategory)
                 }
             }
             if (serchedItem.isNotEmpty()) {
                 search(serchedItem)
             }
             if (showItogData) {
                 showItog()
             }*/

    private fun updateLiveData(model: ChernovikEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            val oldContainer = _liveData.value
            if (oldContainer != null) {
                val newContainer = oldContainer.categories.map { chp ->
                    if (model.categoryId == chp.category.id.toString()) {
                        chp.items.map {
                            if (it.id == model.id) {
                                it.copy(
                                    name = model.name,
                                    categoryId = model.categoryId,
                                    jobId = model.jobId,
                                    materialId = model.materialId,
                                    metricsId = model.metricsId,
                                    itemCount = model.itemCount,
                                    itemPrice = model.itemPrice,
                                    plu = model.plu,
                                    isChecked = model.isChecked,
                                    isShow = model.isShow,
                                    isMaterial = model.isMaterial
                                )
                            }
                        }

                        Log.i(
                            "TAG CHERN CHILD VIEW MODEL",
                            "разница  - 8, ITEMS LIST SIZE IS : ${chp.items.size} "
                        )
                    }
                    //  it.copy(items = items)



/*                    val listSize = chp.items.size
                    var minList: Int = 0
                    if (listSize != 0 && chp.minList != 0) {
                        chp.items.forEachIndexed { index, chernovikEntity ->
                            if (chernovikEntity.id == model.id) {
                                minList = index
                            }
                        }
                    }

                    var maxList = 0
                    if (listSize != 0 && listSize <= 14) {
                        maxList = chp.items.size
                    } else if (listSize == 0) {
                        maxList = 0
                    } else {
                        maxList = minList + 14
                    }*/
                    val newList:MutableList<ChernovikEntity> = mutableListOf<ChernovikEntity>()
                    newList.apply {
                        chp.items.forEach {chernovikEntity ->
                            this.add(chernovikEntity)
                        }
                    }
                    chp.copy(
                        items = newList
                    )
                     //   newList.addAll(repository.getChernovikByIdDiapazon(newList.get(newList.size-1).id, chp.maxList))
                        // val item : ChernovikEntity = repository.getChernovikById(i)
                    }



/*
                val chernPerCategoryList: MutableList<ChernPerCategory> = mutableListOf()
                chernPerCategoryList.apply {
                    initCategoryList().forEach {
                        var items = mutableListOf<ChernovikEntity>()
                        if (showItogData && serchedItem != "") {
                            Log.i(
                                "TAG CHERN CHILD VIEW MODEL",
                                "разница ${model.name} - 1 "
                            )
                            items = repository.filteredChernovikList(
                                serchedItem,
                                check = true,
                                it.id.toString()
                            ).toMutableList()
                        } else if (showItogData == true) {
                            Log.i(
                                "TAG CHERN CHILD VIEW MODEL",
                                "разница ${model.name} - 2 "
                            )
                            items = repository.getChernovikByItogShow(
                                check = true,
                                it.id.toString()
                            ).toMutableList()
                        } else if (serchedItem != "") {
                            Log.i(
                                "TAG CHERN CHILD VIEW MODEL",
                                "разница ${model.name} - 4 "
                            )
                            items = repository.searchChernovikItem(
                                serchedItem,
                                it.id.toString()
                            ).toMutableList()
                        } else {
                            Log.i(
                                "TAG CHERN CHILD VIEW MODEL",
                                "разница ${model.name} - 5 "
                            )
                            items =
                                repository.getChernovikByCategoryId(it.id.toString())
                                    .toMutableList()
                        }
                        //  it.copy(items = items)
                        if (items.size != 0) {
                            Log.i(
                                "TAG CHERN CHILD VIEW MODEL",
                                "лист ${items.toString()} - 1 "
                            )
                            */
/*                   this.add(
                                                   ChernPerCategory(
                                                       category = it,
                                                       items = items,
                                                       onItemTouched,
                                                       onItemCountChange,
                                                       onItemUpdated(),
                                                       initCategoryList(),
                                                       initMetricsList()
                                                   )
                                               )*//*



                        }
                    }
                }
*/

                CoroutineScope(Dispatchers.Main).launch {
                    _liveData.value = oldContainer.copy(categories = newContainer)
                }

                /*        val newContainer = oldContainer.categories.map {
                            var items = mutableListOf<ChernovikEntity>()
                            if (it.category.id.toString() == model.categoryId) {
                                if (showItogData && serchedItem != "") {
                                    items = repository.filteredChernovikList(
                                        serchedItem,
                                        check = true,
                                        it.category.id.toString()
                                    ).toMutableList()
                                } else {
                                    if (showItogData) {

                                        items = repository.getChernovikByItogShow(
                                            check = true,
                                            it.category.id.toString()
                                        ).toMutableList()
                                    } else {
                                        items =
                                            repository.getChernovikByCategoryId(it.category.id.toString())
                                                .toMutableList()
                                    }
                                    if (serchedItem != "") {
                                        items = repository.searchChernovikItem(
                                            serchedItem,
                                            it.category.id.toString()
                                        ).toMutableList()
                                    } else {
                                        items =
                                            repository.getChernovikByCategoryId(it.category.id.toString())
                                                .toMutableList()
                                    }
                                }

                                    it.copy(items = items)
                            } else {
                                it
                            }

                        }*/

            }
        }
    }

    private val onItemTouched: OnChernItemTouched = { model, touchUp ->
        //  Log.i("ChernFragmentViewMOdel", "${model.name} - is touchedUp - $touchUp ")
        //  updateData(model)
        CoroutineScope(Dispatchers.IO).launch {
            val oldContainer = _liveData.value
            if (oldContainer != null) {
                val newContainer = mutableListOf<ChernPerCategory>()
                oldContainer.categories.forEach { chp ->
                    val dataList = repository.getChernovikByCategoryId(chp.category.id.toString())
                    var newMin: Int = 0
                    var newMax: Int = 0

                    val startId: Int = dataList.firstOrNull()!!.id
                    if (model.categoryId == chp.category.id.toString()) {

                        dataList.forEachIndexed { index, chernovikEntity ->

                            if (model.id == chernovikEntity.id) {
                                Log.i(
                                    "TAG CHERN CHILD VIEW MODEL",
                                    " on touch model 1 id ${model.id} "
                                )
                                if (index - 7 > 0) {
                                    //  newMin = dataList.get(index - 7).id
                                   // newMin = model.id - 7
                                    newMin = dataList.get(0).id
                                } else {
                                    newMin = dataList.get(0).id
                                }

                                if (index + 15 < dataList.size-1) {
                                    newMax = model.id+14
                                } else {
                                    newMax = dataList.get(dataList.size-1).id
                                }

                                Log.i(
                                    "TAG CHERN CHILD VIEW MODEL",
                                    " on touch cashMin 2  ${newMin} and cashMax${newMax} "
                                )
                            }
                        }
                        Log.i(
                            "ChernFragmentViewMOdel",
                            "${model.name} - is touchedUp - $touchUp and MIN is - $newMin, and MAX is - $newMax "
                        )
                        for (i in newMin..newMax){
                           val item = repository.getChernovikById(i)
                            if (!chp.items.contains(item)){
                                chp.items.add(item)
                            }
                        }
                        val updatedChp = chp.copy(minList = 0, maxList = chp.items.size, items = chp.items)
                        newContainer.add(updatedChp)
                    } else if (dataList.size != 0) {


                        newContainer.add(chp)
                    }
                }
                CoroutineScope(Dispatchers.Main).launch {
                    _liveData.value = oldContainer.copy(categories = newContainer)
                }
              //  updateLiveData(model)
            }
        }
    }

    private fun onItemUpdated(): OnChernItemChanged = { model ->
        Log.i(
            "TAG CHERN CHILD VIEW MODEL ",
            "6"
        )
        updateData(model)
    }


    private val onItemCountChange: OnChernItemChangeCount = {
        Log.i("ChernFragmentViewMOdel", "${it} - in EdditBox event")

    }

/*    fun showItog() {
        if (showItogData == true) {
            CoroutineScope(Dispatchers.IO).launch {
                val oldContainer = fullLiveData.value
                if (oldContainer != null) {
                    val chernPerCategory = oldContainer.categories.map { chernPerCat ->
                        chernPerCat.copy(items = chernPerCat.items.filter {
                            it.isChecked
                        })
                    }
                    CoroutineScope(Dispatchers.Main).launch {
                        _liveData.value = oldContainer.copy(categories = chernPerCategory)
                    }
                    if (serchedItem.isNotEmpty()) {
                        search(serchedItem)
                    }
                }
            }
        }
    }*/

    private val onChernCategoryExpanded: OnChernCategoryExpanded = { category ->
        CoroutineScope(Dispatchers.IO).launch {
            val oldContainer = _liveData.value
            if (oldContainer != null) {
                oldContainer.onCategoryExpanded
                val newCategories: MutableList<ChernPerCategory> = oldContainer.categories.map {
                    if (it.category.id == category.id) {/*
                        var items = mutableListOf<ChernovikEntity>()
                        if (showItogData && serchedItem != "") {
                            items = repository.filteredChernovikList(
                                serchedItem,
                                check = true,
                                category.id.toString()
                            ).toMutableList()
                        } else {
                            if (showItogData) {
                                items = repository.getChernovikByItogShow(
                                    check = true,
                                    category.id.toString()
                                ).toMutableList()
                            } else {
                                items = repository.getChernovikByCategoryId(category.id.toString())
                                    .toMutableList()
                            }
                            if (serchedItem != "") {
                                items = repository.searchChernovikItem(
                                    serchedItem,
                                    category.id.toString()
                                ).toMutableList()
                            } else {
                                items = repository.getChernovikByCategoryId(category.id.toString())
                                    .toMutableList()
                            }
                        }*/
                        it.copy(
                            category = it.category.copy(
                                isExpand = !category
                                    .isExpand
                            )
                        )
      /*                  ,
                        items = repository.getChernovikByIdDiapazon(it.minList, it.maxList)*/
                    } else {
                        it
                    }
                } as MutableList<ChernPerCategory>
                CoroutineScope(Dispatchers.Main).launch {
                    _liveData.value = oldContainer.copy(categories = newCategories)
                }
                //updateLiveData()
            }
        }

    }


    fun search(serched: String) {
        serchedItem = serched
        CoroutineScope(Dispatchers.IO).launch {
            val oldContainer = _liveData.value
            if (oldContainer != null) {


                val chernPerCategory = oldContainer.categories.map { chernPerCat ->

                    if (showItogData) {
                        chernPerCat.copy(
                            items = repository.filteredChernovikList(
                                serchedItem,
                                check = true,
                                chernPerCat.category.id.toString()
                            ) as MutableList<ChernovikEntity>
                        )
                    } else {
                        chernPerCat.copy(
                            items = repository.searchChernovikItem(
                                serchedItem,
                                chernPerCat.category.id.toString()
                            ) as MutableList<ChernovikEntity>
                        )
                    }
                }
                CoroutineScope(Dispatchers.Main).launch {
                    _liveData.value = oldContainer.copy(categories = chernPerCategory)
                }
            }
        }
    }

/*    fun fetchAllData() {
        CoroutineScope(Dispatchers.Main).launch {
            _liveData.value = fullLiveData.value
        }
    }*/

    fun initCategoryList(): List<CategoryEntity> {
        return repository.getCategories()
    }

    fun initMetricsList(): List<MetricsEntity> {
        return repository.getMetrics()
    }


    fun initChernovikDatabase() {
        CoroutineScope(Dispatchers.IO).launch {

            val data: MutableList<ChernovikEntity> = mutableListOf()
            data.apply {
                repository.getJobs().forEach {
                    this.add(
                        ChernovikEntity(
                            name = it.name.toString(),
                            jobId = it.id.toString(),
                            itemPrice = it.price.toString(),
                            metricsId = it.metrics_id.toString(),
                            categoryId = it.category_id.toString(),
                            isChecked = false,
                            isShow = false,
                            isMaterial = false
                        )
                    )
                }
                repository.getMaterials().forEach {
                    this.add(
                        ChernovikEntity(
                            materialId = it.id.toString(),
                            plu = it.plu.toString(),
                            name = it.name.toString(),
                            itemPrice = it.price.toString(),
                            metricsId = it.metrics_id.toString(),
                            categoryId = it.category_id.toString(),
                            isChecked = false,
                            isShow = false,
                            isMaterial = true
                        )
                    )
                }
            }
            repository.insertAllChernovik(data)
            onItemUpdated()
        }
    }

/*    fun initItemsByCategoryId(id: Int): MutableList<ChernovikEntity> {
        val commonModelList = mutableListOf<ChernovikEntity>()
        repository.getJobsByCategoryId(id.toString()).forEach {
            commonModelList.add(
                ChernovikEntity(
                    //rowId = UUID.randomUUID().toString(),
                    id = it.id.toString(),
                    name = it.name.toString(),
                    price = it.price.toString(),
                    metrics_id = it.metrics_id.toString(),
                    category_id = it.category_id.toString(),
                    price_inzh = it.price_inzh.toString(),
                    price_nalog_zp = it.price_nalog_zp.toString(),
                    price_zp = it.price_zp.toString(),
                    isMaterial = false
                )
            )
        }
        commonModelList.apply {
            val materials = repository.getMaterialByCategoryId(id.toString())
            val start: Int = 0
            val end: Int = 20
            if (materials.size <= end) {
                materials.subList(start, materials.size).map {
                    this.add(
                        ChernovikEntity(
                            rowId = UUID.randomUUID().toString(),
                            id = it.id.toString(),
                            plu = it.plu.toString(),
                            name = it.name.toString(),
                            price = it.price.toString(),
                            metrics_id = it.metrics_id.toString(),
                            category_id = it.category_id.toString(),
                            isMaterial = true
                        )
                    )
                }
            } else {
                materials.subList(start, end).map {
                    this.add(
                        CommonModel(
                            rowId = UUID.randomUUID().toString(),
                            id = it.id.toString(),
                            plu = it.plu.toString(),
                            name = it.name.toString(),
                            price = it.price.toString(),
                            metrics_id = it.metrics_id.toString(),
                            category_id = it.category_id.toString(),
                            isMaterial = true
                        )
                    )
                }
            }
        }
        return commonModelList
    }*/
}



