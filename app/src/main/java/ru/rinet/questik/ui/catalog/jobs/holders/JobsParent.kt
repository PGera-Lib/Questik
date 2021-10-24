package ru.rinet.questik.ui.catalog.jobs.holders

import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.ExpandableItem
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.fragment_jobs_item_parent.*
import ru.rinet.questik.R

class JobsParent(val title: String) : Item(), ExpandableItem {
    private lateinit var expandableGroup: ExpandableGroup

    override fun bind(p0: GroupieViewHolder, p1: Int) {
        p0.jobs_catalog_name.text = title
        p0.itemView.setOnClickListener {
            expandableGroup.onToggleExpanded()
            changeStuff(p0)
        }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_jobs_item_parent
    }

    private fun changeStuff(viewHolder: GroupieViewHolder) {
        viewHolder.parent_icon_arrow.apply {
            setImageResource(
                if (expandableGroup.isExpanded) R.drawable.questik_expand_down
                else R.drawable.questik_expand_up
            )
        }
    }

    override fun setExpandableGroup(onToggleListener: ExpandableGroup) {
        this.expandableGroup = onToggleListener
    }


}