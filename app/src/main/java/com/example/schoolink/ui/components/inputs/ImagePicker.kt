package com.example.schoolink.ui.components.inputs

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.example.schoolink.ui.theme.Cream
import com.example.schoolink.R
import com.example.schoolink.ui.theme.Gravel
import com.example.schoolink.ui.theme.Green
import com.example.schoolink.ui.theme.White

@Composable
fun ImagePicker(
    imageUri: Uri?,
    color: Color = Cream,
    onImagePicked: (Uri?) -> Unit
) {

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        onImagePicked(uri)
    }

    Box {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .background(color)
                .clickable {
                    launcher.launch("image/*")
                },
            contentAlignment = Alignment.Center
        ) {
            if (imageUri == null) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_user),
                    contentDescription = "Default Profile Picture",
                    tint = Gravel,
                    modifier = Modifier.fillMaxSize(0.3f)
                )
            } else {
                Image(
                    painter = rememberAsyncImagePainter(imageUri),
                    contentDescription = "Selected Profile Picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }


        }

        Box(
            modifier = Modifier
                .fillMaxSize(0.3f)
                .clip(CircleShape)
                .background(Green)
                .border(2.dp, White, CircleShape)
                .align(Alignment.BottomEnd)
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(
                    id = if (imageUri == null) R.drawable.ic_camera else R.drawable.ic_pencil
                ),
                contentDescription = if (imageUri == null) "Add Photo" else "Edit Photo",
                tint = White,
                modifier = Modifier.fillMaxSize(0.6f)
            )
        }
    }
}

@Preview
@Composable
private fun ImagePickerPreview() {
    ImagePicker(
        imageUri = null,
        onImagePicked = {}
    )
}

