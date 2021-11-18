package ru.rinet.questik.utils

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class AppValueEventListener(val onSuccess:(DataSnapshot) -> Unit):ValueEventListener {
    override fun onDataChange(snapshot: DataSnapshot) {
    onSuccess(snapshot)
    }

    override fun onCancelled(error: DatabaseError) {
Log.i("AppValueEventListener", error.message.toString())
    }

}