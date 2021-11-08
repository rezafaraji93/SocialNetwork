package com.faraji.socialnetwork.feature_profile.presentation.edit_profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faraji.socialnetwork.R
import com.faraji.socialnetwork.core.domain.states.StandardTextFieldState
import com.faraji.socialnetwork.core.presentation.util.UiEvent
import com.faraji.socialnetwork.core.util.Resource
import com.faraji.socialnetwork.core.util.UiText
import com.faraji.socialnetwork.feature_profile.domain.use_case.ProfileUseCases
import com.faraji.socialnetwork.feature_profile.presentation.profile.ProfileState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val profileUseCases: ProfileUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _usernameState = mutableStateOf(StandardTextFieldState())
    val userNameState: State<StandardTextFieldState> = _usernameState

    private val _githubTextFieldState = mutableStateOf(StandardTextFieldState())
    val githubTextFieldState: State<StandardTextFieldState> = _githubTextFieldState

    private val _instagramTextFieldState = mutableStateOf(StandardTextFieldState())
    val instagramTextFieldState: State<StandardTextFieldState> = _instagramTextFieldState

    private val _linkedinTextFieldState = mutableStateOf(StandardTextFieldState())
    val linkedinTextFieldState: State<StandardTextFieldState> = _linkedinTextFieldState

    private val _bioState = mutableStateOf(StandardTextFieldState())
    val bioState: State<StandardTextFieldState> = _bioState

    private val _skills = mutableStateOf(SkillsState())
    val skills: State<SkillsState> = _skills

    private val _profileState = mutableStateOf(ProfileState())
    val profileState: State<ProfileState> = _profileState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<String>("userId")?.let { userId ->
            viewModelScope.launch {
                getProfile(userId)
                getSkills()

            }
        }
    }

    fun onEvent(event: EditProfileEvent) {
        when (event) {
            is EditProfileEvent.CropBannerImage -> {

            }
            is EditProfileEvent.CropProfilePicture -> {

            }
            is EditProfileEvent.EnteredBio -> {
                _bioState.value = bioState.value.copy(
                    text = event.value
                )
            }
            is EditProfileEvent.EnteredGithubUrl -> {
                _githubTextFieldState.value = githubTextFieldState.value.copy(
                    text = event.value
                )
            }
            is EditProfileEvent.EnteredInstagramUrl -> {
                _instagramTextFieldState.value = instagramTextFieldState.value.copy(
                    text = event.value
                )
            }
            is EditProfileEvent.EnteredLinkedInUrl -> {
                _linkedinTextFieldState.value = linkedinTextFieldState.value.copy(
                    text = event.value
                )
            }
            is EditProfileEvent.EnteredUsername -> {
                _usernameState.value = userNameState.value.copy(
                    text = event.value
                )
            }
            is EditProfileEvent.SetSkillSelected -> {

            }
            is EditProfileEvent.UpdateProfile -> {

            }
        }
    }

    private suspend fun getSkills() {

        when (profileUseCases.getSkills()) {
            is Resource.Success -> {

            }
            is Resource.Error -> {

            }
        }
    }

    private suspend fun getProfile(userId: String) {
        _profileState.value = profileState.value.copy(
            isLoading = true
        )
        when (val result = profileUseCases.getProfile(userId)) {
            is Resource.Success -> {
                val profile = result.data ?: kotlin.run {
                    _eventFlow.emit(
                        UiEvent.ShowSnackbar(
                            UiText.StringResource(R.string.couldnt_load_profile)
                        )
                    )
                    return
                }
                _usernameState.value = userNameState.value.copy(
                    text = profile.username
                )
                _githubTextFieldState.value = githubTextFieldState.value.copy(
                    text = profile.githubUrl ?: ""
                )
                _instagramTextFieldState.value = instagramTextFieldState.value.copy(
                    text = profile.instagramUrl ?: ""
                )
                _linkedinTextFieldState.value = linkedinTextFieldState.value.copy(
                    text = profile.linkedinUrl ?: ""
                )
                _bioState.value = bioState.value.copy(
                    text = profile.bio
                )
                _skills.value = skills.value.copy(
                    skills = profile.topSkills
                )
                _profileState.value = profileState.value.copy(
                    profile = profile,
                    isLoading = false
                )
            }
            is Resource.Error -> {
                _profileState.value = profileState.value.copy(
                    isLoading = false
                )
                _eventFlow.emit(
                    UiEvent.ShowSnackbar(
                        result.uiText ?: UiText.unknownError()
                    )
                )
                return
            }
        }
    }

}