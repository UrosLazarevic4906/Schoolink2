package com.example.schoolink.ui.components.miscellaneous

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.example.schoolink.ui.screens.main.Screen
import com.example.schoolink.R
import com.example.schoolink.domain.models.ProfessorModel
import com.example.schoolink.ui.theme.Ash
import com.example.schoolink.ui.theme.White

@Composable
fun TopContentWithBackground(
    screen: Screen,
    professor: ProfessorModel?,
    onProfile: () -> Unit
) {

    Image(
        painter = painterResource(screen.backgroundRes),
        contentDescription = "Background on screen",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxWidth()
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 28.dp, vertical = 32.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(80.dp)
                .background(color = MaterialTheme.colorScheme.background)
                .clickable { onProfile() },
            contentAlignment = Alignment.Center
        ) {
            if (professor?.profilePicturePath != null) {
                Image(
                    painter = rememberAsyncImagePainter(professor.profilePicturePath),
                    contentDescription = "${professor.id}' profile picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                )
            } else {
                Icon(
                    painter = painterResource(R.drawable.ic_user),
                    contentDescription = "Default profile picture",
                    tint = Ash,
                    modifier = Modifier.fillMaxSize(0.5f)
                )
            }
        }

        Text(
            text = screen.title,
            style = MaterialTheme.typography.displaySmall,
            color = White
        )

        Text(
            text = screen.description,
            style = MaterialTheme.typography.bodyLarge,
            color = White.copy(alpha = 0.8f)
        )
    }
}