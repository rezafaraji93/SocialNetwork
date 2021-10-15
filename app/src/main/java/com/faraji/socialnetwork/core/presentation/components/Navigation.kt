package com.faraji.socialnetwork.core.presentation.components

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.faraji.socialnetwork.core.domain.models.Post
import com.faraji.socialnetwork.core.util.Screen
import com.faraji.socialnetwork.feature_activity.presentation.activity.ActivityScreen
import com.faraji.socialnetwork.feature_auth.presentation.login.LoginScreen
import com.faraji.socialnetwork.feature_auth.presentation.register.RegisterScreen
import com.faraji.socialnetwork.feature_auth.presentation.splash.SplashScreen
import com.faraji.socialnetwork.feature_chat.presentation.chat.ChatScreen
import com.faraji.socialnetwork.feature_post.presentation.create_post.CreatePostScreen
import com.faraji.socialnetwork.feature_post.presentation.main_feed.MainFeedScreen
import com.faraji.socialnetwork.feature_post.presentation.person_list.PersonListScreen
import com.faraji.socialnetwork.feature_post.presentation.post_detail.PostDetailScreen
import com.faraji.socialnetwork.feature_profile.presentation.edit_profile.EditProfileScreen
import com.faraji.socialnetwork.feature_profile.presentation.profile.ProfileScreen
import com.faraji.socialnetwork.feature_profile.presentation.search.SearchScreen

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun Navigation(
    navController: NavHostController,
    scaffoldState: ScaffoldState
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.LoginScreen.route) {
            LoginScreen(
                navController = navController,
                scaffoldState = scaffoldState
            )
        }
        composable(Screen.RegisterScreen.route) {
            RegisterScreen(
                navController = navController,
                scaffoldState = scaffoldState
            )
        }
        composable(Screen.MainFeedScreen.route) {
            MainFeedScreen(
                navController = navController,
                scaffoldState = scaffoldState
            )
        }
        composable(Screen.ChatScreen.route) {
            ChatScreen(navController = navController)
        }
        composable(Screen.ActivityScreen.route) {
            ActivityScreen(navController = navController)
        }
        composable(
            route = Screen.ProfileScreen.route + "?userId={userId}",
            arguments = listOf(
                navArgument(name = "userId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) {
            ProfileScreen(
                navController = navController,
                scaffoldState = scaffoldState
            )
        }
        composable(Screen.EditProfileScreen.route) {
            EditProfileScreen(navController = navController)
        }
        composable(Screen.CreatePostScreen.route) {
            CreatePostScreen(
                navController = navController,
                scaffoldState = scaffoldState
            )
        }
        composable(Screen.SearchScreen.route) {
            SearchScreen(navController = navController)
        }
        composable(Screen.PostDetailScreen.route) {
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
        composable(Screen.PersonListScreen.route) {
            PersonListScreen(navController = navController)
        }


    }
}