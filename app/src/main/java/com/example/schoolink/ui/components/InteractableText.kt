package com.example.schoolink.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun InteractionText(
    text: String,
    onClick: () -> Unit,
    color: Color =  MaterialTheme.colorScheme.primary,
) {
    Text(
        text = text,
        color = color,
        modifier = Modifier.clickable(
            onClick = onClick,
            indication = ripple(color = color),
            interactionSource = remember { MutableInteractionSource() }
        )
    )
}
