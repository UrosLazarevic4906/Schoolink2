package com.example.schoolink.ui.screens.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.schoolink.R
import com.example.schoolink.ui.theme.*

sealed class Screen(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val backgroundRes: Int,
    val statuBarColor: Color
) {
    data object Home : Screen(
        title = "Home",
        description = "Welcome back!",
        icon = Icons.Default.Home,
        backgroundRes = R.drawable.bg_green,
        statuBarColor = Green
        )

    data object Presence : Screen(
        title = "Presence",
        description = "Complete attendance and activity history.",
        icon = Icons.Default.Person,
        backgroundRes = R.drawable.bg_yellow,
        statuBarColor = Yellow
    )

    data object Manage : Screen(
        title = "Management",
        description = "Manage students and groups that you are heading.",
        icon = Icons.Default.Home,
        backgroundRes = R.drawable.bg_blue,
        statuBarColor = Blue
        )

    data object Schedule : Screen(
        title = "Schedule",
        description = "Keep track of monthly your schedule.",
        icon = Icons.Default.Home,
        backgroundRes = R.drawable.bg_pink,
        statuBarColor = Pink
        )
}
