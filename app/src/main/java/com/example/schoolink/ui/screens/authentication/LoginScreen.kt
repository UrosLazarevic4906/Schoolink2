package com.example.schoolink.ui.screens.authentication

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.schoolink.ui.components.HeaderBack
import com.example.schoolink.ui.components.InteractionText
import com.example.schoolink.ui.components.inputs.EmailInputField
import com.example.schoolink.ui.components.inputs.PasswordInputField
import com.example.schoolink.ui.theme.*

@Composable
fun LoginScreen(
    onBack: () -> Unit,
    onNavigateToCreateAccount: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isEmailValid by remember { mutableStateOf(false) }
    var isPasswordValid by remember { mutableStateOf(false) }

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
            verticalArrangement = Arrangement.SpaceBetween
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
                        onBackClick = onBack,
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
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = { /*ToDo: Handle login click */ },
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
                        color = Green,
                        onClick = onNavigateToCreateAccount
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    LoginScreen(
        onBack = {},
        onNavigateToCreateAccount = {}
    )
}
