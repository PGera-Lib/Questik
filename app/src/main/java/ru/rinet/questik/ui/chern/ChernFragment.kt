package ru.rinet.questik.ui.chern

import android.annotation.SuppressLint
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AbsListView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.airbnb.epoxy.EpoxyRecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.chern_footer_item.*
import kotlinx.android.synthetic.main.chern_fragment.*
import ru.rinet.questik.R
import ru.rinet.questik.ui.base.BaseFragment
import ru.rinet.questik.ui.chern.epoxy.ChernController

@AndroidEntryPoint
class ChernFragment : BaseFragment(R.layout.chern_fragment) {
    private val viewModel: ChernViewModel by viewModels()
    private val chernListController = ChernController()
    private lateinit var recyclerView: EpoxyRecyclerView
    var showItog: Boolean = false

    private lateinit var mSwipeRefreshLayout: SwipeRefreshLayout
    private lateinit var mLayoutManager: LinearLayoutManager
    private var mIsScrooling = false


    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        initRecyclerView()

        viewModel.chernLiveData.observe(this, Observer { container ->
            chernListController.setData(container)
            var itog1: Double = 0.0
            var itog2: Double = 0.0
            var itog3: Double = 0.0
            container.categories.map { chernPerCat ->
               chernPerCat.items.map { cm ->
                        if (cm.isChecked) {
                            if (cm.count.length > 0 && cm.price.length > 0) {
                                itog1 += (cm.count.toDouble() * cm.price.toDouble())
                                if (cm.isMaterial == true){
                                    itog3 += (cm.count.toDouble() * cm.price.toDouble())
                                }else{ itog2 += (cm.count.toDouble() * cm.price.toDouble()) }
                            }
                            Log.i(
                                "ChernFragment_UPDATE",
                                "${cm.name} - count changed to ${cm.count}  cheked is  ${cm.isChecked}  Detail view is  ${cm.isShow}  Материал? - ${cm.isMaterial}"
                            )
                        }
                     cm
                }
            }
            str_itog.text = itog1.toString()
            str_itog_rabot.text = itog2.toString()
            str_itog_material.text = itog3.toString()
        })
    }

    private fun initRecyclerView() { mSwipeRefreshLayout = chern_swipe_layout
       mLayoutManager = LinearLayoutManager(this.context)
        recyclerView = recycler_view
        recyclerView.setHasFixedSize(true)
       recyclerView.isNestedScrollingEnabled = false
        epoxy_view_stub.visibility = View.GONE
        recyclerView.layoutManager = mLayoutManager
        recyclerView.adapter = chernListController.adapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    mIsScrooling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (mIsScrooling && dy > 0 && mLayoutManager.findLastVisibleItemPosition() <= 3) {
                             updateData()
                }
            }
        })

        mSwipeRefreshLayout.setOnRefreshListener {
            updateData()
        }
    }


    private fun updateData() {
        mSwipeRefreshLayout.isRefreshing = false

        mIsScrooling = false
//                mListListeners.add(mMessagesListener)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.chern_action_menu, menu)
        val search = menu.findItem(R.id.chern_search)
        val searchView = search.actionView as SearchView
        searchView.queryHint = "Search"
        showItog = menu.findItem(R.id.app_bar_switch).isChecked

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
                if (showItog){
                    itog_layout.visibility = View.VISIBLE
                    item.isChecked = true
                    viewModel.showItogData = true
                    viewModel.showItog()
/*                    str_itog.text = viewModel.itog+" руб."
                    str_itog_rabot.text = viewModel.itogRab+" руб."
                    str_itog_material.text  = viewModel.itogMat+" руб."*/

                } else {
                    itog_layout.visibility = View.GONE
                    item.isChecked = false
                    viewModel.showItogData = false
                    viewModel.fetchAllData()
                }
                showItog = !showItog

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
                viewModel.fetchAllData()
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