package com.example.schoolink.ui.components.inputs

import android.app.TimePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import java.time.LocalTime

@Composable
fun TimePickerField(
    selectedTime: LocalTime,
    onTimeSelected: (LocalTime) -> Unit
) {
    val context = LocalContext.current
    var displayText by remember { mutableStateOf(selectedTime.toString()) }

    // Initialize TimePickerDialog with current selectedTime
    val timePickerDialog = TimePickerDialog(
        context,
        { _, hour: Int, minute: Int ->
            val newTime = LocalTime.of(hour, minute)
            onTimeSelected(newTime)
            displayText = newTime.toString() // Update the displayed time
        },
        selectedTime.hour,
        selectedTime.minute,
        true // Use 24-hour format, set to false for 12-hour format
    )

    OutlinedTextField(
        value = displayText,
        onValueChange = {},
        label = { Text("Select Time") },
        readOnly = true,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                timePickerDialog.show()
            }
    )
}
