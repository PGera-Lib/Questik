package ru.rinet.questik.ui.chern.epoxy

import android.widget.ImageView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import ru.rinet.questik.R
import ru.rinet.questik.databinding.FragmentJobsItemParentBinding
import ru.rinet.questik.utils.helper.ViewBindingEpoxyModelWithHolder


@EpoxyModelClass(layout = R.layout.fragment_jobs_item_parent)
abstract class ChernParentModel : ViewBindingEpoxyModelWithHolder<FragmentJobsItemParentBinding>() {
    @EpoxyAttribute
    lateinit var listener: () -> Unit
    @EpoxyAttribute
    lateinit var title: String
    @EpoxyAttribute
    lateinit var category_size: String

    @EpoxyAttribute
    var expand: Boolean = false

    override fun FragmentJobsItemParentBinding.bind() {
        jobsCatalogName.text = this@ChernParentModel.title
        jobsCatalogCount.text = this@ChernParentModel.category_size

        if (expand) {
            parentIconArrow.setImageResource(R.drawable.questik_expand_up)
        }else {
            parentIconArrow.setImageResource(R.drawable.questik_expand_down)
        }

       // changeRes(parentIconArrow)
        parentIconArrow.setOnClickListener {
            changeRes(parentIconArrow)
            println(" 1 on create icon change $expand")
            listener()
        }
        //jobs_catalog_name { listener() }
    }

        fun changeRes(imageView: ImageView){
        if (expand) {
            imageView.setImageResource(R.drawable.questik_expand_up)
            println("2 on change res method - $expand")
        }else {
            println("3 on change res method - $expand")
            imageView.setImageResource(R.drawable.questik_expand_down)
        }
    }

    override fun FragmentJobsItemParentBinding.unbind() {
        // Don't leak listeners as this view goes back to the view pool
       // title.setOnClickListener(null)
       // jobsCatalogName.setOnClickListener(null)
        parentIconArrow.setOnClickListener(null)
    }
}