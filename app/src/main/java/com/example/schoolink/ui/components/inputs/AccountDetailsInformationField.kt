package com.example.schoolink.ui.components.inputs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.schoolink.ui.theme.Ash
import com.example.schoolink.ui.theme.Black

@Composable
fun AccountDetailsInformationField(
    isPassword: Boolean,
    title: String,
    text: String,
    onEdit: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onEdit() } // Make the entire Box clickable
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = {},
            label = { Text(title) },
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = {
                Text(
                    text = "Edit",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary,
                )
            },
            colors = TextFieldDefaults.colors(
                disabledTextColor = Black,
                disabledLabelColor = Ash,
                disabledContainerColor = MaterialTheme.colorScheme.background
            ),
            readOnly = true,
            enabled = false,
            modifier = Modifier.fillMaxWidth()
        )
    }
}