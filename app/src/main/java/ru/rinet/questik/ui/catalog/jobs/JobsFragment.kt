package ru.rinet.questik.ui.catalog.jobs


import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_jobs.*
import ru.rinet.questik.R
import ru.rinet.questik.ui.base.BaseFragment
import ru.rinet.questik.ui.catalog.jobs.epoxy.JobsController
import ru.rinet.questik.utils.APP_ACTIVITY


@AndroidEntryPoint
class JobsFragment : BaseFragment(R.layout.fragment_jobs) {
    private lateinit var groupLayoutManager: LinearLayoutManager
    private val viewModel: JobsFragmentViewModel by viewModels()
    private val jobsListController = JobsController()


    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        groupLayoutManager = LinearLayoutManager(APP_ACTIVITY)
        fragment_jobs_recyclerview.apply {
            this.layoutManager = groupLayoutManager
            adapter = jobsListController.adapter
        }
        viewModel.jobsLiveData.observe(this, Observer { container ->
            jobsListController.setData(container)
        })
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
            if (newText?.isNotEmpty() == true) {
                viewModel.search(newText)
            } else {
                viewModel.fetchAllData()
            }
        } catch (e: Exception) {
            println(e.message.toString())
        }
    }
}