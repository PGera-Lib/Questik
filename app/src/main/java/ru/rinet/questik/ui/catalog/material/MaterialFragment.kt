package ru.rinet.questik.ui.catalog.material



import android.widget.AbsListView

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.fragment_material.*

import ru.rinet.questik.R
import ru.rinet.questik.models.MaterialModel
import ru.rinet.questik.ui.base.BaseFragment
import ru.rinet.questik.utils.*

class MaterialFragment : BaseFragment(R.layout.fragment_material) {

    private lateinit var mAdapter: MaterialFragmentAdapter
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mMaterialCashList: MutableList<MaterialModel>
    private var mCountMaterials = 15
    private var mIsScrooling = false
    private var mSmoothScrollToPosition = true
    private lateinit var mRefMaterials: DatabaseReference
    private lateinit var mMaterialsListener: AppChildEventListener

    private lateinit var mSwipeRefreshLayout: SwipeRefreshLayout
    private lateinit var mLayoutManager: LinearLayoutManager


    override fun onResume() {
        super.onResume()
        println("------------------------------------onResume--------------------------------------------")
            initMaterialItem()
        initRecyclerView()

    }

    override fun onPause() {
        super.onPause()
        mRefMaterials.removeEventListener(mMaterialsListener)
    }

    private fun initMaterialItem() {
        mRefMaterials = REF_DATABASE_ROOT.child(NODE_MATERIALS)
        mMaterialsListener = AppChildEventListener {snap1 ->
                val material = snap1.getMatModel()
                if (mSmoothScrollToPosition) {
                    mAdapter.addItemToBottom(material) {
                        mRecyclerView.smoothScrollToPosition(mAdapter.itemCount)
                    }
                } else {
                    mAdapter.addItemToBottom(material) {
                        mSwipeRefreshLayout.isRefreshing = false
                    }

            }
            println("------------------------------------mMessagesListener--------------------------------------------")
        }
        mRefMaterials.limitToLast(mCountMaterials).addChildEventListener(mMaterialsListener)
    }

    private fun initRecyclerView() {
        println("------------------------------------initRecyclerView--------------------------------------------")
        mSwipeRefreshLayout = material_swipe_layout
        mLayoutManager = LinearLayoutManager(this.context)
        mRecyclerView = fragment_material_recyclerview
        mAdapter = MaterialFragmentAdapter()

        mRecyclerView.adapter = mAdapter
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.isNestedScrollingEnabled = false
        mRecyclerView.layoutManager = mLayoutManager
        if(CATALOG_LIST_MATERIAL.isNotEmpty()){
            mMaterialCashList = CATALOG_LIST_MATERIAL.subList(0, mCountMaterials)
            if (mSmoothScrollToPosition) {
                for (m in mMaterialCashList) {
                    mAdapter.addItemToBottom(m) {
                        mRecyclerView.smoothScrollToPosition(mAdapter.itemCount)
                    }
                }

            } else {
                for (m in mMaterialCashList) {
                    mAdapter.addItemToBottom(m) {
                        mSwipeRefreshLayout.isRefreshing = false
                    }
                }
            }
        }
   //
//        mListListeners.add(mMessagesListener)

        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    mIsScrooling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (mIsScrooling && dy > 0 && mLayoutManager.findLastCompletelyVisibleItemPosition() >= 3) {
                 updateData()
                }
            }
        })

        mSwipeRefreshLayout.setOnRefreshListener {
            updateData()
        }
    }

    private fun updateData() {
        mSmoothScrollToPosition = false
        mIsScrooling = false
        mCountMaterials += 10
        mRefMaterials.removeEventListener(mMaterialsListener)
        mRefMaterials.limitToLast(mCountMaterials).addChildEventListener(mMaterialsListener)
    }

}