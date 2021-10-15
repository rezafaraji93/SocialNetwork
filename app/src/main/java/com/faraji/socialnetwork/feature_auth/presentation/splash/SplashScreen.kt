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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.faraji.socialnetwork.R
import com.faraji.socialnetwork.core.presentation.util.UiEvent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.withContext

@Composable
fun SplashScreen(
    navController: NavController,
    dispatcher: CoroutineDispatcher = Dispatchers.Main,
    viewModel: SplashViewModel = hiltViewModel()
) {

    val scale = remember {
        Animatable(0f)
    }
    val overshootInterpolator = remember {
        OvershootInterpolator(2f)
    }
    LaunchedEffect(key1 = true) {
        withContext(dispatcher) {
            scale.animateTo(
                1f,
                animationSpec = tween(
                    durationMillis = 1000,
                    easing = {
                        overshootInterpolator.getInterpolation(it)
                    }
                )
            )
        }
    }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.Navigate -> {
                    navController.popBackStack()
                    navController.navigate(event.route)
                }
                else -> Unit
            }
        }
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