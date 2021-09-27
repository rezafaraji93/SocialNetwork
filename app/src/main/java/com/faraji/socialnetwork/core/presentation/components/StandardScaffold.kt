package com.faraji.socialnetwork.core.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Message
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.faraji.socialnetwork.R
import com.faraji.socialnetwork.core.domain.models.BottomNavItem
import com.faraji.socialnetwork.core.util.Screen

@Composable
fun StandardScaffold(
    navController: NavController,
    modifier: Modifier = Modifier,
    showBottomBar: Boolean = true,
    bottomNavItems: List<BottomNavItem> = listOf(
        BottomNavItem(
            route = Screen.MainFeedScreen.route,
            icon = Icons.Outlined.Home,
            contentDescription = "Home",
        ),
        BottomNavItem(
            route = Screen.ChatScreen.route,
            icon = Icons.Outlined.Message,
            contentDescription = "Message",
        ),
        BottomNavItem(route = ""),
        BottomNavItem(
            route = Screen.ActivityScreen.route,
            icon = Icons.Outlined.Notifications,
            contentDescription = "Activity",
        ),
        BottomNavItem(
            route = Screen.ProfileScreen.route,
            icon = Icons.Outlined.Person,
            contentDescription = "Profile",
        ),
    ),
    onFabClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomAppBar(
                    modifier = Modifier
                        .fillMaxWidth(),
                    backgroundColor = MaterialTheme.colors.surface,
                    cutoutShape = CircleShape,
                    elevation = 5.dp
                ) {
                    BottomNavigation {
                        bottomNavItems.forEach { bottomNavItems ->
                            StandardBottomNavItem(
                                icon = bottomNavItems.icon,
                                contentDescription = bottomNavItems.contentDescription,
                                selected = bottomNavItems.route == navController.currentDestination?.route,
                                alertCount = bottomNavItems.alertCount,
                                enabled = bottomNavItems.icon != null
                            ) {
                                if (navController.currentDestination?.route != bottomNavItems.route)
                                    navController.navigate(bottomNavItems.route)
                            }
                        }
                    }
                }
            }
        },
        floatingActionButton = {
            if (showBottomBar) {
                FloatingActionButton(
                    backgroundColor = MaterialTheme.colors.primary,
                    onClick = onFabClick
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(id = R.string.make_post)
                    )
                }
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        modifier = modifier
    ) {
        content()
    }
}