package com.faraji.socialnetwork.feature_auth.presentation.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.faraji.socialnetwork.R
import com.faraji.socialnetwork.core.util.Constants
import com.faraji.socialnetwork.core.util.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
) {
    val scale = remember {
        Animatable(0f)
    }
    val overshootInterpolator = remember {
        OvershootInterpolator(2f)
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            1f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = {
                    overshootInterpolator.getInterpolation(it)
                }
            )
        )
        delay(Constants.SPLASH_SCREEN_DURATION)
        navController.popBackStack()
        navController.navigate(Screen.LoginScreen.route)

    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.logoo),
            contentDescription = "Logo",
            modifier = Modifier
                .size(120.dp)
                .scale(scale.value)
        )
    }
}