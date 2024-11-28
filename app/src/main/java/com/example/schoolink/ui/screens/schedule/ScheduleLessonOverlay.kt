package com.example.schoolink.ui.screens.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.schoolink.R
import com.example.schoolink.data.entities.relations.GroupWithProfessor
import com.example.schoolink.data.mappers.GroupMapper
import com.example.schoolink.domain.models.GroupModel
import com.example.schoolink.domain.models.LessonModel
import com.example.schoolink.domain.models.ProfessorModel
import com.example.schoolink.ui.components.inputs.DatePickerField
import com.example.schoolink.ui.components.inputs.TimePickerField
import com.example.schoolink.ui.components.miscellaneous.GroupCard
import com.example.schoolink.ui.components.miscellaneous.TitleCard
import com.example.schoolink.ui.theme.Cream
import com.example.schoolink.ui.theme.DissabledButton
import com.example.schoolink.ui.theme.Red
import com.example.schoolink.ui.theme.White
import com.example.schoolink.ui.viewmodels.GroupProfessorViewModel
import com.example.schoolink.ui.viewmodels.LessonGroupViewModel
import com.example.schoolink.ui.viewmodels.LessonProfessorViewModel
import com.example.schoolink.ui.viewmodels.LessonViewModel
import com.example.schoolink.ui.viewmodels.ProfessorViewModel
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun ScheduleLessonOverlay(
    email: String,
    professorViewModel: ProfessorViewModel,
    groupProfessorViewModel: GroupProfessorViewModel,
    lessonViewModel: LessonViewModel,
    lessonGroupViewModel: LessonGroupViewModel,
    lessonProfessorViewModel: LessonProfessorViewModel,
    onDismiss: () -> Unit,
    onLessonCreated: () -> Unit,
    focusManager: FocusManager
) {

    var date by remember { mutableStateOf(LocalDate.now()) }
    var startTime by remember { mutableStateOf(LocalTime.of(8, 0)) }
    var endTime by remember { mutableStateOf(LocalTime.of(10, 0)) }
    var groupsWithProfessor by remember { mutableStateOf<GroupWithProfessor?>(null) }
    var selectedGroup by remember { mutableStateOf<GroupModel?>(null) }
    var professor by remember { mutableStateOf<ProfessorModel?>(null) }

    LaunchedEffect(email) {
        professorViewModel.getProfessorByEmail(email) { prof ->
            prof?.let {
                professor = prof
                groupProfessorViewModel.getGroupsWithProfessor(it.id) { data ->
                    groupsWithProfessor = data
                }
            }
        }
    }

    Surface(
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp, start = 10.dp, end = 10.dp)
            .imePadding()
            .clickable(
                onClick = { focusManager.clearFocus() },
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Cream),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                TitleCard(
                    startIcon = painterResource(R.drawable.ic_close),
                    onStartIcon = onDismiss,
                    title = "Create group"
                )
            }

            Column(
                modifier = Modifier.padding(
                    vertical = 24.dp, horizontal = 32.dp,
                ),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DatePickerField(
                    selectedDate = date,
                    onDateSelected = { date = it }
                )
                TimePickerField(
                    title = "Start time",
                    selectedTime = startTime,
                    onTimeSelected = { startTime = it }
                )
                TimePickerField(
                    title = "End time",
                    selectedTime = endTime,
                    onTimeSelected = { endTime = it },
                    minTime = startTime
                )

            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 12.dp)
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                if (!groupsWithProfessor?.groups.isNullOrEmpty()) {
                    item {
                        Text(
                            text = "Select Groups",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }
                    itemsIndexed(groupsWithProfessor!!.groups) { index, group ->
                        GroupCard(
                            group = GroupMapper.fromEntityToModel(group),
                            showTopLine = index > 0,
                            showTrailingIcon = false,
                            backgroundColor = if (selectedGroup == GroupMapper.fromEntityToModel(
                                    group
                                )
                            ) Red.copy(alpha = 0.2f) else White,
                            onClick = {
                                selectedGroup = GroupMapper.fromEntityToModel(group)
                            }

                        )
                    }
                }
            }


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = {
                        val lesson = LessonModel(
                            date = date,
                            startTime = startTime,
                            endTime = endTime
                        )
                        professorViewModel.getProfessorByEmail(email) { professor ->
                            lessonViewModel.createLesson(lesson) { lessonId ->
                                if (lessonId > 0) {
                                    lessonProfessorViewModel.addLessonToProfessor(
                                        lessonId.toInt(),
                                        professor!!.id
                                    )
                                    lessonGroupViewModel.addGroupToLesson(
                                        lessonId.toInt(),
                                        groupId = selectedGroup!!.groupId
                                    )
                                }

                                onLessonCreated()
                            }
                        }

                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        disabledContainerColor = DissabledButton,
                        disabledContentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    enabled = selectedGroup != null
                ) {
                    Text(text = "Create a new student")
                }
            }
        }
    }
}