package ru.rinet.questik.ui.catalog.jobs


import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_jobs.*
import ru.rinet.questik.R
import ru.rinet.questik.ui.base.BaseFragment
import ru.rinet.questik.utils.APP_ACTIVITY

class JobsFragment : BaseFragment(R.layout.fragment_jobs) {
    private lateinit var groupLayoutManager: LinearLayoutManager

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        groupLayoutManager = LinearLayoutManager(APP_ACTIVITY)
        fragment_jobs_recyclerview.apply {
            this.layoutManager = groupLayoutManager
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
        } catch (e: Exception) {
            println(e.message.toString())
        }
    }
}