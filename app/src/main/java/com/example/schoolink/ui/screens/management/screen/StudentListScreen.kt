package com.example.schoolink.ui.screens.management.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.schoolink.data.entities.StudentEntity
import com.example.schoolink.R
import com.example.schoolink.ui.components.TitleLeftButton
import com.example.schoolink.ui.theme.*

@Composable
fun StudentListScreen(
    studentList: List<StudentEntity> = emptyList()
) {
    var showOptions by remember { mutableStateOf(false) }
    var showCreateStudentDialog by remember { mutableStateOf(false) }
    var showAddExistingStudentDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            Box(contentAlignment = Alignment.BottomEnd) {
                FloatingActionButton(
                    onClick = {
                        showOptions = true
                    },
                    shape = CircleShape,
                    containerColor = Green
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_add),
                        contentDescription = "Add Student",
                        tint = White
                    )
                }
                DropdownMenu(
                    expanded = showOptions,
                    onDismissRequest = { showOptions = false },
                    modifier = Modifier.background(White, RoundedCornerShape(8.dp))
                ) {
                    DropdownMenuItem(
                        onClick = {
                            showOptions = false
                            showCreateStudentDialog = true
                        },
                        text = {
                            Text(text = "Create a new student")
                        }
                    )
                    DropdownMenuItem(
                        onClick = {
                            showOptions = false
                            showAddExistingStudentDialog = true
                        },
                        text = { Text(text = "Add an existing student") }
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            TitleLeftButton(
                icon = painterResource(R.drawable.ic_chevron_left),
                title = "Students",
                onClick = {}
            )

            if (studentList.isEmpty()) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.img_nothing_to_show),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds
                        )
                    }
                    Text(
                        text = "No students yet",
                        style = MaterialTheme.typography.displaySmall,
                        fontWeight = FontWeight.ExtraBold,
                        color = Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 32.dp)
                    )
                    Text(
                        text = "Start adding students by pressing the green button",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Ash,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun StudentListScreenPreview() {
    StudentListScreen()
}
