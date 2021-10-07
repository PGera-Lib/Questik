package ru.rinet.questik.ui.settings

import kotlinx.android.synthetic.main.fragment_change_bio.*
import ru.rinet.questik.R
import ru.rinet.questik.ui.base.BaseChangeFragment
import ru.rinet.questik.utils.*


class ChangeBioFragment : BaseChangeFragment(R.layout.fragment_change_bio) {

    override fun onResume() {
        super.onResume()
        settings_input_bio.setText(USER.bio)

    }

    override fun change() {
        super.change()
        val newBio = settings_input_bio.text.toString()
        REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID).child(CHILD_BIO).setValue(newBio)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    showToast("Данные обновлены")
                    USER.bio = newBio
                    fragmentManager?.popBackStack()
                }
            }
    }
}