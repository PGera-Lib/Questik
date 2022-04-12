package ru.rinet.questik.ui.catalog.material.epoxy

import com.airbnb.epoxy.TypedEpoxyController
import ru.rinet.questik.ui.catalog.jobs.epoxy.materialHeader


class MaterialController : TypedEpoxyController<MaterialContainer>() {
    override fun buildModels(data: MaterialContainer?) {
        data?.categories?.forEach {
            materialHeader {
                id(it.category.toString())
                title(it.category.name)
                listener {
                    data.onCategoryExpanded(it.category)
                }
                expand(it.category.isExpand)
            }
            if (it.category.isExpand) {
                it.material.forEach { material ->
                    materialChild {
                        id("${material.id}+${material.name}")
                        price(material.price)
                        name(material.name)
                        sapId(material.plu)
                        onMaterialClickListener{
                            it.onMaterialItemClickes(material)
                        }
                    }

                }
            }
        }
    }
}