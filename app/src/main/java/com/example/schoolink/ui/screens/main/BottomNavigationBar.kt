package com.example.schoolink.ui.screens.main

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.schoolink.ui.theme.Smoke

@Composable
fun BottomNavigationBar(
    screens: List<Screen>,
    selectedScreen: Screen,
    onScreenSelected: (Screen) -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        screens.forEach { screen ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(screen.icon),
                        contentDescription = screen.title,
                        modifier = Modifier.size(24.dp)
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.background,

                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = Smoke,
                    unselectedTextColor = Smoke
                ),
                label = { Text(screen.navTitle) },
                selected = screen == selectedScreen,
                onClick = { onScreenSelected(screen) },
            )
        }
    }
}