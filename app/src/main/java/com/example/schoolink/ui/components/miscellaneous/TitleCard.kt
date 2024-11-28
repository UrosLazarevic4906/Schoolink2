package com.example.schoolink.ui.components.miscellaneous

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
    title: String,
    startIcon: Painter? = null,
    endIcon: Painter? = null,
    clickableText: String? = null,
    onStartIcon: () -> Unit = {},
    onEndIcon: () -> Unit = {},
    onText: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        if (startIcon != null) {
            IconButton(
                onClick = onStartIcon,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {

                Icon(
                    painter = startIcon,
                    contentDescription = "Start Icon",
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
                onClick = onText,
                color = Green,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }

        if(endIcon != null) {
            IconButton(
                onClick = onEndIcon,
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {

                Icon(
                    painter = endIcon,
                    contentDescription = "End Icon",
                    tint = Black
                )
            }
        }
    }
}


@Preview
@Composable
private fun TitleLeftButtonPreview() {
    TitleCard(
        title = "Help & Support",
        startIcon = painterResource(R.drawable.ic_chevron_left),
        clickableText = "Next"
        )
}