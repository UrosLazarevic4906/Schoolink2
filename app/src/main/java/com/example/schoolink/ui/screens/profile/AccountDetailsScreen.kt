package com.example.schoolink.ui.screens.profile

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.schoolink.R
import com.example.schoolink.domain.models.ProfessorModel
import com.example.schoolink.ui.components.inputs.AccountDetailsInformationField
import com.example.schoolink.ui.components.miscellaneous.TitleCard
import com.example.schoolink.ui.screens.profile.overlay.EditEmailOverlay
import com.example.schoolink.ui.screens.profile.overlay.EditPasswordOverlay
import com.example.schoolink.ui.viewmodels.ProfessorViewModel

@Composable
fun AccountDetailsScreen(
    email: String,
    professorViewModel: ProfessorViewModel,
    context: Context,
    onBack: () -> Unit,
    onDeleteAccount: () -> Unit,
    logOut: () -> Unit
) {

    var professor by remember { mutableStateOf<ProfessorModel?>(null) }
    var showEditEmailOverlay by remember { mutableStateOf(false) }
    var showEditPasswordOverlay by remember { mutableStateOf(false) }

    LaunchedEffect(email) {
        professorViewModel.getProfessorByEmail(email) { prof ->
            prof?.let {
                professor = it
            }
        }
    }

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 32.dp)
    ) {
        TitleCard(
            title = "Account details",
            startIcon = painterResource(R.drawable.ic_chevron_left),
            onStartIcon = onBack
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp, horizontal = 24.dp)
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AccountDetailsInformationField(
                isPassword = false,
                title = "Email",
                text = professor?.email ?: "email",
                onEdit = {
                    showEditEmailOverlay = true
                }
            )
            AccountDetailsInformationField(
                isPassword = true,
                title = "Password",
                text = professor?.password ?: "password",
                onEdit = {
                    showEditPasswordOverlay = true
                }
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = onDeleteAccount,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.primary,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(
                    text = "DeleteAccount",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }
    }

    AnimatedVisibility(
        visible = showEditEmailOverlay,
        enter = slideInVertically(
            initialOffsetY = { it },
            animationSpec = tween(1000)
        ),
        exit = slideOutVertically(
            targetOffsetY = { it },
            animationSpec = tween(1000)
        )
    ) {
        EditEmailOverlay(
            onDismis = { showEditEmailOverlay = false },
            focusManager = LocalFocusManager.current,
            context = context,
            professor = professor,
            professorViewModel = professorViewModel,
            onEmailUpdated = {
                logOut()
            }
        )
    }

    AnimatedVisibility(
        visible = showEditPasswordOverlay,
        enter = slideInVertically(
            initialOffsetY = { it },
            animationSpec = tween(1000)
        ),
        exit = slideOutVertically(
            targetOffsetY = { it },
            animationSpec = tween(1000)
        )
    ) {
        EditPasswordOverlay(
            onDismis = { showEditPasswordOverlay = false },
            focusManager = LocalFocusManager.current,
            context = context,
            professor = professor,
            professorViewModel = professorViewModel,
            onPasswordUpdated = {
                logOut()
            }
        )
    }

}