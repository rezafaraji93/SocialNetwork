package com.faraji.socialnetwork.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.faraji.socialnetwork.domain.models.Post
import com.faraji.socialnetwork.presentation.activity.ActivityScreen
import com.faraji.socialnetwork.presentation.chat.ChatScreen
import com.faraji.socialnetwork.presentation.createPost.CreatePostScreen
import com.faraji.socialnetwork.presentation.login.LoginScreen
import com.faraji.socialnetwork.presentation.main_feed.MainFeedScreen
import com.faraji.socialnetwork.presentation.postDetail.PostDetailScreen
import com.faraji.socialnetwork.presentation.profile.ProfileScreen
import com.faraji.socialnetwork.presentation.register.RegisterScreen
import com.faraji.socialnetwork.presentation.splash.SplashScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(Screen.RegisterScreen.route) {
            RegisterScreen(navController = navController)
        }
        composable(Screen.MainFeedScreen.route) {
            MainFeedScreen(navController = navController)
        }
        composable(Screen.ChatScreen.route) {
            ChatScreen(navController = navController)
        }
        composable(Screen.ActivityScreen.route) {
            ActivityScreen(navController = navController)
        }
        composable(Screen.ProfileScreen.route) {
            ProfileScreen (navController = navController)
        }
        composable(Screen.CreatePostScreen.route){
            CreatePostScreen(navController = navController)
        }
        composable(Screen.PostDetailScreen.route){
            PostDetailScreen(
                navController = rememberNavController(),
                post = Post(
                    username = "Reza Faraji",
                    imageUrl = "",
                    profilePictureUrl = "",
                    description = "this is a sample description of the post...",
                    likeCount = 20,
                    commentCount = 12
                )
            )
        }

    }
}