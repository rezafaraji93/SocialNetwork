package com.faraji.socialnetwork.feature_auth.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faraji.socialnetwork.core.domain.states.StandardTextFieldState
import com.faraji.socialnetwork.core.presentation.util.UiEvent
import com.faraji.socialnetwork.core.util.Resource
import com.faraji.socialnetwork.core.util.Screen
import com.faraji.socialnetwork.core.util.UiText
import com.faraji.socialnetwork.feature_auth.domain.user_case.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _emailState = mutableStateOf(StandardTextFieldState())
    val emailState: State<StandardTextFieldState> = _emailState

    private val _passwordState = mutableStateOf(StandardTextFieldState())
    val passwordState: State<StandardTextFieldState> = _passwordState

    private val _loginState = mutableStateOf(LoginState())
    val loginState: State<LoginState> = _loginState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EnteredEmail -> {
                _emailState.value = emailState.value.copy(
                    text = event.email
                )
            }
            is LoginEvent.EnteredPassword -> {
                _passwordState.value = passwordState.value.copy(
                    text = event.password
                )
            }
            is LoginEvent.TogglePasswordVisibility -> {
                _loginState.value = loginState.value.copy(
                    isPasswordVisible = !_loginState.value.isPasswordVisible
                )
            }
            is LoginEvent.Login -> {
                viewModelScope.launch {
                    _emailState.value = emailState.value.copy(error = null)
                    _passwordState.value = passwordState.value.copy(error = null)
                    _loginState.value = loginState.value.copy(
                        isLoading = true
                    )
                    val loginResult = loginUseCase.invoke(
                        email = emailState.value.text,
                        password = passwordState.value.text
                    )
                    _loginState.value = loginState.value.copy(
                        isLoading = false
                    )
                    if (loginResult.emailError != null) {
                        _emailState.value = emailState.value.copy(
                            error = loginResult.emailError
                        )
                    }
                    if (loginResult.passwordError != null) {
                        _passwordState.value = passwordState.value.copy(
                            error = loginResult.passwordError
                        )
                    }

                    when (loginResult.result) {
                        is Resource.Success -> {
                            _eventFlow.emit(
                                UiEvent.Navigate(Screen.MainFeedScreen.route)
                            )
                            _loginState.value = LoginState(isLoading = false)
                            _emailState.value = StandardTextFieldState()
                            _emailState.value = StandardTextFieldState()
                        }
                        is Resource.Error -> {
                            _eventFlow.emit(
                                UiEvent.ShowSnackbar(
                                    uiText = loginResult.result.uiText ?: UiText.unknownError()
                                )
                            )
                        }
                    }
                }
            }
        }
    }


}