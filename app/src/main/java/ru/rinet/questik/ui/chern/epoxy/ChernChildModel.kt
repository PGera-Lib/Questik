package ru.rinet.questik.ui.chern.epoxy


import android.annotation.SuppressLint
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.doAfterTextChanged
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.EpoxyModelClass
import ru.rinet.questik.R
import ru.rinet.questik.databinding.FragmentChernChildItemBinding
import ru.rinet.questik.repo.local.room.entity.CategoryEntity
import ru.rinet.questik.repo.local.room.entity.ChernovikEntity
import ru.rinet.questik.repo.local.room.entity.MetricsEntity
import ru.rinet.questik.utils.APP_ACTIVITY
import ru.rinet.questik.utils.helper.ViewBindingEpoxyModelWithHolder
import ru.rinet.questik.utils.helper.ViewBindingHolder

@EpoxyModelClass(layout = R.layout.fragment_chern_child_item)
abstract class ChernChildModel : ViewBindingEpoxyModelWithHolder<FragmentChernChildItemBinding>() {
    @EpoxyAttribute
    lateinit var chernovik: ChernovikEntity

    @EpoxyAttribute
    lateinit var categoryList: MutableList<CategoryEntity>

    @EpoxyAttribute
    lateinit var metricsList: MutableList<MetricsEntity>

    @EpoxyAttribute
    lateinit var onChernItemTouchListener: (model: ChernovikEntity, touchUp: Boolean) -> Unit

    @EpoxyAttribute
    lateinit var onChernChangeListener: (model: ChernovikEntity) -> Unit

    @EpoxyAttribute
    lateinit var onChernChangeCountListener: (count: String) -> Unit

    private var itemCount: String  = ""

    @SuppressLint("ClickableViewAccessibility")
    override fun FragmentChernChildItemBinding.bind() {
        chernItemLinerLayout.setOnTouchListener { _, motionEvent ->
            when (motionEvent.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    println("motion down")
                    onChernItemTouchListener(chernovik, false)
                }
                  //  return@setOnTouchListener true
                MotionEvent.ACTION_UP -> {
                    println("motion up")
                    onChernItemTouchListener(chernovik, true)
                }
            }
            return@setOnTouchListener true

        }


        if (chernovik.itemCount!=""){
            itemCount = chernovik.itemCount
            detailLayout.itemCatalogCount.setText("$itemCount")
            detailLayout.itemCatalogCount.setSelection(detailLayout.itemCatalogCount.getText().length)
        } else {
          //  detailLayout.itemCatalogCount.setText(itemCount)
           // detailLayout.itemCatalogCount.setSelection(detailLayout.itemCatalogCount.getText().length)
        }
        if (chernovik.isShow) {
            detailLayout.detailItem.visibility= View.VISIBLE
        } else {
            detailLayout.detailItem.visibility = View.GONE
        }
        itemPositionName.text = chernovik.name
        itemPositionSap.setText(chernovik.plu)
        detailLayout.itemPriceCount.setText(chernovik.itemPrice.toString()) //стоимость работ/материала за единицу
        /*****************************************************************************
         * чекбокс
         */
        itemChk.isChecked = chernovik.isChecked

        itemChk.setOnCheckedChangeListener { buttonView, isCheked ->
            itemChk.isChecked = isCheked
            if (buttonView.isShown && buttonView.isPressed) {
                val newModel = chernovik.copy(isChecked = isCheked)
                update(newModel)
                Log.i(
                    "TAG CHERN CHILD MODEL ",
                    "CHEK is $isCheked"
                )
            }
        }
        /********************************************************************
         * панель подробностей
         */

        showhide.setOnClickListener {
            if (it.isShown && it.isPressed) {
                val newModel = chernovik.copy(isShow = !chernovik.isShow)
                update(newModel)
                Log.i(
                    "TAG CHERN CHILD MODEL ",
                    "SHOWHIDE"
                )




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
                if (it.id == chernovik.categoryId.toInt()) {
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
                if (it.id == chernovik.metricsId.toInt()) {
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
            if (itemCount==""){
                itemCount="0"
            }
            if (itemCount.toInt()>0){
                itemCount = "${itemCount.toInt()-1}"
                detailLayout.itemCatalogCount.setText("$itemCount")
                val newModel = chernovik.copy(itemCount = itemCount.toString())
                update(newModel)
                Log.i(
                    "TAG CHERN CHILD MODEL ",
                    "BUTTON MINUS"
                )
                detailLayout.itemCatalogCount.setSelection(detailLayout.itemCatalogCount.getText().length)
            } else {
                    itemCount = ""
                detailLayout.itemCatalogCount.setText("$itemCount")
                    val newModel = chernovik.copy(itemCount = itemCount.toString())
                update(newModel)
                Log.i(
                    "TAG CHERN CHILD MODEL ",
                    "BUTTON MINUS 2"
                )
                detailLayout.itemCatalogCount.setSelection(detailLayout.itemCatalogCount.getText().length)
                }
        }
        //увеличение колличества работ/материала
        detailLayout.itemButtonPlus.setOnClickListener {
            if (itemCount==""){
                itemCount="0"
            }
            if (itemCount.toInt()<=100000){
                itemCount = "${itemCount.toInt()+1}"
                detailLayout.itemCatalogCount.setText("$itemCount")
                val newModel = chernovik.copy(itemCount = itemCount)
                update(newModel)
                Log.i(
                    "TAG CHERN CHILD MODEL ",
                    "BUTTON PLUS"
                )
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
            Log.i(
                "TAG CHERN CHILD MODEL ",
                "CHANGE TEXT LISTENER 1"
            )
            if (detailLayout.itemCatalogCount.hasFocus() && itemCount.toString()!=text.toString()) {
                Log.i(
                    "TAG CHERN CHILD MODEL ",
                    "CHANGE TEXT LISTENER 2"
                )
                Log.i("ChernChildModel ",
                    "in EditText - ${detailLayout.itemCatalogCount.text.toString()}, input data is ${text.toString()} and COUNT var is - $itemCount")
            //    detailLayout.itemCatalogCount.setSelection(text?.length?:0)
                val newModel = chernovik.copy(itemCount = text.toString(), isShow = true)
                update(newModel)
                Log.i(
                    "TAG CHERN CHILD MODEL ",
                    "CHANGE TEXT LISTENER 3"
                )
                itemCount = detailLayout.itemCatalogCount.text.toString()
                detailLayout.itemCatalogCount.setText(text.toString())

                detailLayout.itemCatalogCount.setSelection(detailLayout.itemCatalogCount.getText().length)
            }
        }

    }

    private fun update(model: ChernovikEntity ) {
        Log.i(
            "TAG CHERN CHILD MODEL",
            "разница ${model.name} - count ${chernovik.itemCount}  changed to ${model.itemCount}  cheked is  ${chernovik.isChecked}  changed to ${model.isChecked} Detail view is  ${chernovik.isShow}  changed to ${model.isShow} "
        )
        if (model.isChecked!=chernovik.isChecked||model.itemCount!=chernovik.itemCount||model.isShow!=chernovik.isShow||model!=chernovik){
            onChernChangeListener(model)
            Log.i(
                "TAG CHERN CHILD MODEL ",
                "3")

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
