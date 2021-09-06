package com.faraji.socialnetwork.domain.util

sealed class ActivityAction{
    object LikedPost: ActivityAction()
    object CommentedOnPost: ActivityAction()
}
