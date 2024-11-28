package com.example.schoolink.ui.screens.management.screen

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.schoolink.R
import com.example.schoolink.data.entities.relations.GroupWithProfessor
import com.example.schoolink.data.mappers.GroupMapper
import com.example.schoolink.domain.models.ProfessorModel
import com.example.schoolink.ui.components.miscellaneous.ImageInformation
import com.example.schoolink.ui.components.miscellaneous.GroupCard
import com.example.schoolink.ui.components.miscellaneous.TitleCard
import com.example.schoolink.ui.screens.management.overlay.CreateNewGroupOverlay
import com.example.schoolink.ui.theme.*
import com.example.schoolink.ui.viewmodels.GroupProfessorViewModel
import com.example.schoolink.ui.viewmodels.GroupStudentViewModel
import com.example.schoolink.ui.viewmodels.GroupViewModel
import com.example.schoolink.ui.viewmodels.ProfessorStudentViewModel
import com.example.schoolink.ui.viewmodels.ProfessorViewModel

@Composable
fun GroupManagementScreen(
    email: String,
    onBack: () -> Unit,
    context: Context,
    professorViewModel: ProfessorViewModel,
    professorStudentViewModel: ProfessorStudentViewModel,
    groupViewModel: GroupViewModel,
    groupProfessorViewModel: GroupProfessorViewModel,
    groupStudentViewModel: GroupStudentViewModel
) {
    var showCreateGroupDialog by remember { mutableStateOf(false) }

    var professor by remember { mutableStateOf<ProfessorModel?>(null) }
    var groupsWithProfessor by remember { mutableStateOf<GroupWithProfessor?>(null) }

    LaunchedEffect(email) {
        professorViewModel.getProfessorByEmail(email) { prof ->
            prof?.let {
                professor = it
                groupProfessorViewModel.getGroupsWithProfessor(it.id) { data ->
                    groupsWithProfessor = data
                }
            }
        }
    }

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

                TitleCard(
                    title = "Groups",
                    startIcon = painterResource(R.drawable.ic_chevron_left),
                    onStartIcon = onBack
                )



                if (groupsWithProfessor?.groups.isNullOrEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        ImageInformation(
                            image = painterResource(R.drawable.img_nothing_to_show),
                            title = "No groups yet",
                            description = "Start creating groups by pressing the green button"
                        )
                    }
                } else {
                    LazyColumn {
                        itemsIndexed(groupsWithProfessor!!.groups) { index, group ->
                            GroupCard(
                                group = GroupMapper.fromEntityToModel(group),
                                showTopLine = index > 0,
                                onClick = {}
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
            email = email,
            professorStudentViewModel = professorStudentViewModel,
            professorViewModel = professorViewModel,
            groupViewModel = groupViewModel,
            groupProfessorViewModel = groupProfessorViewModel,
            groupStudentViewModel = groupStudentViewModel,
            focusManager = LocalFocusManager.current,
            onDismiss = { showCreateGroupDialog = false },
            onGroupCreated = {
                groupProfessorViewModel.getGroupsWithProfessor(professor!!.id) { data ->
                    groupsWithProfessor = data
                }
                showCreateGroupDialog = false
            }
        )

    }

}
