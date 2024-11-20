package com.example.schoolink.ui.screens.management.overlay

import android.content.Context
import android.net.Uri
import android.util.Log
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.schoolink.ui.components.miscellaneous.TitleCard
import com.example.schoolink.R
import com.example.schoolink.domain.models.Gender
import com.example.schoolink.domain.models.StudentModel
import com.example.schoolink.ui.components.inputs.DateOfBirthPicker
import com.example.schoolink.ui.components.inputs.EmailInputField
import com.example.schoolink.ui.components.inputs.GenderSelectDropdown
import com.example.schoolink.ui.components.inputs.ImagePicker
import com.example.schoolink.ui.components.inputs.OutlinedInputField
import com.example.schoolink.ui.theme.*
import com.example.schoolink.utils.saveImageToInternalStorage

@Composable
fun CreateNewStudentOverlay(
    onDismiss: () -> Unit,
    onCreateNewStudent: (StudentModel) -> Unit,
    focusManager: FocusManager,
    context: Context
) {
    var profilePictureUri by remember { mutableStateOf<Uri?>(null) }
    var email by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf<Gender?>(null) }
    var dateOfBirth by remember { mutableStateOf("") }
    var description by remember { mutableStateOf(TextFieldValue("")) }

    var isNameValid by remember { mutableStateOf(false) }
    var isLastNameValid by remember { mutableStateOf(false) }
    var isEmailValid by remember { mutableStateOf(false) }

    var isLoading by remember { mutableStateOf(false) }

    val isFormValid =
        isEmailValid && isNameValid && isLastNameValid && gender != null && dateOfBirth.isNotEmpty()

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
                    onButtonClick = {
                        if (!isLoading)
                            onDismiss()
                    },
                    title = "Create student"
                )
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 12.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .size(150.dp)
                    ) {
                        ImagePicker(
                            imageUri = profilePictureUri,
                            color = Sand,
                            onImagePicked = { selectedUri -> profilePictureUri = selectedUri }
                        )
                    }
                    Column(
                        modifier = Modifier.padding(vertical = 24.dp)
                    ) {
                        EmailInputField(
                            value = email,
                            isValid = { isEmailValid = it },
                            onValueChange = { email = it.trim() },
                        )
                        OutlinedInputField(
                            value = firstName,
                            onValueChange = { firstName = it.trim() },
                            label = "First name *",
                            isValid = { isNameValid = it },
                            onDoneAction = {
                                focusManager.clearFocus()
                            }
                        )
                        OutlinedInputField(
                            value = lastName,
                            onValueChange = { lastName = it.trim() },
                            label = "Last name *",
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
                        OutlinedTextField(
                            value = description,
                            onValueChange = {
                                description = it
                            },
                            label = { Text("Description") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp),
                            maxLines = 5,
                            singleLine = false,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MaterialTheme.colorScheme.secondary,
                                unfocusedBorderColor = Smoke,
                                focusedLabelColor = Green,
                                unfocusedLabelColor = Smoke,
                                cursorColor = MaterialTheme.colorScheme.secondary,
                                focusedLeadingIconColor = MaterialTheme.colorScheme.secondary,
                                focusedTrailingIconColor = MaterialTheme.colorScheme.secondary
                            )
                        )
                    }

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
                        isLoading = true
                        val student = StudentModel(
                            email = email,
                            firstName = firstName,
                            lastName = lastName,
                            gender = gender,
                            dateOfBirth = dateOfBirth,
                            description = description.text,
                            profilePicturePath = profilePictureUri?.let { uri ->
                                saveImageToInternalStorage(context, uri)
                            }
                        )
                        onCreateNewStudent(student)
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
                    enabled = isFormValid,
                ) {
                    Text(text = "Create a new student")
                }
            }
        }
    }
}


@Preview
@Composable
private fun CreateNewStudentOverlayPreview() {
    CreateNewStudentOverlay(
        onDismiss = {},
        onCreateNewStudent = {},
        focusManager = LocalFocusManager.current,
        context = LocalContext.current
    )
}
