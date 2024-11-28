package com.example.schoolink.ui.screens.profile.overlay

import android.content.Context
import android.widget.Toast
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.schoolink.R
import com.example.schoolink.domain.models.ProfessorModel
import com.example.schoolink.ui.components.inputs.EmailInputField
import com.example.schoolink.ui.components.inputs.PasswordInputField
import com.example.schoolink.ui.components.miscellaneous.TitleCard
import com.example.schoolink.ui.theme.*
import com.example.schoolink.ui.viewmodels.ProfessorViewModel

@Composable
fun EditEmailOverlay(
    onDismis: () -> Unit,
    onEmailUpdated: () -> Unit,
    focusManager: FocusManager,
    context: Context,
    professor: ProfessorModel?,
    professorViewModel: ProfessorViewModel
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isEmailValid by remember { mutableStateOf(false) }
    var isPasswordValid by remember { mutableStateOf(false) }


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
                modifier = Modifier
                    .padding(12.dp)
                    .weight(1f)
            ) {
                TitleCard(
                    startIcon = painterResource(R.drawable.ic_close),
                    onStartIcon = onDismis,
                    title = "Edit email adress"
                )

                Column(modifier = Modifier.padding(vertical = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Enter your new email address and confirm the change with your password."
                    )

                    EmailInputField(
                        value = email,
                        isValid = { isEmailValid = it },
                        onValueChange = { email = it.trim() }
                    )

                    PasswordInputField(
                        value = password,
                        onValueChange = { password = it.trim() },
                        isValid = { isPasswordValid = it }
                    )
                }


            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = {
                        if (password == professor?.password) {
                            if (email != professor.email) {
                                professorViewModel.getProfessorByEmail(email) { existingProfessor ->
                                    if (existingProfessor != null) {
                                        Toast.makeText(
                                            context,
                                            "Email is already in use",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } else {
                                        val updatedProfessor = professor.copy(email = email)
                                        professorViewModel.updateProfessorAsync(updatedProfessor) {
                                            Toast.makeText(
                                                context,
                                                "Email updated successfully",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            onEmailUpdated()
                                        }
                                    }
                                }
                            } else {
                                Toast.makeText(
                                    context,
                                    "New email cannot be the same",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            Toast.makeText(
                                context,
                                "Incorrect password",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        disabledContainerColor = DissabledButton,
                        disabledContentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    enabled = isEmailValid && isPasswordValid,
                ) {
                    Text(text = "Change email")
                }
            }

        }

    }
}

