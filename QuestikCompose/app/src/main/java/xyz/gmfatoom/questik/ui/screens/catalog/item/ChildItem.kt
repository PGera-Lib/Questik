package xyz.gmfatoom.questik.ui.screens.catalog.item

import android.annotation.SuppressLint

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import xyz.gmfatoom.questik.repo.local.room.entity.JobsEntity
import xyz.gmfatoom.questik.ui.screens.catalog.CatalogViewModel
import xyz.gmfatoom.questik.utils.Constants
import androidx.lifecycle.viewmodel.compose.*


@JvmOverloads
@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedTransitionTargetStateParameter")
@ExperimentalAnimationApi
@Composable
fun ChildItem(visible: Boolean, item: JobsEntity) {
    val enterFadeIn = remember {
        fadeIn(
            animationSpec = TweenSpec(
                durationMillis = Constants.FadeInAnimation,
                easing = FastOutLinearInEasing
            )
        )
    }
    var touched by remember {
        mutableStateOf(false)
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
    val exitCollapse = remember {
        shrinkVertically(animationSpec = tween(Constants.CollapseAnimation))
    }

/*    var changeColorOnClick by remember {
        mutableStateOf(Color.White)
    }*/
    var changeElevationOnClick by remember {
        mutableStateOf(5.dp)
    }

    AnimatedVisibility(
        visible = visible,
        enter = enterExpand + enterFadeIn,
        exit = exitCollapse + exitFadeOut
    ) {

        Card(/*
            backgroundColor = changeColorOnClick,*/
            elevation = changeElevationOnClick,
            shape = RoundedCornerShape(3.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 4.dp,
                    vertical = 1.dp
                ),
            onClick = {
                touched = !touched
         /*       changeColorOnClick = if (touched) Color.LightGray else Color.White*/
                changeElevationOnClick = if (touched) 20.dp else 5.dp
                println("child is ${item.name} clicked and color change to and elevation to ${changeElevationOnClick}   touched is - ${touched} ")
            }) {
            Column() {
                Row(
                    Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "${item.name}",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(4.dp)
                    )
                }

                Row {
                    Row(modifier = Modifier.weight(2.0f, true)){
                        Text(
                            text = "Стоимость: ",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(4.dp)
                        )
                        Text(
                            text = "${item.price} руб. ",
                            modifier = Modifier
                                .padding(4.dp)
                        )
                    }
                    Row(modifier = Modifier.weight(2.0f, true)){
                        Text(
                            text = "за: ",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(4.dp)
                        )
                        Text(
                            text = item.metrics_id,
                            modifier = Modifier

                                .padding(4.dp)
                        )
                    }

                }

            }

        }
    }
}

