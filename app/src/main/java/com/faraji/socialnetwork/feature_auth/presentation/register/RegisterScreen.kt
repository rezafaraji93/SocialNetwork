package com.faraji.socialnetwork.feature_auth.presentation.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.faraji.socialnetwork.R
import com.faraji.socialnetwork.core.presentation.components.StandardTextField
import com.faraji.socialnetwork.core.presentation.ui.theme.SpaceLarge
import com.faraji.socialnetwork.core.presentation.ui.theme.SpaceMedium
import com.faraji.socialnetwork.core.presentation.util.UiEvent
import com.faraji.socialnetwork.core.presentation.util.asString
import com.faraji.socialnetwork.core.util.Constants
import com.faraji.socialnetwork.feature_auth.presentation.util.AuthError
import kotlinx.coroutines.flow.collectLatest

@Composable
fun RegisterScreen(
    navController: NavController,
    scaffoldState: ScaffoldState,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val usernameState = viewModel.usernameState.value
    val emailState = viewModel.emailState.value
    val passwordState = viewModel.passwordState.value
    val registerState = viewModel.registerState.value
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        event.uiText.asString(context),
                        duration = SnackbarDuration.Short
                    )
                }
            }

        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = SpaceLarge,
                end = SpaceLarge,
                top = SpaceLarge,
                bottom = 50.dp
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.register),
                style = MaterialTheme.typography.h1
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                text = emailState.text,
                onValueChanged = {
                    viewModel.onEvent(RegisterEvent.EnteredEmail(it))
                },
                error = when (emailState.error) {
                    is AuthError.FieldEmpty -> stringResource(
                        id = R.string.error_field_empty
                    )
                    is AuthError.InvalidEmail -> stringResource(
                        id = R.string.not_a_valid_email
                    )
                    else -> ""
                },
                hint = stringResource(id = R.string.email),
                keyboardType = KeyboardType.Email
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                text = usernameState.text,
                onValueChanged = {
                    viewModel.onEvent(RegisterEvent.EnteredUsername(it))
                },
                error = when (viewModel.usernameState.value.error) {
                    is AuthError.InputTooShort -> stringResource(
                        id = R.string.input_too_short, Constants.MIN_USERNAME_LENGTH
                    )
                    is AuthError.FieldEmpty -> stringResource(
                        id = R.string.error_field_empty
                    )
                    else -> ""
                },
                hint = stringResource(id = R.string.username),
                keyboardType = KeyboardType.Email
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                text = passwordState.text,
                onValueChanged = {
                    viewModel.onEvent(RegisterEvent.EnteredPassword(it))
                },
                error = when (passwordState.error) {
                    is AuthError.InputTooShort -> stringResource(
                        id = R.string.input_too_short, Constants.MIN_PASSWORD_LENGTH
                    )
                    is AuthError.FieldEmpty -> stringResource(
                        id = R.string.error_field_empty
                    )
                    is AuthError.InvalidPassword -> {
                        stringResource(id = R.string.invalid_password)
                    }
                    else -> ""
                },
                hint = stringResource(id = R.string.password_hint),
                keyboardType = KeyboardType.Password,
                showPasswordToggle = passwordState.isPasswordVisible,
                onPasswordToggleClick = {
                    viewModel.onEvent(RegisterEvent.TogglePasswordVisibility)
                }
            )

            Spacer(modifier = Modifier.height(SpaceMedium))

            Button(
                onClick = {
                    viewModel.onEvent(RegisterEvent.Register)
                },
                enabled = !registerState.isLoading,
                modifier = Modifier
                    .align(Alignment.End)
            ) {
                Text(
                    text = stringResource(id = R.string.register),
                    color = MaterialTheme.colors.onPrimary
                )
            }

            if (registerState.isLoading) {
                CircularProgressIndicator()
            }
        }


        Text(
            text = buildAnnotatedString {
                append(stringResource(id = R.string.already_have_an_account))
                append(" ")
                val signUpText = stringResource(id = R.string.sign_in)
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colors.primary
                    )
                ) {
                    append(signUpText)
                }
            },
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .clickable {
                    navController.popBackStack()

                }
        )
    }
}