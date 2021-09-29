package ru.rinet.questik.ui.base

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem
import ru.rinet.questik.R
import ru.rinet.questik.ui.catalog.CatalogFragment
import ru.rinet.questik.ui.chat.ChatFragment
import ru.rinet.questik.ui.chern.ChernFragment
import ru.rinet.questik.ui.help.HelpFragment
import ru.rinet.questik.ui.projects.ProjectsFragment
import ru.rinet.questik.ui.settings.SettingsFragment

class AppDrawer(val mainActivity: AppCompatActivity, val toolbar: Toolbar) {
    private lateinit var mDrawer: Drawer
    private lateinit var mHeader: AccountHeader
    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var mCurrentProfile: ProfileDrawerItem

    fun create() {
        /* Создания бокового меню */
        initLoader()
        createHeader()
        createDrawer()
        mDrawerLayout = mDrawer.drawerLayout
    }

    fun disableDrawer() {
        /* Отключение выдвигающего меню */
/*        mDrawer.actionBarDrawerToggle?.isDrawerIndicatorEnabled = false
        APP_ACTIVITY.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        APP_ACTIVITY.mToolbar.setNavigationOnClickListener {
            APP_ACTIVITY.supportFragmentManager.popBackStack()
        }*/
    }

    fun enableDrawer() {
        /* Включение выдвигающего меню */
/*        APP_ACTIVITY.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        mDrawer.actionBarDrawerToggle?.isDrawerIndicatorEnabled = true
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        APP_ACTIVITY.mToolbar.setNavigationOnClickListener {
            mDrawer.openDrawer()
        }*/
    }

    private fun createDrawer() {
        mDrawer = DrawerBuilder()
            .withActivity(mainActivity)
            .withToolbar(toolbar)
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
                    .withIcon(R.drawable.questik_icon_settings),
                DividerDrawerItem(),
            ).withOnDrawerItemClickListener(object : Drawer.OnDrawerItemClickListener {
                override fun onItemClick(
                    view: View?,
                    position: Int,
                    drawerItem: IDrawerItem<*>
                ): Boolean {
                    when (position) {
                        1 -> mainActivity.supportFragmentManager.beginTransaction()
                            .replace(R.id.data_container, ProjectsFragment()).commit()
                        2 -> mainActivity.supportFragmentManager.beginTransaction()
                            .replace(R.id.data_container, CatalogFragment()).commit()
                        3 -> mainActivity.supportFragmentManager.beginTransaction()
                            .replace(R.id.data_container, ChernFragment()).commit()
                        5 -> mainActivity.supportFragmentManager.beginTransaction()
                            .replace(R.id.data_container, ChatFragment()).commit()
                        7 -> mainActivity.supportFragmentManager.beginTransaction()
                            .replace(R.id.data_container, HelpFragment()).commit()
                        9 -> mainActivity.supportFragmentManager.beginTransaction()
                            .replace(R.id.data_container, SettingsFragment()).commit()
                    }

                    return false
                }
            })
            .build()
    }

    private fun createHeader() {
        mHeader = AccountHeaderBuilder()
            .withActivity(mainActivity)
            .withHeaderBackground(R.drawable.toolbar_header)
            .addProfiles(
                ProfileDrawerItem().withName(R.string.drawer_header_user)
                    .withEmail(R.string.drawer_header_phone)
            ).build()
    }

    private fun clickToItem(position: Int) {
/*        when (position) {
            1 -> replaceFragment(AddContactsFragment())
            7 -> replaceFragment(SettingsFragment())
            4 -> replaceFragment(ContactsFragment())
        }*/
    }


    fun updateHeader() {
        /* Обновления хедера */
/*
        mCurrentProfile
            .withName(USER.fullname)
            .withEmail(USER.phone)
            .withIcon(USER.photoUrl)

        mHeader.updateProfile(mCurrentProfile)
*/

    }

    private fun initLoader() {
        /* Инициализация лоадера для загрузки картинок в хедер */
/*        DrawerImageLoader.init(object :AbstractDrawerImageLoader(){
            override fun set(imageView: ImageView, uri: Uri, placeholder: Drawable) {
                imageView.downloadAndSetImage(uri.toString())
            }
        })*/
    }
}