package ru.rinet.questik.ui.settings

import kotlinx.android.synthetic.main.fragment_change_username.*
import ru.rinet.questik.R
import ru.rinet.questik.ui.base.BaseChangeFragment
import ru.rinet.questik.utils.USER
import ru.rinet.questik.utils.changeUsername
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
        changeUsername(mNewUserName)
    }


}