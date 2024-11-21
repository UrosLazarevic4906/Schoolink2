package com.example.schoolink.ui.components.inputs

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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.schoolink.ui.theme.*

@Composable
fun CredentialsOutlinedInputField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    isValid: (Boolean) -> Unit,
    onDoneAction: () -> Unit = {}
) {

    var valid by remember { mutableStateOf(true) }
    LaunchedEffect(value) {
        valid = value.isNotEmpty() &&
                value[0].isUpperCase() &&
                value.any { it.isLetter() } &&
                value.drop(1).dropLast(1).all { it.isLowerCase() } &&
                (value.last().isLowerCase() || value.last().isWhitespace())
        isValid(valid)
    }

    val labelColor = when {
        value.isEmpty() -> Smoke
        valid -> Green
        else -> Red
    }
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label, color = labelColor) },
        singleLine = true,
        modifier = modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Text,
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                if (value.isNotEmpty() && value.last().isWhitespace()) {
                    onValueChange(value.trimEnd())
                }
                onDoneAction()
            },
        ),
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

