package ru.rinet.questik.ui.chern.epoxy

import com.airbnb.epoxy.TypedEpoxyController


class ChernController() : TypedEpoxyController<ChernContainer>() {
    override fun buildModels(data: ChernContainer?) {
        data?.categories?.forEach {
            chernParent {
                id("cat${it.category.id}")
                title(it.category.name)
                category_size(it.items.size.toString())
                listener {
                    data.onCategoryExpanded(it.category)
                }
                expand(it.category.isExpand)
            }
            if (it.category.isExpand) {

                it.items.forEach { items ->
                    chernChild {
                        id(items.id)
                        chernovik(items)
                        categoryList(it.categoryEntityList)
                        metricsList(it.metricsEntityList)
                        onChernChangeCountListener { count ->
                            it.OnItemChangeCount(count)
                        }
                        onChernChangeListener {model ->
                            it.onItemUpdated(model)
                        }
                        onChernItemTouchListener { model, b ->
                            it.onItemTouched(model, b)
                        }
                    }
                }
            }
        }
    }
}