package ru.rinet.questik.repo.remote.firebase

import android.net.Uri
import com.google.firebase.storage.StorageReference
import ru.rinet.questik.models.CommonModel
import ru.rinet.questik.repo.remote.BaseDataSource

interface firbaseStorage: BaseDataSource {
    suspend fun updatePhonesToDatabase(arrayContacts: ArrayList<CommonModel>)
    suspend fun putUrlToDatabase(url: String)
    fun putImageToStorage(uri: Uri, path: StorageReference)
    fun initUser()

}