package com.faraji.socialnetwork.core.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import com.faraji.socialnetwork.R
import com.faraji.socialnetwork.core.presentation.ui.theme.IconSizeMedium

@Composable
fun StandardTextField(
    modifier: Modifier = Modifier,
    text: String = "",
    hint: String = "",
    maxLength: Int = 40,
    error: String = "",
    textStyle: TextStyle = TextStyle(
        color = MaterialTheme.colors.onBackground
    ),
    singleLine: Boolean = true,
    maxLines: Int = 1,
    leadingIcon: ImageVector? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPasswordToggleDisplayed: Boolean = keyboardType == KeyboardType.Password,
    showPasswordToggle: Boolean = false,
    onPasswordToggleClick: (Boolean) -> Unit = {},
    onValueChanged: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        TextField(
            value = text,
            onValueChange = {
                if (it.length <= maxLength)
                    onValueChanged(it)
            },
            textStyle = textStyle,
            maxLines = maxLines,
            placeholder = {
                Text(
                    text = hint,
                    style = MaterialTheme.typography.body1
                )
            },
            modifier = modifier
                .fillMaxWidth(),
            isError = error.isNotBlank(),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
            ),
            visualTransformation = if (!showPasswordToggle && isPasswordToggleDisplayed) PasswordVisualTransformation() else VisualTransformation.None,
            singleLine = singleLine,
            leadingIcon = if (leadingIcon != null) {
                {
                    Icon(
                        imageVector = leadingIcon,
                        contentDescription = null,
                        tint = MaterialTheme.colors.onBackground,
                        modifier = Modifier.size(IconSizeMedium)
                    )
                }

            } else null,
            trailingIcon = if (isPasswordToggleDisplayed) {
                {
                    IconButton(
                        onClick = { onPasswordToggleClick(!showPasswordToggle) }) {
                        Icon(
                            imageVector = if (showPasswordToggle) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                            contentDescription = if (showPasswordToggle) stringResource(id = R.string.password_visible_content_description) else stringResource(
                                id = R.string.password_invisible_content_description
                            )
                        )
                    }
                }
            } else null
        )
        if (error.isNotBlank()) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = error,
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.error
            )
        }
    }
}