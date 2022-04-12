package ru.rinet.questik.ui.catalog.material.epoxy

import ru.rinet.questik.repo.local.room.entity.CategoryEntity
import ru.rinet.questik.repo.local.room.entity.MaterialEntity


typealias OnCategoryExpanded = (category: CategoryEntity) -> Unit
typealias OnMaterialItemClickes = (mat: MaterialEntity) -> Unit
data class MaterialContainer(
    val categories: List<MaterialPerCategory>,
    val onCategoryExpanded: OnCategoryExpanded
)
data class MaterialPerCategory(
    val category: CategoryEntity,
    val material: List<MaterialEntity>,
    val onMaterialItemClickes: OnMaterialItemClickes
)