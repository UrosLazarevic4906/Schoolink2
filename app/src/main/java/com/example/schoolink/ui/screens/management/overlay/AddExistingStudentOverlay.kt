package com.example.schoolink.ui.screens.management.overlay

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.schoolink.ui.components.miscellaneous.TitleCard
import com.example.schoolink.R
import com.example.schoolink.ui.theme.*
import com.example.schoolink.ui.theme.DissabledButton

@Composable
fun AddExistingStudentOverlay(
    onDismiss: () -> Unit,
    onAddExistingStudent: (String) -> Unit,
) {
    var credentials by remember { mutableStateOf("") }
    var areCredentialsValid by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    LaunchedEffect(credentials) {
        areCredentialsValid = Patterns.EMAIL_ADDRESS.matcher(credentials).matches() ||
                (credentials.length == 7 && credentials.all { char ->
                    char.isDigit() || char.isUpperCase()
                })
    }


    val labelColor = when {
        credentials.isEmpty() -> Smoke
        areCredentialsValid -> Green
        else -> Red
    }

    Surface(
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp, start = 10.dp, end = 10.dp)
            .imePadding()
            .clickable(
                onClick = { focusManager.clearFocus() },
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Cream),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {

            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                TitleCard(
                    icon = painterResource(R.drawable.ic_close),
                    onClick = onDismiss,
                    title = "Add student"
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 12.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Enter the student code or email of the student you want to add.",
                    color = Ash,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                OutlinedTextField(
                    value = credentials,
                    onValueChange = {
                        credentials = it.trim()
                    },
                    label = { Text("Student code or email") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.secondary,
                        unfocusedBorderColor = Smoke,
                        focusedLabelColor = labelColor,
                        unfocusedLabelColor = labelColor,
                        cursorColor = MaterialTheme.colorScheme.secondary,
                        focusedLeadingIconColor = MaterialTheme.colorScheme.secondary,
                        focusedTrailingIconColor = MaterialTheme.colorScheme.secondary
                    )
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = { onAddExistingStudent(credentials) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        disabledContainerColor = DissabledButton,
                        disabledContentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    enabled = areCredentialsValid,
                ) {
                    Text(text = "Add a student")
                }
            }
        }
    }
}

@Preview
@Composable
private fun AddExistingStudentOverlayPreview() {
    AddExistingStudentOverlay(
        onDismiss = {},
        onAddExistingStudent = {}
    )
}