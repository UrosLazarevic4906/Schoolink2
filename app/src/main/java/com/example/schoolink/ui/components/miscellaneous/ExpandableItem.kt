package com.example.schoolink.ui.components.miscellaneous

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.schoolink.R
import com.example.schoolink.ui.theme.Black

@Composable
fun ExpandableItem(
    title: String,
    content: String,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.padding(vertical = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isExpanded = !isExpanded }
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                )
                Icon(
                    painter = if (isExpanded) painterResource(R.drawable.ic_chevron_up) else painterResource(R.drawable.ic_chevron_down),
                    contentDescription = null,
                    tint = Black,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        if (isExpanded) {
            Text(
                text = content,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(vertical = 8.dp),
            )
        }
    }
}