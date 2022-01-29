package ru.rinet.questik.ui.chern.epoxy

import ru.rinet.questik.models.CommonModel
import ru.rinet.questik.repo.local.room.entity.CategoryEntity


typealias OnChernCategoryExpanded = (category: CategoryEntity) -> Unit
typealias OnChernItemClickes = (item: CommonModel) -> Unit
data class ChernContainer(
    val categories: List<ChernPerCategory>,
    val onCategoryExpanded: OnChernCategoryExpanded
)
data class ChernPerCategory(
    val category: CategoryEntity,
    val items: List<CommonModel>,
    val onItemClicked: OnChernItemClickes,
    val categoryEntityList: List<CategoryEntity>
)