package xyz.gmfatoom.questik.ui.base


import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import xyz.gmfatoom.questik.R
import xyz.gmfatoom.questik.ui.drawer.DrawerScreens
import xyz.gmfatoom.questik.utils.APP_ACTIVITY
import xyz.gmfatoom.questik.utils.AUTH

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }
    val startDestination = remember {
        if(AUTH.currentUser !=null) DrawerScreens.Home.route else DrawerScreens.Login.route
    }
    val infiniteTransition = rememberInfiniteTransition()
    val rotationAngle  by infiniteTransition.animateFloat(
        initialValue = 360.0f,
        targetValue = 0.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(1800, delayMillis = 0,easing = FastOutLinearInEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    val imageSize  by infiniteTransition.animateFloat(
            initialValue = 50.0f,
    targetValue = 150.0f,
    animationSpec = infiniteRepeatable(
        animation = tween(1800, delayMillis = 1000,easing = FastOutLinearInEasing),
        repeatMode = RepeatMode.Reverse
    )
    )

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )
        delay(0L)
        navController.navigate(startDestination)
    }

    // ржака
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.dildo),
            contentDescription = "loader",
            modifier = Modifier
                .rotate(rotationAngle)
                .alpha(1.0f)
                .offset(100.dp, 0.dp)
                .rotate(-40f)
               /* .size(imageSize.dp)
                .scale(scale.value)*/

                )
    }
}