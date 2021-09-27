package com.faraji.socialnetwork.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.faraji.socialnetwork.R
import com.faraji.socialnetwork.core.domain.models.User
import com.faraji.socialnetwork.core.presentation.ui.theme.IconSizeMedium
import com.faraji.socialnetwork.core.presentation.ui.theme.ProfilePictureSizeSmall
import com.faraji.socialnetwork.core.presentation.ui.theme.SpaceMedium
import com.faraji.socialnetwork.core.presentation.ui.theme.SpaceSmall

@ExperimentalMaterialApi
@Composable
fun UserProfileItem(
    user: User,
    modifier: Modifier = Modifier,
    actionIcon: @Composable () -> Unit = {},
    onItemCLick: () -> Unit = {},
    onActionItemClick: () -> Unit = {},
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        onClick = onItemCLick,
        elevation = 5.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = SpaceMedium,
                    vertical = SpaceSmall
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.reza),
                contentDescription = stringResource(
                    id = R.string.profile_image
                ),
                modifier = Modifier
                    .size(ProfilePictureSizeSmall)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.8f)
                    .padding(horizontal = SpaceSmall)
            ) {
                Text(
                    text = user.username,
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.Bold
                    ),
                )
                Spacer(modifier = Modifier.height(SpaceSmall))
                Text(
                    text = user.description,
                    style = MaterialTheme.typography.body2,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
            }
            IconButton(
                onClick = onActionItemClick,
                modifier = Modifier
                    .size(IconSizeMedium)
            ) {
                actionIcon()
            }

        }
    }
}