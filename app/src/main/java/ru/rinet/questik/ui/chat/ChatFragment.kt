package ru.rinet.questik.ui.chat

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import ru.rinet.questik.R
import ru.rinet.questik.utils.APP_ACTIVITY
import ru.rinet.questik.utils.replaceFragment

class ChatFragment : Fragment(R.layout.chat_fragment) {

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        APP_ACTIVITY.title = "Чаты"
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.settings_action_chat_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_chat_contacts -> replaceFragment(ContactsFragment())
        }
        return true
    }

}