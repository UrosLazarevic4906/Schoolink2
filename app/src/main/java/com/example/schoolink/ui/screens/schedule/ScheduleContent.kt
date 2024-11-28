package com.example.schoolink.ui.screens.schedule

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale
import com.example.schoolink.R
import com.example.schoolink.data.mappers.GroupMapper
import com.example.schoolink.data.mappers.LessonMapper
import com.example.schoolink.domain.models.GroupModel
import com.example.schoolink.domain.models.LessonModel
import com.example.schoolink.domain.models.ProfessorModel
import com.example.schoolink.ui.theme.Ash
import com.example.schoolink.ui.theme.Black
import com.example.schoolink.ui.theme.Green
import com.example.schoolink.ui.theme.White
import com.example.schoolink.ui.viewmodels.GroupProfessorViewModel
import com.example.schoolink.ui.viewmodels.LessonGroupViewModel
import com.example.schoolink.ui.viewmodels.LessonProfessorViewModel
import com.example.schoolink.ui.viewmodels.LessonViewModel
import com.example.schoolink.ui.viewmodels.ProfessorViewModel

@Composable
fun ScheduleContent(
    email: String,
    professorViewModel: ProfessorViewModel,
    groupProfessorViewModel: GroupProfessorViewModel,
    lessonGroupViewModel: LessonGroupViewModel,
    lessonProfessorViewModel: LessonProfessorViewModel,
    lessonViewModel: LessonViewModel,
) {
    val currentDate = LocalDate.now()
    val currentMonth = currentDate.month
    val currentMonthName = currentMonth.getDisplayName(TextStyle.FULL, Locale.getDefault())
    val currentYear = currentDate.year
    val daysInMonth = YearMonth.of(currentYear, currentMonth.value).lengthOfMonth()

    var professor by remember { mutableStateOf<ProfessorModel?>(null) }
    var selectedDay by remember { mutableStateOf(currentDate.dayOfMonth) }
    var lessonsForSelectedDate by remember { mutableStateOf<List<LessonModel>>(emptyList()) }
    var allLessons by remember { mutableStateOf<List<LessonModel>>(emptyList()) }
    var showScheduleLessonOverlay by remember { mutableStateOf(false) }

    LaunchedEffect(email) {
        professorViewModel.getProfessorByEmail(email) { prof ->
            prof?.let {
                professor = it
                lessonProfessorViewModel.getLessonsWithProfessor(it.id) { lessonWithProfessor ->
                    allLessons = lessonWithProfessor?.lessons?.map { lessonEntity ->
                        LessonMapper.fromEntityToModel(lessonEntity)
                    } ?: emptyList()
                }
            }
        }
    }

    LaunchedEffect(selectedDay, allLessons) {
        lessonsForSelectedDate = allLessons.filter { lesson ->
            lesson.date.dayOfMonth == selectedDay &&
                    lesson.date.monthValue == currentMonth.value &&
                    lesson.date.year == currentYear
        }
    }

    Box(
        modifier = Modifier.padding(top = 220.dp)
    ) {
        Scaffold(
            containerColor = Color.Transparent,
            floatingActionButton = {
                Box(contentAlignment = Alignment.BottomEnd) {
                    FloatingActionButton(
                        onClick = {
                            showScheduleLessonOverlay = true
                        },
                        shape = CircleShape,
                        containerColor = Green
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_add),
                            contentDescription = "Add Lesson",
                            tint = White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
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

                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        if (lessonsForSelectedDate.isEmpty()) {
                            item {
                                Text(
                                    text = "No lessons scheduled for this day.",
                                    style = MaterialTheme.typography.bodyLarge,
                                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                                    color = Ash
                                )
                            }
                        } else {
                            items(lessonsForSelectedDate) { lesson ->
                                LessonBox(
                                    lesson = lesson,
                                    lessonGroupViewModel = lessonGroupViewModel
                                )
                            }
                        }
                    }
                }
            }
        )
    }

    AnimatedVisibility(
        visible = showScheduleLessonOverlay,
        enter = slideInVertically(
            initialOffsetY = { it },
            animationSpec = tween(1000)
        ),
        exit = slideOutVertically(
            targetOffsetY = { it },
            animationSpec = tween(1000)
        )
    ) {
        ScheduleLessonOverlay(
            email = email,
            focusManager = LocalFocusManager.current,
            groupProfessorViewModel = groupProfessorViewModel,
            professorViewModel = professorViewModel,
            lessonGroupViewModel = lessonGroupViewModel,
            lessonProfessorViewModel = lessonProfessorViewModel,
            lessonViewModel = lessonViewModel,
            onLessonCreated = {
                showScheduleLessonOverlay = false
                // Reload lessons after creating a new lesson
                professor?.let {
                    professor = it
                    lessonProfessorViewModel.getLessonsWithProfessor(it.id) { lessonWithProfessor ->
                        allLessons = lessonWithProfessor?.lessons?.map { lessonEntity ->
                            LessonMapper.fromEntityToModel(lessonEntity)
                        } ?: emptyList()
                    }
                }
            },
            onDismiss = {
                showScheduleLessonOverlay = false
            }
        )
    }
}

@Composable
fun LessonBox(
    lesson: LessonModel,
    lessonGroupViewModel: LessonGroupViewModel
) {
    var group by remember { mutableStateOf<GroupModel?>(null) }

    LaunchedEffect(lesson) {
        lessonGroupViewModel.getLessonWithGroup(lesson.lessonId) { lessonWithGroup ->
            group = lessonWithGroup?.group?.let { GroupMapper.fromEntityToModel(it) }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
            .padding(16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            group?.let {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    val groupImage = if (!it.groupPicturePath.isNullOrEmpty()) {
                        rememberAsyncImagePainter(model = it.groupPicturePath)
                    } else {
                        painterResource(id = R.drawable.ic_group)
                    }

                    Image(
                        painter = groupImage,
                        contentDescription = "${it.groupName} group picture",
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )

                    Column(
                        modifier = Modifier.padding(start = 12.dp)
                    ) {
                        Text(
                            text = it.groupName,
                            color = Black,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = it.groupType?.name ?: "Unknown Type",
                            color = Ash,
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            Text(
                text = "Time: ${lesson.startTime} - ${lesson.endTime}",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = Black
            )
        }
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