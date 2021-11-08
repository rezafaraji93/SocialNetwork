package com.faraji.socialnetwork.feature_post.presentation.create_post

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.faraji.socialnetwork.R
import com.faraji.socialnetwork.core.presentation.components.StandardTextField
import com.faraji.socialnetwork.core.presentation.components.StandardToolbar
import com.faraji.socialnetwork.core.presentation.ui.theme.SpaceMedium
import com.faraji.socialnetwork.core.presentation.ui.theme.SpaceSmall
import com.faraji.socialnetwork.core.presentation.util.CropActivityResultContract
import com.faraji.socialnetwork.core.presentation.util.UiEvent
import com.faraji.socialnetwork.core.presentation.util.asString
import com.faraji.socialnetwork.feature_post.presentation.util.PostConstants.MAX_POST_DESCRIPTION_LENGTH
import com.faraji.socialnetwork.feature_post.presentation.util.PostDescriptionError
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@ExperimentalCoilApi
@Composable
fun CreatePostScreen(
    navController: NavController,
    scaffoldState: ScaffoldState,
    viewModel: CreatePostViewModel = hiltViewModel()
) {

    val imageUri = viewModel.chosenImageUri.value

    val cropActivityLauncher = rememberLauncherForActivityResult(
        contract = CropActivityResultContract(16f, 9f)
    ) {
        viewModel.onEvent(CreatePostEvent.CropImage(it))
    }
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
        cropActivityLauncher.launch(it)
    }

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    GlobalScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = event.uiText.asString(context)
                        )
                    }
                }
                is UiEvent.NavigateUp -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Column {
        StandardToolbar(
            navController = navController,
            showBackArrow = false,
            title = {
                Text(
                    text = stringResource(id = R.string.create_post),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
            },
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(SpaceMedium)
        ) {
            Box(
                modifier = Modifier
                    .aspectRatio(16f / 9f)
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colors.onBackground,
                        shape = MaterialTheme.shapes.medium
                    )
                    .clickable {
                        galleryLauncher.launch("image/*")
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.choose_image),
                    tint = MaterialTheme.colors.onBackground
                )
                imageUri?.let { uri ->
                    Image(
                        painter = rememberImagePainter(
                            request = ImageRequest.Builder(LocalContext.current)
                                .data(uri)
                                .build()
                        ),
                        contentDescription = stringResource(id = R.string.post_image),
                        modifier = Modifier.matchParentSize()
                    )
                }
            }
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                modifier = Modifier.fillMaxWidth(),
                text = viewModel.descriptionState.value.text,
                hint = stringResource(id = R.string.description),
                error = when (viewModel.descriptionState.value.error) {
                    is PostDescriptionError.FieldEmpty -> stringResource(id = R.string.error_field_empty)
                    else -> ""
                },
                singleLine = false,
                maxLines = 5,
                maxLength = MAX_POST_DESCRIPTION_LENGTH,
                onValueChanged = {
                    viewModel.onEvent(CreatePostEvent.EnterDescription(it))
                }
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            Button(
                onClick = {
                    viewModel.onEvent(CreatePostEvent.PostImage)
                },
                enabled = !viewModel.isLoading.value,
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(
                    text = stringResource(id = R.string.post),
                    color = MaterialTheme.colors.onPrimary
                )
                Spacer(modifier = Modifier.width(SpaceSmall))
                if (viewModel.isLoading.value) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colors.onPrimary,
                        modifier = Modifier
                            .size(20.dp)
                            .align(CenterVertically)
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = null,
                        tint = MaterialTheme.colors.onPrimary
                    )
                }

            }

        }

    }
}