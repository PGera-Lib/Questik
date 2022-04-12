package ru.rinet.questik.ui.chern.epoxy

import ru.rinet.questik.models.CommonModel
import ru.rinet.questik.repo.local.room.entity.CategoryEntity
import ru.rinet.questik.repo.local.room.entity.MetricsEntity


typealias OnChernCategoryExpanded = (category: CategoryEntity) -> Unit
typealias OnChernItemClickes = (item: CommonModel) -> Unit
typealias OnChernItemChangeCount = (count: String) -> Unit
typealias OnChernItemChanged = (item: CommonModel) -> Unit

data class ChernContainer(
    val categories: List<ChernPerCategory>,
    val onCategoryExpanded: OnChernCategoryExpanded
)
data class ChernPerCategory(
    val category: CategoryEntity,
    val items: List<CommonModel>,
    val onItemClicked: OnChernItemClickes,
    val OnItemChangeCount: OnChernItemChangeCount,
    val onItemUpdated: OnChernItemChanged,
    val categoryEntityList: List<CategoryEntity>,
    val metricsEntityList: List<MetricsEntity>
)