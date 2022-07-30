package xyz.gmfatoom.questik.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Checkbox
import androidx.compose.material.RadioButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import xyz.gmfatoom.questik.R
import xyz.gmfatoom.questik.repo.local.room.entity.JobsEntity
import xyz.gmfatoom.questik.repo.local.room.entity.RequestsEntity
import xyz.gmfatoom.questik.ui.components.ThemeType.*
import xyz.gmfatoom.questik.ui.drawer.UserCardDrawer
import xyz.gmfatoom.questik.ui.screens.catalog.item.ChildItem
import xyz.gmfatoom.questik.ui.screens.chernovik.SetClentCard
import xyz.gmfatoom.questik.ui.screens.chernovik.items.SearchView
import xyz.gmfatoom.questik.ui.screens.requests.item.RequestScreenItem
import xyz.gmfatoom.questik.ui.theme.AppTheme

@SuppressLint("UnrememberedMutableState")
@Composable
fun SettingView(
    modifier: Modifier = Modifier,
    darkMode: MutableState<Boolean>,
    themeType: MutableState<ThemeType>
) {

    val cardExpandState = remember{ mutableStateOf(false)}
    val scrollState = rememberScrollState()
    Surface(modifier = modifier.scrollable(state = scrollState, orientation = Orientation.Vertical)) {

        LazyColumn{
            item{
                DarkModeCheckBox(darkMode)
                SmallSpacer()
            }
            item{
                ThemePickRadioGroup(themeType)
                LargeSpacer()
            }
            item{
                UserCardDrawer(0f,cardExpandState)
            }
            item{
                SetClentCard()
            }
            item{

                SearchView(state = mutableStateOf(TextFieldValue("")))
            }
            item{

                ChildItem(visible = true, item = JobsEntity(id=0, name="Наименование", price="1500") )
            }

            item{
      /*          RequestScreenItem(
                    req = RequestsEntity(
                        id = 0,
                        name = "test-test",
                        user_id = 1,
                        corp_id = 1,
                        object_id = 1,
                        data_create = "02.02.2022 22:22",
                        data_start = "02.02.2022 22:22",
                        data_end = "02.02.2022 22:22"
                    )
                )*/
            }
        }
    }
}

@Composable
fun DarkModeCheckBox(darkMode: MutableState<Boolean>) {
    Row {
        Checkbox(
            checked = darkMode.value,
            onCheckedChange = { checked -> darkMode.value = checked },
        )
        SmallSpacer()
        Text(text = stringResource(R.string.dark_mode))
    }
}

@Composable
fun ThemePickRadioGroup(themeType: MutableState<ThemeType>) {
    Column(Modifier.fillMaxWidth()) {
        Text(text = stringResource(R.string.theme_select))
        TinySpacer()
        LazyRow(Modifier.fillMaxWidth()) {
            item{
                TinySpacer()
                RadioButton(selected = themeType.value == GRAYGREEN, onClick = {
                    themeType.value = GRAYGREEN
                })
                Text(
                    text = GRAYGREEN.name,
                    modifier = Modifier
                        .clickable(onClick = { themeType.value = GRAYGREEN })
                        .padding(start = AppTheme.paddings.tinyPadding)
                )
            }
            item{
                TinySpacer()
                RadioButton(selected = themeType.value == YELLOW, onClick = {
                    themeType.value = YELLOW
                })
                Text(
                    text = YELLOW.name,
                    modifier = Modifier
                        .clickable(onClick = { themeType.value = YELLOW })
                        .padding(start = AppTheme.paddings.tinyPadding)
                )
            }
          item {

              TinySpacer()
              RadioButton(selected = themeType.value == WHITE, onClick = {
                  themeType.value = WHITE
              })
              Text(
                  text = WHITE.name,
                  modifier = Modifier
                      .clickable(onClick = { themeType.value = WHITE })
                      .padding(start = AppTheme.paddings.tinyPadding)
              )
          }
/*            item {
            TinySpacer()
            RadioButton(selected = themeType.value == PINK, onClick = {
                themeType.value = PINK
            })
            Text(
                text = PINK.name,
                modifier = Modifier
                    .clickable(onClick = { themeType.value = PINK })
                    .padding(start = AppTheme.paddings.tinyPadding)
            )
            }*/
        }
    }
}

enum class ThemeType {
    GRAYGREEN,
    YELLOW,
    WHITE/*,
    PINK*/
}
