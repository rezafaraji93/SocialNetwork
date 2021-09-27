package com.faraji.socialnetwork.feature_profile.presentation.edit_profile

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.faraji.socialnetwork.R
import com.faraji.socialnetwork.core.domain.states.StandardTextFieldState
import com.faraji.socialnetwork.core.presentation.components.StandardTextField
import com.faraji.socialnetwork.core.presentation.components.StandardToolbar
import com.faraji.socialnetwork.core.presentation.ui.theme.ProfilePictureSizeLarge
import com.faraji.socialnetwork.core.presentation.ui.theme.SpaceLarge
import com.faraji.socialnetwork.core.presentation.ui.theme.SpaceMedium
import com.faraji.socialnetwork.core.presentation.ui.theme.SpaceSmall
import com.faraji.socialnetwork.feature_profile.presentation.edit_profile.components.Chip
import com.faraji.socialnetwork.feature_profile.presentation.util.EditProfileError
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import kotlin.random.Random

@Composable
fun EditProfileScreen(
    navController: NavController,
    viewModel: EditProfileViewModel = hiltViewModel(),
    profilePictureSize: Dp = ProfilePictureSizeLarge
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        StandardToolbar(
            navController = navController,
            showBackArrow = true,
            navActions = {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = stringResource(id = R.string.save_changes),
                        tint = MaterialTheme.colors.onBackground
                    )
                }
            },
            title = {
                Text(
                    text = stringResource(id = R.string.edit_your_profile),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            BannerEditSection(
                bannerImage = painterResource(id = R.drawable.android),
                profileImage = painterResource(id = R.drawable.reza),
                profilePictureSize = profilePictureSize
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(SpaceLarge)
            ) {
                Spacer(modifier = Modifier.height(SpaceMedium))
                StandardTextField(
                    modifier = Modifier.fillMaxWidth(),
                    text = viewModel.userNameState.value.text,
                    hint = stringResource(id = R.string.username),
                    error = when (viewModel.userNameState.value.error) {
                        is EditProfileError.FieldEmpty -> stringResource(
                            id = R.string.this_field_cant_be_empty
                        )
                        else -> ""
                    },
                    leadingIcon = Icons.Default.Person,
                    onValueChanged = {
                        viewModel.setUsernameState(
                            StandardTextFieldState(text = it)
                        )
                    }
                )
                Spacer(modifier = Modifier.height(SpaceMedium))
                StandardTextField(
                    modifier = Modifier.fillMaxWidth(),
                    text = viewModel.githubTextFieldState.value.text,
                    hint = stringResource(id = R.string.github_profile_url),
                    error = when (viewModel.githubTextFieldState.value.error) {
                        is EditProfileError.FieldEmpty -> stringResource(
                            id = R.string.this_field_cant_be_empty
                        )
                        else -> ""
                    },
                    leadingIcon = ImageVector.vectorResource(id = R.drawable.ic_github_icon),
                    onValueChanged = {
                        viewModel.setGithubTextFieldState(
                            StandardTextFieldState(text = it)
                        )
                    }
                )
                Spacer(modifier = Modifier.height(SpaceMedium))
                StandardTextField(
                    modifier = Modifier.fillMaxWidth(),
                    text = viewModel.instagramTextFieldState.value.text,
                    hint = stringResource(id = R.string.instagram_profile_url),
                    error = when (viewModel.instagramTextFieldState.value.error) {
                        is EditProfileError.FieldEmpty -> stringResource(
                            id = R.string.this_field_cant_be_empty
                        )
                        else -> ""
                    },
                    leadingIcon = ImageVector.vectorResource(id = R.drawable.ic_instagram),
                    onValueChanged = {
                        viewModel.setInstagramTextFieldState(
                            StandardTextFieldState(text = it)
                        )
                    }
                )
                Spacer(modifier = Modifier.height(SpaceMedium))
                StandardTextField(
                    modifier = Modifier.fillMaxWidth(),
                    text = viewModel.linkedinTextFieldState.value.text,
                    hint = stringResource(id = R.string.linkedin_profile_url),
                    error = when (viewModel.linkedinTextFieldState.value.error) {
                        is EditProfileError.FieldEmpty -> stringResource(
                            id = R.string.this_field_cant_be_empty
                        )
                        else -> ""
                    },
                    leadingIcon = ImageVector.vectorResource(id = R.drawable.ic_linkedin_icon),
                    onValueChanged = {
                        viewModel.setLinkedinTextFieldState(
                            StandardTextFieldState(text = it)
                        )
                    }
                )
                Spacer(modifier = Modifier.height(SpaceMedium))
                StandardTextField(
                    modifier = Modifier.fillMaxWidth(),
                    text = viewModel.bioState.value.text,
                    hint = stringResource(id = R.string.bio),
                    error = when (viewModel.bioState.value.error) {
                        is EditProfileError.FieldEmpty -> stringResource(
                            id = R.string.this_field_cant_be_empty
                        )
                        else -> ""
                    },
                    singleLine = false,
                    maxLines = 3,
                    leadingIcon = Icons.Default.Description,
                    onValueChanged = {
                        viewModel.setBioState(
                            StandardTextFieldState(text = it)
                        )
                    }
                )
                Spacer(modifier = Modifier.height(SpaceMedium))
                Text(
                    text = stringResource(id = R.string.top_3_skills),
                    style = MaterialTheme.typography.h2,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(SpaceMedium))
                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    mainAxisAlignment = MainAxisAlignment.Center,
                    mainAxisSpacing = SpaceSmall,
                    crossAxisSpacing = SpaceMedium
                ) {
                    listOf(
                        "Kotlin",
                        "JavaScript",
                        "Java",
                        "Python",
                        "C++",
                        "PHP",
                        "Swift",
                        "Go",
                        "C#",
                    ).forEach {
                        Chip(
                            text = it,
                            selected = Random.nextInt(2) == 0
                        ) {

                        }

                    }
                }
            }
        }
    }
}

@Composable
fun BannerEditSection(
    bannerImage: Painter,
    profileImage: Painter,
    onBannerClick: () -> Unit = {},
    onProfileImageClick: () -> Unit = {},
    profilePictureSize: Dp = ProfilePictureSizeLarge
) {
    val bannerHeight = (LocalConfiguration.current.screenWidthDp / 2.5f).dp
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(bannerHeight + profilePictureSize / 2f)
    ) {
        Image(
            painter = bannerImage,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(bannerHeight)
                .clickable {
                    onBannerClick()
                }
        )
        Image(
            painter = profileImage,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .size(profilePictureSize)
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colors.onSurface,
                    shape = CircleShape
                )
                .clickable {
                    onProfileImageClick()
                }
        )
    }
}