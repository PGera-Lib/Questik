package ru.rinet.questik.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import ru.rinet.questik.MainActivity
import ru.rinet.questik.R
import java.text.SimpleDateFormat
import java.util.*

fun showToast(message: String) {
    Toast.makeText(APP_ACTIVITY, message, Toast.LENGTH_SHORT).show()
}

fun hideKeyBoard() {
    val imm: InputMethodManager =
        APP_ACTIVITY.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(APP_ACTIVITY.window.decorView.windowToken, 0)
}

fun restartActivity() {
    /* Функция расширения для AppCompatActivity, позволяет запускать активити */
    val intent = Intent(APP_ACTIVITY, MainActivity::class.java)
    APP_ACTIVITY.startActivity(intent)
    APP_ACTIVITY.finish()
}

fun replaceFragment(fragment: Fragment, addStack: Boolean = true) {
    /* Функция расширения для AppCompatActivity, позволяет устанавливать фрагменты */
    if (addStack) {
        APP_ACTIVITY.supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(
                R.id.data_container,
                fragment
            ).commit()
    } else {
        APP_ACTIVITY.supportFragmentManager.beginTransaction()
            .replace(
                R.id.data_container,
                fragment
            ).commit()
    }
}

fun ImageView.downloadAndSetImage(url: String) {
    Picasso.get()
        .load(url)
        .fit()
        .placeholder(R.drawable.questik_userphoto)
        .into(this)

}

val launcher =
    APP_ACTIVITY.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK && result.data != null) {
            CropImage.getActivityResult(result.data)?.let { cropResult ->
                val uri = CropImage.getActivityResult(result.data).uri
                val path = REF_STORAGE_ROOT.child(FOLDER_PROFILE_IMAGE)
                    .child(CURRENT_UID)
                putImageToStorage(uri, path) {
                    getUrlFromStorage(path) {
                        putUrlToDatabase(it) {
                            USER.photoUrl = it
                            showToast("Данные обновлены")
                            APP_ACTIVITY.updateUserPhoto(it)
                        }
                    }
                }
            }
        }
    }

fun changeUserPhoto(imv: ImageView) {
    val intent = CropImage
        .activity()
        .setAspectRatio(1, 1)
        .setRequestedSize(600, 600)
        .setCropShape(CropImageView.CropShape.OVAL)
        .getIntent(APP_ACTIVITY)
    launcher.launch(intent)

}

fun String.asTime(): String {
    val time = Date(this.toLong())
    val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    return timeFormat.format(time)
}