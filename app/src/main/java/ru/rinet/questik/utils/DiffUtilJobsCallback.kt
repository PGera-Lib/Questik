package ru.rinet.questik.utils

import androidx.recyclerview.widget.DiffUtil
import ru.rinet.questik.models.JobsModel

class DiffUtilJobsCallback(
    private val oldList: List<JobsModel>,
    private val newList: List<JobsModel>
):DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}
