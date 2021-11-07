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
import ru.rinet.questik.utils.JOBS_SEARCHED_MAP
import ru.rinet.questik.utils.initSearchedMap

class JobsFragment : BaseFragment(R.layout.fragment_jobs) {

    private lateinit var jobsExpandableGroup: ExpandableGroup
    private lateinit var pagedGroupsList: MutableList<ExpandableGroup>

    private val groupAdapter = GroupAdapter<GroupieViewHolder>()
    private lateinit var groupLayoutManager: LinearLayoutManager
    override fun onStart() {
        super.onStart()
/*        CoroutineScope(Dispatchers.IO).launch {
            for ((k, v) in JOBS_HASHMAP) {
                println("‐-------------------" + k.name + "‐-------------------")
                for (job in v) {
                    println("Работы: -------" + job.name)
                }
            }
        }*/
    }

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        pagedGroupsList = mutableListOf()
        groupLayoutManager = LinearLayoutManager(APP_ACTIVITY)
        fragment_jobs_recyclerview.apply {
            this.layoutManager = groupLayoutManager
            adapter = groupAdapter
        }
        try {
            initGroupie {
                println("------------------------------------------------------------------------------------------------------")
            }
        } catch (e: Exception) {
            println(e.message.toString())
        }
    }


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
                filterSearch(newText)
                return true
            }
        })
    }

    private fun filterSearch(newText: String?) {

        try {
                initSearchedMap(newText.toString()) {
                    updateData()
                    CoroutineScope(Dispatchers.IO).launch {
                        for ((k, v) in JOBS_SEARCHED_MAP) {
                            println("‐-------------------" + k.name + "‐-------------------")
                            for (job in v) {
                                println("Работы: -------" + job.name)
                            }
                        }
                    }
                }
        } catch (e: Exception) {
            println(e.message.toString())
        }
    }


    private fun updateData() {
       pagedGroupsList.clear()
        pagedGroupsList.apply {
            for ((cat, list) in JOBS_SEARCHED_MAP) {
                val jobsPagedListGroup = JobPagedListGroup()
                jobsExpandableGroup = ExpandableGroup(JobsParent(cat)).apply {
                    //   addAll(list.map { JobsChild(it) })
                    add(jobsPagedListGroup)
                }
                add(jobsExpandableGroup)
                val lastJobsItem = list.lastOrNull()
                val factory = JobsDataSourceFactory(cat, lastJobsItem?.id?.toLong() ?: 0)
                val config = PagedList.Config.Builder()
                    .setInitialLoadSizeHint(15)
                    .setPageSize(15)
                    .build()
                LivePagedListBuilder(factory, config).build().observe(APP_ACTIVITY, Observer {
                    jobsPagedListGroup.submitList(it)
                })
            }
        }

        groupAdapter.updateAsync(pagedGroupsList)
     //  groupAdapter.onDataSetInvalidated()
    }

    private fun initGroupie(function: () -> Unit) {
        JOBS_HASHMAP.forEach { (cat, list) ->
            val jobsPagedListGroup = JobPagedListGroup()
            jobsExpandableGroup = ExpandableGroup(JobsParent(cat)).apply {
                //   addAll(list.map { JobsChild(it) })
                add(jobsPagedListGroup)
            }
            pagedGroupsList.add(jobsExpandableGroup)
            val lastJobsItem = list.lastOrNull()
            groupAdapter.add(jobsExpandableGroup)
            val factory = JobsDataSourceFactory(cat, lastJobsItem?.id?.toLong() ?: 0)
            val config = PagedList.Config.Builder()
                .setInitialLoadSizeHint(15)
                .setPageSize(15)
                .build()
            LivePagedListBuilder(factory, config).build().observe(APP_ACTIVITY, Observer {
                jobsPagedListGroup.submitList(it)
            })
        }
        function()
    }
}