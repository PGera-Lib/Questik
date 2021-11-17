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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.settings_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.rinet.questik.databinding.ActivityMainBinding
import ru.rinet.questik.repo.QuestikRepository
import ru.rinet.questik.repo.local.room.entity.CategoryEntity
import ru.rinet.questik.repo.local.room.entity.JobsEntity
import ru.rinet.questik.repo.local.room.entity.MaterialEntity
import ru.rinet.questik.repo.local.room.entity.MetricsEntity
import ru.rinet.questik.ui.base.AppDrawer
import ru.rinet.questik.ui.login.LoginFragment
import ru.rinet.questik.ui.projects.ProjectsFragment
import ru.rinet.questik.utils.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    lateinit var mToolbar: Toolbar
    lateinit var mAppDrawer: AppDrawer
    lateinit var mDrawable: MaterialDrawerSliderView
    lateinit var mDrawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    @Inject
    lateinit var repository: QuestikRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        mDrawerLayout = root
        mDrawable = mBinding.slider
        APP_ACTIVITY = this

        initFirebase()
        initUser {
            mAppDrawer.updateHeader()

        }
        CoroutineScope(Dispatchers.IO).launch {
            initContacts()
            initJobHashMap {
                initMaterialCatalog {
   initDatabaseCatalog()
                }

            }

        }
        initFileSystem()
        initFields()
        initFunc()

}

    private fun initDatabaseCatalog() {
        val scope = CoroutineScope(Job() + Dispatchers.IO)
        val job = scope.launch {
            val metrics = mutableListOf<MetricsEntity>()
            val jobs = mutableListOf<JobsEntity>()
            val categories = mutableListOf<CategoryEntity>()
            val materials = mutableListOf<MaterialEntity>()
            if (repository.getMetrics().size != CATALOG_LIST_METRICS.size){
                initListMetrics {
                    CoroutineScope(Dispatchers.IO).launch {
                        println("initListMetrics ------------------------1")
                        for (m in CATALOG_LIST_METRICS) {
                            val met: MetricsEntity = MetricsEntity()
                            met.id = m.id.toInt()
                            met.name = m.name
                            metrics.add(met)
                        }
                        repository.insertAllMetrics(metrics)
                    }
                }
            }
            if (repository.getCategories().size != CATALOG_LIST_CATEGORY.size){
                initListCategories {
                    CoroutineScope(Dispatchers.IO).launch  {
                        println("initListCategories ------------------------1")
                        for (c in CATALOG_LIST_CATEGORY) {
                            val cat: CategoryEntity = CategoryEntity()
                            cat.id = c.id?.toInt()!!
                            cat.name = c.name
                            categories.add(cat)
                        }
                        repository.insertAllCategories(categories)
                    }
                }
            }
            if (repository.getJobs().size != CATALOG_LIST_JOB.size){
                initListJob {
                    CoroutineScope(Dispatchers.IO).launch {
                        println("initListJob ------------------------1")
                        for (j in CATALOG_LIST_JOB) {
                            val job: JobsEntity = JobsEntity()
                            job.id = j.id?.toInt()!!
                            job.name = j.name
                            job.price = j.price
                            job.metrics_id = j.metrics_id
                            job.category_id = j.category_id
                            job.price_inzh = j.price_inzh
                            job.price_nalog_zp = j.price_nalog_zp
                            job.price_zp = j.price_zp
                            jobs.add(job)
                        }
                        repository.installJobData(jobs)
                    }
                }
            }

            if (repository.getMaterials().size != CATALOG_LIST_MATERIAL.size){
                initMaterialCatalog {
                    CoroutineScope(Dispatchers.IO).launch {
                        println("initListJob ------------------------1")
                        for (m in CATALOG_LIST_MATERIAL) {
                            val mat: MaterialEntity = MaterialEntity()
                            mat.id = m.id?.toInt()!!
                            mat.name = m.name
                            mat.price = m.price
                            mat.metrics_id = m.metrics_id
                            mat.category_id = m.category_id
                            mat.plu = m.plu
                            materials.add(mat)
                        }
                        repository.insertAllMaterials(materials)
                    }
                }
            }
            println("initCatalog Finish ------------------------2")
        }
        job.onJoin
        println("-------------------------------------3-------------------------------------")
    }

    override fun onStart() {
    super.onStart()
    if (AUTH.currentUser != null) {
        AppStatus.updateStatus(AppStatus.ONLINE)
    }
}

override fun onStop() {
    super.onStop()
    if (AUTH.currentUser != null) {
        AppStatus.updateStatus(AppStatus.OFFLINE)
    }

}


private fun initFunc() {
    setSupportActionBar(mToolbar)
    actionBarDrawerToggle = ActionBarDrawerToggle(
        this,
        mBinding.root,
        mToolbar,
        com.mikepenz.materialdrawer.R.string.material_drawer_open,
        com.mikepenz.materialdrawer.R.string.material_drawer_close
    )
    mAppDrawer.create()
    if (AUTH.currentUser != null) {
        replaceFragment(ProjectsFragment(), false)
    } else {
        replaceFragment(LoginFragment(), false)
    }

}

fun updateUserPhoto(url: String) {
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

    if (ContextCompat.checkSelfPermission(
            APP_ACTIVITY,
            READ_CONTACTS
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        initContacts()
    }
    if (ContextCompat.checkSelfPermission(
            APP_ACTIVITY,
            READ_FILES
        ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
            APP_ACTIVITY,
            WRITE_FILES
        ) == PackageManager.PERMISSION_GRANTED
    ) {
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