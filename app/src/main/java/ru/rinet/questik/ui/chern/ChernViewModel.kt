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

    init {
        CoroutineScope(Dispatchers.IO).launch {
        if (repository.getChernovikCount()!=0){
                val chernPerCategoryList: MutableList<ChernPerCategory> = mutableListOf()
                chernPerCategoryList.apply {
                    initCategoryList().forEach {
                        val itemsList = mutableListOf<ChernovikEntity>()
                        this.add(
                            ChernPerCategory(
                                category = it,items = repository.getChernovikByCategoryId(it.id.toString()),
                                onItemTouched,
                                onItemCountChange,
                                onItemUpdated(),
                                initCategoryList(),
                                initMetricsList()
                            )
                        )
                    }
                }
                CoroutineScope(Dispatchers.Main).launch {
                   // fullLiveData.value = ChernContainer(chernPerCategoryList, onChernCategoryExpanded)
                    _liveData.value =
                        ChernContainer(chernPerCategoryList, onChernCategoryExpanded)
                }

        } else {
            initChernovikDatabase()
            CoroutineScope(Dispatchers.IO).launch {
                val chernPerCategoryList: MutableList<ChernPerCategory> = mutableListOf()
                chernPerCategoryList.apply {
                    initCategoryList().forEach {
                        this.add(
                            ChernPerCategory(
                                category = it, items = repository.getChernovikByCategoryId(it.id.toString()),
                                onItemTouched,
                                onItemCountChange,
                                onItemUpdated(),
                                initCategoryList(),
                                initMetricsList()
                            )
                        )
                    }
                }
                CoroutineScope(Dispatchers.Main).launch {
                   // fullLiveData.value = ChernContainer(chernPerCategoryList, onChernCategoryExpanded)
                    _liveData.value =
                        ChernContainer(chernPerCategoryList, onChernCategoryExpanded)
                }
            }
        }
        }
    }

    private fun updateData(model: ChernovikEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            if (!model.equals(repository.getChernovikById(model.id))){
                repository.updateChernovik(model)
            }
            updateLiveData(model)

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
        }
    }

    private fun updateLiveData(model: ChernovikEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            val oldContainer = _liveData.value
            if (oldContainer != null) {
                val newContainer = oldContainer.categories.map {
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

                }


/*               var chernPerCategoryList: MutableList<ChernPerCategory> = mutableListOf()
                   chernPerCategoryList.apply {
                    initCategoryList().forEach {
                        if (showItogData&&serchedItem!=""){
                            this.add(
                                ChernPerCategory(
                                    category = it,
                                    items = repository.filteredChernovikList(
                                        serchedItem,
                                        check = true,
                                        model.categoryId
                                    ),
                                    onItemTouched,
                                    onItemCountChange,
                                    onItemUpdated(),
                                    initCategoryList(),
                                    initMetricsList()
                                )
                            )
                        }else{
                            if (showItogData) {
                                this.add(
                                    ChernPerCategory(
                                        category = it,
                                        items = repository.getChernovikByItogShow(
                                            check = true,
                                            model.categoryId
                                        ),
                                        onItemTouched,
                                        onItemCountChange,
                                        onItemUpdated(),
                                        initCategoryList(),
                                        initMetricsList()
                                    )
                                )
                            } else {
                                this.add(
                                    ChernPerCategory(
                                        category = it,
                                        items = repository.getChernovikByCategoryId(it.id.toString()),
                                        onItemTouched,
                                        onItemCountChange,
                                        onItemUpdated(),
                                        initCategoryList(),
                                        initMetricsList()
                                    )
                                )
                            }
                            if (serchedItem!="") {
                                this.add(
                                    ChernPerCategory(
                                        category = it,
                                        items = repository.searchChernovikItem(
                                            serchedItem,
                                            model.categoryId
                                        ),
                                        onItemTouched,
                                        onItemCountChange,
                                        onItemUpdated(),
                                        initCategoryList(),
                                        initMetricsList()
                                    )
                                )
                            } else {
                                this.add(
                                    ChernPerCategory(
                                        category = it,
                                        items = repository.getChernovikByCategoryId(it.id.toString()),
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


                }*/
                CoroutineScope(Dispatchers.Main).launch {
                    _liveData.value = oldContainer.copy(categories = newContainer)
                }
            }
        }
    }

    private val onItemTouched: OnChernItemTouched = { model, touchUp ->
        Log.i("ChernFragmentViewMOdel", "${model.name} - is touchedUp - $touchUp ")

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
                    if (it.category.id == category.id) {
                        var items = mutableListOf<ChernovikEntity>()
                        if (showItogData&&serchedItem!=""){
                                    items = repository.filteredChernovikList(
                                        serchedItem,
                                        check = true,
                                        category.id.toString()
                                    ).toMutableList()
                        }else{
                            if (showItogData) {
                                        items = repository.getChernovikByItogShow(
                                            check = true,
                                            category.id.toString()
                                        ).toMutableList()
                            } else {
                                        items = repository.getChernovikByCategoryId(category.id.toString())
                                            .toMutableList()
                            }
                            if (serchedItem!="") {
                                        items = repository.searchChernovikItem(
                                            serchedItem,
                                            category.id.toString()).toMutableList()
                            } else {
                                        items = repository.getChernovikByCategoryId(category.id.toString())
                                            .toMutableList()
                            }
                        }


                        it.copy(
                            category = it.category.copy(
                                isExpand = !category
                                    .isExpand
                            ),
                             items = items
                        )
                    } else {
                        it
                    }
                } as MutableList<ChernPerCategory>

                CoroutineScope(Dispatchers.Main).launch {
                    _liveData.value = oldContainer.copy(categories = newCategories)
                }
            }
        }

    }



    fun search(serched: String) {
        serchedItem = serched
       CoroutineScope(Dispatchers.IO).launch {
            val oldContainer = _liveData.value
            if (oldContainer != null) {


                val chernPerCategory = oldContainer.categories.map { chernPerCat ->

                    if(showItogData){
                        chernPerCat.copy(items = repository.filteredChernovikList(
                            serchedItem,
                            check = true,
                            chernPerCat.category.id.toString()
                        ))
                    }else{
                        chernPerCat.copy(items = repository.searchChernovikItem(serchedItem,chernPerCat.category.id.toString()))
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



