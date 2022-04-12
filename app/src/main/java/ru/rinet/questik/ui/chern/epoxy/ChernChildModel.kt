package ru.rinet.questik.ui.chern.epoxy


import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.doAfterTextChanged
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.EpoxyModelClass
import ru.rinet.questik.R
import ru.rinet.questik.databinding.FragmentChernChildItemBinding
import ru.rinet.questik.models.CommonModel
import ru.rinet.questik.repo.local.room.entity.CategoryEntity
import ru.rinet.questik.repo.local.room.entity.MetricsEntity
import ru.rinet.questik.utils.APP_ACTIVITY
import ru.rinet.questik.utils.helper.ViewBindingEpoxyModelWithHolder
import ru.rinet.questik.utils.helper.ViewBindingHolder

@EpoxyModelClass(layout = R.layout.fragment_chern_child_item)
abstract class ChernChildModel : ViewBindingEpoxyModelWithHolder<FragmentChernChildItemBinding>() {
    @EpoxyAttribute
    lateinit var commonModel: CommonModel

    @EpoxyAttribute
    lateinit var categoryList: MutableList<CategoryEntity>

    @EpoxyAttribute
    lateinit var metricsList: MutableList<MetricsEntity>

    @EpoxyAttribute
    lateinit var onChernItemCheckListener: () -> Unit

    @EpoxyAttribute
    lateinit var onChernChangeListener: (model: CommonModel) -> Unit

    @EpoxyAttribute
    lateinit var onChernChangeCountListener: (count: String) -> Unit

    private var itemCount: String  = ""

    override fun FragmentChernChildItemBinding.bind() {

        if (commonModel.count!=""){
            itemCount = commonModel.count
            detailLayout.itemCatalogCount.setText("$itemCount")
            detailLayout.itemCatalogCount.setSelection(detailLayout.itemCatalogCount.getText().length)
        } else {
            detailLayout.itemCatalogCount.setText("")
        }
        if (commonModel.isShow) {
            detailLayout.detailItem.visibility= View.VISIBLE
        } else {
            detailLayout.detailItem.visibility = View.GONE
        }
        itemPositionName.text = commonModel.name
        itemPositionSap.setText(commonModel.plu)
        detailLayout.itemPriceCount.setText(commonModel.price.toString()) //стоимость работ/материала за единицу
        /*****************************************************************************
         * чекбокс
         */
        itemChk.isChecked = commonModel.isChecked
        itemChk.setOnCheckedChangeListener { buttonView, isCheked ->
            itemChk.isChecked = isCheked
            if (buttonView.isShown && buttonView.isPressed) {
                val newModel = commonModel.copy(isChecked = isCheked)
                update(newModel)
            }
        }
/*
            if (commonModel.isChecked == true) {
                println("1 check  is  - ${commonModel.isChecked}")
                val newModel = commonModel.copy(isChecked = isCheked)
                onChernChangeListener(newModel)
            } else {
                detailItem.visibility = View.GONE
                println("2 check  is  - ${commonModel.isChecked}")
                val newModel = commonModel.copy(isChecked = isCheked)
                onChernChangeListener(newModel)
            }

        }*/
        /********************************************************************
         * панель подробностей
         */

        showhide.setOnClickListener {
            if (it.isShown && it.isPressed) {
                val newModel = commonModel.copy(isShow = !commonModel.isShow)
                update(newModel)
            }
        }


        /********************************************************************
         * Спинер категорий
         */
        val categoryOptionAdapter: ArrayAdapter<CategoryEntity> =
            ArrayAdapter(
                APP_ACTIVITY,
                android.R.layout.simple_spinner_dropdown_item,
                this@ChernChildModel.categoryList
            )
        detailLayout.itemCategorySpinner.adapter = categoryOptionAdapter
        detailLayout.itemCategorySpinner.run {
            categoryList.forEach {
                if (it.id == commonModel.category_id.toInt()) {
                    setSelection(categoryOptionAdapter.getPosition(it))
                }
            }
        }
        detailLayout.itemCategorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                this@ChernChildModel.categoryList.get(p2).name = p0?.selectedItem.toString()
                /*category = p0?.selectedItem.toString()*/
            }
        }

        /********************************************************************
         * Спинер метрики
         */
        val metricsOptionAdapter: ArrayAdapter<MetricsEntity> =
            ArrayAdapter(
                APP_ACTIVITY,
                android.R.layout.simple_spinner_dropdown_item,
                this@ChernChildModel.metricsList
            )
        detailLayout.itemMetSpinner.adapter = metricsOptionAdapter
        detailLayout.itemMetSpinner.run {
            metricsList.forEach {
                if (it.id == commonModel.metrics_id.toInt()) {
                    setSelection(metricsOptionAdapter.getPosition(it))
                }
            }
        }
        detailLayout.itemMetSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                this@ChernChildModel.metricsList.get(p2).name = p0?.selectedItem.toString()
                Log.d("TAG", p0?.selectedItem.toString())
                /*category = p0?.selectedItem.toString()*/
            }
        }
        /********************************************************************
         * Кнопки + и -
         */
        //уменьшение колличества работ/материала
        detailLayout.itemButtonMinus.setOnClickListener {
            if (itemCount.toInt()>0){
                itemCount = "${itemCount.toInt()-1}"
                detailLayout.itemCatalogCount.setText("$itemCount")
                val newModel = commonModel.copy(count = itemCount.toString())
                update(newModel)
                detailLayout.itemCatalogCount.setSelection(detailLayout.itemCatalogCount.getText().length)
            } else {
                    itemCount = ""
                detailLayout.itemCatalogCount.setText("$itemCount")
                    val newModel = commonModel.copy(count = itemCount.toString())
                update(newModel)
                detailLayout.itemCatalogCount.setSelection(detailLayout.itemCatalogCount.getText().length)
                }
        }
        //увеличение колличества работ/материала
        detailLayout.itemButtonPlus.setOnClickListener {
            if (itemCount.toInt()<=100000){
                itemCount = "${itemCount.toInt()+1}"
                detailLayout.itemCatalogCount.setText("$itemCount")
                val newModel = commonModel.copy(count = itemCount)
                update(newModel)
                detailLayout.itemCatalogCount.setSelection(detailLayout.itemCatalogCount.getText().length)
            } else {
                println("слишком большое колличество бля!")
            }
        }

        /********************************************************************
         * Колличестиво
         */
        //колличество работ/материала
        detailLayout.itemCatalogCount.doAfterTextChanged { text ->

            if (detailLayout.itemCatalogCount.hasFocus() && itemCount.toString()!=text.toString()) {
                Log.i("ChernChildModel ",
                    "in EditText - ${detailLayout.itemCatalogCount.text.toString()}, input data is ${text.toString()} and COUNT var is - $itemCount")
            //    detailLayout.itemCatalogCount.setSelection(text?.length?:0)
                val newModel = commonModel.copy(count = text.toString(), isShow = true)
                update(newModel)
                itemCount = detailLayout.itemCatalogCount.text.toString()
                detailLayout.itemCatalogCount.setText(text.toString())

                detailLayout.itemCatalogCount.setSelection(detailLayout.itemCatalogCount.getText().length)
            }
        }





    }

    private fun update(model: CommonModel) {
        if (!commonModel.equals(model)){
            onChernChangeListener(model)
        } else {
            println("no update neeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeed*******************************")
        }

    }

    override fun id(key: CharSequence?): EpoxyModel<ViewBindingHolder> {
        return super.id(key)
    }

    override fun FragmentChernChildItemBinding.unbind() {
        // Don't leak listeners as this view goes back to the view pool
        //   onChernItemCheckListener(null)
    }
}
