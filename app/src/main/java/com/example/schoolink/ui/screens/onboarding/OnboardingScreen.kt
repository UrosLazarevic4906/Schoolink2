package com.example.schoolink.ui.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.schoolink.R
import com.example.schoolink.ui.theme.Ash
import com.example.schoolink.ui.theme.Black


@Composable
fun OnboardingScreen(
    onNavigationToLogin: () -> Unit,
    onNavigationToCreateAccount: () -> Unit
) {
    val pages = listOf(
        Triple(
            R.drawable.onboarding_support_solid,
            "Welcome to Schoolink!",
            "Simply log in with your Schoolink account or create a new one for free."
        ),
        Triple(
            R.drawable.onboarding_team_at_work_solid,
            "Effortlessly Manage Students!",
            "Keep track of attendance, assignments, and performance for each student."
        ),
        Triple(
            R.drawable.onboarding_team_at_work_2,
            "Organize and Connect Groups!",
            "Easily create and manage school groups, making communication and collaboration more effective."
        )
    )

    val pagerState = rememberPagerState(initialPage = 0, pageCount = { pages.size })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 24.dp, vertical = 32.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(state = pagerState) { pageIndex ->
            val (imageRes, title, description) = pages[pageIndex]

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 56.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                    ) {
                        Image(
                            painter = painterResource(imageRes),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds
                        )
                    }
                    Text(
                        text = title,
                        style = MaterialTheme.typography.displaySmall,
                        fontWeight = FontWeight.ExtraBold,
                        color = Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 32.dp, start = 32.dp, end = 32.dp)
                    )

                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Ash,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 42.dp, start = 32.dp, end = 32.dp)
                    )
                }
            }


        }

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            pages.forEachIndexed { index, _ ->
                val isCurrentPage = pagerState.currentPage == index
                Box(
                    modifier = Modifier
                        .size(width = 28.dp, height = 8.dp)
                        .padding(horizontal = 4.dp)
                        .background(
                            color = if (isCurrentPage) MaterialTheme.colorScheme.primary else Color.Transparent,
                            shape = RoundedCornerShape(50)
                        )
                        .border(
                            width = if (!isCurrentPage) 1.dp else 0.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(50)
                        )
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 52.dp)
            ) {
                Button(
                    onClick =  onNavigationToCreateAccount,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text("Create Account", color = MaterialTheme.colorScheme.onPrimary)
                }

                Button(
                    onClick = onNavigationToLogin,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text("Log In", color = MaterialTheme.colorScheme.onSecondary)
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOnboardingScreen() {
    OnboardingScreen(
        onNavigationToLogin = {},
        onNavigationToCreateAccount = {}
    )
}

