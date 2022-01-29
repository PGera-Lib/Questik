package ru.rinet.questik.ui.chern.epoxy


import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import ru.rinet.questik.R
import ru.rinet.questik.databinding.FragmentChernChildItemBinding
import ru.rinet.questik.models.CommonModel
import ru.rinet.questik.repo.local.room.entity.CategoryEntity
import ru.rinet.questik.utils.APP_ACTIVITY
import ru.rinet.questik.utils.helper.ViewBindingEpoxyModelWithHolder

@EpoxyModelClass(layout = R.layout.fragment_chern_child_item)
abstract class ChernChildModel : ViewBindingEpoxyModelWithHolder<FragmentChernChildItemBinding>() {
    @EpoxyAttribute
    lateinit var onChernItemClickListener: () -> Unit
    @EpoxyAttribute lateinit var rowId: String
    @EpoxyAttribute
    lateinit var commonModel: CommonModel

    @EpoxyAttribute
    lateinit var categoryList: MutableList<CategoryEntity>


    override fun FragmentChernChildItemBinding.bind() {
        itemPositionName.text = this@ChernChildModel.commonModel.name
        if(this@ChernChildModel.commonModel.plu.isEmpty()) {
            itemPositionSap.visibility = View.GONE
        } else {
            itemPositionSap.setText(this@ChernChildModel.commonModel.plu)
        }

        itemChk.isChecked = this@ChernChildModel.commonModel.isChecked
        itemChk.setOnCheckedChangeListener { buttonView, isCheked ->
            this@ChernChildModel.commonModel.isChecked = isCheked
        }
        showhide.setOnClickListener {
            if (detailItem.visibility == View.GONE) { //контейнер для детализации
                detailItem.visibility = View.VISIBLE
            }else{
                detailItem.visibility = View.GONE
            }
        }

        var categoryOptionAdapter: ArrayAdapter<CategoryEntity> =
            ArrayAdapter(APP_ACTIVITY, android.R.layout.simple_spinner_dropdown_item, this@ChernChildModel.categoryList)
        itemCategorySpinner.adapter = categoryOptionAdapter
        itemCategorySpinner.run {
            categoryList.forEach{
                if (it.id == commonModel.category_id.toInt()){
                    setSelection(categoryOptionAdapter.getPosition(it))
                }
            }

        }
        itemCategorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                this@ChernChildModel.categoryList.get(p2).name = p0?.selectedItem.toString()
                Log.d("TAG", p0?.selectedItem.toString())
                /*category = p0?.selectedItem.toString()*/
            }
        }
        itemPriceCount //стоимость работ/материала за единицу
        itemMetSpinner //единица работ/материала
        itemButtonMinus //уменьшение колличества работ/материала
        itemButtonPlus  //увеличение колличества работ/материала
        itemCatalogCount //колличество работ/материала


    }

    override fun FragmentChernChildItemBinding.unbind() {
        // Don't leak listeners as this view goes back to the view pool
  /*      jobsItemCard.setOnClickListener(null)*/
    }
}
