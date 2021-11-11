package ru.rinet.questik.ui.catalog.material

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_material_item_child.view.*
import ru.rinet.questik.R
import ru.rinet.questik.models.MaterialModel


class MaterialFragmentAdapter: RecyclerView.Adapter<MaterialFragmentAdapter.MaterialHolder>() {

    private var mListMaterialCash = mutableListOf<MaterialModel>()
    private lateinit var mDiffResult: DiffUtil.DiffResult


    class MaterialHolder(view: View) : RecyclerView.ViewHolder(view) {
        val materialName: TextView = view.material_item_name
        val materialPrice: TextView = view.material_item_price
        val materialPlu: TextView = view.material_item_plu
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaterialHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_material_item_child, parent, false)
        return MaterialHolder(view)

    }

    override fun onBindViewHolder(holder: MaterialHolder, position: Int) {
            holder.materialName.text = mListMaterialCash[position].name
            holder.materialPrice.text = mListMaterialCash[position].price
            holder.materialPlu.text = mListMaterialCash[position].plu
        }

    override fun getItemCount(): Int =mListMaterialCash.size

    fun addItemToBottom(item: MaterialModel, isSuccess: () -> Unit) {
        if (!mListMaterialCash.contains(item)) {
            mListMaterialCash.add(item)
            notifyItemInserted(mListMaterialCash.size)
        }
        isSuccess()
    }
    fun addItemToTop(item: MaterialModel, isSuccess: () -> Unit) {
        if (!mListMaterialCash.contains(item)) {
            mListMaterialCash.add(item)
            mListMaterialCash.sortBy { it.id.toString() }
            notifyItemInserted(0)
        }
        isSuccess()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun afterSearch(list: List<MaterialModel>, isSuccess: () -> Unit) {
       mListMaterialCash.clear()
        for (m in list){
            mListMaterialCash.add(m)
            notifyItemInserted(mListMaterialCash.size)
        }
        notifyDataSetChanged()
        isSuccess()
    }

}