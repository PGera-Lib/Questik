package ru.rinet.questik.ui.settings

import kotlinx.android.synthetic.main.fragment_change_name.*
import ru.rinet.questik.R
import ru.rinet.questik.ui.base.BaseChangeFragment
import ru.rinet.questik.utils.*

class ChangeNameFragment : BaseChangeFragment(R.layout.fragment_change_name) {

    private lateinit var name: String
    private lateinit var surename: String
    private lateinit var fullname: String

    override fun onStart() {
        super.onStart()

        APP_ACTIVITY.title = "Редактирвание"
        initFullnames()
    }


    override fun onResume() {
        super.onResume()

    }

    private fun initFullnames() {
        val fullnameList = USER.fullname.split(" ")
        if (fullnameList.size > 1) {
            settings_input_name.setText(fullnameList[0])
            settings_input_surname.setText(fullnameList[1])
        } else {
            settings_input_name.setText(fullnameList[0])
        }
    }

    override fun change() {
        name = settings_input_name.text.toString()
        surename = settings_input_surname.text.toString()

        if (name.isEmpty()) {
            showToast("Имя не может быть пустым")
        } else {
            fullname = "$name $surename"
            REF_DATABASE_ROOT
                .child(NODE_USERS)
                .child(CURRENT_UID)
                .child(CHILD_FULLNAME)
                .setValue(fullname)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        showToast("Ваши данные были обновлены")
                        USER.fullname = fullname
                        fragmentManager?.popBackStack()
                    }
                }

        }
    }

}