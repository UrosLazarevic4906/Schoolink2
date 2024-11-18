package com.example.schoolink.ui.screens.main

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.schoolink.domain.models.ProfessorModel
import com.example.schoolink.ui.theme.Cream
import com.example.schoolink.ui.viewmodels.ProfessorViewModel

@Composable
fun HomeScreen(
    email: String,
    viewModel: ProfessorViewModel,
) {
    var professor by remember { mutableStateOf<ProfessorModel?>(null) }

    // Fetch professor details based on the email
    LaunchedEffect(email) {
        viewModel.getProfessorByEmail(email) { fetchedProfessor ->
            professor = fetchedProfessor
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Cream)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        if (professor != null) {
            Text(
                text = "Welcome, ${professor?.firstName}!",
            )
        } else {
            Text(
                text = "Loading...",
            )
        }
    }
}

