package ru.rinet.questik.ui.chern.epoxy

import ru.rinet.questik.repo.local.room.entity.CategoryEntity
import ru.rinet.questik.repo.local.room.entity.ChernovikEntity
import ru.rinet.questik.repo.local.room.entity.MetricsEntity


typealias OnChernCategoryExpanded = (category: CategoryEntity) -> Unit
typealias OnChernItemTouched = (item: ChernovikEntity, touchUp: Boolean) -> Unit
typealias OnChernItemChangeCount = (count: String) -> Unit
typealias OnChernItemChanged = (item: ChernovikEntity) -> Unit

data class ChernContainer(
    val categories: List<ChernPerCategory>,
    val onCategoryExpanded: OnChernCategoryExpanded
)
data class ChernPerCategory(
    val category: CategoryEntity,
    val itemsSize: Int,
    val minList: Int,
    val maxList: Int,
    val items: MutableList<ChernovikEntity>,
    val onItemTouched: OnChernItemTouched,
    val OnItemChangeCount: OnChernItemChangeCount,
    val onItemUpdated: OnChernItemChanged,
    val categoryEntityList: List<CategoryEntity>,
    val metricsEntityList: List<MetricsEntity>
)