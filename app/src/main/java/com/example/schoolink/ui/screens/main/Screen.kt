package com.example.schoolink.ui.screens.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.schoolink.R

sealed class Screen(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val backgroundColor: Color,
    val imageRes: Int
) {
    object Home : Screen(
        title = "Home",
        description = "Welcome back!",
        icon = Icons.Default.Home,
        backgroundColor = Color(0xFF00796B),
        imageRes = R.drawable.ic_add // Replace with your resource
    )

    object Presence : Screen(
        title = "Presence",
        description = "Complete attendance and activity history.",
        icon = Icons.Default.Person,
        backgroundColor = Color(0xFFFFA726),
        imageRes = R.drawable.ic_add // Replace with your resource
    )

    object Manage : Screen(
        title = "Management",
        description = "Manage students and groups.",
        icon = Icons.Default.Home,
        backgroundColor = Color(0xFF29B6F6),
        imageRes = R.drawable.ic_add // Replace with your resource
    )

    object Schedule : Screen(
        title = "Schedule",
        description = "Keep track of your schedule.",
        icon = Icons.Default.Home,
        backgroundColor = Color(0xFFE91E63),
        imageRes = R.drawable.ic_add // Replace with your resource
    )

    object Messages : Screen(
        title = "Messages",
        description = "View and manage your messages.",
        icon = Icons.Default.Home,
        backgroundColor = Color(0xFFD32F2F),
        imageRes = R.drawable.ic_add // Replace with your resource
    )
}
