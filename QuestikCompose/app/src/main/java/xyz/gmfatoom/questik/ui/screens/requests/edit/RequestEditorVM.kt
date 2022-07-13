package xyz.gmfatoom.questik.ui.screens.requests.edit

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import xyz.gmfatoom.questik.repo.QuestikRepository
import xyz.gmfatoom.questik.repo.local.room.dao.CorpDao
import xyz.gmfatoom.questik.repo.local.room.entity.*
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class RequestEditorVM @Inject constructor(
    private val repository: QuestikRepository
) : ViewModel() {

    private val _requestId = MutableStateFlow(0)
    val requestId: StateFlow<Int> get() = _requestId

    private var serched: String = ""
    val itogRabot = MutableStateFlow(0.0)
    val itogMaterial = MutableStateFlow(0.0)


    private val _request = MutableStateFlow(RequestsEntity())
    val request: StateFlow<RequestsEntity> get() = _request

    private val _corp = MutableStateFlow(CorpEntity())
    val corp: StateFlow<CorpEntity> get() = _corp

    private val _contacts = MutableStateFlow(ContactsEntity())
    val contacts: StateFlow<ContactsEntity> get() = _contacts

    private val _reqObject = MutableStateFlow(ObjectsEntity())
    val reqObject: StateFlow<ObjectsEntity> get() = _reqObject

    private val _expandableCategoryList = MutableStateFlow(listOf<Int>())
    val expandableCategoryList: StateFlow<List<Int>> get() = _expandableCategoryList


    private val _fullRequestItems =
        MutableStateFlow(mutableMapOf<CategoryEntity, List<RequestItemEntity>>())

    private val _requestJobsCatalog =
        MutableStateFlow(mutableMapOf<CategoryEntity, List<RequestItemEntity>>())
    val requestJobsCatalog: StateFlow<MutableMap<CategoryEntity, List<RequestItemEntity>>> get() = _requestJobsCatalog

    private val _requestMaterialsCatalog =
        MutableStateFlow(mutableMapOf<CategoryEntity, List<RequestItemEntity>>())
    val requestMaterialsCatalog: StateFlow<MutableMap<CategoryEntity, List<RequestItemEntity>>> get() = _requestMaterialsCatalog

/*
    private val _fullJobsCatalog =
        MutableStateFlow(mutableMapOf<CategoryEntity, List<JobsEntity>>())
    val fullJobsCatalog: StateFlow<MutableMap<CategoryEntity, List<JobsEntity>>> get() = _fullJobsCatalog

    private val _fullMaterialsCatalog =
        MutableStateFlow(mutableMapOf<CategoryEntity, List<MaterialEntity>>())
    val fullMaterialsCatalog: StateFlow<MutableMap<CategoryEntity, List<MaterialEntity>>> get() = _fullMaterialsCatalog*/

    init {
        getRequest()
        getRequestData()
/*        getJobsData()
        getMaterialsData()*/
        getCorp()
        getContacts()
        getObject()
    }

    fun setRequestId(id: Int) {

        if (_requestId.value != id) {
            _requestId.value = id
            fetchData()
        }

    }

    private fun getObject() {
        viewModelScope.launch(Dispatchers.Default) {
            if (_requestId.value != 0) {
                _reqObject.value =
                    repository.getObjectsById(repository.getRequestById(requestId.value).object_id)
            }
        }
    }

    fun fetchData() {
        viewModelScope.launch(Dispatchers.Default) {
            getRequestData()
/*        getJobsData()
        getMaterialsData()*/
            getRequest()
            getCorp()
            getContacts()
            getObject()
        }
    }

    private fun getContacts() {
        viewModelScope.launch(Dispatchers.Default) {
            if (_requestId.value != 0) {
                val contactsEntity =
                    repository.getContactsById(repository.getRequestById(requestId.value).contacts_id)
                println("VM                       Contacts - ${contactsEntity.toString()}")
                joinAll()
                _contacts.value = contactsEntity
            }
        }
    }

    private fun getCorp() {
        viewModelScope.launch(Dispatchers.Default) {
            if (_requestId.value != 0) {
                val corpEntity =
                    repository.getCorpById(repository.getRequestById(requestId.value).corp_id)
                println("VM                       Corp - ${corpEntity.toString()}")
                joinAll()
                _corp.emit(corpEntity)
            }
        }
    }

    private fun getRequest() {
        viewModelScope.launch(Dispatchers.Default) {
            if (_requestId.value != 0) {
                val requestEntity = repository.getRequestById(requestId.value)
                println("VM                       Request - ${requestEntity.name}")
                joinAll()
                _request.emit(requestEntity)
            }
        }
    }

/*    private fun getJobsData() {
        viewModelScope.launch(Dispatchers.Default) {
            val sampleList = mutableMapOf<CategoryEntity, List<JobsEntity>>()
            repository.getCategories().forEach { category ->
                val itemsList =
                    repository.getJobsByCategoryId(category.id.toString()).map { jobItem ->
                        jobItem.copy(metrics_id = repository.getMetricsById(jobItem.metrics_id.toInt()).name)
                    }
                if (itemsList.isNotEmpty()) {
                    sampleList.put(category, itemsList)
                }
            }
            _fullJobsCatalog.value = sampleList
           updateSelected()
        }
    }*/

/*
    private fun updateSelected() {
        viewModelScope.launch(Dispatchers.Default) {
            val oldFullJob = _fullJobsCatalog.value
            val newFull = mutableMapOf<CategoryEntity, List<JobsEntity>>()
            oldFullJob.forEach { categoryEntity1, list1 ->
                _requestJobsCatalog.value.forEach { categoryEntity2, list2 ->
                    if (categoryEntity1.id == categoryEntity2.id) {
                        val newList = mutableListOf<JobsEntity>()
                        list1.forEach { job ->
                            list2.forEach { reqItem ->
                                if (job.id.toString() == reqItem.jobId) {
                                    newList.add(job.copy(isSelected = true))
                                } else {
                                    newList.add(job)
                                }
                            }
                        }
                        newFull.put(categoryEntity1, newList)
                    } else {
                        newFull.put(categoryEntity1, list1)
                    }
                }
            }
            _fullJobsCatalog.value = newFull
        }
    }*/

/*
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
        }
    }*/

    fun cardArrowClick(cardId: Int) {
        _expandableCategoryList.value = _expandableCategoryList.value.toMutableList().also { list ->
            if (list.contains(cardId)) {
                list.remove(cardId)
            } else {
                list.add(cardId)
            }
        }
    }

    private fun getRequestData() {
        viewModelScope.launch(Dispatchers.Default) {
            var sampleJob = mutableMapOf<CategoryEntity, List<RequestItemEntity>>()
            val sampleMaterial = mutableMapOf<CategoryEntity, List<RequestItemEntity>>()
            var sumJob: Double = 0.0
            var sumMaterial: Double = 0.0
            repository.getCategories().forEach { category ->
                val itemsList =
                    repository.getRequestItemsByProjectId(_requestId.value, category.id.toString())

/*
                        .map {
                            it.copy(metricsId = repository.getMetricsById(it.metricsId.toInt()).name)
                        }
*/
                if (itemsList.isNotEmpty()) {
                    _fullRequestItems.value.put(category, itemsList)

                    sampleJob.put(category, itemsList.filter {
                        !it.isMaterial
                    })

                    sampleMaterial.put(category, itemsList.filter {
                        it.isMaterial
                    })

                    sumJob += itemsList.filter {
                        !it.isMaterial
                    }.sumOf { it.itemCount.toDouble() * it.itemPrice.toDouble() }
                    sumMaterial += itemsList.filter {
                        it.isMaterial
                    }.sumOf { it.itemCount.toDouble() * it.itemPrice.toDouble() }
                }

            }
            itogRabot.value = sumJob
            itogMaterial.value = sumMaterial
            _requestJobsCatalog.value = sampleJob
            _requestMaterialsCatalog.value = sampleMaterial
            updateFullRequest()
        }

    }

    fun serchedFilter(serchWord: String) {
        serched = serchWord
        viewModelScope.launch(Dispatchers.Default) {
            if (serchWord.length >= 3) {
                val filteredJobs =
                    MutableStateFlow(mutableMapOf<CategoryEntity, List<RequestItemEntity>>())
                val filteredMaterial =
                    MutableStateFlow(mutableMapOf<CategoryEntity, List<RequestItemEntity>>())
                _fullRequestItems.value.forEach { category, list ->
                    val newList = list.filter {
                        it.name.toLowerCase(Locale.getDefault()).contains(
                            serchWord.toLowerCase(
                                Locale.getDefault()
                            )
                        )
                    }
                    if (newList.isNotEmpty()) {
                        filteredJobs.value.put(category, newList.filter { !it.isMaterial })
                        filteredMaterial.value.put(category, newList.filter { it.isMaterial })
                    }
                }
                _requestJobsCatalog.value = filteredJobs.value
                _requestMaterialsCatalog.value = filteredMaterial.value
            } else if (serchWord.isEmpty() || serchWord.length < 3) {
                getRequestData()
            }
        }
    }


    fun updateRequestJobsCatalog(jobsMap: MutableMap<CategoryEntity, List<RequestItemEntity>>) {
        viewModelScope.launch(Dispatchers.Default) {
            var sum: Double = 0.0
            val selectedCatalogItems =
                MutableStateFlow(mutableMapOf<CategoryEntity, List<RequestItemEntity>>())
            jobsMap.forEach { (category, list) ->
                val itemsList = arrayListOf<RequestItemEntity>().apply {
                    list.forEach {
                        if (it.isChecked) {
                            this += it
                            var count = it.itemCount
                            if (count.length == 0) {
                                count = "0"
                            }
                            sum += it.itemPrice.toDouble() * count.toDouble()
                        }
                    }
                }
                if (itemsList.isNotEmpty()) {
                    selectedCatalogItems.value.put(category, itemsList)
                }
            }
            itogRabot.value = sum
            _requestJobsCatalog.value = selectedCatalogItems.value
        }
    }

    fun updateRequestMaterialsCatalog(materialsMap: MutableMap<CategoryEntity, List<RequestItemEntity>>) {
        viewModelScope.launch(Dispatchers.Default) {
            var sum: Double = 0.0
            val selectedCatalogItems =
                MutableStateFlow(mutableMapOf<CategoryEntity, List<RequestItemEntity>>())
            materialsMap.forEach { (category, list) ->
                val itemsList = arrayListOf<RequestItemEntity>().apply {
                    list.forEach {
                        if (it.isChecked) {
                            this += it
                            var count = it.itemCount
                            if (count.length == 0) {
                                count = "0"
                            }
                            sum += it.itemPrice.toDouble() * count.toDouble()
                        }
                    }
                }
                if (itemsList.isNotEmpty()) {
                    selectedCatalogItems.value.put(category, itemsList)
                }
            }
            itogMaterial.value = sum
            _requestMaterialsCatalog.value = selectedCatalogItems.value
        }
    }


/*    fun checkJobsItem(item: JobsEntity) {
        viewModelScope.launch(Dispatchers.Default) {
            val filteredCatalog = mutableMapOf<CategoryEntity, List<JobsEntity>>()
            _fullJobsCatalog.value.forEach { (category, list) ->
                val newList = ArrayList<JobsEntity>()
                newList.apply {
                    list.forEach {
                        if (item.id.equals(it.id)) {
                            this += item
                        } else {
                            this += it
                        }
                    }
                }
                filteredCatalog.put(category, newList)
            }

            _fullJobsCatalog.value = filteredCatalog
            if (serched.length >= 3) {
                serchedFilter(serched)
            }

            val newItem = RequestItemEntity(
                id = item.id,
                name = item.name,
                categoryId = item.category_id,
                jobId = item.id.toString(),
                metricsId = item.metrics_id,
                itemCount = item.count,
                itemPrice = item.price,
                isMaterial = false
            )


            val itemCategory = repository.getCategoryById(item.category_id.toInt())
            val newMap = mutableMapOf<CategoryEntity, List<RequestItemEntity>>()
            val oldMap = _requestJobsCatalog.value
            if (oldMap.keys.contains(itemCategory)) {
                oldMap.forEach { categoryEntity, list ->
                    val newList = mutableListOf<RequestItemEntity>()
                    if (itemCategory.id == categoryEntity.id) {
                        newList.apply {
                            if (list.filter { it.jobId == newItem.jobId }.isEmpty()) {
                                addAll(list)
                                add(newItem)
                            } else {
                                addAll(list.filter { it.jobId != newItem.jobId })
                            }
                        }
                        newMap.put(categoryEntity, newList)
                    } else {
                        newMap.put(categoryEntity, list)
                    }
                }
                _requestJobsCatalog.value = newMap
            } else {
                val newList = mutableListOf<RequestItemEntity>()
                newList.add(newItem)
                oldMap.put(itemCategory, newList)
                println()
                _requestJobsCatalog.value = oldMap
            }
        }
        updateFullRequest()
        println()
        if (serched.length >= 3) {
            serchedFilter(serched)
        }
    }*/


    private fun updateFullRequest() {
        viewModelScope.launch(Dispatchers.Default) {
            val newFull = MutableStateFlow(mutableMapOf<CategoryEntity, List<RequestItemEntity>>())
            newFull.value.apply {
                _requestJobsCatalog.value.forEach { categoryEntity, list ->
                    this.put(categoryEntity, list)
                }
                _requestMaterialsCatalog.value.forEach { categoryEntity, list ->
                    this.put(categoryEntity, list)
                }
            }
            _fullRequestItems.value = newFull.value
        }
    }
/*
    fun checkMaterialsItem(item: MaterialEntity) {
        println(" RequestItemsVM item -  ${item.name}    is checked - ${item.isSelected}")
        viewModelScope.launch(Dispatchers.Default) {

            val filteredCatalog = mutableMapOf<CategoryEntity, List<MaterialEntity>>()
            _fullMaterialsCatalog.value.forEach { (category, list) ->
                val newList = ArrayList<MaterialEntity>()
                newList.apply {
                    list.forEach {
                        if (item.id.equals(it.id)) {
                            this += item
                        } else {
                            this += it
                        }
                    }
                }
                filteredCatalog.put(category, newList)
            }
            _fullMaterialsCatalog.value = filteredCatalog
            if (serched.length >= 3) {
                serchedFilter(serched)
            }

            val newItem = RequestItemEntity(
                id = item.id,
                name = item.name,
                categoryId = item.category_id,
                materialId = item.id.toString(),
                metricsId = item.metrics_id,
                itemCount = item.count,
                itemPrice = item.price,
                plu = item.plu ?: "",
                isMaterial = true
            )


            val itemCategory = repository.getCategoryById(item.category_id.toInt())
            val newMap = mutableMapOf<CategoryEntity, List<RequestItemEntity>>()
            val oldMap = _requestMaterialsCatalog.value
            if (oldMap.keys.contains(itemCategory)) {
                oldMap.forEach { categoryEntity, list ->
                    val newList = mutableListOf<RequestItemEntity>()
                    if (itemCategory.id == categoryEntity.id) {
                        newList.apply {
                            if (list.filter { it.jobId == newItem.jobId }.isEmpty()) {
                                addAll(list)
                                add(newItem)
                            } else {
                                addAll(list.filter { it.jobId != newItem.jobId })
                            }
                        }
                        newMap.put(categoryEntity, newList)
                    } else {
                        newMap.put(categoryEntity, list)
                    }
                }
                _requestMaterialsCatalog.value = newMap
            } else {
                val newList = mutableListOf<RequestItemEntity>()
                newList.add(newItem)
                oldMap.put(itemCategory, newList)
                println()
                _requestMaterialsCatalog.value = oldMap
            }
            updateFullRequest()

            if (serched.length >= 3) {
                serchedFilter(serched)
            }
        }

    }*/

    fun updateRequestJobsItem(item: RequestItemEntity) {
        println(" RequestItemsVM item -  ${item.name}    is count is - ${item.itemCount}")
        viewModelScope.launch(Dispatchers.Default) {
            val filteredCatalog = mutableMapOf<CategoryEntity, List<RequestItemEntity>>()
            _requestJobsCatalog.value.forEach { (category, list) ->
                val newList = ArrayList<RequestItemEntity>()
                newList.apply {
                    list.forEach {
                        if (item.id == it.id) {
                            println("chernviewmodel , changed count to - ${item.itemCount}}  ")
                            this += item
                        } else {
                            this += it
                        }
                    }
                }
                filteredCatalog.put(category, newList)
            }
            _requestJobsCatalog.value = filteredCatalog
            if (serched.length >= 3) {
                serchedFilter(serched)
            }
        }
    }


    fun updateRequestMaterialsItem(item: RequestItemEntity) {
        println(" RequestItemsVM item -  ${item.name}    is count is - ${item.itemCount}")
        viewModelScope.launch(Dispatchers.Default) {
            val filteredCatalog = mutableMapOf<CategoryEntity, List<RequestItemEntity>>()
            _requestMaterialsCatalog.value.forEach { (category, list) ->
                val newList = ArrayList<RequestItemEntity>()
                newList.apply {
                    list.forEach {
                        if (item.id == it.id) {
                            println("chernviewmodel , changed count to - ${item.itemCount}}  ")
                            this += item
                        } else {
                            this += it
                        }
                    }
                }
                filteredCatalog.put(category, newList)
            }
            _requestMaterialsCatalog.value = filteredCatalog
            if (serched.length >= 3) {
                serchedFilter(serched)
            }
        }
    }

    fun saveFullRequest(
/*        request: RequestsEntity,
       items: List<RequestItemEntity>,
        corp: CorpEntity,
        contactsEntity: ContactsEntity,
        objectsEntity: ObjectsEntity*/
    ) {
        viewModelScope.launch(Dispatchers.Default) {
           val contactsId = repository.insertContacts(_contacts.value)
            val corpId = repository.insertCorp(_corp.value)
            val objectId = repository.insertObjects(_reqObject.value)
            val id = repository.insertRequest(
                _request.value.copy(
                    contacts_id = contactsId.toInt(),
                    corp_id = corpId.toInt(),
                    object_id = objectId.toInt()
                )
            )

            _requestJobsCatalog.value.forEach { categoryEntity, list ->
                list.forEach {
                    repository.insertRequestItem(it.copy(requestId = id.toInt()))
                }
            }
            _requestMaterialsCatalog.value.forEach { categoryEntity, list ->
                list.forEach {
                    repository.insertRequestItem(it.copy(requestId = id.toInt()))
                }
            }
            println("для сохранения набрано:Контакт - ${contacts.value.contact_name}, Юр.Лицо: ${corp.value.company_name}, Обьект: ${reqObject.value.objects_name},Req Items: Job ${_fullRequestItems.value.toString()} size - ${_fullRequestItems.value.size}")
        }
    }

    fun updateRequest(request: RequestsEntity) {
        viewModelScope.launch(Dispatchers.Default) {
            _request.value = request
        }
    }

    fun updateObject(objectsEntity: ObjectsEntity) {
        viewModelScope.launch(Dispatchers.Default) {
            _reqObject.value = objectsEntity
        }
    }

    fun updateCorp(corp: CorpEntity) {
        viewModelScope.launch(Dispatchers.Default) {
            _corp.value = corp
        }
    }

    fun updateContacts(contacts: ContactsEntity) {
        viewModelScope.launch(Dispatchers.Default) {
            _contacts.value = contacts
        }
    }
/*
    fun getFullRequestItems(
        jobs: MutableMap<CategoryEntity, List<RequestItemEntity>>,
        materials: MutableMap<CategoryEntity, List<RequestItemEntity>>
    ): MutableList<RequestItemEntity> {
        val items = mutableListOf<RequestItemEntity>()
        return items.apply {
            jobs.map {
                val rtr = it.value
                this.addAll(rtr)
            }
            materials.map {
                val rtr = it.value
                this.addAll(rtr)
            }
        }
    }*/
}
