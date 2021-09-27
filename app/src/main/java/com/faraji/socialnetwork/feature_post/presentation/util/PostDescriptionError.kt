package com.faraji.socialnetwork.feature_post.presentation.util

import com.faraji.socialnetwork.core.util.Error

sealed class PostDescriptionError: Error() {
    object FieldEmpty: PostDescriptionError()
}
