package ru.rinet.questik.ui.chern

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.airbnb.epoxy.EpoxyRecyclerView
import com.airbnb.epoxy.EpoxyTouchHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.chern_fragment.*
import ru.rinet.questik.R
import ru.rinet.questik.ui.base.BaseFragment
import ru.rinet.questik.ui.chern.epoxy.ChernChildModel
import ru.rinet.questik.ui.chern.epoxy.ChernController

@AndroidEntryPoint
class ChernFragment : BaseFragment(R.layout.chern_fragment) {
    private val viewModel: ChernViewModel by viewModels()
    private val chernListController = ChernController()
    private lateinit var recyclerView: EpoxyRecyclerView

    override fun onResume() {
        super.onResume()
        recyclerView = recycler_view
        epoxy_view_stub.visibility = View.GONE
        recyclerView.adapter = chernListController.adapter
          viewModel.chernLiveData.observe(this, Observer { container ->
              chernListController.setData(container)
          })

        EpoxyTouchHelper.initSwiping(recyclerView)
            .leftAndRight()
            .withTarget(ChernChildModel::class.java)
            .andCallbacks(object :
                EpoxyTouchHelper.SwipeCallbacks<ChernChildModel>() {
                override fun onSwipeCompleted(
                    model: ChernChildModel?,
                    itemView: View?,
                    position: Int,
                    direction: Int
                ) {
          /*        val itemToDelete = model?.commonModel ?: return
                    toBuyViewModel.deleteItem(itemToDelete)*/
                }
            })



/*        EpoxyTouchHelper.initDragging(chernListController)
            .withRecyclerView(recyclerView)
            .forVerticalList()
            .withTarget(ChernChildModel::class.java)
            .andCallbacks(object : EpoxyTouchHelper.DragCallbacks<ChernChildModel>() {
                override fun onModelMoved(
                    fromPosition: Int,
                    toPosition: Int,
                    modelBeingMoved: ChernChildModel?,
                    itemView: View?
                ) {
                   viewModel.chernLiveData.observe(APP_ACTIVITY, Observer { container ->
                        if (modelBeingMoved?.commonModel?.category_id== viewModel.catalogList[toPosition].category_id){
                            Log.d(TAG, "onModelMoved from:$fromPosition -> to:$toPosition"+"    on Category ${viewModel.catalogList[fromPosition].category_id} to --->  ${viewModel.catalogList[toPosition].category_id}")
                            val removed = viewModel.catalogList.removeAt(fromPosition)
                            viewModel.catalogList.add(toPosition, removed)
                            chernListController.setData(container)
                        }else{
                            Log.d(TAG, "onModelMoved from:$fromPosition -> to:$toPosition"+" on Category ${viewModel.catalogList[fromPosition].category_id} to ${viewModel.catalogList[toPosition].category_id}")
                            viewModel.catalogList[fromPosition].category_id = viewModel.catalogList[toPosition].category_id
                            val removed = viewModel.catalogList.removeAt(fromPosition)
                            viewModel.catalogList.add(toPosition, removed)
                            chernListController.setData(container)
                        }
                    })

                }
            })*/
    }
    companion object {
        private const val TAG = "ChernFragment"
    }


}