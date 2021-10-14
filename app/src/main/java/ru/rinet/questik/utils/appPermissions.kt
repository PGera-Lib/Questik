package ru.rinet.questik.utils

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

const val PERMISSION_REQUEST = 200
const val READ_CONTACTS = Manifest.permission.READ_CONTACTS
const val READ_FILES = Manifest.permission.READ_EXTERNAL_STORAGE
const val WRITE_FILES = Manifest.permission.WRITE_EXTERNAL_STORAGE


fun checkPermissions(permission: String): Boolean {
    return if (Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(
            APP_ACTIVITY,
            permission
        ) != PackageManager.PERMISSION_GRANTED
    ) {
ActivityCompat.requestPermissions(APP_ACTIVITY, arrayOf(permission), PERMISSION_REQUEST)

        false
    } else true
}