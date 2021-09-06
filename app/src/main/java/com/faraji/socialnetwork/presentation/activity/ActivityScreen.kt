package com.faraji.socialnetwork.presentation.activity

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.faraji.socialnetwork.R
import com.faraji.socialnetwork.domain.models.Activity
import com.faraji.socialnetwork.domain.util.ActivityAction
import com.faraji.socialnetwork.domain.util.DateFormatUtil
import com.faraji.socialnetwork.presentation.activity.components.ActivityItem
import com.faraji.socialnetwork.presentation.components.StandardToolbar
import com.faraji.socialnetwork.presentation.ui.theme.SpaceMedium
import kotlin.random.Random

@Composable
fun ActivityScreen(
    navController: NavController,
    viewModel: ActivityViewModel = hiltViewModel()
    ) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        StandardToolbar(
            navController = navController,
            title = {
                Text(
                    text = stringResource(id = R.string.your_activity),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
            },
            modifier = Modifier.fillMaxWidth(),
            showBackArrow = false,
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(SpaceMedium)
        ) {
            items(20) {
                ActivityItem(
                    activity = Activity(
                        username = "Reza Faraji",
                        actionType = if (Random.nextInt(2) == 0)
                            ActivityAction.LikedPost
                        else ActivityAction.CommentedOnPost,
                        formattedTime = DateFormatUtil.timestampToFormattedString(
                            timestamp = System.currentTimeMillis(),
                            pattern = "MMM dd, HH:mm"
                        )
                    ),
                )
                if (it < 19) {
                    Spacer(modifier = Modifier.height(SpaceMedium))
                }
            }
        }
    }
}