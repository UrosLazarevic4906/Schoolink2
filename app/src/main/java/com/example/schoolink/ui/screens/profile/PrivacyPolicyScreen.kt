package com.example.schoolink.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.schoolink.R
import com.example.schoolink.ui.components.miscellaneous.TitleCard
import com.example.schoolink.ui.components.miscellaneous.ExpandableItem

@Composable
fun PrivacyPolicyScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TitleCard(
            title = "Privacy Policy",
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
                    content = "Schoolink values your privacy. This policy outlines the data we collect, how we use it, and your rights regarding your information."
                )
            }
            item {
                ExpandableItem(
                    title = "Data Collection",
                    content = "We may collect the following types of data:\n\nPersonal Information: Name, email address, and other details provided during registration.\n\nUsage Data: Information about how you interact with the app."
                )
            }
            item {
                ExpandableItem(
                    title = "Data Sharing",
                    content = "We do not sell or rent your personal data to third parties.\n\nYour data may be shared with service providers who help us deliver the app’s features."
                )
            }
            item {
                ExpandableItem(
                    title = "Data Security",
                    content = "We implement industry-standard measures to protect your data.\n\nWhile we take steps to secure your data, we cannot guarantee 100% security."
                )
            }
            item {
                ExpandableItem(
                    title = "Cookies",
                    content = "The app may use cookies to enhance user experience. You can manage or disable cookies through your device settings."
                )
            }
            item {
                ExpandableItem(
                    title = "Your Rights",
                    content = "You have the right to access, update, or delete your personal data.\n\nTo exercise these rights, contact us at: support@schoolink.com"
                )
            }
            item {
                ExpandableItem(
                    title = "Policy Updates",
                    content = "This privacy policy may be updated periodically. We will notify you of any significant changes through the app or email."
                )
            }
            item {
                ExpandableItem(
                    title = "Contact Information",
                    content = "If you have any questions or concerns about this policy, please contact us at: support@schoolink.com"
                )
            }
        }
    }
}
