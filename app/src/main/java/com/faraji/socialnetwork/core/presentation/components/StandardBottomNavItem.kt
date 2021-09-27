package com.faraji.socialnetwork.core.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.faraji.socialnetwork.core.presentation.ui.theme.HintGray
import com.faraji.socialnetwork.core.presentation.ui.theme.SpaceSmall

@Composable
fun RowScope.StandardBottomNavItem(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    contentDescription: String? = null,
    selected: Boolean = false,
    alertCount: Int? = null,
    selectedColor: Color = MaterialTheme.colors.primary,
    unselectedColor: Color = HintGray,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    alertCount?.let {
        require(alertCount >= 0)
    }

    val lineLength = animateFloatAsState(
        targetValue = if (selected) 1f else 0f,
        animationSpec = tween(
            durationMillis = 200
        )
    )

    BottomNavigationItem(
        modifier = modifier,
        selected = selected,
        onClick = onClick,
        enabled = enabled,
        selectedContentColor = selectedColor,
        unselectedContentColor = unselectedColor,
        icon = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(SpaceSmall)
                    .drawBehind {
                        if (lineLength.value > 0)
                            drawLine(
                                color = selectedColor,
                                start = Offset(
                                    x = size.width / 2f - lineLength.value * 15.dp.toPx(),
                                    size.height
                                ),
                                end = Offset(
                                    x = size.width / 2f + lineLength.value * 15.dp.toPx(),
                                    size.height
                                ),
                                strokeWidth = 2.dp.toPx(),
                                cap = StrokeCap.Round
                            )
                    }
            ) {
                if (icon != null) {
                    Icon(
                        imageVector = icon,
                        contentDescription = contentDescription,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                if (alertCount != null) {
                    val alertText = if (alertCount > 99) "99+" else alertCount.toString()

                    Text(
                        text = alertText,
                        color = MaterialTheme.colors.onPrimary,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp,
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .offset(10.dp)
                            .size(15.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colors.primary)
                    )
                }
            }
        }
    )

}