package ru.rinet.questik.ui.chern.epoxy

import com.airbnb.epoxy.TypedEpoxyController

class ChernController() : TypedEpoxyController<ChernContainer>() {

    override fun buildModels(data: ChernContainer?) {
        data?.categories?.forEach {
            chernParent {
                id("cat${it.category.id}")
                title(it.category.name)
                position(data.categories.indexOf(it).toInt())
                category_size(it.itemsSize.toString())
                listener {
                    data.onCategoryExpanded(it.category)
                }
                expand(it.category.isExpand)
            }
            if (it.category.isExpand) {
                it.items.forEach { chernovik ->
                    chernChild {
                        id("${chernovik.item.id}${chernovik.item.name}")
                        chernovik(chernovik.item)
                        position(it.items.indexOf(chernovik).toInt())
                        categoryList(chernovik.categoryEntityList)
                        metricsList(chernovik.metricsEntityList)
                        onChernChangeCountListener { count ->
                            chernovik.OnItemChangeCount(count)
                        }
                        onChernChangeListener { model ->
                            chernovik.onItemUpdated(model)
                        }
                        onChernItemTouchListener { model ->
                            chernovik.onItemTouched(model)
                        }
                    }
                }
            }
        }
/*        data?. {

            if (it.category.isExpand) {

                it.items.forEach { items ->
                    chernChild {
                        id("${items.id}${items.name}")
                        chernovik(items)
                        categoryList(it.categoryEntityList)
                        metricsList(it.metricsEntityList)
                        onChernChangeCountListener { count ->
                            it.OnItemChangeCount(count)
                        }
                        onChernChangeListener { model ->
                            it.onItemUpdated(model)
                        }
                        onChernItemTouchListener { model, b ->
                            it.onItemTouched(model, b)
                        }
                    }
                }
            }
        }*/
    }
}
