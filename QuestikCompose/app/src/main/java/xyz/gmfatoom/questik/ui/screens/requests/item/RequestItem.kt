package xyz.gmfatoom.questik.ui.screens.requests.item

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import xyz.gmfatoom.questik.repo.local.room.entity.ContactsEntity
import xyz.gmfatoom.questik.repo.local.room.entity.CorpEntity
import xyz.gmfatoom.questik.repo.local.room.entity.ObjectsEntity
import xyz.gmfatoom.questik.repo.local.room.entity.RequestsEntity
import xyz.gmfatoom.questik.ui.drawer.DrawerScreens
import xyz.gmfatoom.questik.ui.theme.AppTheme


@Composable
fun RequestScreenItem(
    req: RequestsEntity,
    reqObject: ObjectsEntity,
    contacts: ContactsEntity,
    corp: CorpEntity,
    navController: NavController
) {
    val expandCard = remember { mutableStateOf(false) } // Expand State
    val rotationCardArrowState by animateFloatAsState(if (expandCard.value) 180f else 0f) // Rotation State
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp)
        .clickable {
            navController.navigate(DrawerScreens.RequestEditor.route + "?reqId=${req.id}")
        }, elevation = AppTheme.elevations.card) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(4.dp)) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Задача: ")
                Text(text = "${req.name}")
                Text(text = "${req.data_start}")
                IconButton(onClick = { expandCard.value = !expandCard.value }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Расширение карты",
                        modifier = Modifier
                            .padding(
                                horizontal = 4.dp,
                                vertical = 4.dp
                            )
                            .clip(CircleShape)
                            .size(20.dp)
                            .rotate(rotationCardArrowState)
                    )
                }
            }
            if (expandCard.value) {
                Divider(color = Color.LightGray, thickness = 1.dp)
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                    Text(text = "Обьект:  - ${reqObject.objects_name}")
                }
                Divider(color = Color.LightGray, thickness = 1.dp)
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "Клиент : ")
                }
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    Text(text = " - юр.лицо: ${corp.company_name}")
                }
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    Text(text = " - контакт: ${contacts.contact_name} (${contacts.contact_phone}) ")
                }
                Divider(color = Color.LightGray, thickness = 1.dp)
                LazyRow(
                    Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    item {
                        Text(text = "создан:", style = MaterialTheme.typography.caption)
                        Text(text = "${req.data_create} ", style = MaterialTheme.typography.caption)
                    }
                    item {
                        Text(text = "выезд: ", style = MaterialTheme.typography.caption)
                        Text(text = "${req.data_start}", style = MaterialTheme.typography.caption)
                    }
                    item {
                        Text(text = "закрыт: ", style = MaterialTheme.typography.caption)
                        Text(text = "${req.data_end}", style = MaterialTheme.typography.caption)
                    }

                }
            }
        }
    }
}


@Composable
@Preview
fun PreviewRequestScreenItem() {
/*    RequestScreenItem(
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