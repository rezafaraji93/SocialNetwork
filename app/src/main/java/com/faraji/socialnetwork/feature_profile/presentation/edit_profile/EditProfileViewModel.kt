package com.faraji.socialnetwork.feature_profile.presentation.edit_profile

import android.net.Uri
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
import com.faraji.socialnetwork.feature_profile.domain.model.UpdateProfileData
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

    private val _bannerImageUri = mutableStateOf<Uri?>(null)
    val bannerImageUri: State<Uri?> = _bannerImageUri

    private val _profilePictureUri = mutableStateOf<Uri?>(null)
    val profilePictureUri: State<Uri?> = _profilePictureUri

    init {
        savedStateHandle.get<String>("userId")?.let { userId ->
            getSkills()
            getProfile(userId)
        }
    }

    fun onEvent(event: EditProfileEvent) {
        when (event) {
            is EditProfileEvent.CropBannerImage -> {
                _bannerImageUri.value = event.uri
            }
            is EditProfileEvent.CropProfilePicture -> {
                _profilePictureUri.value = event.uri
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
                val result = profileUseCases.setSkillSelected(
                    selectedSkills = _skills.value.selectedSkills,
                    skillToToggle = event.skill
                )
                viewModelScope.launch {
                    when (result) {
                        is Resource.Success -> {
                            _skills.value = skills.value.copy(
                                selectedSkills = result.data ?: kotlin.run {
                                    _eventFlow.emit(
                                        UiEvent.ShowSnackbar(
                                            UiText.unknownError()
                                        )
                                    )
                                    return@launch
                                }
                            )
                        }
                        is Resource.Error -> {
                            _eventFlow.emit(
                                UiEvent.ShowSnackbar(
                                    result.uiText ?: UiText.unknownError()
                                )
                            )
                            return@launch
                        }
                    }
                }

            }
            is EditProfileEvent.UpdateProfile -> {
                updateProfile()
            }
        }
    }

    private fun updateProfile() {
        viewModelScope.launch {
            val result = profileUseCases.updateProfile(
                updateProfileData = UpdateProfileData(
                    username = userNameState.value.text,
                    bio = bioState.value.text,
                    gitHubUrl = githubTextFieldState.value.text,
                    instagramUrl = instagramTextFieldState.value.text,
                    linkedInUrl = linkedinTextFieldState.value.text,
                    skills = skills.value.selectedSkills
                ),
                profilePictureUri = profilePictureUri.value,
                bannerImageUri = bannerImageUri.value
            )

            when (result) {
                is Resource.Success -> {
                    _eventFlow.emit(
                        UiEvent.ShowSnackbar(
                            UiText.StringResource(R.string.updated_profile)
                        )
                    )
                    _eventFlow.emit(UiEvent.NavigateUp)
                }
                is Resource.Error -> {
                    _eventFlow.emit(
                        UiEvent.ShowSnackbar(result.uiText ?: UiText.unknownError())
                    )
                }
            }
        }
    }

    private fun getSkills() {
        viewModelScope.launch {

            when (val result = profileUseCases.getSkills()) {
                is Resource.Success -> {
                    _skills.value = skills.value.copy(
                        skills = result.data ?: kotlin.run {
                            _eventFlow.emit(
                                UiEvent.ShowSnackbar(
                                    UiText.StringResource(R.string.error_couldnt_load_skills)
                                )
                            )
                            return@launch
                        }
                    )
                }
                is Resource.Error -> {
                    _eventFlow.emit(
                        UiEvent.ShowSnackbar(
                            result.uiText ?: UiText.unknownError()
                        )
                    )
                    return@launch
                }
            }
        }
    }

    private fun getProfile(userId: String) {
        viewModelScope.launch {
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
                        return@launch
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
                        selectedSkills = profile.topSkills
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
                    return@launch
                }
            }
        }
    }

}