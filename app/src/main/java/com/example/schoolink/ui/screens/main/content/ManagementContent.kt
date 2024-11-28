package com.example.schoolink.ui.screens.main.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.schoolink.R
import com.example.schoolink.ui.components.miscellaneous.ManagementCard

@Composable
fun ManagementContent(
    onStudent: () -> Unit,
    onGroup: () -> Unit
) {
    Box(
        modifier = Modifier.padding(top = 200.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 50.dp, vertical = 50.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ManagementCard(
                icon = painterResource(R.drawable.ic_smiley_color),
                title = "Students",
                onClick = onStudent
            )

            ManagementCard(
                icon = painterResource(R.drawable.ic_group_color),
                title = "Groups",
                onClick = onGroup
            )
        }
    }
}