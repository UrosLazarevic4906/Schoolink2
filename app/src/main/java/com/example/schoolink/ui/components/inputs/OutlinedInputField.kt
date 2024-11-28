package com.example.schoolink.ui.components.inputs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.schoolink.ui.theme.*

@Composable
fun OutlinedInputField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    onDoneAction: () -> Unit = {}
) {


    val labelColor = when {
        value.isEmpty() -> Smoke
        else -> Green
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

