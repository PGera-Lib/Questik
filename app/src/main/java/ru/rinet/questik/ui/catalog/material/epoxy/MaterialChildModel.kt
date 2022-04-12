package ru.rinet.questik.ui.catalog.material.epoxy


import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import ru.rinet.questik.R
import ru.rinet.questik.databinding.FragmentMaterialItemChildBinding
import ru.rinet.questik.utils.helper.ViewBindingEpoxyModelWithHolder

@EpoxyModelClass(layout = R.layout.fragment_material_item_child)
abstract class MaterialChildModel : ViewBindingEpoxyModelWithHolder<FragmentMaterialItemChildBinding>() {

    @EpoxyAttribute
    lateinit var onMaterialClickListener:() -> Unit
    @EpoxyAttribute
    lateinit var name: String
    @EpoxyAttribute
    lateinit var price: String
    @EpoxyAttribute
    lateinit var sapId: String


    override fun FragmentMaterialItemChildBinding.bind() {
        materialItemName.text = this@MaterialChildModel.name
        materialItemPrice.text = this@MaterialChildModel.price
        materialItemPlu.text = this@MaterialChildModel.sapId
        materialItemLayout.setOnClickListener { this@MaterialChildModel.onMaterialClickListener()
        println("onMaterialHolder material clicked is ---- ${this@MaterialChildModel.name}")}
    }

    override fun FragmentMaterialItemChildBinding.unbind() {
        // Don't leak listeners as this view goes back to the view pool
        materialItemLayout.setOnClickListener(null)
    }
}