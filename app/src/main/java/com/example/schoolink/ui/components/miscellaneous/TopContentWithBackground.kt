package com.example.schoolink.ui.components.miscellaneous

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.schoolink.ui.screens.main.Screen
import com.example.schoolink.R
import com.example.schoolink.ui.theme.White

@Composable
fun TopContentWithBackground(
    screen: Screen
) {

    Image(
        painter = painterResource(screen.backgroundRes),
        contentDescription = "Background on screen",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxWidth()
    )

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        Image(
            painter = painterResource(R.drawable.ic_user),
            contentDescription = "Profile picture",
            modifier = Modifier.size(80.dp)
        )

        Text(
            text = screen.title,
            style = MaterialTheme.typography.titleLarge,
            color = White
        )

        Text(
            text = screen.description,
            style = MaterialTheme.typography.bodyLarge,
            color = White.copy(alpha = 0.8f)
        )
    }
}