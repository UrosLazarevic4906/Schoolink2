package com.example.schoolink.ui.screens.management.screen

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.example.schoolink.R
import com.example.schoolink.data.entities.relations.ProfessorWithStudents
import com.example.schoolink.data.mappers.StudentMapper
import com.example.schoolink.domain.models.ProfessorModel
import com.example.schoolink.domain.models.StudentModel
import com.example.schoolink.ui.components.miscellaneous.EmptyState
import com.example.schoolink.ui.components.miscellaneous.StudentCardEdit
import com.example.schoolink.ui.components.miscellaneous.TitleCard
import com.example.schoolink.ui.screens.management.overlay.AddExistingStudentOverlay
import com.example.schoolink.ui.screens.management.overlay.CreateNewGroupOverlay
import com.example.schoolink.ui.screens.management.overlay.CreateNewStudentOverlay
import com.example.schoolink.ui.theme.*
import com.example.schoolink.ui.viewmodels.ProfessorStudentViewModel
import com.example.schoolink.ui.viewmodels.ProfessorViewModel
import com.example.schoolink.ui.viewmodels.StudentViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun GroupManagementScreen(
    isOnMain: Boolean,
    onNext: () -> Unit = {},
    onBack: () -> Unit = {},
    context: Context,
//    professorViewModel: ProfessorViewModel,
//    studentViewModel: StudentViewModel,
//    professorStudentViewModel: ProfessorStudentViewModel
) {
    var showCreateGroupDialog by remember { mutableStateOf(false) }

    var professor by remember { mutableStateOf<ProfessorModel?>(null) }
    var professorWithStudents by remember { mutableStateOf<ProfessorWithStudents?>(null) }

//    LaunchedEffect(email) {
//        professorViewModel.getProfessorByEmail(email) { prof ->
//            prof?.let {
//                professor = it
//                professorStudentViewModel.getProfessorWithStudent(it.id) { data ->
//                    professorWithStudents = data
//                }
//            }
//        }
//    }

    Scaffold(
        floatingActionButton = {
            Box(contentAlignment = Alignment.BottomEnd) {
                FloatingActionButton(
                    onClick = {
                        showCreateGroupDialog = true
                    },
                    shape = CircleShape,
                    containerColor = Green
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_add),
                        contentDescription = "Add Student",
                        tint = White,
                        modifier = Modifier.size(24.dp)
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

            Column(
                modifier = Modifier.padding(horizontal = 18.dp, vertical = 24.dp)
            ) {
                if (isOnMain) {
                    TitleCard(
                        title = "Groups",
                        startIcon = painterResource(R.drawable.ic_chevron_left),
                        onStartIcon = onBack
                    )
                } else {
                    TitleCard(
                        title = "Groups",
                        clickableText = "Next",
                        onText = onNext,
                        startIcon = painterResource(R.drawable.ic_chevron_left),
                        onStartIcon = onBack
                    )
                }


                if (professorWithStudents?.students.isNullOrEmpty()) {
                    EmptyState(
                        image = painterResource(R.drawable.img_nothing_to_show),
                        title = "No groups yet",
                        description = "Start creating groups by pressing the green button"
                    )
                } else {
                    LazyColumn {
                        itemsIndexed(professorWithStudents!!.students) { index, student ->
                            StudentCardEdit(
                                student = StudentMapper.fromEntityToModel(student),
                                trailingIcon = painterResource(R.drawable.ic_pencil),
                                showTopLine = index > 0
                            )
                        }
                    }
                }
            }
        }
    }

    AnimatedVisibility(
        visible = showCreateGroupDialog,
        enter = slideInVertically(
            initialOffsetY = { it },
            animationSpec = tween(1000)
        ),
        exit = slideOutVertically(
            targetOffsetY = { it },
            animationSpec = tween(1000)
        )
    ) {
        CreateNewGroupOverlay(
            context = context,
            focusManager = LocalFocusManager.current,
            onDismiss = { showCreateGroupDialog = false },
            onCreateGroup = {
            }
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun GroupManagementScreenPreview() {
    GroupManagementScreen(
        isOnMain = false,
        context = LocalContext.current,
    )
}