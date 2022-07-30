package xyz.gmfatoom.questik.repo.remote.firebase

import android.net.Uri
import com.google.firebase.storage.StorageReference
import xyz.gmfatoom.questik.repo.remote.models.CommonModel
import xyz.gmfatoom.questik.repo.remote.BaseDataSource

interface firbaseStorage: BaseDataSource {
    suspend fun updatePhonesToDatabase(arrayContacts: ArrayList<CommonModel>)
    suspend fun putUrlToDatabase(url: String)
    fun putImageToStorage(uri: Uri, path: StorageReference)
    fun initUser()

}