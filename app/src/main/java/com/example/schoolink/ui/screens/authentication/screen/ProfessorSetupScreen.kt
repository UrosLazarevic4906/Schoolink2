package com.example.schoolink.ui.screens.authentication.screen

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.example.schoolink.domain.models.Gender
import com.example.schoolink.ui.components.inputs.DateOfBirthPicker
import com.example.schoolink.ui.components.inputs.GenderSelectDropdown
import com.example.schoolink.ui.components.inputs.ImagePicker
import com.example.schoolink.ui.components.inputs.OutlinedInputField
import com.example.schoolink.ui.components.header.HeaderBack
import com.example.schoolink.ui.theme.DissabledButton
import com.example.schoolink.ui.theme.SchoolinkTheme
import com.example.schoolink.ui.viewmodels.ProfessorViewModel
import com.example.schoolink.utils.saveImageToInternalStorage


@Composable
fun ProfessorSetupScreen(
    email: String,
    onBack: () -> Unit,
    viewModel: ProfessorViewModel,
    context: Context,
    onAddStudents: () -> Unit
) {

    var profilePictureUri by remember { mutableStateOf<Uri?>(null) }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf<Gender?>(null) }
    var dateOfBirth by remember { mutableStateOf("") }

    var isNameValid by remember { mutableStateOf(false) }
    var isLastNameValid by remember { mutableStateOf(false) }
    val isFormValid = isNameValid && isLastNameValid && gender != null && dateOfBirth.isNotEmpty()


    val focusManager = LocalFocusManager.current


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
        ) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                item {
                    HeaderBack(
                        onBackClick = onBack,
                        title = "First things first",
                        description = "Upload your photo and tell us your name, gender and when you were born"
                    )
                }

                item {
                    Box(
                        modifier = Modifier
                            .size(150.dp)
                    ) {
                        ImagePicker(
                            imageUri = profilePictureUri,
                            onImagePicked = { selectedUri -> profilePictureUri = selectedUri }
                        )
                    }
                }

                item {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 14.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        OutlinedInputField(
                            value = firstName,
                            onValueChange = { firstName = it.trim() },
                            label = "First name",
                            isValid = { isNameValid = it },
                            onDoneAction = {
                                focusManager.clearFocus()
                            }
                        )
                        OutlinedInputField(
                            value = lastName,
                            onValueChange = { lastName = it.trim() },
                            label = "Last name",
                            isValid = { isLastNameValid = it },

                            onDoneAction = {
                                focusManager.clearFocus()
                            }

                        )
                        GenderSelectDropdown(
                            selectedGender = gender,
                            onGenderSelected = {
                                gender = it
                                focusManager.clearFocus()
                            }
                        )
                        DateOfBirthPicker(
                            dateOfBirth = dateOfBirth
                        ) { selectedDate ->
                            dateOfBirth = selectedDate
                            focusManager.clearFocus()
                        }
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
                            existingProfessor?.let {
                                val professor = it.copy(
                                    firstName = firstName,
                                    lastName = lastName,
                                    gender = gender,
                                    dateOfBirth = dateOfBirth,
                                    profilePicturePath = profilePictureUri?.let { uri ->
                                        saveImageToInternalStorage(context, uri)
                                    }
                                )
                                viewModel.updateProfessor(professor)
                                onAddStudents()
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
                    Text(text = "Add some students")
                }
            }

        }
    }

}

