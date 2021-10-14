package ru.rinet.questik.utils

import android.net.Uri
import android.provider.ContactsContract
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import ru.rinet.questik.models.CommonModel
import ru.rinet.questik.models.UserModel

lateinit var AUTH: FirebaseAuth
lateinit var CURRENT_UID: String
lateinit var REF_DATABASE_ROOT: DatabaseReference
lateinit var REF_STORAGE_ROOT: StorageReference
lateinit var USER: UserModel

const val TYPE_TEXT = "text"

const val NODE_USERS = "users"
const val NODE_USERNAMES = "usernames"
const val NODE_PHONES = "phones"
const val NODE_PHONE_CONTACTS = "phone_contacts"
const val NODE_MESSAGES = "messages"

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


fun initFileSystem() {
    if (checkPermissions(READ_FILES) && checkPermissions(WRITE_FILES)) {
        showToast("Получен разрешение на сохранение в файловой системе")

    }
}

fun initContacts() {
    if (checkPermissions(READ_CONTACTS)) {
        val arrayContacts = arrayListOf<CommonModel>()
        val cursor = APP_ACTIVITY.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        cursor?.let {
            while (it.moveToNext()) {
                val fullName =
                    it.getString(it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                val phone =
                    it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                val newModel = CommonModel()
                newModel.fullname = fullName
                newModel.phone = phone.replace(
                    Regex("[\\s,()-]"),
                    ""
                )
                arrayContacts.add(newModel)
            }
        }
        cursor?.close()
        updatePhonesToDatabase(arrayContacts)
    }
}

fun updatePhonesToDatabase(arrayContacts: ArrayList<CommonModel>) {
    if (AUTH.currentUser != null) {
        if (AUTH.currentUser != null) {
            REF_DATABASE_ROOT.child(NODE_PHONES)
                .addListenerForSingleValueEvent(AppValueEventListener {
                    it.children.forEach { snapshot ->
                        arrayContacts.forEach { contact ->
                            if (snapshot.key == contact.phone) {
                                REF_DATABASE_ROOT.child(NODE_PHONE_CONTACTS).child(CURRENT_UID)
                                    .child(snapshot.value.toString()).child(CHILD_ID)
                                    .setValue(snapshot.value.toString())
                                    .addOnFailureListener { showToast(it.message.toString()) }

                                REF_DATABASE_ROOT.child(NODE_PHONE_CONTACTS).child(CURRENT_UID)
                                    .child(snapshot.value.toString()).child(CHILD_FULLNAME)
                                    .setValue(contact.fullname)
                                    .addOnFailureListener { showToast(it.message.toString()) }
                            }
                        }
                    }
                })
        }
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

fun DataSnapshot.getCommonModel(): CommonModel =
    this.getValue(CommonModel::class.java) ?: CommonModel()

fun DataSnapshot.getUserModel(): UserModel = this.getValue(UserModel::class.java) ?: UserModel()

fun sendMessage(message: String, receivingUserId: String, typeText: String, function: () -> Unit) {
    println("------------------------------------sendMessage--------------------------------------------")
    val refDialogUser = "$NODE_MESSAGES/$CURRENT_UID/$receivingUserId"
    val refDialogReceivningUser = "$NODE_MESSAGES/$receivingUserId/$CURRENT_UID"
    val messageKey = REF_DATABASE_ROOT.child(refDialogUser).push().key
    val mapMessages = hashMapOf<String, Any>()
    mapMessages[CHILD_FROM] = CURRENT_UID
    mapMessages[CHILD_TYPE] = typeText
    mapMessages[CHILD_TEXT] = message
    mapMessages[CHILD_TIMESTAMP] = ServerValue.TIMESTAMP

    val mapDialog = hashMapOf<String, Any>()
    mapDialog["$refDialogUser/$messageKey"] = mapMessages
    mapDialog["$refDialogReceivningUser/$messageKey"] = mapMessages

    REF_DATABASE_ROOT
        .updateChildren(mapDialog)
        .addOnFailureListener { function() }
        .addOnFailureListener { showToast(it.message.toString()) }

}