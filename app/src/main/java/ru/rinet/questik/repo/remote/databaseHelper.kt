package ru.rinet.questik.utils

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import ru.rinet.questik.models.*

lateinit var JOBS_LIST: List<JobsModel>
lateinit var CATEGORY_LIST: List<CategoryModel>
lateinit var AUTH: FirebaseAuth
lateinit var CURRENT_UID: String
lateinit var REF_DATABASE_ROOT: DatabaseReference
lateinit var REF_STORAGE_ROOT: StorageReference
lateinit var USER: UserModel



lateinit var CATALOG_HASHMAP: HashMap<CategoryModel, List<JobsModel>>


const val TYPE_TEXT = "text"

const val NODE_USERS = "users"
const val NODE_USERNAMES = "usernames"
const val NODE_PHONES = "phones"
const val NODE_PHONE_CONTACTS = "phone_contacts"
const val NODE_MESSAGES = "messages"

const val NODE_JOBS = "jobs"
const val NODE_MATERIALS = "material"
const val NODE_CATEGORY = "category"
const val NODE_METRICS = "metrics"

const val FOLDER_PROFILE_IMAGE = "profile_image"



const val CHILD_ID = "id"
const val CHILD_PHONE = "phone"

const val CHILD_USERNAME = "username"
const val CHILD_FULLNAME = "fullname"
const val CHILD_BIO = "bio"
const val CHILD_USER_PHOTO = "photoUrl"
const val CHILD_STATUS = "status"

const val CHILD_TEXT = "text"
const val CHILD_TYPE = "type"
const val CHILD_FROM = "from"
const val CHILD_TIMESTAMP = "timestamp"

/**
 * NODE_JOBS
 */

const val CHILD_NAME = "name"
const val CHILD_CATEGORY_ID = "category_id"
const val CHILD_METRICS_ID = "metrics_id"
const val CHILD_PRICE = "price"
const val CHILD_PRICE_INZH = "price_inzh"
const val CHILD_PRICE_NALOG_ZP = "price_nalog_zp"
const val CHILD_PRICE_ZP = "price_zp"

/**
 * NODE_MATERIALS
 */

const val CHILD_PLU = "plu"

fun updatePhonesToDatabase(arrayContacts: ArrayList<CommonModel>) {
    if (AUTH.currentUser != null) {
        REF_DATABASE_ROOT.child(NODE_PHONES)
            .addListenerForSingleValueEvent(AppValueEventListener {
                it.children.forEach { snapshot ->
                    arrayContacts.forEach { contact ->
                        if (snapshot.key == contact.phone) {
                            REF_DATABASE_ROOT.child(NODE_PHONE_CONTACTS).child(CURRENT_UID)
                                .child(snapshot.value.toString()).child(CHILD_ID)
                                .setValue(snapshot.value.toString())
                                .addOnFailureListener { exception -> showToast(exception.message.toString()) }

                            REF_DATABASE_ROOT.child(NODE_PHONE_CONTACTS).child(CURRENT_UID)
                                .child(snapshot.value.toString()).child(CHILD_FULLNAME)
                                .setValue(contact.fullname)
                                .addOnFailureListener { exception -> showToast(exception.message.toString()) }
                        }
                    }
                }
            })
    }
}

fun initFirebase() {
    AUTH = FirebaseAuth.getInstance()
    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
    REF_STORAGE_ROOT = FirebaseStorage.getInstance().reference
    USER = UserModel()
    CURRENT_UID = AUTH.currentUser?.uid.toString()

}

inline fun putUrlToDatabase(url: String, crossinline function: () -> Unit) {
    REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID).child(
        CHILD_USER_PHOTO
    ).setValue(url).addOnSuccessListener { function() }
        .addOnFailureListener {
            showToast(it.message.toString())
        }
}

inline fun getUrlFromStorage(path: StorageReference, crossinline function: (url: String) -> Unit) {
    path.downloadUrl.addOnSuccessListener { function(it.toString()) }
        .addOnFailureListener { showToast(it.message.toString()) }
}

inline fun putImageToStorage(uri: Uri, path: StorageReference, crossinline function: () -> Unit) {
    path.putFile(uri).addOnSuccessListener { function() }
        .addOnFailureListener { showToast(it.message.toString()) }
}

inline fun initUser(crossinline function: () -> Unit) {
    if (AUTH.currentUser != null) {
        REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID)
            .addListenerForSingleValueEvent(AppValueEventListener {
                USER = it.getValue(UserModel::class.java) ?: UserModel()
                if (USER.username.isEmpty()) {
                    REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID).child(CHILD_USERNAME)
                        .setValue(
                            CURRENT_UID
                        ).addOnSuccessListener {
                            USER.username = CURRENT_UID
                        }.addOnFailureListener { showToast(it.message.toString()) }

                }
                if (USER.photoUrl.isEmpty()) {
                    REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID).child(CHILD_USER_PHOTO)
                        .setValue(
                            "no_photo"
                        ).addOnSuccessListener {
                            USER.photoUrl = "no_photo"
                        }.addOnFailureListener { showToast(it.message.toString()) }

                }
                function()
            })
    }
}
fun initCatalogHashMap() {
    if (AUTH.currentUser != null) {
        var cat = CategoryModel()
        val list = mutableListOf<JobsModel>()
        CATALOG_HASHMAP.apply {
            REF_DATABASE_ROOT.child(NODE_JOBS)
                .addChildEventListener(AppChildEventListener { it1 ->
                    val mJob = it1.getJobsModel()

                    REF_DATABASE_ROOT.child(NODE_CATEGORY)
                        .addChildEventListener(AppChildEventListener { it2 ->
                            val mCategory = it2.getCategoryModel()
                           if (mJob.category_id == mCategory.id) {
                               cat = mCategory
                               list.add(mJob)
                           }
                        })
                })
            put(cat, list)
        }
    }




}
fun DataSnapshot.getCommonModel(): CommonModel =
    this.getValue(CommonModel::class.java) ?: CommonModel()

fun DataSnapshot.getUserModel(): UserModel = this.getValue(UserModel::class.java) ?: UserModel()
fun DataSnapshot.getCategoryModel(): CategoryModel = this.getValue(CategoryModel::class.java) ?: CategoryModel()
fun DataSnapshot.getJobsModel(): JobsModel = this.getValue(JobsModel::class.java) ?: JobsModel()
fun DataSnapshot.getMetricsModel(): MetricsModel =
    this.getValue(MetricsModel::class.java) ?: MetricsModel()

fun sendMessage(message: String, receivingUserId: String, typeText: String, function: () -> Unit) {
    val refDialogUser = "$NODE_MESSAGES/$CURRENT_UID/$receivingUserId"
    val refDialogReceivningUser = "$NODE_MESSAGES/$receivingUserId/$CURRENT_UID"
    val messageKey = REF_DATABASE_ROOT.child(refDialogUser).push().key
    val mapMessages = hashMapOf<String, Any>()
    mapMessages[CHILD_FROM] = CURRENT_UID
    mapMessages[CHILD_TYPE] = typeText
    mapMessages[CHILD_TEXT] = message
    mapMessages[CHILD_ID] = messageKey.toString()
    mapMessages[CHILD_TIMESTAMP] = ServerValue.TIMESTAMP

    val mapDialog = hashMapOf<String, Any>()
    mapDialog["$refDialogUser/$messageKey"] = mapMessages
    mapDialog["$refDialogReceivningUser/$messageKey"] = mapMessages

    REF_DATABASE_ROOT
        .updateChildren(mapDialog)
        .addOnCompleteListener { function() }
        .addOnFailureListener { showToast(it.message.toString()) }

}

fun changeUsername(mNewUserName: String) {
    if (mNewUserName.isEmpty()) {
        showToast("Ваш логин не может быть пустым!!!")
    } else {
        REF_DATABASE_ROOT.child(NODE_USERNAMES)
            .addListenerForSingleValueEvent(AppValueEventListener {
                if (it.hasChild(mNewUserName)) {
                    showToast("Такой пользователь уже есть")
                } else {
                    REF_DATABASE_ROOT.child(NODE_USERNAMES).child(mNewUserName)
                        .setValue(CURRENT_UID)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                updateCurentUsername(mNewUserName)
                            }
                        }
                        .addOnFailureListener { exception -> showToast(exception.message.toString()) }
                }
            })

    }

}

fun updateCurentUsername(mNewUserName: String) {
    REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID).child(CHILD_USERNAME)
        .setValue(mNewUserName)
        .addOnCompleteListener {
            if (it.isSuccessful) {
                deleteOldUsername(mNewUserName)
                showToast("Данные обновлены")
            } else {
                showToast(it.exception?.message.toString())
            }
        }
        .addOnFailureListener { exception -> showToast(exception.message.toString()) }
}

fun deleteOldUsername(mNewUserName: String) {
    REF_DATABASE_ROOT.child(NODE_USERNAMES).child(USER.username).removeValue()
        .addOnCompleteListener {
            if (it.isSuccessful) {
                showToast("Данные обновлены")
                APP_ACTIVITY.fragmentManager?.popBackStack()
                USER.username = mNewUserName
            } else {
                showToast(it.exception?.message.toString())
            }

        }
        .addOnFailureListener { exception -> showToast(exception.message.toString()) }
}

fun changeBio(newBio: String) {
    REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID).child(CHILD_BIO).setValue(newBio)
        .addOnCompleteListener {
            if (it.isSuccessful) {
                showToast("Данные обновлены")
                USER.bio = newBio
                APP_ACTIVITY.fragmentManager?.popBackStack()
            }
        }
        .addOnFailureListener { exception -> showToast(exception.message.toString()) }
}

fun changeFullname(name: String, surename: String) {
    if (name.isEmpty()) {
        showToast("Имя не может быть пустым")
    } else {
        val mFullName = "$name $surename"
        REF_DATABASE_ROOT
            .child(NODE_USERS)
            .child(CURRENT_UID)
            .child(CHILD_FULLNAME)
            .setValue(mFullName)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    showToast("Ваши данные были обновлены")
                    USER.fullname = mFullName
                    APP_ACTIVITY.fragmentManager?.popBackStack()
                }
            }
            .addOnFailureListener { exception -> showToast(exception.message.toString()) }

    }
}