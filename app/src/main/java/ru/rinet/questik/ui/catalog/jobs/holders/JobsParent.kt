package ru.rinet.questik.ui.catalog.jobs.holders

import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.ExpandableItem
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.fragment_jobs_item_parent.view.*
import ru.rinet.questik.R
import ru.rinet.questik.models.CategoryModel

class JobsParent(val category: CategoryModel) : Item<GroupieViewHolder>(), ExpandableItem {
    private lateinit var expandableGroup: ExpandableGroup
    override fun bind(p0: GroupieViewHolder, p1: Int) {
        p0.root.jobs_catalog_name.text = category.name
        p0.root.parent_icon_arrow.setImageResource(
            R.drawable.questik_expand_down
        )
        p0.root.parent_icon_arrow.setOnClickListener {
            expandableGroup.onToggleExpanded()
            changeStuff(p0)
        }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_jobs_item_parent
    }

    private fun changeStuff(viewHolder: GroupieViewHolder) {
        viewHolder.root.parent_icon_arrow.apply {
            setImageResource(
                if (expandableGroup.isExpanded) R.drawable.questik_expand_up
                else R.drawable.questik_expand_down
            )
        }
    }

    override fun setExpandableGroup(onToggleListener: ExpandableGroup) {
        this.expandableGroup = onToggleListener
    }



}