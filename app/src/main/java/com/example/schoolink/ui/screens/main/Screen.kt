package com.example.schoolink.ui.screens.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.schoolink.R
import com.example.schoolink.ui.theme.*


sealed class Screen(
    val title: String,
    val navTitle: String,
    val description: String,
    val icon: Int,
    val backgroundRes: Int,
) {
//    data object Home : Screen(
//        title = "Home",
//        navTitle = "Home",
//        description = "Welcome back!",
//        icon = R.drawable.ic_home,
//        backgroundRes = R.drawable.bg_green,
//        )

    data object Schedule : Screen(
        title = "Schedule",
        navTitle = "Schedule",
        description = "Keep track of monthly your schedule.",
        icon = R.drawable.ic_clippboard,
        backgroundRes = R.drawable.bg_pink,
    )

//    data object History : Screen(
//        title = "History",
//        navTitle = "History",
//        description = "Complete attendance and activity history.",
//        icon = R.drawable.ic_calendar,
//        backgroundRes = R.drawable.bg_yellow,
//    )

    data object Manage : Screen(
        title = "Management",
        navTitle = "Manage",
        description = "Manage students and groups that you are heading.",
        icon = R.drawable.ic_group,
        backgroundRes = R.drawable.bg_blue,
        )

//    companion object {
//        fun fromNavTitle(navTitle: String): Screen {
//            return when (navTitle) {
//                Home.navTitle -> Home
//                Schedule.navTitle -> Schedule
////                History.navTitle -> History
//                Manage.navTitle -> Manage
////                else -> Home
//            }
//        }
//    }
}
