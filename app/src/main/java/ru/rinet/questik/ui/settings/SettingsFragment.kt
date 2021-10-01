package ru.rinet.questik.ui.settings

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import ru.rinet.questik.R
import ru.rinet.questik.ui.base.BaseFragment

class SettingsFragment : BaseFragment(R.layout.settings_fragment) {

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.settings_action_menu, menu)
    }
}