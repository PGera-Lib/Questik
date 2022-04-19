package ru.rinet.questik.ui.catalog.jobs.epoxy

import ru.rinet.questik.repo.local.room.entity.CategoryEntity
import ru.rinet.questik.repo.local.room.entity.JobsEntity


typealias OnCategoryExpanded = (category: CategoryEntity) -> Unit
typealias OnJobsItemClickes = (job: JobsEntity) -> Unit
data class JobsContainer(
    val categories: List<JobsPerCategory>,
    val onCategoryExpanded: OnCategoryExpanded
)
data class JobsPerCategory(
    val category: CategoryEntity,
    val jobs: List<JobsEntity>,
    val onJobsItemClickes: OnJobsItemClickes
)