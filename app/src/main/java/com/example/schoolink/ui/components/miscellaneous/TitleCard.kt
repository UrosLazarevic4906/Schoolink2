package com.example.schoolink.ui.components.miscellaneous

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.schoolink.R
import com.example.schoolink.ui.components.inputs.InteractionText
import com.example.schoolink.ui.theme.Black
import com.example.schoolink.ui.theme.Green

@Composable
fun TitleCard(
    icon: Painter? = null,
    title: String,
    clickableText: String? = null,
    onButtonClick: () -> Unit = {},
    onTextClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        if (icon != null) {
            IconButton(
                onClick = onButtonClick,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {

                Icon(
                    painter = icon,
                    contentDescription = "Icon",
                    tint = Black
                )
            }
        }

        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            color = Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center)
        )

        if (clickableText != null) {
            InteractionText(
                text = clickableText,
                onClick = onTextClick,
                color = Green,
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }
    }
}


@Preview
@Composable
private fun TitleLeftButtonPreview() {
    TitleCard(
        title = "Help & Support",
        icon = painterResource(R.drawable.ic_chevron_left),
        clickableText = "Next"
    )
}