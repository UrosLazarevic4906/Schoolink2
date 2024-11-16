package com.example.schoolink.ui.components.inputs

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import java.util.Calendar
import com.example.schoolink.R
import com.example.schoolink.ui.theme.*

@Composable
fun DateOfBirthPicker(
    dateOfBirth: String,
    onDateSelected: (String) -> Unit,
) {
    val context = LocalContext.current
    var dateText by remember { mutableStateOf(dateOfBirth) }

    val calendar = Calendar.getInstance()
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            dateText = "$dayOfMonth/${month + 1}/$year"
            onDateSelected(dateText)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    OutlinedTextField(
        value = dateText,
        onValueChange = {},
        readOnly = true,
        enabled = false,
        label = { Text("Date of Birth") },
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_calendar),
                contentDescription = "Select Date",
                tint = Smoke,
                modifier = Modifier.size(20.dp)
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            disabledTextColor = Black,
            disabledLabelColor = if(dateText == "") Smoke else Green,
            disabledBorderColor = Smoke,
            disabledContainerColor = White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
                datePickerDialog.show()
            }
    )
}


