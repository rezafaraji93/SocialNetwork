package com.faraji.socialnetwork.presentation.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.faraji.socialnetwork.R
import com.faraji.socialnetwork.presentation.components.StandardToolbar
import com.faraji.socialnetwork.presentation.ui.theme.SpaceMedium


@Composable
fun ProfileScreen(navController: NavController) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        StandardToolbar(
            navController = navController,
            title = {
                Text(
                    text = stringResource(id = R.string.your_profile),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
            },
            navActions = {

            },
            modifier = Modifier.fillMaxWidth(),
            showBackArrow = false,
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(SpaceMedium)
        ) {
            item{

            }
        }
    }

}