package com.example.schoolink.ui.screens.management.overlay

import android.content.Context
import android.net.Uri
import android.widget.Toast
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.schoolink.ui.components.miscellaneous.TitleCard
import com.example.schoolink.R
import com.example.schoolink.domain.models.Gender
import com.example.schoolink.domain.models.ProfessorModel
import com.example.schoolink.domain.models.StudentModel
import com.example.schoolink.ui.components.inputs.CredentialsOutlinedInputField
import com.example.schoolink.ui.components.inputs.DateOfBirthPicker
import com.example.schoolink.ui.components.inputs.EmailInputField
import com.example.schoolink.ui.components.inputs.GenderSelectDropdown
import com.example.schoolink.ui.components.inputs.ImagePicker
import com.example.schoolink.ui.theme.*
import com.example.schoolink.ui.viewmodels.ProfessorStudentViewModel
import com.example.schoolink.ui.viewmodels.StudentViewModel
import com.example.schoolink.utils.saveImageToInternalStorage

@Composable
fun EditExistingStudentOverlay(
    onDismiss: () -> Unit,
    student: StudentModel?,
    studentViewMode: StudentViewModel,
    professor: ProfessorModel?,
    professorStudentViewModel: ProfessorStudentViewModel,
    onEditStudent: (StudentModel?) -> Unit,
    onDeleteStudent: () -> Unit,
    focusManager: FocusManager,
    context: Context
) {
    var profilePictureUri by remember { mutableStateOf<Uri?>(null) }
    var email by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf<Gender?>(null) }
    var description by remember { mutableStateOf("") }

    var isNameValid by remember { mutableStateOf(false) }
    var isLastNameValid by remember { mutableStateOf(false) }
    var isEmailValid by remember { mutableStateOf(false) }

    var isLoading by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    LaunchedEffect(student) {
        profilePictureUri = student?.profilePicturePath?.let { Uri.parse(it) }
        email = student?.email ?: "email"
        firstName = student?.firstName ?: "first name"
        lastName = student?.lastName ?: "last name"
        gender = student?.gender
        description = student?.description.orEmpty()
        isNameValid = firstName.isNotEmpty()
        isLastNameValid = lastName.isNotEmpty()
        isEmailValid = email.isNotEmpty()
    }

    val isFormValid =
        isEmailValid && isNameValid && isLastNameValid && gender != null

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
                    startIcon = painterResource(R.drawable.ic_close),
                    onStartIcon = {
                        if (!isLoading)
                            onDismiss()
                    },
                    title = "Edit student",
                    endIcon = painterResource(R.drawable.ic_bin),
                    onEndIcon = {
                        showDeleteDialog = true
                    }
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
                        CredentialsOutlinedInputField(
                            value = firstName,
                            onValueChange = { firstName = it.trim() },
                            label = "First name *",
                            isValid = { isNameValid = it },
                            onDoneAction = {
                                focusManager.clearFocus()
                            }
                        )
                        CredentialsOutlinedInputField(
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
                        if (email != student?.email) {
                            studentViewMode.getStudentByEmail(email) { existingStudent ->
                                if (existingStudent != null) {
                                    Toast.makeText(
                                        context,
                                        "Email is already in use!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    isLoading = false
                                } else {
                                    val updatedStudent = student?.copy(
                                        email = email,
                                        firstName = firstName,
                                        lastName = lastName,
                                        gender = gender,
                                        description = description,
                                        profilePicturePath = profilePictureUri?.let { uri ->
                                            saveImageToInternalStorage(context, uri)
                                        } ?: student.profilePicturePath
                                    )
                                    onEditStudent(updatedStudent)
                                }
                            }
                        } else {
                            val updatedStudent = student.copy(
                                email = email,
                                firstName = firstName,
                                lastName = lastName,
                                gender = gender,
                                description = description,
                                profilePicturePath = profilePictureUri?.let { uri ->
                                    saveImageToInternalStorage(context, uri)
                                } ?: student.profilePicturePath
                            )
                            onEditStudent(updatedStudent)
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
                    enabled = isFormValid,
                ) {
                    Text(text = "Update student")
                }
            }
        }
    }

    if (showDeleteDialog) {
        AlertDialog(
            containerColor = MaterialTheme.colorScheme.background,
            onDismissRequest = { showDeleteDialog = false },
            title = {
                Text(
                    text = "Delete Student",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            text = {
                Text(
                    text = "Are you sure you want to delete this student? This action cannot be undone.",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        professor?.let { prof ->
                            student?.let { stud ->
                                professorStudentViewModel.removeStudentFromProfessor(prof.id, stud.id)
                                Toast.makeText(context, "Student deleted successfully!", Toast.LENGTH_SHORT).show()
                                showDeleteDialog = false
                                onDeleteStudent()
                            }

                        }

                    },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colorScheme.primary,
                        containerColor = MaterialTheme.colorScheme.background
                    )
                ) {
                    Text(text = "Delete")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDeleteDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colorScheme.secondary,
                        containerColor = MaterialTheme.colorScheme.background
                    )
                ) {
                    Text(
                        text = "Cancel"
                    )
                }
            }
        )
    }
}