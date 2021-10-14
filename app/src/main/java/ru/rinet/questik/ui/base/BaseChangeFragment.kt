package ru.rinet.questik.ui.base

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import ru.rinet.questik.R
import ru.rinet.questik.utils.APP_ACTIVITY
import ru.rinet.questik.utils.hideKeyBoard

open class BaseChangeFragment(layout:Int) : Fragment(layout) {

    lateinit var mRootView: View

    override fun onStart() {
        super.onStart()
        hideKeyBoard()
        setHasOptionsMenu(true)
        APP_ACTIVITY.mAppDrawer.disableDrawer()
    }

    override fun onPause() {
        super.onPause()
        hideKeyBoard()
    }
    override fun onStop() {
        super.onStop()
        hideKeyBoard()
        APP_ACTIVITY.mAppDrawer.updateHeader()
        APP_ACTIVITY.mAppDrawer.enableDrawer()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.settings_action_change, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_menu_change_aplay -> change()
        }
        return true
    }

    open fun change() {

    }
}