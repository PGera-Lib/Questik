package ru.rinet.questik.ui.settings

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContentProviderCompat.requireContext
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.settings_fragment.*
import ru.rinet.questik.R
import ru.rinet.questik.ui.base.BaseFragment
import ru.rinet.questik.utils.*

class SettingsFragment : BaseFragment(R.layout.settings_fragment) {


    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        APP_ACTIVITY.title = "Страница настроек"
        initFields()
    }

    private fun initFields() {
        settings_bio.text = USER.bio
        settings_login.text = USER.username
        settings_phone_number.text = USER.phone
        settings_user_fullname.text = USER.fullname
        settings_user_status.text = USER.status
        settings_user_photo.downloadAndSetImage(USER.photoUrl)
        settings_btn_change_login.setOnClickListener { replaceFragment(ChangeUsernameFragment()) }
        settings_btn_change_bio.setOnClickListener { replaceFragment(ChangeBioFragment()) }
        setting_btn_change_photo.setOnClickListener { changeUserPhoto() }
    }

    private var launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK && result.data != null) {
                CropImage.getActivityResult(result.data)?.let { cropResult ->
                    val uri = CropImage.getActivityResult(result.data).uri
                    val path = REF_STORAGE_ROOT.child(FOLDER_PROFILE_IMAGE)
                        .child(CURRENT_UID)
                    putImageToStorage(uri, path) {
                        getUrlFromStorage(path) {
                            putUrlToDatabase(it) {
                                settings_user_photo.downloadAndSetImage(it)
                                showToast("Данные обновлены")
                                USER.photoUrl = it
                            }
                        }
                    }
                }
            }
        }



    private fun changeUserPhoto() {
        val intent = CropImage
            .activity()
            .setAspectRatio(1, 1)
            .setRequestedSize(600, 600)
            .setCropShape(CropImageView.CropShape.OVAL)
            .getIntent(requireContext())
        launcher.launch(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.settings_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_menu_exit -> {
                AUTH.signOut()
                restartActivity()
            }
            R.id.settings_menu_changname -> replaceFragment(ChangeNameFragment())

        }
        return true
    }
}
