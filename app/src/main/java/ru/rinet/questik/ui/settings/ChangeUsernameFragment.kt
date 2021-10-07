package ru.rinet.questik.ui.settings

import kotlinx.android.synthetic.main.fragment_change_username.*

import ru.rinet.questik.R
import ru.rinet.questik.ui.base.BaseChangeFragment
import ru.rinet.questik.utils.*
import java.util.*

class ChangeUsernameFragment : BaseChangeFragment(R.layout.fragment_change_username) {

    lateinit var mNewUserName: String
    override fun onResume() {
        super.onResume()
        initUsername()

    }

    private fun initUsername() {
        if (USER.username == USER.id) {
            settings_input_username.setText("")
        } else {
            settings_input_username.setText(USER.username)
        }
    }


    override fun change() {
        mNewUserName = settings_input_username.text.toString().lowercase(Locale.getDefault())
        if (mNewUserName.isEmpty()) {
            showToast("Ваш логин не может быть пустым!!!")
        } else {
            REF_DATABASE_ROOT.child(NODE_USERNAMES)
                .addListenerForSingleValueEvent(AppValueEventListener {
                    if (it.hasChild(mNewUserName)) {
                        showToast("Такой пользователь уже есть")
                    } else {
                        changeUsername()
                    }
                })

        }
    }

    private fun changeUsername() {
        REF_DATABASE_ROOT.child(NODE_USERNAMES).child(mNewUserName).setValue(CURRENT_UID)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    updateCurentUsername()
                }
            }
    }

    private fun updateCurentUsername() {
        REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID).child(CHILD_USERNAME).setValue(mNewUserName)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    deleteOldUsername()
                    showToast("Данные обновлены")
                } else {
                    showToast(it.exception?.message.toString())
                }
            }
    }

    private fun deleteOldUsername() {
        REF_DATABASE_ROOT.child(NODE_USERNAMES).child(USER.username).removeValue()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    showToast("Данные обновлены")
                    fragmentManager?.popBackStack()
                    USER.username = mNewUserName
                } else {
                    showToast(it.exception?.message.toString())
                }

            }
    }
}