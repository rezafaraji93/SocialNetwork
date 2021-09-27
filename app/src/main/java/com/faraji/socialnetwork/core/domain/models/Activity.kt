package com.faraji.socialnetwork.core.domain.models

import com.faraji.socialnetwork.feature_activity.domain.ActivityAction

data class Activity(
    val username: String,
    val actionType: ActivityAction,
    val formattedTime: String,
)
