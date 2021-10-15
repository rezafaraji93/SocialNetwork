package com.faraji.socialnetwork.feature_profile.presentation.profile

import com.faraji.socialnetwork.feature_profile.domain.model.Profile

data class ProfileState(
    val profile: Profile? = null,
    val isLoading: Boolean = false
)