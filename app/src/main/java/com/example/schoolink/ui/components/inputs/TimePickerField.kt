package com.example.schoolink.ui.components.inputs

import android.app.TimePickerDialog
import android.widget.Toast
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
    onTimeSelected: (LocalTime) -> Unit,
    title: String,
    minTime: LocalTime? = null
) {
    val context = LocalContext.current
    var displayText by remember { mutableStateOf(selectedTime.toString()) }

    val timePickerDialog = TimePickerDialog(
        context,
        { _, hour: Int, minute: Int ->
            val newTime = LocalTime.of(hour, minute)

            // Check if the selected time is valid based on minTime
            if (minTime == null || newTime >= minTime) {
                onTimeSelected(newTime)
                displayText = newTime.toString()
            } else {
                Toast.makeText(context, "Please select a time after $minTime", Toast.LENGTH_SHORT).show()
            }
        },
        selectedTime.hour,
        selectedTime.minute,
        true
    )

    OutlinedTextField(
        value = displayText,
        onValueChange = {},
        label = { Text(text = title) },
        readOnly = true,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                timePickerDialog.show()
            }
    )
}
