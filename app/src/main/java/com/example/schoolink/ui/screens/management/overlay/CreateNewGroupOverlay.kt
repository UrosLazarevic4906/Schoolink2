package com.example.schoolink.ui.screens.management.overlay

import android.content.Context
import android.net.Uri
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.schoolink.R
import com.example.schoolink.domain.models.GroupType
import com.example.schoolink.ui.components.inputs.ImagePicker
import com.example.schoolink.ui.components.inputs.GroupTypePicker
import com.example.schoolink.ui.components.inputs.OutlinedInputField
import com.example.schoolink.ui.components.miscellaneous.TitleCard
import com.example.schoolink.ui.theme.*

@Composable
fun CreateNewGroupOverlay(
    onDismiss: () -> Unit,
    onCreateGroup: () -> Unit,
    focusManager: FocusManager,
    context: Context
) {
    var groupPictureUri by remember { mutableStateOf<Uri?>(null) }
    var name by remember { mutableStateOf("") }
    var groupType by remember { mutableStateOf<GroupType?>(null) }

    val isFormValid = false

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
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 12.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp),
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
                    Text(text = "Create a new student")
                }
            }
        }
    }
}

@Preview
@Composable
private fun GroupOverlayPreview() {
    CreateNewGroupOverlay(
        context = LocalContext.current,
        focusManager = LocalFocusManager.current,
        onCreateGroup = {},
        onDismiss = {}
    )
}