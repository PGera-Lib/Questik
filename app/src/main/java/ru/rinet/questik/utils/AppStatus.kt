package ru.rinet.questik.utils

enum class AppStatus(val status: String) {
    ONLINE("работает"),
    OFFLINE("отдыхает"),
    TYPING("тыкается");

    companion object {
        fun updateStatus(appStatus: AppStatus) {
            REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID).child(CHILD_STATUS).setValue(appStatus.status)
                .addOnSuccessListener { USER.status = appStatus.status }
                .addOnFailureListener{ showToast(it.message.toString())}
        }
    }
}