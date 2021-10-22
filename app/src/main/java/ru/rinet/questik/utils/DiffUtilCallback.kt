package ru.rinet.questik.utils

import androidx.recyclerview.widget.DiffUtil
import ru.rinet.questik.models.CommonModel

class DiffUtilCallback(
    private val oldList: List<CommonModel>,
    private val newList: List<CommonModel>
):DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].timestamp == newList[newItemPosition].timestamp

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}