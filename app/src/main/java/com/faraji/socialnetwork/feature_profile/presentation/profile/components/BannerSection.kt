package com.faraji.socialnetwork.feature_profile.presentation.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.faraji.socialnetwork.R
import com.faraji.socialnetwork.core.presentation.ui.theme.SpaceSmall
import com.faraji.socialnetwork.presentation.util.toPx

@Composable
fun BannerSection(
    modifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    iconSize: Dp = 35.dp,
    bannerImageUrl: String? = null,
    leftIconModifier: Modifier = Modifier,
    rightIconModifier: Modifier = Modifier,
    topSkillUrls: List<String> = emptyList(),
    shouldShowGitHub: Boolean = false,
    shouldShowInstagram: Boolean = false,
    shouldShowLinkedIn: Boolean = false,
    onGitHubClick: () -> Unit = {},
    onInstagramClick: () -> Unit = {},
    onLinkedInClick: () -> Unit = {}
) {
    BoxWithConstraints(
        modifier = modifier
    ) {
        Image(
            painter = rememberImagePainter(
                data = bannerImageUrl,
                builder = {
                    crossfade(true)
                }
            ),
            contentDescription = stringResource(id = R.string.banner_image),
            contentScale = ContentScale.Crop,
            modifier = imageModifier
                .fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black
                        ),
                        startY = constraints.maxHeight - iconSize.toPx() * 2f
                    )
                )
        )
        Row(
            modifier = leftIconModifier
                .height(iconSize)
                .align(Alignment.BottomStart)
                .padding(SpaceSmall)
        ) {
            topSkillUrls.forEach { skillUrl ->
                Spacer(modifier = Modifier.width(SpaceSmall))
                Image(
                    painter = rememberImagePainter(
                        data = skillUrl,
                        builder = {
                            crossfade(true)
                        }
                    ),
                    contentDescription = "Javscript",
                    modifier = Modifier.height(iconSize)
                )
            }
        }
        Row(
            modifier = rightIconModifier
                .height(iconSize)
                .align(Alignment.BottomEnd)
                .padding(SpaceSmall)
        ) {
            if (shouldShowGitHub) {
                IconButton(
                    onClick = onGitHubClick,
                    modifier = Modifier.size(iconSize)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_github_icon),
                        contentDescription = "GitHub",
                        modifier = Modifier.size(iconSize)
                    )
                }
            }
            if (shouldShowInstagram) {
                IconButton(
                    onClick = onInstagramClick,
                    modifier = Modifier.size(iconSize)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_instagram),
                        contentDescription = "Instagram",
                        modifier = Modifier.size(iconSize)
                    )
                }
            }
            if (shouldShowLinkedIn) {
                IconButton(
                    onClick = onLinkedInClick,
                    modifier = Modifier.size(iconSize)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_linkedin_icon),
                        contentDescription = "LinkedIn",
                        modifier = Modifier.size(iconSize)
                    )
                }
            }
        }
    }
}