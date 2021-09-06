package com.faraji.socialnetwork.domain.models

import com.faraji.socialnetwork.domain.util.ActivityAction

data class Activity(
    val username: String,
    val actionType: ActivityAction,
    val formattedTime: String,
)
