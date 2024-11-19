package com.example.schoolink.ui.screens.authentication.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.example.schoolink.ui.components.header.HeaderBack
import com.example.schoolink.ui.components.inputs.InteractionText
import com.example.schoolink.ui.components.inputs.EmailInputField
import com.example.schoolink.ui.components.inputs.PasswordInputField
import com.example.schoolink.ui.screens.authentication.overlay.ForgotPasswordOverlay
import com.example.schoolink.ui.theme.*
import com.example.schoolink.ui.viewmodels.ProfessorViewModel

@Composable
fun LoginScreen(
    viewModel: ProfessorViewModel,
    context: Context,
    onBack: () -> Unit,
    onNavigateToCreateAccount: () -> Unit,
    onSetupAccount: (String) -> Unit,
    onLogin: (String) -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isEmailValid by remember { mutableStateOf(false) }
    var isPasswordValid by remember { mutableStateOf(false) }

    var showForgotPasswordOverlay by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current
    val isFormValid = isEmailValid && isPasswordValid

    SchoolinkTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .imePadding()
                .padding(horizontal = 24.dp, vertical = 32.dp)
                .clickable(
                    onClick = { focusManager.clearFocus() },
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    HeaderBack(
                        onBackClick = {
                            onBack()
                        },
                        title = "Welcome Back",
                        description = "Enter your email address and password to access your account."
                    )
                }

                item {
                    EmailInputField(
                        value = email,
                        isValid = { isEmailValid = it },
                        onValueChange = { email = it })
                }

                item {
                    PasswordInputField(
                        value = password,
                        isValid = { isPasswordValid = it },
                        onValueChange = { password = it })
                }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        InteractionText(
                            text = "Forgot password",
                            onClick = {
                                if (!showForgotPasswordOverlay)
                                    showForgotPasswordOverlay = true
                            }
                        )
                    }
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        viewModel.getProfessorByEmail(email) { existingProfessor ->
                            if (existingProfessor != null) {
                                if (existingProfessor.password == password) {
                                    if(existingProfessor.firstName.isNullOrEmpty()) {
                                        focusManager.clearFocus()
                                        onSetupAccount(existingProfessor.email)
                                    } else {
                                        focusManager.clearFocus()
                                        onLogin(email)
                                    }
                                } else {
                                    focusManager.clearFocus()
                                    Toast.makeText(context, "Incorrect password", Toast.LENGTH_LONG).show()
                                }
                            } else {
                                focusManager.clearFocus()
                                Toast.makeText(context, "User not found", Toast.LENGTH_LONG).show()
                            }
                        }
                    },
                    enabled = isFormValid,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        disabledContainerColor = DissabledButton,
                        disabledContentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text("Log in")
                }

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Don't have an account?",
                        color = Ash
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    InteractionText(
                        text = "Get started",
                        onClick = onNavigateToCreateAccount
                    )
                }
            }
        }
        AnimatedVisibility(
            visible = showForgotPasswordOverlay,
            enter = slideInVertically(
                initialOffsetY = { it },
                animationSpec = tween(1000)
            ),
            exit = slideOutVertically(
                targetOffsetY = { it },
                animationSpec = tween(1000)
            )
        ) {
            ForgotPasswordOverlay(
                onDismiss = { showForgotPasswordOverlay = false },
                onResetPassword = {
                }
            )
        }
    }
}
