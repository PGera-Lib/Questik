package xyz.gmfatoom.questik.ui.screens.catalog.item

import android.annotation.SuppressLint
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import xyz.gmfatoom.questik.R
import xyz.gmfatoom.questik.repo.local.room.entity.CategoryEntity
import xyz.gmfatoom.questik.ui.screens.catalog.CatalogViewModel
import xyz.gmfatoom.questik.utils.Constants



@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun CategoryHeader(
    initial: CategoryEntity,
    onCardArrowClick: () -> Unit,
    expanded: Boolean,
    viewModel: CatalogViewModel,
    size: Int
) {
    val transitionState = viewModel.expandableCategoryList.collectAsState()
    val transition = updateTransition(targetState = transitionState, label = "transition")

/*    val cardBgColor by transition.animateColor({
        tween(durationMillis = Constants.ExpandAnimation)
    }, label = "bgColorTransition") {
        if (expanded) Color.Black else Color.Blue
    }*/
    val cardPaddingHorizontal by transition.animateDp({
        tween(durationMillis = Constants.ExpandAnimation)
    }, label = "paddingTransition") {
        4.dp
    }
    val cardElevation by transition.animateDp({
        tween(durationMillis = Constants.ExpandAnimation)
    }, label = "elevationTransition") {
        if (expanded) 20.dp else 5.dp
    }
    val cardRoundedCorners by transition.animateDp({
        tween(
            durationMillis = Constants.ExpandAnimation,
            easing = FastOutSlowInEasing
        )
    }, label = "cornersTransition") {
        3.dp
    }
    val arrowRotationDegree by transition.animateFloat({
        tween(durationMillis = Constants.ExpandAnimation)
    }, label = "rotationDegreeTransition") {
        if (expanded) 180f else 0f

    }

    Card(/*
        backgroundColor = cardBgColor,*/
        elevation = cardElevation,
        shape = RoundedCornerShape(cardRoundedCorners),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = cardPaddingHorizontal,
                vertical = 1.dp
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.weight(0.85f)
                    ) {
                        Text(
                            text = initial.name,/*
                            color = Color.White,*/
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp)
                        )

                    }
                    Column(
                        modifier = Modifier.weight(0.5f)
                    ) {
                        Text(
                            text = "$size",
                            color = Color.White,
                            textAlign = TextAlign.End,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp)
                        )
                    }
                    Column(
                        modifier = Modifier.weight(0.15f)
                    ) {
                        CardArrow(
                            degrees = arrowRotationDegree,
                            onClick = onCardArrowClick
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun CardArrow(
    degrees: Float,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        content = {

            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_keyboard_arrow_down_24),
                contentDescription = "Expandable Arrow",
                modifier = Modifier.rotate(degrees),
                tint = Color.White
            )
        }
    )
}
