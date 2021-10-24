package ru.rinet.questik.ui.catalog.jobs.holders

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.fragment_jobs_item_child.*
import ru.rinet.questik.R

class JobsChild(val name : String, val price : String, val sap : String) : Item() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.jobs_item_name.text = name
        viewHolder.jobs_item_price.text = price
        viewHolder.jobs_item_sap.text = sap
    }
    override fun getLayout(): Int {
        return R.layout.fragment_jobs_item_child
    }

}