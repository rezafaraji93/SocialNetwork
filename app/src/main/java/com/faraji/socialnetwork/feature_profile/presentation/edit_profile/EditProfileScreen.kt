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
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.faraji.socialnetwork.R
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
    val profileState = viewModel.profileState.value
    val usernameState = viewModel.userNameState.value
    val githubState = viewModel.githubTextFieldState.value
    val instagramState = viewModel.instagramTextFieldState.value
    val linkedInState = viewModel.linkedinTextFieldState.value
    val bioState = viewModel.bioState.value

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
                bannerImage = rememberImagePainter(
                    data = profileState.profile?.bannerUrl,
                    builder = {
                        crossfade(true)
                    }
                ),
                profileImage = rememberImagePainter(
                    data = profileState.profile?.profilePictureUrl,
                    builder = {
                        crossfade(true)
                    }
                ),
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
                    text = usernameState.text,
                    hint = stringResource(id = R.string.username),
                    error = when (usernameState.error) {
                        is EditProfileError.FieldEmpty -> stringResource(
                            id = R.string.error_field_empty
                        )
                        else -> ""
                    },
                    leadingIcon = Icons.Default.Person,
                    onValueChanged = {
                        viewModel.onEvent(EditProfileEvent.EnteredUsername(it))
                    }
                )
                Spacer(modifier = Modifier.height(SpaceMedium))
                StandardTextField(
                    modifier = Modifier.fillMaxWidth(),
                    text = githubState.text,
                    hint = stringResource(id = R.string.github_profile_url),
                    error = when (githubState.error) {
                        is EditProfileError.FieldEmpty -> stringResource(
                            id = R.string.error_field_empty
                        )
                        else -> ""
                    },
                    leadingIcon = ImageVector.vectorResource(id = R.drawable.ic_github_icon),
                    onValueChanged = {
                        viewModel.onEvent(EditProfileEvent.EnteredGithubUrl(it))
                    }
                )
                Spacer(modifier = Modifier.height(SpaceMedium))
                StandardTextField(
                    modifier = Modifier.fillMaxWidth(),
                    text = instagramState.text,
                    hint = stringResource(id = R.string.instagram_profile_url),
                    error = when (instagramState.error) {
                        is EditProfileError.FieldEmpty -> stringResource(
                            id = R.string.error_field_empty
                        )
                        else -> ""
                    },
                    leadingIcon = ImageVector.vectorResource(id = R.drawable.ic_instagram),
                    onValueChanged = {
                        viewModel.onEvent(EditProfileEvent.EnteredInstagramUrl(it))
                    }
                )
                Spacer(modifier = Modifier.height(SpaceMedium))
                StandardTextField(
                    modifier = Modifier.fillMaxWidth(),
                    text = linkedInState.text,
                    hint = stringResource(id = R.string.linkedin_profile_url),
                    error = when (linkedInState.error) {
                        is EditProfileError.FieldEmpty -> stringResource(
                            id = R.string.error_field_empty
                        )
                        else -> ""
                    },
                    leadingIcon = ImageVector.vectorResource(id = R.drawable.ic_linkedin_icon),
                    onValueChanged = {
                        viewModel.onEvent(EditProfileEvent.EnteredLinkedInUrl(it))
                    }
                )
                Spacer(modifier = Modifier.height(SpaceMedium))
                StandardTextField(
                    modifier = Modifier.fillMaxWidth(),
                    text = bioState.text,
                    hint = stringResource(id = R.string.bio),
                    error = when (bioState.error) {
                        is EditProfileError.FieldEmpty -> stringResource(
                            id = R.string.error_field_empty
                        )
                        else -> ""
                    },
                    singleLine = false,
                    maxLines = 3,
                    leadingIcon = Icons.Default.Description,
                    onValueChanged = {
                        viewModel.onEvent(EditProfileEvent.EnteredBio(it))
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