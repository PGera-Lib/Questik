package ru.rinet.questik.ui.catalog.jobs.holders

import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.fragment_material_item_child.view.*
import ru.rinet.questik.R
import ru.rinet.questik.models.MaterialModel

class MaterialChild(var material: MaterialModel) : Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.root.material_item_name.text = material.name
        viewHolder.root.material_item_price.text = material.price
        viewHolder.root.material_item_plu.text = material.plu
    }

    override fun getLayout(): Int {
        return R.layout.fragment_jobs_item_child
    }

}