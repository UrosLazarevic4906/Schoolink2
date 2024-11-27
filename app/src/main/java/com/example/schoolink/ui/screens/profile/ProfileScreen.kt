package com.example.schoolink.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.schoolink.R
import com.example.schoolink.domain.models.ProfessorModel
import com.example.schoolink.ui.components.miscellaneous.TitleCard
import com.example.schoolink.ui.theme.Ash
import com.example.schoolink.ui.theme.Gravel
import com.example.schoolink.ui.viewmodels.ProfessorViewModel

@Composable
fun ProfileScreen(
    onBack: () -> Unit,
//    email: String,
//    professorViewModel: ProfessorViewModel,

) {

//    var professor by remember { mutableStateOf<ProfessorModel?>(null) }
//
//
//    LaunchedEffect(email) {
//        professorViewModel.getProfessorByEmail(email) { prof ->
//            prof?.let {
//                professor = it
//            }
//        }
//    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 18.dp, vertical = 24.dp)
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleCard(
            title = "My profile",
            startIcon = painterResource(R.drawable.ic_chevron_left),
            onStartIcon = onBack
        )

        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(150.dp)
                .background(color = Gravel),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_user),
                contentDescription = "Default profile picture",
                tint = Ash,
                modifier = Modifier.fillMaxSize(0.3f)
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    ProfileScreen(onBack = {})
}