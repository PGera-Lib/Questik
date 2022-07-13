package xyz.gmfatoom.questik.ui.screens.chernovik.itogi.items


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun RequestItogListItem(background: Color, textStyle: TextStyle, title: String, sum: String) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(background)
        ) {
            Box(
                modifier = Modifier
                    .weight(3f)
            ) {
                Text(
                    text = title, style = textStyle, modifier = Modifier
                        .padding(4.dp)
                )
            }
            Box(
                modifier = Modifier
                    .weight(1.2f)
            ) {
                Text(
                    text = "$sum руб.", style = textStyle, modifier = Modifier
                        .padding(4.dp)
                )
            }
        }
    }
}

