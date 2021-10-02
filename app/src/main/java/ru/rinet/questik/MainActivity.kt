package ru.rinet.questik

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem
import ru.rinet.questik.databinding.ActivityMainBinding
import ru.rinet.questik.ui.base.AppDrawer
import ru.rinet.questik.ui.catalog.CatalogFragment
import ru.rinet.questik.ui.chat.ChatFragment
import ru.rinet.questik.ui.chern.ChernFragment
import ru.rinet.questik.ui.help.HelpFragment
import ru.rinet.questik.ui.login.ConfirmLoginFragment
import ru.rinet.questik.ui.login.LoginFragment
import ru.rinet.questik.ui.projects.ProjectsFragment
import ru.rinet.questik.ui.settings.SettingsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mToolbar: Toolbar
    private lateinit var mAppDrawer: AppDrawer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()
        initFields()
        initFunc()
    }

    private fun initFunc() {
        if (false) {
            setSupportActionBar(mToolbar)
            mAppDrawer.create()
            supportFragmentManager.beginTransaction()
                .replace(R.id.data_container, ProjectsFragment())
                .commit()
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.data_container, LoginFragment())
                .commit()
        }

    }

    private fun initFields() {
        mToolbar = mBinding.mainToolbar
        mAppDrawer = AppDrawer(this, mToolbar)
    }
}