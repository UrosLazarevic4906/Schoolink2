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
import com.example.schoolink.ui.theme.Black

@Composable
fun TitleCard(
    icon: Painter? = null,
    title: String,
    clickableText: String? = null,
    onButtonClickClick: () -> Unit = {},
    onTextClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
//            .clickable { onClick() }
            .padding(8.dp)
    ) {
        if (icon != null) {
            IconButton(
                onClick = onButtonClickClick,
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

        if(clickableText != null){
            //TODO: ADD CLICKABLE TEXT AT ENDS
        }
    }
}


@Preview
@Composable
private fun TitleLeftButtonPreview() {
    TitleCard(
        title = "Help & Support",
        icon = painterResource(R.drawable.ic_chevron_left)
    )
}