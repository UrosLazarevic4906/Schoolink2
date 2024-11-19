package com.example.schoolink.ui.screens.authentication.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.schoolink.domain.models.ProfessorModel
import com.example.schoolink.ui.components.header.HeaderBack
import com.example.schoolink.ui.components.inputs.InteractionText
import com.example.schoolink.ui.components.inputs.ConfirmPasswordInputField
import com.example.schoolink.ui.components.inputs.EmailInputField
import com.example.schoolink.ui.components.inputs.PasswordInputField
import com.example.schoolink.ui.theme.DissabledButton
import com.example.schoolink.ui.theme.SchoolinkTheme
import com.example.schoolink.ui.viewmodels.ProfessorViewModel

@Composable
fun CreateAccountScreen(
    viewModel: ProfessorViewModel,
    onBack: () -> Unit,
    onCreateAccount: (String) -> Unit
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var isEmailValid by remember { mutableStateOf(false) }
    var isPasswordValid by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current
    val isFormValid = isEmailValid && isPasswordValid && confirmPassword == password

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
                        title = "Create an account",
                        description = "We’ll send you a verification code to your email in order to verify your account."
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
                    ConfirmPasswordInputField(
                        password = password,
                        confirmPassword = confirmPassword,
                        onConfirmPasswordChange = { confirmPassword = it }
                    )
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

                            if (existingProfessor == null) {
                                val professor = ProfessorModel(
                                    email = email,
                                    password = password
                                )
                                viewModel.createProfessor(professor)
                                onCreateAccount(professor.email)
                            }
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
                    enabled =  isFormValid,
                ) {
                    Text(text = "Create account")
                }


                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "By tapping on “Create account” you agree to our",
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Row {
                        InteractionText(
                            text = "Terms & Conditions",
                            onClick = { /* TODO: Handle terms click */ }
                        )
                        Text(" and ", color = MaterialTheme.colorScheme.onBackground)
                        InteractionText(
                            text = "Privacy Policy.",
                            onClick = { /* ToDo: Handle privacy policy click */ }
                        )
                    }
                }


            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CreateAccountPreview() {

//    CreateAccountScreen(
////        onBack = {}
//    )

}