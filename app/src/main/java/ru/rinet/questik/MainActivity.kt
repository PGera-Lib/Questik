package ru.rinet.questik

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.getSystemService
import com.theartofdev.edmodo.cropper.CropImage
import ru.rinet.questik.databinding.ActivityMainBinding
import ru.rinet.questik.models.User
import ru.rinet.questik.ui.base.AppDrawer
import ru.rinet.questik.ui.login.LoginFragment
import ru.rinet.questik.ui.projects.ProjectsFragment
import ru.rinet.questik.utils.*

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
   lateinit var mToolbar: Toolbar
   lateinit var mAppDrawer: AppDrawer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        APP_ACTIVITY = this
        initFields()
        initFunc()
    }


    private fun initFunc() {
        setSupportActionBar(mToolbar)
        mAppDrawer.create()
        if (AUTH.currentUser!=null) {
            replaceFragment(ProjectsFragment(), false)
        } else {
            replaceFragment(LoginFragment(), false)
        }

    }


    private fun initFields() {
        mToolbar = mBinding.mainToolbar
        mAppDrawer = AppDrawer(this, mToolbar)
        initFirebase()
        initUser()
    }

    private fun initUser() {
        REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID)
            .addListenerForSingleValueEvent(AppValueEventListener{
                USER = it.getValue(User::class.java) ?: User()
            })
    }

}