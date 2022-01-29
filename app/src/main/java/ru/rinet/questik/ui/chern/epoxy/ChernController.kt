package ru.rinet.questik.ui.chern.epoxy

import com.airbnb.epoxy.TypedEpoxyController
import ru.rinet.questik.ui.catalog.jobs.epoxy.jobsHeader


class ChernController() : TypedEpoxyController<ChernContainer>() {
    override fun buildModels(data: ChernContainer?) {
        data?.categories?.forEach {
            jobsHeader {
                id(it.category.id)
                title(it.category.name)
                listener {
                    data.onCategoryExpanded(it.category)
                }
                expand(it.category.isExpand)
            }
            if (it.category.isExpand) {
                it.items.forEach { items ->
                    chernChild {
                        id(items.rowId)
                        rowId(items.rowId)
                        commonModel(items)
                        categoryList(it.categoryEntityList)
                    }
                }
            }
        }
    }


}