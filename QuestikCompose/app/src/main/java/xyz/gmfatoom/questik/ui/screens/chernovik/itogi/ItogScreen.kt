package xyz.gmfatoom.questik.ui.screens.chernovik.itogi

import androidx.compose.foundation.layout.Column

import androidx.compose.material.Divider
import androidx.compose.runtime.*

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import xyz.gmfatoom.questik.repo.local.room.entity.CategoryEntity
import xyz.gmfatoom.questik.repo.local.room.entity.JobsEntity
import xyz.gmfatoom.questik.repo.local.room.entity.MaterialEntity

import xyz.gmfatoom.questik.ui.screens.chernovik.ChernovikVM
import xyz.gmfatoom.questik.ui.screens.chernovik.itogi.items.ItogListItem


@Composable
fun ItogScreen(
    viewModel: ChernovikVM = viewModel(),
    catalogJobsMapState: State<MutableMap<CategoryEntity, List<JobsEntity>>>,
    catalogMaterialsMapState: State<MutableMap<CategoryEntity, List<MaterialEntity>>>
) {
    var itogRab = viewModel.itogRabot.collectAsState()
    var itogMat = viewModel.itogMaterial.collectAsState()
    var itogState by remember { mutableStateOf(0.0)}


    Column {
        ItogListItem(background = Color.Gray, textStyle = TextStyle(color = Color.White), title = "Итого за работы: ", sum = itogRab.value.toString())
        catalogJobsMapState.value.forEach { (sampleCategoryData, list) ->
            var rabotaState by remember { mutableStateOf(0.0)}
            var sum = 0.0
            if (list.isNotEmpty()) {
                list.forEach {
                    if (it.count.isEmpty()){
                        sum += it.price.toDouble()*0.0
                    } else {
                        sum += it.price.toDouble() * it.count.toDouble()
                    }
                }
                rabotaState = sum
                Divider(color = Color.Gray, thickness = 1.dp)
                ItogListItem(background = Color.LightGray, textStyle = TextStyle(color = Color.Black), title = sampleCategoryData.name, sum = rabotaState.toString())
            }
        }
        ItogListItem(background = Color.Gray, textStyle = TextStyle(color = Color.White), title = "Итого за материалы: ", sum = itogMat.value.toString())
        catalogMaterialsMapState.value.forEach { (sampleCategoryData, list) ->
            var rabotaState by remember { mutableStateOf(0.0)}
            var sum = 0.0
            if (list.isNotEmpty()) {
                list.forEach {
                    if (it.count.isEmpty()){
                        sum += it.price.toDouble()*0.0
                    } else {
                        sum += it.price.toDouble() * it.count.toDouble()
                    }
                }
                rabotaState = sum
                Divider(color = Color.Gray, thickness = 1.dp)
                ItogListItem(background = Color.LightGray, textStyle = TextStyle(color = Color.Black), title = sampleCategoryData.name, sum = rabotaState.toString())
            }
        }
        itogState = itogMat.value+itogRab.value
        Divider(color = Color.LightGray, thickness = 1.dp)
        ItogListItem(background = Color.Gray, textStyle = TextStyle(color = Color.White), title = "Итого: ", sum = itogState.toString())
    }



}