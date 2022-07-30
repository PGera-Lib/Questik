package xyz.gmfatoom.questik.ui.screens.chernovik.items.change

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.StateFlow
import xyz.gmfatoom.questik.repo.local.room.entity.CategoryEntity
import xyz.gmfatoom.questik.repo.local.room.entity.JobsEntity

@Composable
fun AddMapItems(cashMapState :StateFlow<MutableMap<CategoryEntity, List<JobsEntity>>>) : StateFlow<MutableMap<CategoryEntity, List<JobsEntity>>>{


    return cashMapState
}