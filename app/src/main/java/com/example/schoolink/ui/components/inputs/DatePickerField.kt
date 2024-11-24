package com.example.schoolink.ui.components.inputs

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import java.time.LocalDate
import java.util.Calendar

@Composable
fun DatePickerField(
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit
) {
    val context = LocalContext.current
    var displayText by remember { mutableStateOf(selectedDate.toString()) }

    val calendar = Calendar.getInstance()

    // Initialize DatePickerDialog with current selectedDate
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            val newDate = LocalDate.of(year, month + 1, dayOfMonth) // Months are 0-based in DatePicker
            onDateSelected(newDate)
            displayText = newDate.toString() // Update the displayed date
        },
        selectedDate.year,
        selectedDate.monthValue - 1, // Months are 0-based
        selectedDate.dayOfMonth
    )

    OutlinedTextField(
        value = displayText,
        onValueChange = {},
        label = { Text("Select Date") },
        readOnly = true,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                datePickerDialog.datePicker.maxDate = System.currentTimeMillis() // Optional: Limit to current or future dates
                datePickerDialog.show()
            }
    )
}
