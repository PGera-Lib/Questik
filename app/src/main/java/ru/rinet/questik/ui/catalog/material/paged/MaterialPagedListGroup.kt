package ru.rinet.questik.ui.catalog.jobs.paged

import androidx.paging.AsyncPagedListDiffer
import androidx.paging.PagedList
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import com.xwray.groupie.Group
import com.xwray.groupie.GroupDataObserver
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import ru.rinet.questik.ui.catalog.jobs.holders.MaterialChild

class MaterialPagedListGroup : Group, GroupDataObserver {

    private var parentObserver: GroupDataObserver? = null

    private val listUpdateCallback: ListUpdateCallback = object : ListUpdateCallback {
        override fun onInserted(position: Int, count: Int) {
            parentObserver!!.onItemRangeInserted(this@MaterialPagedListGroup, position, count)
        }

        override fun onRemoved(position: Int, count: Int) {
            parentObserver!!.onItemRangeRemoved(this@MaterialPagedListGroup, position, count)
        }

        override fun onMoved(fromPosition: Int, toPosition: Int) {
            parentObserver!!.onItemMoved(this@MaterialPagedListGroup, fromPosition, toPosition)
        }

        override fun onChanged(
            position: Int,
            count: Int,
            payload: Any?
        ) {
            parentObserver!!.onItemRangeChanged(this@MaterialPagedListGroup, position, count)
        }
    }
    private val differ: AsyncPagedListDiffer<MaterialChild> = AsyncPagedListDiffer<MaterialChild>(
        listUpdateCallback,
        AsyncDifferConfig.Builder<MaterialChild>(object : DiffUtil.ItemCallback<MaterialChild>() {
            override fun areItemsTheSame(oldItem: MaterialChild, newItem: MaterialChild): Boolean {
                return newItem.material.id== oldItem.material.id
            }

            override fun areContentsTheSame(oldItem: MaterialChild, newItem: MaterialChild): Boolean {
                return newItem.material.name == oldItem.material.name
            }
        }).build()
    )
    private var placeHolder: Item<GroupieViewHolder>? = null
    fun setPlaceHolder(placeHolder: Item<GroupieViewHolder>?) {
        this.placeHolder = placeHolder
    }

    fun submitList(newPagedList: PagedList<MaterialChild>?) {
        differ.submitList(newPagedList)
    }

    override fun getItemCount(): Int {
        return differ.itemCount
    }

    override fun getItem(position: Int): Item<GroupieViewHolder> {
        val item: Item<GroupieViewHolder>? = differ.getItem(position)
        if (item != null) { // TODO find more efficiency registration timing, and removing observer
            item.registerGroupDataObserver(this)
            return item
        }
        return placeHolder!!
    }

    override fun getPosition(item: Item<GroupieViewHolder>): Int {
        val currentList = differ.currentList ?: return -1
        return currentList.indexOf(item as MaterialChild)
    }

    override fun registerGroupDataObserver(groupDataObserver: GroupDataObserver) {
        parentObserver = groupDataObserver
    }

    override fun unregisterGroupDataObserver(groupDataObserver: GroupDataObserver) {
        parentObserver = null
    }

    override fun onChanged(group: Group) {
        parentObserver!!.onChanged(this)
    }

    override fun onItemInserted(group: Group, position: Int) {
        val index = getItemPosition(group)
        if (index >= 0 && parentObserver != null) {
            parentObserver!!.onItemInserted(this, index)
        }
    }

    override fun onItemChanged(group: Group, position: Int) {
        val index = getItemPosition(group)
        if (index >= 0 && parentObserver != null) {
            parentObserver!!.onItemChanged(this, index)
        }
    }

    override fun onItemChanged(
        group: Group,
        position: Int,
        payload: Any
    ) {
        val index = getItemPosition(group)
        if (index >= 0 && parentObserver != null) {
            parentObserver!!.onItemChanged(this, index, payload)
        }
    }

    override fun onItemRemoved(group: Group, position: Int) {
        val index = getItemPosition(group)
        if (index >= 0 && parentObserver != null) {
            parentObserver!!.onItemRemoved(this, index)
        }
    }

    override fun onItemRangeChanged(
        group: Group,
        positionStart: Int,
        itemCount: Int
    ) {
        val index = getItemPosition(group)
        if (index >= 0 && parentObserver != null) {
            parentObserver!!.onItemRangeChanged(this, index, itemCount)
        }
    }

    override fun onItemRangeChanged(
        group: Group,
        positionStart: Int,
        itemCount: Int,
        payload: Any
    ) {
        val index = getItemPosition(group)
        if (index >= 0 && parentObserver != null) {
            parentObserver!!.onItemRangeChanged(this, index, itemCount, payload)
        }
    }

    override fun onItemRangeInserted(
        group: Group,
        positionStart: Int,
        itemCount: Int
    ) {
        val index = getItemPosition(group)
        if (index >= 0 && parentObserver != null) {
            parentObserver!!.onItemRangeInserted(this, index, itemCount)
        }
    }

    override fun onItemRangeRemoved(
        group: Group,
        positionStart: Int,
        itemCount: Int
    ) {
        val index = getItemPosition(group)
        if (index >= 0 && parentObserver != null) {
            parentObserver!!.onItemRangeRemoved(this, index, itemCount)
        }
    }

    override fun onItemMoved(
        group: Group,
        fromPosition: Int,
        toPosition: Int
    ) {
        val index = getItemPosition(group)
        if (index >= 0 && parentObserver != null) {
            parentObserver!!.onItemRangeChanged(this, index, toPosition)
        }
    }

    override fun onDataSetInvalidated() {
        parentObserver?.onDataSetInvalidated()
    }

    private fun getItemPosition(group: Group): Int {
        val currentList = differ.currentList ?: return -1
        return currentList.indexOf(group)
    }
}