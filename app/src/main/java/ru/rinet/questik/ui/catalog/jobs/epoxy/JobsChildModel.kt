package ru.rinet.questik.ui.catalog.jobs.epoxy


import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import ru.rinet.questik.R
import ru.rinet.questik.databinding.FragmentJobsItemChildBinding
import ru.rinet.questik.utils.helper.ViewBindingEpoxyModelWithHolder

@EpoxyModelClass(layout = R.layout.fragment_jobs_item_child)
abstract class JobsChildModel : ViewBindingEpoxyModelWithHolder<FragmentJobsItemChildBinding>() {

    @EpoxyAttribute
    lateinit var onJobsClickListener:() -> Unit
    @EpoxyAttribute
    lateinit var name: String
    @EpoxyAttribute
    lateinit var price: String


    override fun FragmentJobsItemChildBinding.bind() {
        jobsItemName.text = this@JobsChildModel.name
        jobsItemPrice.text = this@JobsChildModel.price
        jobsItemCard.setOnClickListener { this@JobsChildModel.onJobsClickListener()
        /*println("onJobsHolder jobs clicked is ---- ${this@JobsChildModel.name}")*/}
    }

    override fun FragmentJobsItemChildBinding.unbind() {
        // Don't leak listeners as this view goes back to the view pool
        jobsItemCard.setOnClickListener(null)
    }
}