package xyz.gmfatoom.questik.ui.screens.requests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import xyz.gmfatoom.questik.repo.QuestikRepository
import xyz.gmfatoom.questik.repo.local.room.entity.*
import javax.inject.Inject

@HiltViewModel
class RequestScreeenVM @Inject constructor(
    private val repository: QuestikRepository
) : ViewModel() {

    private val _requestsListState = MutableStateFlow(listOf<RequestsEntity>())
    val requestsListState: StateFlow<List<RequestsEntity>> get() = _requestsListState

    init {
        viewModelScope.launch(Dispatchers.Default) {
            _requestsListState.value = repository.getRequests() as MutableList<RequestsEntity>
        }
    }
    fun refreshRequestList (){
        viewModelScope.launch(Dispatchers.Default) {
            val items = repository.getRequests()
            _requestsListState.value =  items
        }
    }


     fun getRequestObject(objectId: Int) :ObjectsEntity {
        return repository.getObjectsById(objectId)
    }
    fun getRequestContacts(id: Int) :ContactsEntity {

        return repository.getContactsById(id)
    }

    fun getRequestCorp(id: Int) :CorpEntity {
         return repository.getCorpById(id)
    }
}