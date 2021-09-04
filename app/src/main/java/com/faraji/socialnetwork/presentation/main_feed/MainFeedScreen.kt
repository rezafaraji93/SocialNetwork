package com.faraji.socialnetwork.presentation.main_feed

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.faraji.socialnetwork.presentation.components.Post

@Composable
fun MainFeedScreen(
    navController: NavController
) {
    Post(
        post = com.faraji.socialnetwork.domain.models.Post(
            username = "Reza Faraji",
            imageUrl = "",
            profilePictureUrl = "",
            description = "this is a sample description of the post...",
            likeCount = 20,
            commentCount = 12
        )
    )
}