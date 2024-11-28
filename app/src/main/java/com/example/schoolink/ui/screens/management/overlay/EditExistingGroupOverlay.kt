package com.example.schoolink.ui.screens.management.overlay

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.schoolink.R
import com.example.schoolink.data.entities.relations.ProfessorWithStudents
import com.example.schoolink.data.mappers.StudentMapper
import com.example.schoolink.domain.models.GroupModel
import com.example.schoolink.domain.models.GroupType
import com.example.schoolink.domain.models.StudentModel
import com.example.schoolink.ui.components.inputs.ImagePicker
import com.example.schoolink.ui.components.inputs.GroupTypePicker
import com.example.schoolink.ui.components.inputs.OutlinedInputField
import com.example.schoolink.ui.components.miscellaneous.StudentCardBox
import com.example.schoolink.ui.components.miscellaneous.TitleCard
import com.example.schoolink.ui.theme.*
import com.example.schoolink.ui.viewmodels.GroupStudentViewModel
import com.example.schoolink.ui.viewmodels.GroupViewModel
import com.example.schoolink.ui.viewmodels.ProfessorStudentViewModel
import com.example.schoolink.utils.saveImageToInternalStorage

@Composable
fun EditExistingGroupOverlay(
    onDismiss: () -> Unit,
    onGroupUpdated: () -> Unit,
    group: GroupModel,
    studentsInGroup: List<StudentModel>,
    focusManager: FocusManager,
    context: Context,
    groupViewModel: GroupViewModel,
    groupStudentViewModel: GroupStudentViewModel,
    professorStudentViewModel: ProfessorStudentViewModel,
    professorId: Int
) {
    var groupPictureUri by remember { mutableStateOf<Uri?>(null) }
    var name by remember { mutableStateOf("") }
    var groupType by remember { mutableStateOf<GroupType?>(null) }
    var professorWithStudents by remember { mutableStateOf<ProfessorWithStudents?>(null) }
    val selectedStudents = remember { mutableStateListOf<StudentModel>() }

    val isFormValid = name.isNotBlank() && groupType != null

    LaunchedEffect(group) {
        groupPictureUri = group.groupPicturePath?.let { Uri.parse(it) }
        name = group.groupName
        groupType = group.groupType
        selectedStudents.clear()
        selectedStudents.addAll(studentsInGroup)
    }

    LaunchedEffect(professorId) {
        professorStudentViewModel.getProfessorWithStudent(professorId) { data ->
            professorWithStudents = data
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
                    title = "Edit group"
                )
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 12.dp)
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .size(150.dp)
                    ) {
                        ImagePicker(
                            imageUri = groupPictureUri,
                            color = Sand,
                            onImagePicked = { selectedUri -> groupPictureUri = selectedUri }
                        )
                    }
                }

                item {
                    Column(
                        modifier = Modifier.padding(vertical = 24.dp)
                    ) {
                        OutlinedInputField(
                            value = name,
                            onValueChange = { name = it.trim() },
                            label = "Group name*",
                        )
                        GroupTypePicker(
                            selectedGroupType = groupType,
                            onGroupTypeSelected = {
                                groupType = it
                                focusManager.clearFocus()
                            }
                        )
                    }
                }

                if (!professorWithStudents?.students.isNullOrEmpty()) {
                    item {
                        Text(
                            text = "Select Students",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }
                    itemsIndexed(professorWithStudents!!.students) { index, student ->
                        val mappedStudent = StudentMapper.fromEntityToModel(student)

                        // Determine if the student is currently selected or pre-selected in the group
                        val isPreSelected = studentsInGroup.any { it.id == mappedStudent.id }
                        val isChecked = selectedStudents.contains(mappedStudent) || isPreSelected

                        StudentCardBox(
                            student = mappedStudent,
                            isChecked = isChecked,
                            showTopLine = index > 0,
                            onCheckChange = { isChecked ->
                                if (isChecked) {
                                    // Add the student if not already in the selection
                                    if (!selectedStudents.contains(mappedStudent)) {
                                        selectedStudents.add(mappedStudent)
                                    }
                                } else {
                                    // Remove the student from the selection
                                    if (selectedStudents.contains(mappedStudent)) {
                                        selectedStudents.remove(mappedStudent)
                                    }
                                }
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
                        groupStudentViewModel.removeAllStudentsFromGroup(group.groupId) {
                            selectedStudents.forEach { student ->
                                groupStudentViewModel.addStudentToGroup(group.groupId, student.id)
                            }

                            val updatedGroup = group.copy(
                                groupName = name,
                                groupType = groupType,
                                groupPicturePath = groupPictureUri?.let { uri ->
                                    saveImageToInternalStorage(context, uri)
                                } ?: group.groupPicturePath
                            )

                            groupViewModel.updateGroupAsync(updatedGroup) {
                                Toast.makeText(context, "Group updated successfully!", Toast.LENGTH_SHORT).show()
                                onGroupUpdated()
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
                    enabled = isFormValid,
                ) {
                    Text(text = "Update Group")
                }
            }
        }
    }
}
