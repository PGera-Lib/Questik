package ru.rinet.questik.ui.chern.epoxy

import ru.rinet.questik.repo.local.room.entity.CategoryEntity
import ru.rinet.questik.repo.local.room.entity.ChernovikEntity
import ru.rinet.questik.repo.local.room.entity.MetricsEntity


typealias OnChernCategoryExpanded = (category: CategoryEntity) -> Unit
typealias OnChernItemTouched = (item: ChernovikEntity) -> Unit
typealias OnChernItemChangeCount = (count: String) -> Unit
typealias OnChernItemChanged = (item: ChernovikEntity) -> Unit

data class ChernContainer(
    val categories: List<CategoryItems>,
    val onCategoryExpanded: OnChernCategoryExpanded
)

data class CategoryItems(
    val category: CategoryEntity,
    val minList: Int = 0,
    val maxList: Int = 0,
    val itemsSize: Int = 0,
    val items: MutableList<ChernovikItems>
)

data class ChernovikItems(
    val item: ChernovikEntity,
    val onItemTouched: OnChernItemTouched,
    val OnItemChangeCount: OnChernItemChangeCount,
    val onItemUpdated: OnChernItemChanged,
    val categoryEntityList: List<CategoryEntity>,
    val metricsEntityList: List<MetricsEntity>
)

/*
data class ChernPerCategory(
    val category: CategoryEntity,
    val minList: Int,
    val maxList: Int,
    val items: List<ChernovikEntity>,
    val onItemTouched: OnChernItemTouched,
    val OnItemChangeCount: OnChernItemChangeCount,
    val onItemUpdated: OnChernItemChanged,
    val categoryEntityList: List<CategoryEntity>,
    val metricsEntityList: List<MetricsEntity>
)*/
