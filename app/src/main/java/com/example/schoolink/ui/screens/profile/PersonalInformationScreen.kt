package com.example.schoolink.ui.screens.profile

import androidx.compose.ui.res.painterResource
import com.example.schoolink.domain.models.ProfessorModel
import com.example.schoolink.ui.components.miscellaneous.TitleCard
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
import com.example.schoolink.ui.components.inputs.DateOfBirthPicker
import com.example.schoolink.ui.components.inputs.GenderSelectDropdown
import com.example.schoolink.ui.components.inputs.ImagePicker
import com.example.schoolink.ui.components.inputs.CredentialsOutlinedInputField
import com.example.schoolink.ui.theme.DissabledButton
import com.example.schoolink.ui.theme.SchoolinkTheme
import com.example.schoolink.ui.viewmodels.ProfessorViewModel
import com.example.schoolink.utils.saveImageToInternalStorage
import com.example.schoolink.R
import com.example.schoolink.domain.models.Gender

@Composable
fun ProfessorInformationScreen(
    email: String,
    onBack: () -> Unit,
    onSave: () -> Unit,
    professorViewModel: ProfessorViewModel,
    context: Context,
) {
    var professor by remember { mutableStateOf<ProfessorModel?>(null) }
    var profilePictureUri by remember { mutableStateOf<Uri?>(null) }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf<Gender?>(null) }

    var isNameValid by remember { mutableStateOf(false) }
    var isLastNameValid by remember { mutableStateOf(false) }
    val isFormValid = isNameValid && isLastNameValid && gender != null

    val focusManager = LocalFocusManager.current

    LaunchedEffect(email) {
        professorViewModel.getProfessorByEmail(email) { prof ->
            prof?.let {
                professor = it
                firstName = it.firstName ?: ""
                lastName = it.lastName ?: ""
                gender = it.gender
                profilePictureUri = it.profilePicturePath?.let { path -> Uri.parse(path) }
            }
        }
    }

    SchoolinkTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 24.dp, vertical = 32.dp)
                .clickable(
                    onClick = { focusManager.clearFocus() },
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                )
        ) {
            Column(verticalArrangement = Arrangement.SpaceBetween) {

                TitleCard(
                    title = "Personal information",
                    startIcon = painterResource(R.drawable.ic_chevron_left),
                    onStartIcon = onBack,
                    clickableText = "Save",
                    onText = {
                        if (isFormValid) {
                            val updatedProfessor = professor?.copy(
                                firstName = firstName,
                                lastName = lastName,
                                gender = gender,
                                profilePicturePath = profilePictureUri?.let {
                                    saveImageToInternalStorage(context, it)
                                } ?: professor?.profilePicturePath
                            )
                            professorViewModel.updateProfessorAsync(updatedProfessor!!) {
                                onSave()
                            }
                        }
                    }
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Box(
                        modifier = Modifier
                            .size(150.dp)
                    ) {
                        ImagePicker(
                            imageUri = profilePictureUri,
                            onImagePicked = { selectedUri -> profilePictureUri = selectedUri }
                        )
                    }

                    Column(
                        modifier = Modifier.padding(horizontal = 14.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        CredentialsOutlinedInputField(
                            value = firstName,
                            onValueChange = { firstName = it.trim() },
                            label = "First name",
                            isValid = { isNameValid = it },
                            onDoneAction = {
                                focusManager.clearFocus()
                            }
                        )
                        CredentialsOutlinedInputField(
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
                    }
                }
            }
        }
    }
}
