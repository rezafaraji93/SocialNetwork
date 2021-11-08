package com.faraji.socialnetwork.feature_profile.presentation.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.faraji.socialnetwork.R
import com.faraji.socialnetwork.core.domain.models.User
import com.faraji.socialnetwork.core.presentation.ui.theme.TextWhite

@Composable
fun ProfileStats(
    user: User,
    modifier: Modifier = Modifier,
    isOwnProfile: Boolean,
    isFollowing: Boolean = false,
    onFollowClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProfileNumber(number = user.followerCount, text = stringResource(id = R.string.follower))
        ProfileNumber(number = user.followingCount, text = stringResource(id = R.string.following))
        ProfileNumber(number = user.postCount, text = stringResource(id = R.string.posts))

        if (!isOwnProfile) {
            Button(
                onClick = onFollowClick,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (isFollowing) Color.Red else MaterialTheme.colors.primary
                )
            ) {
                Text(
                    text = if (isFollowing) stringResource(id = R.string.unfollow) else stringResource(
                        id = R.string.follow
                    ),
                    style = TextStyle(
                        color = if (isFollowing) TextWhite else MaterialTheme.colors.onPrimary
                    )
                )
            }
        }

    }
}