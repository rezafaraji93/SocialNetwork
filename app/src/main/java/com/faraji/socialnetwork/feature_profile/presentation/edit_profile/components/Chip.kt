package com.faraji.socialnetwork.feature_profile.presentation.edit_profile.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.faraji.socialnetwork.core.presentation.ui.theme.SpaceSmall

@Composable
fun Chip(
    text: String,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    selectedColor: Color = MaterialTheme.colors.primary,
    unSelectedColor: Color = MaterialTheme.colors.onSurface,
    onChipClick: () -> Unit
) {
    Text(
        text = text,
        color = if (selected) selectedColor else unSelectedColor,
        textAlign = TextAlign.Center,
        modifier = modifier
            .clip(RoundedCornerShape(50.dp))
            .border(
                width = 1.dp,
                color = if (selected) selectedColor else unSelectedColor,
                shape = RoundedCornerShape(50.dp)
            )
            .clickable {
                onChipClick()
            }
            .padding(SpaceSmall)
            .defaultMinSize(minWidth = 35.dp),

        )
}