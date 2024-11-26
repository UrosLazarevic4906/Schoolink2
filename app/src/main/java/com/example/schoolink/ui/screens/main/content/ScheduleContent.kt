package com.example.schoolink.ui.screens.main.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale
import com.example.schoolink.R
import com.example.schoolink.ui.theme.Ash
import com.example.schoolink.ui.theme.Black
import com.example.schoolink.ui.theme.Green
import com.example.schoolink.ui.theme.White

@Composable
fun ScheduleContent() {

    val currentDate = LocalDate.now()
    val currentMonth = currentDate.month
    val currentMonthName = currentMonth.getDisplayName(TextStyle.FULL, Locale.getDefault())
    val currentYear = currentDate.year
    val daysInMonth = YearMonth.of(currentYear, currentMonth.value).lengthOfMonth()

    var selectedDay by remember { mutableStateOf(currentDate.dayOfMonth) }

    Box(
        modifier = Modifier.padding(top = 220.dp)
    ) {
        Scaffold(
            containerColor = Color.Transparent,
            floatingActionButton = {
                FloatingActionButton(onClick = {

                }) {
                    Image(
                        painter = painterResource(R.drawable.ic_add),
                        contentDescription = "Floating action button"
                    )
                }
            },
            content = { padding ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                ) {
                    ScheduleHeader(
                        monthName = currentMonthName,
                        selectedDay = selectedDay,
                        onDaySelected = { selectedDay = it },
                        daysInMonth = daysInMonth
                    )

                    // Schedule Content
                    ScheduleBody()
                }
            }
        )
    }
}

@Composable
fun ScheduleHeader(
    monthName: String,
    selectedDay: Int,
    onDaySelected: (Int) -> Unit,
    daysInMonth: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        // Display Current Month
        Text(
            text = monthName,
            color = Black,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 12.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(daysInMonth) { day ->

                val dayName = LocalDate.of(LocalDate.now().year, LocalDate.now().month, day + 1)
                    .dayOfWeek
                    .getDisplayName(TextStyle.SHORT, Locale.getDefault()).uppercase(Locale.getDefault())

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    // Day Name (e.g., Mon)
                    Text(
                        text = dayName,

                        style = MaterialTheme.typography.bodySmall,
                        color = Ash
                    )

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(
                                if (day + 1 == selectedDay) MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.background
                            )
                            .clickable { onDaySelected(day + 1) }
                    ) {
                        Text(
                            text = "${day + 1}",
                            color = if (day + 1 == selectedDay) Color.White
                            else MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ScheduleBody() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        items(24) { hour ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${if (hour < 10) "0$hour" else hour}:00",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.width(64.dp)
                )
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(56.dp)
//                        .background(MaterialTheme.colorScheme.secondaryContainer)
//                ) {
//                    Text(
//                        text = "Class 102\n11:00 - 13:00",
//                        modifier = Modifier.padding(8.dp),
//                        color = MaterialTheme.colorScheme.onSecondaryContainer
//                    )
//                }
            }
        }
    }
}