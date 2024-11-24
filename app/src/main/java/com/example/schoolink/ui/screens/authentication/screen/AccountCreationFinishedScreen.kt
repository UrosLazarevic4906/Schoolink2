package com.example.schoolink.ui.screens.authentication.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.schoolink.ui.components.miscellaneous.ImageInformation
import com.example.schoolink.R
import com.example.schoolink.ui.theme.DissabledButton
import com.example.schoolink.ui.theme.SchoolinkTheme

@Composable
fun AccountCreationFinishedScreen(
    onFinish: () -> Unit,
    onNavigateBack: () -> Unit
) {

    BackHandler {
        onNavigateBack()
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            ImageInformation(
                image = painterResource(R.drawable.img_cat_in_box),
                title = "Everything's set!",
                description = "You have successfully created your account"
            )
        }

        Column(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = onFinish,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledContainerColor = DissabledButton,
                    disabledContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
            ) {
                Text(text = "Continue")
            }
        }

    }
}

@Preview
@Composable
private fun AccountCreationFinishedScreenPreview() {
    AccountCreationFinishedScreen(
        onFinish = {},
        onNavigateBack = {}
    )
}