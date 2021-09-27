package com.faraji.socialnetwork.feature_profile.presentation.util

import com.faraji.socialnetwork.core.util.Error

sealed class EditProfileError: Error() {
    object FieldEmpty: EditProfileError()
}
