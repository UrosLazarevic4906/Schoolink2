package com.example.schoolink.ui.components.inputs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.schoolink.domain.models.Gender
import com.example.schoolink.R
import com.example.schoolink.domain.models.GroupType
import com.example.schoolink.ui.theme.Black
import com.example.schoolink.ui.theme.Smoke
import com.example.schoolink.ui.theme.Green

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupTypePicker(
    selectedGroupType: GroupType?,
    onGroupTypeSelected: (GroupType) -> Unit
) {
    val groupTypeOptions = GroupType.entries
    var isExpanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = {
                isExpanded = !isExpanded
            }
        ) {
            OutlinedTextField(
                value = selectedGroupType?.name ?: "",
                onValueChange = {},
                readOnly = true,
                label = { Text(text = "Group Type *") },
                trailingIcon = {
                    Icon(
                        painter = painterResource(
                            id = if (isExpanded) R.drawable.ic_chevron_up else R.drawable.ic_chevron_down
                        ),
                        contentDescription = null,
                        tint = Smoke,
                        modifier = Modifier.size(24.dp)
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.secondary,
                    unfocusedBorderColor = Smoke,
                    focusedLabelColor = Green,
                    unfocusedLabelColor = if(selectedGroupType == null) Smoke else Green,
                    cursorColor = MaterialTheme.colorScheme.secondary,
                    focusedLeadingIconColor = MaterialTheme.colorScheme.secondary,
                    focusedTrailingIconColor = MaterialTheme.colorScheme.secondary
                ),
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }
            ) {
                groupTypeOptions.forEach { groupTypeOption ->
                    DropdownMenuItem(
                        text = { Text(text = groupTypeOption.name) },
                        onClick = {
                            onGroupTypeSelected(groupTypeOption)
                            isExpanded = false
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GenderSelectDropdownPreview() {
    GenderSelectDropdown(
        onGenderSelected = {},
        selectedGender = Gender.MALE
    )
}

