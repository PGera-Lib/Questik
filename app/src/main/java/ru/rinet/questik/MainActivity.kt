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
import ru.rinet.questik.ui.catalog.CatalogFragment
import ru.rinet.questik.ui.chat.ChatFragment
import ru.rinet.questik.ui.chern.ChernFragment
import ru.rinet.questik.ui.help.HelpFragment
import ru.rinet.questik.ui.projects.ProjectsFragment
import ru.rinet.questik.ui.settings.SettingsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mDrawer: Drawer
    private lateinit var mHeader: AccountHeader
    private lateinit var mToolbar: Toolbar

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
        setSupportActionBar(mToolbar)
        createHeader()
        createDrawer()
        supportFragmentManager.beginTransaction().replace(R.id.data_container, ProjectsFragment())
            .commit()
    }


    private fun createDrawer() {
        mDrawer = DrawerBuilder()
            .withActivity(this)
            .withToolbar(mToolbar)
            .withActionBarDrawerToggle(true)
            .withSelectedItem(-1)
            .withAccountHeader(mHeader)
            .addDrawerItems(
                PrimaryDrawerItem().withIdentifier(100)
                    .withIconTintingEnabled(true)
                    .withName("Проекты")
                    .withSelectable(false)
                    .withIcon(R.drawable.questik_icon_project),
                PrimaryDrawerItem().withIdentifier(101)
                    .withIconTintingEnabled(true)
                    .withName("Каталог")
                    .withSelectable(false)
                    .withIcon(R.drawable.questik_icon_catalog),
                PrimaryDrawerItem().withIdentifier(102)
                    .withIconTintingEnabled(true)
                    .withName("Черновик")
                    .withSelectable(false)
                    .withIcon(R.drawable.questik_icon_chernovik),
                DividerDrawerItem(),
                PrimaryDrawerItem().withIdentifier(103)
                    .withIconTintingEnabled(true)
                    .withName("Чат")
                    .withSelectable(false)
                    .withIcon(R.drawable.questik_icon_chat),
                DividerDrawerItem(),
                PrimaryDrawerItem().withIdentifier(104)
                    .withIconTintingEnabled(true)
                    .withName("Библиотека знаний")
                    .withSelectable(false)
                    .withIcon(R.drawable.questik_icon_helper),
                DividerDrawerItem(),
                PrimaryDrawerItem().withIdentifier(105)
                    .withIconTintingEnabled(true)
                    .withName("Настройки")
                    .withSelectable(false)
                    .withIcon(R.drawable.questik_icon_settings)
            ).withOnDrawerItemClickListener(object : Drawer.OnDrawerItemClickListener {
                override fun onItemClick(
                    view: View?,
                    position: Int,
                    drawerItem: IDrawerItem<*>
                ): Boolean {
                    when (position) {
                        1 -> supportFragmentManager.beginTransaction()
                            .replace(R.id.data_container, ProjectsFragment()).commit()
                        2 -> supportFragmentManager.beginTransaction()
                            .replace(R.id.data_container, CatalogFragment()).commit()
                        3 -> supportFragmentManager.beginTransaction()
                            .replace(R.id.data_container, ChernFragment()).commit()
                        4 -> supportFragmentManager.beginTransaction()
                            .replace(R.id.data_container, ChatFragment()).commit()
                        5 -> supportFragmentManager.beginTransaction()
                            .replace(R.id.data_container, HelpFragment()).commit()
                        6 -> supportFragmentManager.beginTransaction()
                            .replace(R.id.data_container, SettingsFragment()).commit()
                    }

                    return false
                }
            })
            .build()
    }

    private fun createHeader() {
        mHeader = AccountHeaderBuilder()
            .withActivity(this)
            .withHeaderBackground(R.drawable.toolbar_header)
            .addProfiles(
                ProfileDrawerItem().withName(getString(R.string.drawer_header_user))
                    .withEmail(getString(R.string.drawer_header_phone))
            ).build()
    }

    private fun initFields() {
        mToolbar = mBinding.mainToolbar
    }
}