package com.example.schoolink.ui.components.inputs

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.schoolink.R
import com.example.schoolink.ui.theme.*

@Composable
fun PasswordInputField(
    value: String,
    onValueChange: (String) -> Unit,
    isValid: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    var valid by remember { mutableStateOf(true) }
    var passwordVisible by remember { mutableStateOf(false) }

    LaunchedEffect(value) {
        valid = value.length > 8 &&
                value.any { it.isUpperCase() } &&
                value.any { it.isDigit() || !it.isLetterOrDigit() } &&
                !value.contains(' ')
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
        label = { Text(text = "Password *", color = labelColor) },
        singleLine = true,
        modifier = modifier.fillMaxWidth(),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Password
        ),
        keyboardActions = KeyboardActions.Default,
        trailingIcon = {
            val iconResId = if (passwordVisible) R.drawable.ic_eye_open else R.drawable.ic_eye_closed
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Image(painter = painterResource(id = iconResId), contentDescription = if (passwordVisible) "Hide password" else "Show password")
            }
        },
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

// TODO: Add trailing icon and text below the input field if field is not valid