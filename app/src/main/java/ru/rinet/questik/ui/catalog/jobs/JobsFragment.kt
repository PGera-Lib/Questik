package ru.rinet.questik.ui.catalog.jobs


import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_jobs.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.rinet.questik.R
import ru.rinet.questik.ui.base.BaseFragment
import ru.rinet.questik.ui.catalog.jobs.holders.JobsParent
import ru.rinet.questik.ui.catalog.jobs.paged.JobPagedListGroup
import ru.rinet.questik.ui.catalog.jobs.paged.JobsDataSourceFactory
import ru.rinet.questik.utils.APP_ACTIVITY
import ru.rinet.questik.utils.JOBS_HASHMAP

class JobsFragment : BaseFragment(R.layout.fragment_jobs) {

    //    lateinit var jobParentItem: JobsParent
//
//    lateinit var jobChildItem: JobsChild
//    private var childList = mutableListOf<JobsChild>()
//    private var parentList = mutableListOf<JobsParent>()
    private lateinit var exGrList: MutableList<ExpandableGroup>
   // private lateinit var jobsPagedListGroup : JobPagedListGroup
   private lateinit var jobsExpandableGroup: ExpandableGroup

    //    private var newExGrList = mutableListOf<ExpandableGroup>()
    private val groupAdapter = GroupAdapter<GroupieViewHolder>()
    private lateinit var groupLayoutManager: LinearLayoutManager
    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            for ((k, v) in JOBS_HASHMAP) {
                println("‐-------------------" + k.name + "‐-------------------")
                for (job in v) {
                    println("Работы: -------" + job.name)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        groupLayoutManager = LinearLayoutManager(APP_ACTIVITY)
        fragment_jobs_recyclerview.apply {
            this.layoutManager = groupLayoutManager
            adapter = groupAdapter
        }

        try {
            initGroupie {
                println("------------------------------------------------------------------------------------------------------")
/*                    groupAdapter.apply { add(jobsPagedListGroup)}*/
            }
/*            CoroutineScope(Dispatchers.IO).launch {

            }*/

        } catch (e: Exception) {
            println(e.message.toString())
        }


/*        MainScope().launch {
            withContext(Dispatchers.Default) {
            }
        }*/


/*        CoroutineScope(Dispatchers.IO).launch {
            groupAdapter.notifyDataSetChanged()
        }*/
    }

/*    private inline fun initLists(crossinline function: () -> Unit) {
        if (mJobList.isEmpty()) {
            showToast("lol")
        } else {
            childList.clear()
            parentList.clear()
            for (job in mJobList) {
                jobChildItem = JobsChild(job)
                childList.add(jobChildItem)
            }
            mCategoryList.forEach {
                jobParentItem = JobsParent(it)
                parentList.add(jobParentItem)
            }
        }
        function()
    }*/

/*    private inline fun loadData(function: () -> Unit) {
            for (job in mJobList) {
                jobChildItem = JobsChild(job)
                childList.add(jobChildItem)
            }
            for (cat in mCategoryList) {
                jobParentItem = JobsParent(cat)
                parentList.add(jobParentItem)
            }
        function()
    }*/

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.catalog_action_menu, menu)
        val search = menu.findItem(R.id.catalog_search)
        val searchView = search.actionView as SearchView
        searchView.queryHint = "Search"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //filterSearch(newText)
                return true
            }
        })
    }

    private fun filterSearch(newText: String?) {
/*        try {
            updateData(newText.toString()) {
                    groupAdapter.updateAsync(newExGrList)
            }
            println(newExGrList.size.toString())
        } catch (e: Exception) {
            println(e.message.toString())
        }*/
    }

    private fun updateData(toString: String, function: () -> Unit) {
/*        if (mJobList.isEmpty()) {
            showToast("lol")
        } else {
            for (job in mJobList) {
                jobChildItem = JobsChild(job)
                val st1 = job.name.toLowerCase(Locale.getDefault())
                val st2 = toString.toLowerCase(Locale.getDefault())
                if (st1.contains(st2)) {
                    childList.add(jobChildItem)
                }
            }
            mCategoryList.forEach {
                jobParentItem = JobsParent(it)
                parentList.add(jobParentItem)
            }
        }
        function()*/
    }

    private fun initGroupie(function: () -> Unit) {
        //val jobsPagedListGroup = JobPagedListGroup()
        JOBS_HASHMAP.forEach { (cat, list) ->
            val jobsPagedListGroup = JobPagedListGroup()
            jobsExpandableGroup = ExpandableGroup(JobsParent(cat)).apply {
             //   addAll(list.map { JobsChild(it) })
                add(jobsPagedListGroup)
            }
            val lastJobsItem = list.lastOrNull()
            groupAdapter.add(jobsExpandableGroup)
            val factory = JobsDataSourceFactory(cat, lastJobsItem?.id?.toLong() ?: 0)
            val config = PagedList.Config.Builder()
                .setInitialLoadSizeHint(15)
                .setPageSize(15)
                .build()
            LivePagedListBuilder(factory, config).build().observe(this, Observer {
                jobsPagedListGroup.submitList(it)
            })
            }
/*            jobsPagedListGroup = JobPagedListGroup(JobsParent(cat)).apply {
                addAll(list.map { JobsChild(it) })
            }*/
            //groupAdapter.addAll(list.map { JobsChild(it) })


        }

/*        CATALOG_HASHMAP.forEach { cat, list ->
            val expandableGroup = ExpandableGroup(JobsParent(cat)).apply {
                for (job in list) {
                    Log.i("INIT GROUPIE    ----   ", cat.name + " :  " + job.name)
                    add(JobsChild(job))
                }
            }
            exGrList.add(expandableGroup)
        }
*//*        groupAdapter.apply {
            for (i in mCategoryList) {
                parentList.add(JobsParent(i))
                this += ExpandableGroup(JobsParent(i)).apply {
                    for (j in mJobList) {
                        if (i.id == j.category_id) {
                            add(JobsChild(j))
                        }
                    }
                    exGrList.add(this)
                }
            }
        }*//*
        function()
        println("  ---- ")
        */
}