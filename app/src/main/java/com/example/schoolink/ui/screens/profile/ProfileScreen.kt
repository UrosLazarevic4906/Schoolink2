package com.example.schoolink.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.example.schoolink.R
import com.example.schoolink.domain.models.ProfessorModel
import com.example.schoolink.ui.components.miscellaneous.ProfileOption
import com.example.schoolink.ui.components.miscellaneous.TitleCard
import com.example.schoolink.ui.theme.Ash
import com.example.schoolink.ui.theme.Gravel
import com.example.schoolink.ui.theme.SchoolinkTheme
import com.example.schoolink.ui.viewmodels.ProfessorViewModel

@Composable
fun ProfileScreen(
    onBack: () -> Unit,
    onLogOut: () -> Unit,
    onTermsAndConditions: () -> Unit,
    onPrivacyPolicy: () -> Unit,
    onPersonalInformation: () -> Unit,
    onAccountDetails: () -> Unit,
    email: String,
    professorViewModel: ProfessorViewModel,
) {

    var professor by remember { mutableStateOf<ProfessorModel?>(null) }

    LaunchedEffect(email) {
        professorViewModel.getProfessorByEmail(email) { prof ->
            prof?.let {
                professor = it
            }
        }
    }

    SchoolinkTheme {
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
                .fillMaxSize()
                .padding(horizontal = 24.dp , vertical = 32.dp)
        ) {
            TitleCard(
                title = "My profile",
                startIcon = painterResource(R.drawable.ic_chevron_down),
                onStartIcon = onBack
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 18.dp, vertical = 24.dp)
                    .weight(1.0f),
                verticalArrangement = Arrangement.spacedBy(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(150.dp)
                        .background(color = Gravel),
                    contentAlignment = Alignment.Center
                ) {
                    if (professor?.profilePicturePath != null) {
                        Image(
                            painter = rememberAsyncImagePainter(professor!!.profilePicturePath),
                            contentDescription = "${professor!!.id}' profile picture",
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
                    "${professor?.firstName} ${professor?.lastName}",
                    style = MaterialTheme.typography.bodyLarge,
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Profile",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                    ProfileOption(
                        icon = R.drawable.ic_user,
                        title = "Personal information",
                        onClick = {
                            onPersonalInformation()
                        }
                    )
                    ProfileOption(
                        icon = R.drawable.ic_shield,
                        title = "Account details",
                        onClick = {
                            onAccountDetails()
                        }
                    )
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        "Legal",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                    ProfileOption(
                        icon = R.drawable.ic_document,
                        title = "Terms & Conditions",
                        onClick = {
                            onTermsAndConditions()
                        }
                    )
                    ProfileOption(
                        icon = R.drawable.ic_livebuoy,
                        title = "Privacy policy",
                        onClick = {
                            onPrivacyPolicy()
                        }
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = onLogOut,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        contentColor = MaterialTheme.colorScheme.primary,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text(
                        text = "Log out",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
        }
    }
}