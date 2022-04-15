package ru.rinet.questik.ui.chern

import android.annotation.SuppressLint
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.airbnb.epoxy.EpoxyRecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.chern_footer_item.*
import kotlinx.android.synthetic.main.chern_fragment.*
import ru.rinet.questik.R
import ru.rinet.questik.ui.base.BaseFragment
import ru.rinet.questik.ui.chern.epoxy.ChernContainer
import ru.rinet.questik.ui.chern.epoxy.ChernController

@AndroidEntryPoint
class ChernFragment : BaseFragment(R.layout.chern_fragment) {
    private val viewModel: ChernViewModel by viewModels()
    private val chernListController = ChernController()
    private lateinit var recyclerView: EpoxyRecyclerView

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        initRecyclerView()

        viewModel.chernLiveData.observe(this, Observer { container ->
            chernListController.setData(container)
           updateItogData(container)
        })
    }

    private fun updateItogData(conteiner: ChernContainer) {
        var itog1: Double = 0.0
        var itog2: Double = 0.0
        var itog3: Double = 0.0
        conteiner.categories.map { chernPerCat ->
            chernPerCat.items.map { cm ->
                if (cm.isChecked) {
                    if (cm.itemCount.length > 0 && cm.itemPrice.length > 0) {
                        itog1 += (cm.itemCount.toDouble() * cm.itemPrice.toDouble())
                        if (cm.isMaterial == true){
                            itog3 += (cm.itemCount.toDouble() * cm.itemPrice.toDouble())
                        }else{ itog2 += (cm.itemCount.toDouble() * cm.itemPrice.toDouble()) }
                    }
                    Log.i(
                        "ChernFragment_UPDATE",
                        "${cm.name} - count changed to ${cm.itemCount}  cheked is  ${cm.isChecked}  Detail view is  ${cm.isShow}  Материал? - ${cm.isMaterial}"
                    )
                }
                cm
            }
        }
        str_itog.text = itog1.toString()
        str_itog_rabot.text = itog2.toString()
        str_itog_material.text = itog3.toString()
    }

    private fun initRecyclerView() {
        recyclerView = recycler_view
        recyclerView.setHasFixedSize(true)
        epoxy_view_stub.visibility = View.GONE
        recyclerView.adapter = chernListController.adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.chern_action_menu, menu)
        val search = menu.findItem(R.id.chern_search)
        val searchView = search.actionView as SearchView
        searchView.queryHint = "Search"
        menu.findItem(R.id.app_bar_switch).isCheckable = true
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
    @SuppressLint("SetTextI18n")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {

            R.id.app_bar_switch -> {
                if (!item.isChecked){
                    itog_layout.visibility = View.VISIBLE
                    item.isChecked = true
                    viewModel.showItogData = true
                //    viewModel.showItog()
                } else {
                    itog_layout.visibility = View.GONE
                    item.isChecked = false
                    viewModel.showItogData = false
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)

    }




    private fun filterSearch(newText: String?) {
        try {
            if (newText?.isNotEmpty() == true){
                viewModel.search(newText)
            } else {
                println("search is clear")
                viewModel.search("")
            }

/*            initSearchedMaterialList(newText.toString()) {

            }*/
        } catch (e: Exception) {
            println(e.message.toString())
        }
    }

    companion object {
        private const val TAG = "ChernFragment"
    }


}