package com.example.schoolink.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.schoolink.R
import com.example.schoolink.ui.components.miscellaneous.TitleCard
import com.example.schoolink.ui.components.miscellaneous.ExpandableItem

@Composable
fun TermsAndConditionsScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TitleCard(
            title = "Terms & Conditions",
            startIcon = painterResource(R.drawable.ic_chevron_left),
            onStartIcon = onBack
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
        ) {
            item {
                ExpandableItem(
                    title = "Introduction",
                    content = "Welcome to Schoolink! By downloading or using this app, you agree to comply with and be bound by the following terms and conditions. Please read these terms carefully before using the app."
                )
            }
            item {
                ExpandableItem(
                    title = "Use of the App",
                    content = "The app is intended for personal and educational use only.\n\nUsers must not attempt to hack, disrupt, or reverse-engineer any features of the app."
                )
            }
            item {
                ExpandableItem(
                    title = "User Accounts",
                    content = "Users are responsible for maintaining the confidentiality of their login information.\n\nAny unauthorized use of your account should be reported immediately."
                )
            }
            item {
                ExpandableItem(
                    title = "Prohibited Conduct",
                    content = "You agree not to:\n\nUse the app for unlawful purposes.\n\nEngage in activities that disrupt the functionality of the app or its services."
                )
            }
            item {
                ExpandableItem(
                    title = "Intellectual Property",
                    content = "All content, trademarks, and data on this app are owned by Schoolink or its licensors You are not permitted to reproduce, distribute, or modify any app materials without prior written permission."
                )
            }
            item {
                ExpandableItem(
                    title = "Termination",
                    content = "We reserve the right to suspend or terminate your account for any breach of these terms."
                )
            }
            item {
                ExpandableItem(
                    title = "Limitation of Liability",
                    content = "Schoolink will not be liable for any damages resulting from the use or inability to use the app."
                )
            }
            item {
                ExpandableItem(
                    title = "Changes to Terms",
                    content = "These terms may be updated from time to time. Continued use of the app constitutes acceptance of any changes."
                )
            }
            item {
                ExpandableItem(
                    title = "Contact Information",
                    content = "For questions or concerns, please contact us at: support@schoolink.com"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TermsAndConditionsPreview() {
    TermsAndConditionsScreen(onBack = {})
}
