package xyz.gmfatoom.questik.ui.screens.chernovik.itogi

import androidx.compose.foundation.layout.Column

import androidx.compose.material.Divider
import androidx.compose.runtime.*

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

import xyz.gmfatoom.questik.ui.screens.chernovik.itogi.items.ItogListItem
import xyz.gmfatoom.questik.ui.screens.requests.edit.dialog.RequestDialogVM


@Composable
fun RequestItog(
) {
    val viewModel = hiltViewModel<RequestDialogVM>()
    var itogRab = viewModel.itogRabot.collectAsState()
    var itogMat = viewModel.itogMaterial.collectAsState()
    val jobsState = viewModel.requestJobsCatalog.collectAsState()
    val materialsState = viewModel.requestMaterialsCatalog.collectAsState()
    var itogState by remember { mutableStateOf(0.0)}

    Column {
        ItogListItem(background = Color.Gray, textStyle = TextStyle(color = Color.White), title = "Итого за работы: ", sum = itogRab.value.toString())

                jobsState.value.forEach { (sampleCategoryData, list) ->
            var rabotaState by remember { mutableStateOf(0.0)}
            var sum = 0.0
            if (list.isNotEmpty()) {
                list.forEach {
                    if (it.itemCount.isEmpty()){
                        sum += it.itemPrice.toDouble()*0.0
                    } else {
                        sum += it.itemPrice.toDouble() * it.itemCount.toDouble()
                    }
                }
                rabotaState = sum
                Divider(color = Color.Gray, thickness = 1.dp)
                ItogListItem(background = Color.LightGray, textStyle = TextStyle(color = Color.Black), title = sampleCategoryData.name, sum = rabotaState.toString())
            }
        }
        ItogListItem(background = Color.Gray, textStyle = TextStyle(color = Color.White), title = "Итого за материалы: ", sum = itogMat.value.toString())
        materialsState.value.forEach { (sampleCategoryData, list) ->
            var materialState by remember { mutableStateOf(0.0)}
            var sum = 0.0
            if (list.isNotEmpty()) {
                list.forEach {
                    if (it.itemCount.isEmpty()){
                        sum += it.itemPrice.toDouble()*0.0
                    } else {
                        sum += it.itemPrice.toDouble() * it.itemCount.toDouble()
                    }
                }
                materialState = sum
                Divider(color = Color.Gray, thickness = 1.dp)
                ItogListItem(background = Color.LightGray, textStyle = TextStyle(color = Color.Black), title = sampleCategoryData.name, sum = materialState.toString())
            }
        }
        itogState = itogMat.value+itogRab.value
        Divider(color = Color.LightGray, thickness = 1.dp)
        ItogListItem(background = Color.Gray, textStyle = TextStyle(color = Color.White), title = "Итого: ", sum = itogState.toString())
    }



}