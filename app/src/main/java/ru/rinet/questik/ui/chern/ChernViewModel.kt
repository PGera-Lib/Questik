package ru.rinet.questik.ui.chern

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.rinet.questik.repo.QuestikRepository
import ru.rinet.questik.repo.local.room.entity.CategoryEntity
import ru.rinet.questik.repo.local.room.entity.ChernovikEntity
import ru.rinet.questik.ui.chern.epoxy.*
import javax.inject.Inject
import kotlin.system.measureTimeMillis

@HiltViewModel
class ChernViewModel @Inject constructor(
    private val repository: QuestikRepository
) : ViewModel() {
    val chernLiveData: LiveData<ChernContainer>
        get() = _liveData
    private val _liveData = MutableLiveData<ChernContainer>()


    var isLoading = MutableLiveData<Boolean>()


    //  private val fullLiveData = MutableLiveData<ChernContainer>()
    private var serchedItem: String = ""
    var showItogData: Boolean = false
    init {

        CoroutineScope(Dispatchers.IO).launch {
            CoroutineScope(Dispatchers.Main).launch {
                isLoading.value = true
            }
            if (repository.getChernovikCount() == 0) {
                initChernovikDatabase()
            }
            val chernCategoryList: MutableList<CategoryItems> = mutableListOf()
            chernCategoryList.apply {
                initCategoryList().forEach {
                    var minItemId = 0
                    var maxItemId = 0
                    val emptyList = mutableListOf<ChernovikItems>()
                    val itemListCount = repository.getChernovikCountByCategoryId(it.id.toString())
                    val itemsList = repository.getChernovikByCategoryId(it.id.toString())
                    if (itemsList.size != 0) {
                        minItemId = itemsList.get(0).id
                        if (itemListCount > 100) {
                            maxItemId = itemsList.get(99).id
                        } else {
                            maxItemId = itemsList.get(itemListCount - 1).id
                        }
                    }

                    if (itemListCount != 0) {
                        this.add(
                            CategoryItems(
                                category = it,
                                itemsSize = repository.getChernovikCountByCategoryId(it.id.toString()),
                                minList = minItemId,
                                maxList = maxItemId,
                                items = emptyList
                            )
                        )
                    }
                }
            }

            CoroutineScope(Dispatchers.Main).launch {

                // fullLiveData.value = ChernContainer(chernPerCategoryList, onChernCategoryExpanded)
                _liveData.value =
                    ChernContainer(chernCategoryList, onChernCategoryExpanded)

            }
            CoroutineScope(Dispatchers.Main).launch {
                isLoading.value = false
            }
        }

    }

    private fun updateData(model: ChernovikEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            CoroutineScope(Dispatchers.Main).launch {
                isLoading.value = true
            }
            if (!model.equals(repository.getChernovikById(model.id))) {
                val elapsedTime = measureTimeMillis {
                    repository.updateChernovik(model)
                    updateLiveDataModel(model)
                }
                println("------------------------------------------------UPDATE LIVEDATA ITEM in UI THREAD  выполнен за  $elapsedTime ms")
            }
            CoroutineScope(Dispatchers.Main).launch {
                isLoading.value = false
            }
        }

    }

    private fun updateLiveDataScale(model: ChernovikEntity) {
        val oldContainer = _liveData.value
        if (oldContainer != null) {
            val newContainer = oldContainer.categories.map { chp ->
                if (chp.category.id.toString() == model.categoryId) {
                    val items =
                        repository.getChernovikByIdDiapazon(chp.minList, chp.maxList)
                    val chernovikItems = mutableListOf<ChernovikItems>()
                    chernovikItems.apply {
                        items.forEach { entity ->
                            val item = ChernovikItems(
                                item = entity,
                                onItemTouched = onItemTouched,
                                OnItemChangeCount = onItemCountChange,
                                onItemUpdated = onItemUpdated(),
                                categoryEntityList = repository.getCategories(),
                                metricsEntityList = repository.getMetrics()
                            )
                            add(item)
                        }
                    }
                    chp.copy(
                        items = chernovikItems
                    )
                } else {
                    chp
                }
            }
            CoroutineScope(Dispatchers.Main).launch {
                _liveData.value =
                    oldContainer.copy(categories = newContainer)

            }

        }
    }


    private fun updateLiveDataModel(model: ChernovikEntity) {
        val oldContainer = _liveData.value
        if (oldContainer != null) {
            val newContainer = oldContainer.categories.map { chp ->
                if (model.categoryId == chp.category.id.toString() && chp.itemsSize != 0) {
                    Log.i(
                        "TAG CHERN CHILD VIEW MODEL",
                        "разница  - 8, ITEMS LIST SIZE IS : ${chp.items.size} "
                    )
                    chp.copy(items = chp.items.map {
                        if (it.item.id == model.id) {
                            it.copy(item = model)
                        } else {
                            it
                        }
                    } as MutableList<ChernovikItems>)
                } else {
                    chp
                }
            }
            CoroutineScope(Dispatchers.Main).launch {

                _liveData.value =
                    oldContainer.copy(categories = newContainer as MutableList<CategoryItems>)

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

    private val onItemTouched: OnChernItemTouched = { model ->
        CoroutineScope(Dispatchers.IO).launch {
            CoroutineScope(Dispatchers.Main).launch {
                isLoading.value = true
            }
            val elapsedTime = measureTimeMillis {
                updateOnTouch(model)
            }
            println("------------------------------------------------onItemTouched in UI THREAD  выполнен за  $elapsedTime ms")
            if (model.equals(repository.getChernovikById(model.id))) {
                val elapsedTime = measureTimeMillis {
                    updateLiveDataScale(model)
                }
                println("------------------------------------------------UPDATE LIVEDATA ITEMS SIZE in UI THREAD  выполнен за  $elapsedTime ms")
            }
            CoroutineScope(Dispatchers.Main).launch {
                isLoading.value = false
            }
        }
    }

    private fun updateOnTouch(model: ChernovikEntity) {
        val oldContainer = _liveData.value
        if (oldContainer != null) {
            var newMin: Int = 0
            var newMax: Int = 0
            val newContainer = oldContainer.categories.map { chp ->
                if (model.categoryId == chp.category.id.toString()&&model.id +15 >= chp.minList + chp.items.size) {

                    if (chp.items.size + 100 < chp.itemsSize) {
                        Log.i(
                            "ChernFragmentViewMOdel",
                            "---------------------------------------------oooops 1 ----------------------------------------- "
                        )
                        newMax = chp.maxList + 100
                    } else {
                        Log.i(
                            "ChernFragmentViewMOdel",
                            "---------------------------------------------oooops 2 ----------------------------------------- "
                        )
                        newMax = repository.getChernovikById(chp.minList + chp.itemsSize).id

                        Log.i(
                            "ChernFragmentViewMOdel",
                            " AFTER ${model.name} - is touched and MIN is - $newMin, and MAX is - $newMax and LIST SIZE is ${chp.items.size} "
                        )

                    }
                    chp.copy(minList = chp.minList, maxList = newMax)
                } else {
                    chp
                }
            }

            GlobalScope.launch(Dispatchers.Main) {
                val elapsedTime = measureTimeMillis {
                    if (!newContainer.equals(oldContainer.categories)){
                        _liveData.value = oldContainer.copy(categories = newContainer)
                    }
                }
                println("------------------------------------------------onItemTouched in MAIN THREAD  выполнен за  $elapsedTime ms     and EQUALS is ${newContainer.equals(oldContainer.categories)} ")
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

            CoroutineScope(Dispatchers.Main).launch {
                isLoading.value = true
            }

            val oldContainer = _liveData.value
            if (oldContainer != null) {
                oldContainer.onCategoryExpanded
                val newCategories: MutableList<CategoryItems> =
                    oldContainer.categories.map { it ->
                        if (it.category.id == category.id) {
                            val items =
                                repository.getChernovikByIdDiapazon(it.minList, it.maxList)
                            val chernovikItems = mutableListOf<ChernovikItems>()
                            chernovikItems.apply {
                                items.forEach { entity ->
                                    val item = ChernovikItems(
                                        item = entity,
                                        onItemTouched = onItemTouched,
                                        OnItemChangeCount = onItemCountChange,
                                        onItemUpdated = onItemUpdated(),
                                        categoryEntityList = repository.getCategories(),
                                        metricsEntityList = repository.getMetrics()
                                    )
                                    add(item)
                                }
                            }
                            it.copy(
                                category = it.category.copy(
                                    isExpand = !category.isExpand
                                ),
                                items = chernovikItems
                            )
                        } else {
                            it
                        }
                    } as MutableList<CategoryItems>
                CoroutineScope(Dispatchers.Main).launch {

                    _liveData.value = oldContainer.copy(categories = newCategories)

                }
                //updateLiveData()
                CoroutineScope(Dispatchers.Main).launch {
                    isLoading.value = false
                }
            }

        }

    }


    fun search(serched: String) {
        serchedItem = serched

/*        CoroutineScope(Dispatchers.IO).launch {
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
        }*/
    }


    fun initCategoryList(): List<CategoryEntity> {
        return repository.getCategories()
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

}



