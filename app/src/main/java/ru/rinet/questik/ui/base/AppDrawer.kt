package ru.rinet.questik.ui.base

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.mikepenz.materialdrawer.holder.ImageHolder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.*
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader
import com.mikepenz.materialdrawer.util.DrawerImageLoader
import com.mikepenz.materialdrawer.util.addItems
import com.mikepenz.materialdrawer.widget.AccountHeaderView
import com.mikepenz.materialdrawer.widget.MaterialDrawerSliderView
import ru.rinet.questik.R
import ru.rinet.questik.ui.catalog.CatalogFragment
import ru.rinet.questik.ui.chat.ChatFragment
import ru.rinet.questik.ui.chat.ContactsFragment
import ru.rinet.questik.ui.chern.ChernFragment
import ru.rinet.questik.ui.help.HelpFragment
import ru.rinet.questik.ui.projects.ProjectsFragment
import ru.rinet.questik.ui.settings.SettingsFragment
import ru.rinet.questik.utils.APP_ACTIVITY
import ru.rinet.questik.utils.USER
import ru.rinet.questik.utils.downloadAndSetImage
import ru.rinet.questik.utils.replaceFragment

class AppDrawer(val mDrawer: MaterialDrawerSliderView, val mToolbar: Toolbar) {

    private lateinit var mCurrentProfile: ProfileDrawerItem
    private lateinit var mHeader: AccountHeaderView
    fun create() {
        /* Создания бокового меню */
        initLoader()
        createHeader()
        createDrawer()
    }


    fun disableDrawer() {
        APP_ACTIVITY.actionBarDrawerToggle.isDrawerIndicatorEnabled = false
        APP_ACTIVITY.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        APP_ACTIVITY.mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        APP_ACTIVITY.mToolbar.setNavigationOnClickListener {
            APP_ACTIVITY.supportFragmentManager.popBackStack()
        }
    }

    fun enableDrawer() {

        APP_ACTIVITY.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        APP_ACTIVITY.actionBarDrawerToggle.isDrawerIndicatorEnabled = true
        APP_ACTIVITY.mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        APP_ACTIVITY.mToolbar.setNavigationOnClickListener {
            APP_ACTIVITY.mDrawerLayout.openDrawer(mDrawer)
        }
    }

    private fun createDrawer() {

        val item1 = PrimaryDrawerItem().apply {
            nameRes = R.string.nav_project
            isIconTinted = true
            isSelectable = false
            iconRes = R.drawable.questik_icon_project
            identifier = 1
        }
        val item2 = PrimaryDrawerItem().apply {
            nameRes = R.string.nav_catalog;
            isIconTinted = true
            isSelectable = false
            iconRes = R.drawable.questik_icon_catalog
            identifier = 2
        }
        val item3 = PrimaryDrawerItem().apply {
            nameRes = R.string.nav_chern;
            isIconTinted = true
            isSelectable = false
            iconRes = R.drawable.questik_icon_chernovik
            identifier = 3
        }
        val item4 = DividerDrawerItem().apply {
            contentDescription = APP_ACTIVITY.getString(R.string.nav_descrition_chat); identifier =
            4
        }
        val item5 = PrimaryDrawerItem().apply {
            nameRes = R.string.nav_chat;
            isIconTinted = true
            isSelectable = false
            iconRes = R.drawable.questik_icon_chat
            identifier = 5
        }
        val item6 = SecondaryDrawerItem().apply {
            nameRes = R.string.nav_contacts;
            isIconTinted = true
            isSelectable = false
            iconRes = R.drawable.questik_icon_contacts
            identifier = 6
        }
        val item7 = DividerDrawerItem().apply {
            contentDescription = APP_ACTIVITY.getString(R.string.nav_descrition_help); identifier =
            7
        }
        val item8 = PrimaryDrawerItem().apply {
            nameRes = R.string.nav_help;
            isIconTinted = true
            isSelectable = false
            iconRes = R.drawable.questik_icon_helper
            identifier = 8
        }
        val item9 = DividerDrawerItem().apply {
            contentDescription =
                APP_ACTIVITY.getString(R.string.nav_descrition_settings); identifier = 9
        }
        val item10 = PrimaryDrawerItem().apply {
            nameRes = R.string.nav_settings;
            isIconTinted = true
            isSelectable = false
            iconRes = R.drawable.questik_icon_settings
            identifier = 10
        }

        mDrawer.apply {
       //mDrawer.accountHeader = APP_ACTIVITY.mHeader
            selectedItemPosition = -1
            addItems(
                item1, item2, item3, item4, item5, item6, item7, item8, item9, item10
            )
        }
        mDrawer.onDrawerItemClickListener = { v, drawerItem, position ->
            clickToItem(position)
            false
        }
    }
    private fun createHeader() {
        mCurrentProfile = ProfileDrawerItem().apply {
            nameText = USER.fullname
            descriptionText = USER.phone

            if (USER.photoUrl.isEmpty()) {
                iconRes = R.drawable.questik_userphoto
                identifier = 100
            }
            if (USER.photoUrl == "no_photo"){
                iconUrl = USER.photoUrl
                identifier = 100
            }

        }

        mHeader = AccountHeaderView(APP_ACTIVITY, compact = false)
            .apply {
                attachToSliderView(mDrawer) // attach to the slider
                headerBackground = ImageHolder(R.drawable.toolbar_header)
                addProfiles(
                    mCurrentProfile
                )
                /*mHeader.setBackgroundResource(R.color.colorPrimary)*/

                onAccountHeaderListener = { view, profile, current ->
                    // react to profile changes
                    false
                }
                /*          withSavedInstance(savedInstanceState)*/
            }

    }

    private fun clickToItem(position: Int) {
        when (position) {
            1 -> replaceFragment(ProjectsFragment())
            2 -> replaceFragment(CatalogFragment())
            3 -> replaceFragment(ChernFragment())
            5 -> replaceFragment(ChatFragment())
            6 -> replaceFragment(ContactsFragment())
            8 -> replaceFragment(HelpFragment())
            10 -> replaceFragment(SettingsFragment())
        }
    }

    fun updateHeader() {
        mCurrentProfile.apply {
            nameText = USER.fullname
            descriptionText = USER.phone
            identifier = 100
            if (USER.photoUrl.isEmpty()) {
                iconRes = R.drawable.questik_userphoto
            } else if (USER.photoUrl == "no_photo"){
                iconRes = R.drawable.questik_userphoto
            } else {
                iconUrl = USER.photoUrl
            }
        }
        mHeader.updateProfile(mCurrentProfile)
    }

    private fun initLoader() {
        DrawerImageLoader.init(object : AbstractDrawerImageLoader() {
            override fun set(imageView: ImageView, uri: Uri, placeholder: Drawable, tag: String?) {
                imageView.downloadAndSetImage(uri.toString())
            }
        }
        )
    }
}