package ru.rinet.questik.ui.catalog.jobs.holders

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.fragment_jobs_item_child.view.*
import ru.rinet.questik.R
import ru.rinet.questik.models.JobsModel

class JobsChild(var job: JobsModel) : Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.root.jobs_item_name.text = job.name
        viewHolder.root.jobs_item_price.text = job.price
    }

    override fun getLayout(): Int {
        return R.layout.fragment_jobs_item_child
    }
}