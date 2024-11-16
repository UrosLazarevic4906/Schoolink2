package com.example.schoolink.ui.components.inputs

import android.util.Patterns
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.schoolink.ui.theme.*

@Composable
fun EmailInputField(
    value: String,
    onValueChange: (String) -> Unit,
    isValid: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    var valid by remember { mutableStateOf(true) }

    LaunchedEffect(value) {
        valid = Patterns.EMAIL_ADDRESS.matcher(value).matches()
        isValid(valid)
    }

    val labelColor = when {
        value.isEmpty() -> Smoke
        valid -> Green
        else -> Color.Red
    }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = "Email Address", color = labelColor) },
        singleLine = true,
        modifier = modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email
        ),
        keyboardActions = KeyboardActions.Default,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.secondary,
            unfocusedBorderColor = Smoke,
            focusedLabelColor = labelColor,
            unfocusedLabelColor = Smoke,
            cursorColor = MaterialTheme.colorScheme.secondary,
            focusedLeadingIconColor = MaterialTheme.colorScheme.secondary,
            focusedTrailingIconColor = MaterialTheme.colorScheme.secondary
        )
    )
}

// TODO: add text for email already in use