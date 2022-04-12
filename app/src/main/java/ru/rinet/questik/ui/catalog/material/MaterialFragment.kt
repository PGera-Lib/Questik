package ru.rinet.questik.ui.catalog.material



import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_material.*
import ru.rinet.questik.R
import ru.rinet.questik.ui.base.BaseFragment
import ru.rinet.questik.ui.catalog.material.epoxy.MaterialController
import ru.rinet.questik.utils.APP_ACTIVITY

@AndroidEntryPoint
class MaterialFragment : BaseFragment(R.layout.fragment_material) {
    private lateinit var groupLayoutManager: LinearLayoutManager
    private val viewModel : MaterialFragmentViewModel by viewModels()
    private val materialListController = MaterialController()




    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        println("------------------------------------onResume--------------------------------------------")
        groupLayoutManager = LinearLayoutManager(APP_ACTIVITY)
        fragment_material_recyclerview.apply {
            this.layoutManager = groupLayoutManager
            adapter = materialListController.adapter
        }
        viewModel.materialLiveData.observe(this, Observer { container ->
            materialListController.setData(container)
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
            println("search")
            if (newText?.isNotEmpty() == true){
                viewModel.search(newText)
            } else {
            viewModel.fetchAllData()
        }

/*            initSearchedMaterialList(newText.toString()) {

            }*/
        } catch (e: Exception) {
            println(e.message.toString())
        }
    }

}