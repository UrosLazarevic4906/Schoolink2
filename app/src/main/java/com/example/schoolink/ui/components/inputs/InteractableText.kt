package com.example.schoolink.ui.components.inputs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.schoolink.ui.theme.*

@Composable
fun InteractionText(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    color: Color =  Green,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Text(
        text = text,
        color = color,
        fontWeight = fontWeight,
        modifier = modifier.clickable(
            onClick = onClick,
            indication = ripple(color = color),
            interactionSource = remember { MutableInteractionSource() }
        )
    )
}
