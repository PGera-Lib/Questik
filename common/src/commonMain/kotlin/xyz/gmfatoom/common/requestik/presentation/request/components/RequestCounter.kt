package xyz.gmfatoom.common.requestik.presentation.request.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RequestCounter(text: String,
    modifier: Modifier = Modifier, background: Color, textColor: Color, fontSize : TextUnit = 10.sp
) {
    Box(
        modifier = modifier
            .size(15.dp)
            .clip(CircleShape)
            .background(background),
        contentAlignment = Alignment.Center
    ) {
        val textMeasurer = rememberTextMeasurer()
        val style = TextStyle(
            fontSize = fontSize,
            color = textColor
        )
        val textLayoutResult = remember(text) {
            textMeasurer.measure(text, style)
        }
        Canvas(modifier = Modifier.size(15.dp)) {
            drawText(
                textMeasurer = textMeasurer,
                text = text,
                style = style,
                topLeft = Offset(
                    x = center.x - textLayoutResult.size.width / 2,
                    y = center.y - textLayoutResult.size.height / 2,
                )
            )
/*            drawPoints(
                points = listOf(Offset(center.x, center.y)),
                pointMode = PointMode.Points,
                cap = StrokeCap.Round,
                color = Color.Red,
                strokeWidth = 1f
            )*/
        }
    }
}
@Composable
fun NullRequestCounter(
) {
    Box(
        modifier = Modifier
            .size(15.dp)
            .clip(CircleShape),
        contentAlignment = Alignment.Center
    ) {/*
        val textMeasurer = rememberTextMeasurer()
        val style = TextStyle(
            fontSize = fontSize,
            color = textColor
        )
        val textLayoutResult = remember(text) {
            textMeasurer.measure(text, style)
        }
        Canvas(modifier = Modifier.size(15.dp)) {
            drawText(
                textMeasurer = textMeasurer,
                text = text,
                style = style,
                topLeft = Offset(
                    x = center.x - textLayoutResult.size.width / 2,
                    y = center.y - textLayoutResult.size.height / 2,
                )
            )
            *//*            drawPoints(
                            points = listOf(Offset(center.x, center.y)),
                            pointMode = PointMode.Points,
                            cap = StrokeCap.Round,
                            color = Color.Red,
                            strokeWidth = 1f
                        )*//*
        }*/
    }
}