package com.example.schoolink.ui.screens.main

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BottomNavigationBar(
    screens: List<Screen>,
    selectedScreen: Screen,
    onScreenSelected: (Screen) -> Unit
) {
    NavigationBar {
        screens.forEach { screen ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = screen.title
                    )
                },
                label = { Text(screen.title) },
                selected = screen == selectedScreen,
                onClick = { onScreenSelected(screen) }
            )
        }
    }
}