package ru.rinet.questik.ui.settings

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import kotlinx.android.synthetic.main.settings_fragment.*
import ru.rinet.questik.R
import ru.rinet.questik.ui.base.BaseFragment
import ru.rinet.questik.utils.*

open class SettingsFragment : BaseFragment(R.layout.settings_fragment) {


    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        APP_ACTIVITY.title = "Страница настроек"
        initFields()
    }

    private fun initFields() {
        settings_bio.text = USER.bio
        if (USER.username == USER.id) {
            settings_login.text = "podovan"
        } else {
            settings_login.text = USER.username
        }
        settings_phone_number.text = USER.phone
        settings_user_fullname.text = USER.fullname
        settings_user_status.text = USER.status
        settings_user_photo.downloadAndSetImage(USER.photoUrl)
        settings_btn_change_login.setOnClickListener { replaceFragment(ChangeUsernameFragment()) }
        settings_btn_change_bio.setOnClickListener { replaceFragment(ChangeBioFragment()) }
        setting_btn_change_photo.setOnClickListener {
            changeUserPhoto(settings_user_photo)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.settings_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_menu_exit -> {
                if (AUTH.currentUser != null) {
                    AppStatus.updateStatus(AppStatus.OFFLINE)
                    AUTH.signOut()
                }
                restartActivity()


            }
            R.id.settings_menu_changname -> replaceFragment(ChangeNameFragment())

        }
        return true
    }


}
