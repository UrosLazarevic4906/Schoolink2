package com.example.schoolink.ui.screens.authentication

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.example.schoolink.R
import com.example.schoolink.domain.models.Gender
import com.example.schoolink.domain.models.StudentModel
import com.example.schoolink.ui.theme.Cream
import com.example.schoolink.ui.viewmodels.StudentViewModel
import com.example.schoolink.utils.saveImageToInternalStorage

@Composable
fun StudentInputScreen(
    viewModel: StudentViewModel,
    context: Context
) {
    var email by remember { mutableStateOf("") }
    var profileUri by remember { mutableStateOf<Uri?>(null) }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf(Gender.MALE) }
    var dateOfBirth by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        profileUri = uri
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(modifier = Modifier
            .size(100.dp)
            .background(Cream, CircleShape)
            .clickable { launcher.launch("image/*") }) {
            if (profileUri == null) {
                Image(
                    painter = painterResource(id = R.drawable.ic_user),
                    contentDescription = "Profile Picture Placeholder",
                    modifier = Modifier.size(100.dp),
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    painter = rememberAsyncImagePainter(profileUri),
                    contentDescription = "Selected Profile Picture",
                    modifier = Modifier.size(100.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }

        OutlinedTextField(
            label = { Text("Email") },
            value = email,
            onValueChange = { email = it },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )


        OutlinedTextField(
            label = { Text("First Name") },
            value = firstName,
            onValueChange = { firstName = it },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            label = { Text("Last Name") },
            value = lastName,
            onValueChange = { lastName = it },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Gender.entries.forEach { genderOption ->
                Button(
                    onClick = { gender = genderOption },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (gender == genderOption) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
                    )
                ) {
                    Text(genderOption.name)
                }
            }
        }

        OutlinedTextField(
            label = { Text("Date of Birth") },
            value = dateOfBirth,
            onValueChange = { dateOfBirth = it },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            label = { Text("Description") },
            value = description,
            onValueChange = { description = it },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val newStudent = StudentModel(
                    email = email,
                    profilePicturePath = profileUri?.let { saveImageToInternalStorage(context, it) },
                    firstName = firstName,
                    lastName = lastName,
                    gender = gender,
                    dateOfBirth = dateOfBirth,
                    description = description
                )
                viewModel.addStudent(newStudent)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Student")
        }
    }
}
