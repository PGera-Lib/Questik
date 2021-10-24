package ru.rinet.questik.ui.catalog.jobs


import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DatabaseReference
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_jobs.*
import ru.rinet.questik.R
import ru.rinet.questik.models.CategoryModel
import ru.rinet.questik.models.JobsModel
import ru.rinet.questik.ui.base.BaseFragment
import ru.rinet.questik.utils.*

class JobsFragment : BaseFragment(R.layout.fragment_jobs) {

    private lateinit var mJob: JobsModel
    private lateinit var mCategory: CategoryModel

    private lateinit var mRefJobs: DatabaseReference
    private lateinit var mListenerJobs: AppChildEventListener
    private lateinit var mListenerCategory: AppChildEventListener
    private lateinit var mRefCategory: DatabaseReference
    private lateinit var mJobsListMap: HashMap<CategoryModel, List<JobsModel>>
    private var mJobList = mutableListOf<JobsModel>()
    private var mCategoryList = mutableListOf<CategoryModel>()


    private val groupAdapter = GroupAdapter<GroupieViewHolder>()
    private lateinit var groupLayoutManager: LinearLayoutManager
    override fun onStart() {
        super.onStart()
    }


    override fun onResume() {
        super.onResume()
        fragment_jobs_recyclerview.apply {
            layoutManager = groupLayoutManager
            adapter = groupAdapter
        }
/*        groupAdapter.apply {
            for (i in parentList){
                this += ExpandableGroup(i).apply {
                    for (j  in childList) {
                        add(j)
                    }
                }

            }
        }*/
    }
}