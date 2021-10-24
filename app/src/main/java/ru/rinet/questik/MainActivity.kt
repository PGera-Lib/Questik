package ru.rinet.questik

import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.mikepenz.materialdrawer.widget.MaterialDrawerSliderView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.settings_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.rinet.questik.databinding.ActivityMainBinding
import ru.rinet.questik.ui.base.AppDrawer
import ru.rinet.questik.ui.login.LoginFragment
import ru.rinet.questik.ui.projects.ProjectsFragment
import ru.rinet.questik.utils.*

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
   lateinit var mToolbar: Toolbar
   lateinit var mAppDrawer: AppDrawer
   lateinit var mDrawable: MaterialDrawerSliderView
   lateinit var mDrawerLayout: DrawerLayout
   lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        mDrawerLayout = root
        mDrawable = mBinding.slider
        APP_ACTIVITY = this


        initFirebase()
        initUser{
            initJobsHashMap {
                println("______________________________________FINISH_______________________________________"
                )
            }
           mAppDrawer.updateHeader()
        }
        CoroutineScope(Dispatchers.IO).launch {
            initContacts()
        }
        initFileSystem()
        initFields()
        initFunc()

    }



    override fun onStart() {
        super.onStart()
        if (AUTH.currentUser!=null) {
            AppStatus.updateStatus(AppStatus.ONLINE)
        }

    }

    override fun onStop() {
        super.onStop()
        if (AUTH.currentUser!=null) {
            AppStatus.updateStatus(AppStatus.OFFLINE)
        }

    }


    private fun initFunc() {
        setSupportActionBar(mToolbar)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, mBinding.root, mToolbar, com.mikepenz.materialdrawer.R.string.material_drawer_open, com.mikepenz.materialdrawer.R.string.material_drawer_close)
        mAppDrawer.create()
        if (AUTH.currentUser!=null) {
            replaceFragment(ProjectsFragment(), false)
        } else {
            replaceFragment(LoginFragment(), false)
        }

    }

    fun updateUserPhoto(url: String){
        mAppDrawer.updateHeader()
        settings_user_photo.downloadAndSetImage(url)
    }
    private fun initFields() {
        mToolbar = mBinding.mainToolbar
        mAppDrawer = AppDrawer(mDrawable, mToolbar)

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        actionBarDrawerToggle.onConfigurationChanged(newConfig)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        actionBarDrawerToggle.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (mBinding.root.isDrawerOpen(mBinding.slider)) {
            mBinding.root.closeDrawer(mBinding.slider)
        } else {
            super.onBackPressed()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(ContextCompat.checkSelfPermission(APP_ACTIVITY, READ_CONTACTS)==PackageManager.PERMISSION_GRANTED){
            initContacts()
        }
        if(ContextCompat.checkSelfPermission(APP_ACTIVITY, READ_FILES)==PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(APP_ACTIVITY, WRITE_FILES)==PackageManager.PERMISSION_GRANTED ){
            initFileSystem()
        }
    }

    override fun onSaveInstanceState(_outState: Bundle) {
        var outState = _outState
        //add the values which need to be saved from the drawer to the bundle
        outState = mBinding.slider.saveInstanceState(outState)

        //add the values which need to be saved from the accountHeader to the bundle
       // outState = mHeader.saveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

}