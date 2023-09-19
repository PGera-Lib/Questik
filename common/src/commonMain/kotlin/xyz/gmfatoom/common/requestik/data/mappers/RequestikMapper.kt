package xyz.gmfatoom.common.requestik.data.mappers

import database.RequestEntity
import xyz.gmfatoom.common.requestik.domain.model.ContactsModel
import xyz.gmfatoom.common.requestik.domain.model.CorpModel
import xyz.gmfatoom.common.requestik.domain.model.ObjectsModel
import xyz.gmfatoom.common.requestik.domain.model.RequestItemModel
import xyz.gmfatoom.common.requestik.domain.model.RequestModel
import xyz.gmfatoom.common.requestik.domain.model.UsersModel


suspend fun RequestEntity.toRequestMoodel(
    corpModel: CorpModel?,
    objectsModel: ObjectsModel?,
    contactList: List<ContactsModel>?,
    userCreator: UsersModel?,
    userCurrent: UsersModel?,
    requestItems: List<RequestItemModel>?
): RequestModel {
    return RequestModel(
        id = id,
        name = name,
        corp = corpModel,
        object_request = objectsModel,
        data_create = dataCreate,
        data_start = dataStart,
        data_end = dataEnd,
        request_description = description,
        contactsList = contactList,
        user_creator = userCreator,
        user_current = userCurrent,
        requestItems = requestItems,

        )
}




/*

import xyz.gmfatoom.common.request.domain.model.CategoryModel


class RequestikMapper {
    */
/**
 * Category Mapper
 *//*



    fun categoryEntityToCategoryModel(item: CategoryEntity) = CategoryModel(
        id = item.id, name = item.name
    )

    fun categoryModelToCategoryEntity(item: CategoryModel) = CategoryEntity(
        id = item.id, name = item.name
    )

    fun categoryDtoToCategoryEntity(item: CategoryDto) = CategoryEntity(
        id = item.id.toLong(), name = item.name
    )
    fun categoryDtoToCategoryModel(item: CategoryDto) = CategoryModel(
        id = item.id.toLong(), name = item.name
    )
*//*
    fun categoriesDtoListToCategoriesModelList(list: List<CategoryDto>) = list.map {
        categoryDtoToCategoryModel(it)
    }
    fun categoriesDtoListToCategoriesEntityList(list: List<CategoryDto>) = list.map {
        categoryDtoToCategoryEntity(it)
    }

    fun categoriesModelListToCategoriesEntityList(list: List<CategoryModel>) = list.map {
        categoryModelToCategoryEntity(it)
    }

    fun categoriesEntityListToCategoriesModelList(list: List<CategoryEntity>) = list.map {
        categoryEntityToCategoryModel(it)
    }
*//*


    */
/**
 * Contacts Mapper
 *//*

    fun contactsEntityToContactsModel(item: ContactsEntity) = ContactsModel(
        contact_id = item.contact_id,
        contact_name = item.contact_name,
        contact_phone = item.contact_phone,
        contact_mail = item.contact_mail,
        company_id = item.company_id,
        object_id = item.object_id,
        contact_description = item.contact_description
    )

    fun contactsModelToContactsEntity(item: ContactsModel) = ContactsEntity(
        contact_id = item.contact_id,
        contact_name = item.contact_name,
        contact_phone = item.contact_phone,
        contact_mail = item.contact_mail,
        company_id = item.company_id,
        object_id = item.object_id,
        contact_description = item.contact_description
    )

    fun contactsDtoToContactsEntity(item: ContactsDto) = ContactsEntity(
        contact_id = item.contact_id.toLong(),
        contact_name = item.contact_name,
        contact_phone = item.contact_phone,
        contact_mail = item.contact_mail,
        company_id = item.company_id.toLong(),
        object_id = item.object_id.toLong(),
        contact_description = item.contact_description
    )

*//*
    fun contactsDtoListToContactsEntityList(list: List<ContactsDto>) = list.map {
        contactsDtoToContactsEntity(it)
    }

    fun contactsModelListToContactsEntityList(list: List<ContactsModel>) = list.map {
        contactsModelToContactsEntity(it)
    }

    fun contactsEntityListToContactsModelList(list: List<ContactsEntity>) = list.map {
        contactsEntityToContactsModel(it)
    }
*//*



    */
/**
 * Corp Mapper
 *//*


    fun corpEntityToCorpModel(item: CorpEntity, contacts: List<ContactsModel>?) = CorpModel(
        corp_id = item.corp_id,
        corp_login = item.corp_login,
        company_name = item.company_name,
        company_inn = item.company_inn,
        company_adress = item.company_adress,
        company_phone = item.company_phone,
        company_mail = item.company_mail,
        isPhis = item.isPhis,
        company_contatcts = contacts ?: emptyList()
    )

    fun corpModelToCorpEntity(item: CorpModel) = CorpEntity(
        corp_id = item.corp_id,
        corp_login = item.corp_login,
        company_name = item.company_name,
        company_inn = item.company_inn,
        company_adress = item.company_adress,
        company_phone = item.company_phone,
        company_mail = item.company_mail,
        isPhis = item.isPhis
    )

    fun corpDtoToCorpEntity(item: CorpDto) = CorpEntity(
        corp_id = item.corp_id.toLong(),
        corp_login = item.corp_login,
        company_name = item.company_name,
        company_inn = item.company_inn,
        company_adress = item.company_adress,
        company_phone = item.company_phone,
        company_mail = item.company_mail,
        isPhis = item.isPhis
    )
*//*
    fun corpDtoListToCorpEntityList(items: List<CorpDto>) = items.map { corpDtoToCorpEntity(it) }
    fun corpModelListToCorpEntityList(items: List<CorpModel>) =
        items.map { corpModelToCorpEntity(it) }

    fun corpEntityListToCorpModelList(list: List<CorpEntity>) = list.map {
        corpEntityToCorpModel(it)
    }*//*


    */
/**
 * Job Mapper
 *//*


    fun jobEntityToJobModel(item: JobEntity) = JobModel(
        categoryId = item.categoryId,
        id = item.id,
        metricsId = item.metricsId,
        name = item.name,
        price = item.price,
        priceInzh = item.priceInzh,
        priceNalogZp = item.priceNalogZp,
        priceZp = item.priceZp
    )

    fun jobModelToJobEntity(item: JobModel) = JobEntity(
        categoryId = item.categoryId,
        id = item.id,
        metricsId = item.metricsId,
        name = item.name,
        price = item.price,
        priceInzh = item.priceInzh,
        priceNalogZp = item.priceNalogZp,
        priceZp = item.priceZp
    )

    fun jobDtoToJobEntity(item: JobDto) = JobEntity(
        categoryId = item.categoryId,
        id = item.id.toLong(),
        metricsId = item.metricsId,
        name = item.name,
        price = item.price,
        priceInzh = item.priceInzh,
        priceNalogZp = item.priceNalogZp,
        priceZp = item.priceZp
    )
*//*
    fun jobDtoListToJobEntityList(items: List<JobDto>) = items.map { jobDtoToJobEntity(it) }
    fun jobModelListToJobEntityList(items: List<JobModel>) = items.map { jobModelToJobEntity(it) }
    fun jobEntityListToJobModelList(list: List<JobEntity>) = list.map {
        jobEntityToJobModel(it)
    }*//*


    */
/**
 * Material Mapper
 *//*


    fun materialEntityToMaterialModel(item: MaterialEntity) = MaterialModel(
        categoryId = item.categoryId,
        id = item.id,
        metricsId = item.metricsId,
        name = item.name,
        price = item.price,
        plu = item.plu
    )

    fun materialModelToMaterialEntity(item: MaterialModel) = MaterialEntity(
        categoryId = item.categoryId,
        id = item.id,
        metricsId = item.metricsId,
        name = item.name,
        price = item.price,
        plu = item.plu
    )

    fun materialDtoToMaterialEntity(item: MaterialDto) = MaterialEntity(
        categoryId = item.categoryId,
        id = item.id.toLong(),
        metricsId = item.metricsId,
        name = item.name,
        price = item.price,
        plu = item.plu
    )

*//*    fun materialDtoListToMaterialEntityList(items: List<MaterialDto>) =
        items.map { materialDtoToMaterialEntity(it) }

    fun materialModelListToMaterialEntityList(items: List<MaterialModel>) =
        items.map { materialModelToMaterialEntity(it) }

    fun materialEntityListToMaterialModelList(list: List<MaterialEntity>) = list.map {
        materialEntityToMaterialModel(it)
    }*//*


    */
/**
 * Messages Mapper
 *//*



    fun messagesEntityToMessagesModel(item: MessagesEntity) = MessagesModel(
        from = item.from,
        id = item.id,
        text = item.text,
        timestamp = item.timestamp,
        type = item.type
    )

    fun messagesModelToMessagesEntity(item: MessagesModel) = MessagesEntity(
        from = item.from,
        id = item.id,
        text = item.text,
        timestamp = item.timestamp,
        type = item.type
    )

    fun messagesDtoToMessagesEntity(item: MessagesDto) = MessagesEntity(
        from = item.from,
        id = item.id.toLong(),
        text = item.text,
        timestamp = item.timestamp,
        type = item.type
    )

*//*
    fun messagesDtoListToMessagesEntityList(items: List<MessagesDto>) =
        items.map { messagesDtoToMessagesEntity(it) }

    fun messagesModelListToMessagesEntityList(items: List<MessagesModel>) =
        items.map { messagesModelToMessagesEntity(it) }

    fun messagesEntityListToMessagesModelList(list: List<MessagesEntity>) = list.map {
        messagesEntityToMessagesModel(it)
    }
*//*


    */
/**
 * Metric Mapper
 *//*


    fun metricEntityToMetricModel(item: MetricEntity) = MetricModel(
        id = item.id, name = item.name
    )

    fun metricModelToMetricEntity(item: MetricModel) = MetricEntity(
        id = item.id, name = item.name
    )

    fun metricDtoToMetricEntity(item: MetricDto) = MetricEntity(
        id = item.id.toLong(), name = item.name
    )
*//*

    fun metricsDtoListToMetricsEntityList(list: List<MetricDto>) = list.map {
        metricDtoToMetricEntity(it)
    }

    fun metricsModelListToMetricsEntityList(list: List<MetricModel>) = list.map {
        metricModelToMetricEntity(it)
    }

    fun metricsEntityListToMetricsModelList(list: List<MetricEntity>) = list.map {
        metricEntityToMetricModel(it)
    }
*//*


    */
/**
 * Objects Mapper
 *//*



    fun objectsEntityToObjectsModel(item: ObjectsEntity, contacts: List<ContactsModel>) = ObjectsModel(
        objects_id = item.objects_id,
        objects_login = item.objects_login,
        objects_name = item.objects_name,
        objects_adress = item.objects_adress,
        objects_adm_phone = item.objects_adm_contacts,
        objects_contatcts = contacts
    )

    fun objectsModelToObjectsEntity(item: ObjectsModel) = ObjectsEntity(
        objects_id = item.objects_id,
        objects_login = item.objects_login,
        objects_name = item.objects_name,
        objects_adress = item.objects_adress,
        objects_adm_contacts = item.objects_adm_phone
    )

    fun objectsDtoToObjectsEntity(item: ObjectsDto) = ObjectsEntity(
        objects_id = item.objects_id.toLong(),
        objects_login = item.objects_login,
        objects_name = item.objects_name,
        objects_adress = item.objects_adress,
        objects_adm_contacts = item.objects_adm_contacts
    )

*//*    fun objectsDtoListToObjectsEntityList(items: List<ObjectsDto>) =
        items.map { objectsDtoToObjectsEntity(it) }

    fun objectsModelListToObjectsEntityList(items: List<ObjectsModel>) =
        items.map { objectsModelToObjectsEntity(it) }

    fun objectsEntityListToObjectsModelList(list: List<ObjectsEntity>) = list.map {
        objectsEntityToObjectsModel(it)
    }*//*


    */
/**
 * PhoneContacts Mapper
 *//*



    fun phoneContactsEntityToPhoneContactsModel(item: PhoneContactsEntity) = PhoneContactsModel(
        id = item.id, fullname = item.fullname
    )

    fun phoneContactsModelToPhoneContactsEntity(item: PhoneContactsModel) = PhoneContactsEntity(
        id = item.id, fullname = item.fullname
    )

    fun phoneContactsDtoToPhoneContactsEntity(item: PhoneContactsDto) = PhoneContactsEntity(
        id = item.id.toLong(), fullname = item.fullname
    )
*//*

    fun phoneContactsDtoListToPhoneContactsEntityList(items: List<PhoneContactsDto>) =
        items.map { phoneContactsDtoToPhoneContactsEntity(it) }

    fun phoneContactsModelListToPhoneContactsEntityList(items: List<PhoneContactsModel>) =
        items.map { phoneContactsModelToPhoneContactsEntity(it) }

    fun phoneContactsEntityListToPhoneContactsModelList(list: List<PhoneContactsEntity>) =
        list.map {
            phoneContactsEntityToPhoneContactsModel(it)
        }
*//*


    */
/**
 * RequestItem Mapper
 *//*


    fun requestItemEntityToRequestItemModel(item: RequestItemEntity) = RequestItemModel(
        id = item.id,
        name = item.name,
        categoryId = item.categoryId,
        requestId = item.requestId,
        jobId = item.jobId,
        materialId = item.materialId,
        metricsId = item.metricsId,
        itemCount = item.itemCount,
        itemPrice = item.itemPrice,
        plu = item.plu,
        isChecked = item.isChecked,
        isMaterial = item.isMaterial
    )

    fun requestItemModelToRequestItemEntity(item: RequestItemModel) = RequestItemEntity(
        id = item.id,
        name = item.name,
        categoryId = item.categoryId,
        requestId = item.requestId,
        jobId = item.jobId,
        materialId = item.materialId,
        metricsId = item.metricsId,
        itemCount = item.itemCount,
        itemPrice = item.itemPrice,
        plu = item.plu,
        isChecked = item.isChecked,
        isMaterial = item.isMaterial
    )

    fun requestItemDtoToRequestItemEntity(item: RequestItemDto) = RequestItemEntity(
        id = item.id.toLong(),
        name = item.name,
        categoryId = item.categoryId,
        requestId = item.requestId.toLong(),
        jobId = item.jobId,
        materialId = item.materialId,
        metricsId = item.metricsId,
        itemCount = item.itemCount,
        itemPrice = item.itemPrice,
        plu = item.plu,
        isChecked = item.isChecked,
        isMaterial = item.isMaterial
    )
*//*
    fun requestItemDtoListToRequestItemEntityList(items: List<RequestItemDto>) =
        items.map { requestItemDtoToRequestItemEntity(it) }

    fun requestItemModelListToRequestItemEntityList(items: List<RequestItemModel>) =
        items.map { requestItemModelToRequestItemEntity(it) }

    fun requestItemEntityListToRequestItemModelList(list: List<RequestItemEntity>) = list.map {
        requestItemEntityToRequestItemModel(it)
    }*//*


    */
/**
 * Requests Mapper
 *//*



    fun requestsEntityToRequestsModel(requestsEntity: RequestsEntity, userCreator: UsersModel, userCurrernt: UsersModel, contactsList: List<ContactsModel>?, corp: CorpModel, objectModel : ObjectsModel, requestItemsList: List<RequestItemModel>?  ) = RequestsModel(
        id = requestsEntity.id,
        name = requestsEntity.name,
        user_creator = userCreator,
        user_current = userCurrernt,
        contactsList = contactsList ?: emptyList(),
        corp = corp,
        object_request = objectModel,
        data_create = requestsEntity.data_create,
        data_start = requestsEntity.data_start,
        data_end = requestsEntity.data_end,
        request_description =requestsEntity.request_description,
        requestItems = requestItemsList ?: emptyList()

    )

    fun requestsModelToRequestsEntity(item: RequestsModel ) = RequestsEntity(
        id = item.id,
        name = item.name,
        user_current = item.user_current.id,
        user_creator = item.user_creator.id,
        corp_id = item.corp.corp_id,
        object_id = item.object_request.objects_id,
        data_create = item.data_create,
        data_start = item.data_start,
        data_end = item.data_end,
        request_description = item.request_description
    )

    fun requestsDtoToRequestsEntity(item: RequestsDto) = RequestsEntity(
        id = item.id,
        name = item.name,
        user_current = item.user_current,
        user_creator = item.user_creator,
        corp_id = item.corp_id,
        object_id = item.object_id,
        data_create = item.data_create,
        data_start = item.data_start,
        data_end = item.data_end,
        request_description = item.request_description
    )

*//*    fun requestsDtoListToRequestsEntityList(list: List<RequestsDto>) = list.map {
        requestsDtoToRequestsEntity(it)
    }

    fun requestsModelListToRequestsEntityList(list: List<RequestsModel>) = list.map {
        requestsModelToRequestsEntity(it)
    }

    fun requestsEntityListToRequestsModelList(list: List<RequestsEntity>) = list.map {
        requestsEntityToRequestsModel(it)
    }*//*


    */
/**
 * Users Mapper
 *//*



    fun usersEntityToUsersModel(item: UsersEntity) = UsersModel(
        employePos = item.employePos,
        fullname = item.fullname,
        id = item.id,
        phone = item.phone,
        photoUrl = item.photoUrl,
        status = item.status,
        username = item.username,
        dataBirth = item.dataBirth

    )

    fun usersModelToUsersEntity(item: UsersModel) = UsersEntity(
        employePos = item.employePos,
        fullname = item.fullname,
        id = item.id,
        phone = item.phone,
        photoUrl = item.photoUrl,
        status = item.status,
        username = item.username,
        dataBirth = item.dataBirth
    )

    fun usersDtoToUsersEntity(item: UsersDto) = UsersEntity(
        employePos = item.employePos,
        fullname = item.fullname,
        id = item.id.toLong(),
        phone = item.phone,
        photoUrl = item.photoUrl,
        status = item.status,
        username = item.username,
        dataBirth = item.dataBirth
    )

*//*    fun usersDtoListToUsersEntityList(list: List<UsersDto>) = list.map {
        usersDtoToUsersEntity(it)
    }

    fun usersModelListToUsersEntityList(list: List<UsersModel>) = list.map {
        usersModelToUsersEntity(it)
    }

    fun usersEntityListToUsersModelList(list: List<UsersEntity>) = list.map {
        usersEntityToUsersModel(it)
    }*//*

}



*/
