package xyz.gmfatoom.questik.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.provider.ContactsContract
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import xyz.gmfatoom.questik.MainActivity
import xyz.gmfatoom.questik.repo.remote.models.CommonModel
import java.text.SimpleDateFormat
import java.util.*

/**
 * ************************************************************************************************
 *  ----- Отображением всплывающих сообщений
 * ************************************************************************************************
 **/
fun showToast(message: String) {
    Toast.makeText(APP_ACTIVITY, message, Toast.LENGTH_SHORT).show()
}
/**
 * ************************************************************************************************
 *  ----- Скрытие программной клавиатуры
 * ************************************************************************************************
 **/
fun hideKeyBoard() {
    val imm: InputMethodManager =
        APP_ACTIVITY.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(APP_ACTIVITY.window.decorView.windowToken, 0)
}
/**
 * ************************************************************************************************
 *  ----- Перезапуск Активити
 * ************************************************************************************************
 **/
fun restartActivity() {
    /* Функция расширения для AppCompatActivity, позволяет запускать активити */
    val intent = Intent(APP_ACTIVITY, MainActivity::class.java)
    APP_ACTIVITY.startActivity(intent)
    APP_ACTIVITY.finish()
}
/**
 * ************************************************************************************************
 *  ----- Переход в другой фрагмент
 * ************************************************************************************************
 **/
/*fun replaceFragment(fragment: Fragment, addStack: Boolean = true) {
    *//* Функция расширения для AppCompatActivity, позволяет устанавливать фрагменты *//*
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
}*/
/**
 * ************************************************************************************************
 *  ----- Загрузка и отображение изображений
 * ************************************************************************************************
 **/
/*fun ImageView.downloadAndSetImage(url: String) {
    Picasso.get()
        .load(url)
        .fit()
        .placeholder(R.drawable.questik_userphoto)
        .into(this)

}*/
/**
 * ************************************************************************************************
 *  ----- получение изображения из фото и картинки, для аватарки
 * ************************************************************************************************
 **/


/*fun changeUserPhoto(imv: ImageView) {
    val intent = CropImage
        .activity()
        .setAspectRatio(1, 1)
        .setRequestedSize(600, 600)
        .setCropShape(CropImageView.CropShape.OVAL)
        .getIntent(APP_ACTIVITY)
    launcher.launch(intent)

}*/

/**
 * ************************************************************************************************
 *  ----- проверка и получение доступа к файловой системе
 * ************************************************************************************************
 **/
fun initFileSystem() {
    if (checkPermissions(READ_FILES) && checkPermissions(WRITE_FILES)) {
        showToast("Получен разрешение на сохранение в файловой системе")

    }
}
/**
 * ************************************************************************************************
 *  ----- получение данных контактов
 * ************************************************************************************************
 **/
@SuppressLint("Range")
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
/**
 * ************************************************************************************************
 *  ----- Получение нормального времени из таймстампа
 * ************************************************************************************************
 **/
fun String.asTime(): String {
    val time = Date(this.toLong())
    val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    return timeFormat.format(time)
}