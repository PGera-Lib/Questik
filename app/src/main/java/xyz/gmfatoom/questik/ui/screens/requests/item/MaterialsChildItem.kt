package xyz.gmfatoom.questik.ui.screens.requests.views

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import xyz.gmfatoom.questik.repo.local.room.entity.MaterialEntity
import xyz.gmfatoom.questik.repo.local.room.entity.RequestItemEntity

import xyz.gmfatoom.questik.ui.screens.requests.edit.dialog.RequestDialogVM
import xyz.gmfatoom.questik.utils.Constants


@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedTransitionTargetStateParameter")
@ExperimentalAnimationApi
@Composable
fun RequestMaterialsChildItem(
    visible: Boolean,
    item: MaterialEntity,
    viewModel: RequestDialogVM
) {
    var metricsName by remember { mutableStateOf(item.metrics_id)}
    val enterFadeIn = remember {
        fadeIn(
            animationSpec = TweenSpec(
                durationMillis = Constants.FadeInAnimation,
                easing = FastOutLinearInEasing
            )
        )
    }



    var touched by remember {
        mutableStateOf(item.isSelected)
    }


    val enterExpand = remember {
        expandVertically(animationSpec = tween(Constants.ExpandAnimation))
    }
    val exitFadeOut = remember {
        fadeOut(
            animationSpec = TweenSpec(
                durationMillis = Constants.FadeOutAnimation,
                easing = LinearOutSlowInEasing
            )
        )
    }


    val changeColorOnClick =
        remember { mutableStateOf(if (touched) Color.LightGray else Color.White) }
    val changeElevationOnClick = remember { mutableStateOf(5.dp) }


    val exitCollapse = remember {
        shrinkVertically(animationSpec = tween(Constants.CollapseAnimation))
    }

/*
    var changeColorOnClick by remember {
        mutableStateOf(Color.White)
    }
    var changeElevationOnClick by remember {
        mutableStateOf(5.dp)
    }
*/

    AnimatedVisibility(
        visible = visible,
        enter = enterExpand + enterFadeIn,
        exit = exitCollapse + exitFadeOut
    ) {
        Card(
            backgroundColor = changeColorOnClick.value,
            elevation = changeElevationOnClick.value,
            shape = RoundedCornerShape(3.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 4.dp,
                    vertical = 1.dp
                ),
            onClick = {
                changeColorOnClick.value = if (!touched) Color.LightGray else Color.White
                changeElevationOnClick.value = if (!touched) 20.dp else 5.dp
                val newItem = item.copy(isSelected = !item.isSelected)
                viewModel.checkMaterialsItem(newItem)
                touched = newItem.isSelected
                println("child is ${item.name} clicked and color change to ${changeColorOnClick.toString()} and elevation to ${changeElevationOnClick}   touched is - ${touched} selected is ${!item.isSelected} ")
            }) {
            Column() {
                Row(
                    Modifier
                        .background(
                            Color.LightGray
                        )
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "${item.name}",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(4.dp),
                        color = Color.Black
                    )
                }

                Row {
                    Row(modifier = Modifier.weight(3.0f, fill = true)) {
                        Text(
                            text = "Стоимость: ",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(4.dp),
                            color = Color.Black
                        )
                        Text(
                            text = "${item.price} руб. ",
                            modifier = Modifier
                                .padding(4.dp),
                            color = Color.Black
                        )
                    }
                    Row(modifier = Modifier.weight(1.0f, fill = true), horizontalArrangement = Arrangement.End) {
                        Text(
                            text = "за: ",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(4.dp),
                            color = Color.Black
                        )
                        Text(
                            text = metricsName,
                            modifier = Modifier

                                .padding(4.dp),
                            color = Color.Black
                        )
                    }


                }

            }

        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedTransitionTargetStateParameter")
@ExperimentalAnimationApi
@Composable
fun RequestMaterialsChekedChildItem(
    visible: Boolean,
    item: RequestItemEntity,
    viewModel: RequestDialogVM
) {
    var metricsName by remember { mutableStateOf(item.metricsId)}
    val enterFadeIn = remember {
        fadeIn(
            animationSpec = TweenSpec(
                durationMillis = Constants.FadeInAnimation,
                easing = FastOutLinearInEasing
            )
        )
    }
    var touched by remember {
        mutableStateOf(item.isChecked)
    }
    val enterExpand = remember {
        expandVertically(animationSpec = tween(Constants.ExpandAnimation))
    }
    val exitFadeOut = remember {
        fadeOut(
            animationSpec = TweenSpec(
                durationMillis = Constants.FadeOutAnimation,
                easing = LinearOutSlowInEasing
            )
        )
    }
    val changeColorOnClick = remember { mutableStateOf(if (touched) Color.Green else Color.White) }
    val changeElevationOnClick = remember { mutableStateOf(5.dp) }
    val changeCount = remember { mutableStateOf("1") }
    changeCount.value = item.itemCount
    val exitCollapse = remember {
        shrinkVertically(animationSpec = tween(Constants.CollapseAnimation))
    }

/*
    var changeColorOnClick by remember {
        mutableStateOf(Color.White)
    }
    var changeElevationOnClick by remember {
        mutableStateOf(5.dp)
    }
*/

    AnimatedVisibility(
        visible = visible,
        enter = enterExpand + enterFadeIn,
        exit = exitCollapse + exitFadeOut
    ) {
        Card(
            backgroundColor = Color.White,
            elevation = changeElevationOnClick.value,
            shape = RoundedCornerShape(3.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 4.dp,
                    vertical = 1.dp
                ),
            onClick = {
                /*          changeColorOnClick.value = if (!touched) Color.Green else Color.White
                          changeElevationOnClick.value = if (!touched) 20.dp else 5.dp
                          val newItem = item.copy(isSelected = !item.isSelected)
                          viewModel.selectItem(newItem)
                          touched = !item.isSelected*/
              //  println("1 child is ${item.name} clicked and color change to ${changeColorOnClick.toString()} and elevation to ${changeElevationOnClick}   touched is - ${touched} selected is ${!item.isChecked} ")

            }) {
            Column(
                Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray)
                ) {
                    Text(
                        text = "${item.name}",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(4.dp),
                        color = Color.Black
                    )
                }
                Divider(color = Color.LightGray, thickness = 1.dp)

                Row {
                    Row(modifier = Modifier.weight(3f, fill = true)) {
                        Text(
                            text = "Стоимость: ",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(4.dp),
                            color = Color.Black
                        )
                        Text(
                            text = "${item.itemPrice} руб. ",
                            modifier = Modifier
                                .padding(4.dp),
                            color = Color.Black
                        )
                    }
                    Row(modifier = Modifier.weight(1f, fill = true), horizontalArrangement = Arrangement.End) {
                        Text(
                            text = "за: ",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(4.dp),
                            color = Color.Black
                        )
                        Text(
                            text = metricsName,
                            modifier = Modifier
                                .padding(4.dp),
                            color = Color.Black
                        )
                    }

                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 50.dp),
                ) {
                    Text(
                        text = "колличество: ",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(4.dp),
                        color = Color.Black,
                        fontSize = 18.sp
                    )
                    TextField(
                        value = changeCount.value,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        onValueChange = { value ->
                            try {
                                changeCount.value = value
                                viewModel.updateRequestMaterialsItem(item.copy(itemCount = changeCount.value))
                            } catch (e: Exception){
                                println(e.message.toString())
                            }
                               },
                        modifier = Modifier

                            .fillMaxWidth()
                            .padding(end = 30.dp),
                        textStyle = LocalTextStyle.current.copy(
                            textAlign = TextAlign.Center,
                            color = Color.Black,
                            fontSize = 18.sp
                        ),
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    var count = 0
                                    if (changeCount.value.isEmpty()) {
                                        count = 0
                                        count++
                                        changeCount.value = count.toString()
                                    } else {
                                        count = changeCount.value.toInt()
                                        count++
                                        changeCount.value = count.toString()
                                    }
                                    viewModel.updateRequestMaterialsItem(item.copy(itemCount = changeCount.value))
                                }
                            ) {
                                Icon(
                                    Icons.Default.Add,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .padding(15.dp)
                                        .size(24.dp)
                                )
                            }
                        },
                        leadingIcon = {
                            IconButton(
                                onClick = {
                                    var count = 0
                                    if (changeCount.value.length == 0) {
                                        count = 0
                                        changeCount.value = "0"
                                    } else if (changeCount.value.toInt() > 0) {
                                        count = changeCount.value.toInt()
                                        count--
                                        changeCount.value = count.toString()
                                    }
                                    viewModel.updateRequestMaterialsItem(item.copy(itemCount = changeCount.value))
                                }
                            ) {
                                Icon(
                                    Icons.Default.Remove,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .padding(15.dp)
                                        .size(24.dp)
                                )
                            }
                        },
                        singleLine = true,
                        shape = RectangleShape, // The TextFiled has rounded corners top left and right by default
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.Black,
                            cursorColor = Color.White,
                            backgroundColor = Color.White,
                            leadingIconColor = Color.Black,
                            trailingIconColor = Color.Black,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        )
                    )
                }


            }
        }
    }
}


@Composable
@Preview
fun ChernovikChildItemPreview() {
/*    ChernovikSelectedChildItem(
        visible = true, item = JobsEntity(
            id = 1,
            category_id = "2",
            name = "Item 2",
            price = "${(100..20000).random()}",
            count = "1",
            metrics_id = "ШТ.",
            isSelected = false
        )
    )*/
}

