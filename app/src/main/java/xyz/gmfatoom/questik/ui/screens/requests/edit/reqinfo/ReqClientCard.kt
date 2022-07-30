package xyz.gmfatoom.questik.ui.screens.requests.edit.reqinfo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.*
import xyz.gmfatoom.questik.repo.local.room.entity.*
import xyz.gmfatoom.questik.ui.screens.requests.edit.RequestEditorVM


@Composable
fun ReqClientCard(reqId: String?,
    viewModel: RequestEditorVM = viewModel()) {
  //  viewModel.currentReq = reqId?.toInt() ?:0
 /*    @Composable
    fun ReqClientCard(
        requestState: State<RequestsEntity>,
        corpState: State<CorpEntity>,
        contactsState: State<ContactsEntity>,
        reqObjectState: State<ObjectsEntity>,
        viewModel: RequestItemVM) {*/
    if(!reqId.isNullOrEmpty()) {
        viewModel.setRequestId(id = reqId?.toInt() ?: 0)
    }
    val requestState = viewModel.request.collectAsState()
    val requestIdState = viewModel.requestId.collectAsState()
    val contactsState = viewModel.contacts.collectAsState()
    val reqObjectState = viewModel.reqObject.collectAsState()
    val corpState = viewModel.corp.collectAsState()



        println(" ReqClientCard ViewModel req id is ---------- ${requestIdState.value}")
   // viewModel.fetchData()
    //viewModel.fetchData(viewModel.currentReq)
    fun updateRequest(): (RequestsEntity) -> Unit = {
        viewModel.updateRequest(it)
        }
    fun updateContacts(): (ContactsEntity) -> Unit = {
        viewModel.updateContacts(it)
    }
    fun updateCorp(): (CorpEntity) -> Unit = {
        viewModel.updateCorp(it)
    }
    fun updateObjects(): (ObjectsEntity) -> Unit = {
        viewModel.updateObject(it)
    }

   // viewModel.fetchData()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(50f)
            .padding(8.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            item {
                RequestCard(
                    requestState,
                    updateRequest()
                )
            }
            item {
                ContactCard(
                    contactsState,
                    updateContacts()
                )
            }
            item {
                CorpCard(
                    corpState,
                    updateCorp()
                )
            }
            item {
                ObjectCard(
                    reqObjectState,
                    updateObjects()
                )
            }
        }
    }
}

@Composable
fun RequestCard( request: State<RequestsEntity>,
                updateReq: (requestsEntity: RequestsEntity) -> Unit) {
    Card(/*
                backgroundColor = Color.White,*/
        /*  elevation = 4.dp,*/
        shape = RoundedCornerShape(3.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 4.dp,
                vertical = 4.dp
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row {
                Text(
                    text = "Карточка заявки: ",/*
                            color = Color.Black,*/
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(1f)
                        .padding(
                            horizontal = 4.dp,
                            vertical = 4.dp
                        )
                )
            }
            OutlinedTextField(
                value = request.value.name,
                onValueChange = { updateReq(request.value.copy(name = it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 4.dp,
                        vertical = 1.dp
                    ),
                label = { Text("Наименование/логин клиента") }
            )

       /*
            OutlinedTextField(
                value = contacts.value.contact_name,
                onValueChange = { viewModel.updateContacts(contacts.value.copy(contact_name = it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 4.dp,
                        vertical = 1.dp
                    ),
                label = { Text("ФИО Заказчика: ") }
            )
            OutlinedTextField(
                value = contacts.value.contact_phone,
                onValueChange = { viewModel.updateContacts(contacts.value.copy(contact_phone = it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 4.dp,
                        vertical = 1.dp
                    ),
                label = { Text("Телефон: ") }
            )
            OutlinedTextField(
                value =  contacts.value.contact_mail,
                onValueChange = { viewModel.updateContacts(contacts.value.copy(contact_mail = it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 4.dp,
                        vertical = 1.dp
                    ),
                label = { Text("Почта: ") }
            )*/
            Row {
                OutlinedTextField(
                    value = request.value.data_start,
                    onValueChange = {  updateReq(request.value.copy(data_start = it))  },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.CalendarToday,
                            contentDescription = "Выбрать дату",
                            modifier = Modifier.clickable(onClick = { println("Кликнуто на иконку выбора даты начала проекта") })
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(
                            horizontal = 4.dp,
                            vertical = 1.dp
                        ),
                    label = { Text("Начало: ") }
                )
                OutlinedTextField(
                    value = request.value.data_end,
                    onValueChange = { updateReq(request.value.copy(data_end = it)) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.CalendarToday,
                            contentDescription = "Выбрать дату",
                            modifier = Modifier.clickable(onClick = { println("Кликнуто на иконку выбора даты завершения проекта") })
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(
                            horizontal = 4.dp,
                            vertical = 1.dp
                        ),
                    label = { Text("Завершение: ") }
                )
            }
            Text(
                text = "Создано: ${request.value.data_create}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 4.dp,
                        vertical = 4.dp
                    )
            )
        }
    }
}


@Composable
fun CorpCard(corp: State<CorpEntity>,
                updateCorp: (corp: CorpEntity) -> Unit) {
    Card(/*
                backgroundColor = Color.White,*/
        /*  elevation = 4.dp,*/
        shape = RoundedCornerShape(3.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 4.dp,
                vertical = 4.dp
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row {
                Text(
                    text = "Карточка клиента: ",/*
                            color = Color.Black,*/
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(1f)
                        .padding(
                            horizontal = 4.dp,
                            vertical = 4.dp
                        )
                )
            }
            OutlinedTextField(
                value = corp.value.corp_login,
                onValueChange = { updateCorp(corp.value.copy(corp_login = it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 4.dp,
                        vertical = 1.dp
                    ),
                label = { Text("Идентификатор клиента в базах") }
            )
            OutlinedTextField(
                value = corp.value.company_name,
                onValueChange = { updateCorp(corp.value.copy(company_name = it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 4.dp,
                        vertical = 1.dp
                    ),
                label = { Text("Наименование клиента") }
            )
            OutlinedTextField(
                value = corp.value.company_inn,
                onValueChange = { updateCorp(corp.value.copy(company_inn = it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 4.dp,
                        vertical = 1.dp
                    ),
                label = { Text("ИНН: ") }
            )
            OutlinedTextField(
                value = corp.value.company_adress,
                onValueChange = { updateCorp(corp.value.copy(company_adress = it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 4.dp,
                        vertical = 1.dp
                    ),
                label = { Text("Юридический адресс: ") }
            )
            OutlinedTextField(
                value = corp.value.company_phone,
                onValueChange = { updateCorp(corp.value.copy(company_phone = it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 4.dp,
                        vertical = 1.dp
                    ),
                label = { Text("Телефон: ") }
            )
            OutlinedTextField(
                value = corp.value.company_mail,
                onValueChange = { updateCorp(corp.value.copy(company_mail = it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 4.dp,
                        vertical = 1.dp
                    ),
                label = { Text("Почта: ") }
            )
        }
    }
}
@Composable
fun ContactCard(contact: State<ContactsEntity>,
             updateContact: (contact: ContactsEntity) -> Unit) {


    Card(shape = RoundedCornerShape(3.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 4.dp,
                vertical = 4.dp
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row {
                Text(
                    text = "Контактное лицо: ",/*
                            color = Color.Black,*/
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(1f)
                        .padding(
                            horizontal = 4.dp,
                            vertical = 4.dp
                        )
                )
            }
            OutlinedTextField(
                value = contact.value.contact_name.toString(),
                onValueChange = { updateContact(contact.value.copy(contact_name = it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 4.dp,
                        vertical = 1.dp
                    ),
                label = { Text("Ф.И.О Контакта") }
            )
            OutlinedTextField(
                value = contact.value.contact_phone,
                onValueChange = { updateContact(contact.value.copy(contact_phone = it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 4.dp,
                        vertical = 1.dp
                    ),
                label = { Text("Phone") }
            )
            OutlinedTextField(
                value = contact.value.contact_mail,
                onValueChange = { updateContact(contact.value.copy(contact_mail = it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 4.dp,
                        vertical = 1.dp
                    ),
                label = { Text("Email") }
            )
            OutlinedTextField(
                value = contact.value.contact_description,
                onValueChange = { updateContact(contact.value.copy(contact_description = it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 4.dp,
                        vertical = 1.dp
                    ),
                label = { Text("Описание/Должность ") }
            )

        }
    }
}

@Composable
fun ObjectCard(reqObjectsEntity: State<ObjectsEntity>,
                updateObject: (reqObjectsEntity: ObjectsEntity) -> Unit) {

    Card(/*
                backgroundColor = Color.White,*/
        /*  elevation = 4.dp,*/
        shape = RoundedCornerShape(3.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 4.dp,
                vertical = 4.dp
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row {
                Text(
                    text = "Информация об обьекте: ",/*
                            color = Color.Black,*/
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(1f)
                        .padding(
                            horizontal = 4.dp,
                            vertical = 4.dp
                        )
                )
            }
            OutlinedTextField(
                value = reqObjectsEntity.value.objects_login,
                onValueChange = { updateObject(reqObjectsEntity.value.copy(objects_login = it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 4.dp,
                        vertical = 1.dp
                    ),
                label = { Text("Идентификатор обьекта") }
            )
            OutlinedTextField(
                value = reqObjectsEntity.value.objects_name,
                onValueChange = { updateObject(reqObjectsEntity.value.copy(objects_name = it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 4.dp,
                        vertical = 1.dp
                    ),
                label = { Text("Name: ") }
            )
            OutlinedTextField(
                value = reqObjectsEntity.value.objects_adress,
                onValueChange = { updateObject(reqObjectsEntity.value.copy(objects_adress = it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 4.dp,
                        vertical = 1.dp
                    ),
                label = { Text("Address: ") }
            )
            OutlinedTextField(
                value = reqObjectsEntity.value.objects_adm_contacts,
                onValueChange = { updateObject(reqObjectsEntity.value.copy(objects_adm_contacts = it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 4.dp,
                        vertical = 1.dp
                    ),
                label = { Text("Adm contacts: ") }
            )
        }
    }
}

