    package com.example.schoolink.ui.navigation

    import android.net.Uri
    import android.util.Log
    import androidx.compose.animation.AnimatedContentTransitionScope
    import androidx.compose.animation.EnterTransition
    import androidx.compose.animation.ExitTransition
    import androidx.compose.animation.core.tween
    import androidx.compose.runtime.Composable
    import androidx.compose.ui.platform.LocalContext
    import androidx.lifecycle.viewmodel.compose.viewModel
    import androidx.navigation.NavController
    import androidx.navigation.NavType
    import androidx.navigation.compose.NavHost
    import androidx.navigation.compose.composable
    import androidx.navigation.compose.rememberNavController
    import androidx.navigation.navArgument
    import com.example.schoolink.ui.screens.authentication.screen.CreateAccountScreen
    import com.example.schoolink.ui.screens.authentication.screen.LoginScreen
    import com.example.schoolink.ui.screens.authentication.screen.ProfessorSetupScreen
    import com.example.schoolink.ui.screens.main.HomeScreen
    import com.example.schoolink.ui.screens.onboarding.OnboardingScreen
    import com.example.schoolink.ui.viewmodels.ProfessorViewModel
    import com.example.schoolink.ui.viewmodels.factory.ProfessorViewModelFactory

    @Composable
    fun AppNavigation(
        professorViewModelFactory: ProfessorViewModelFactory,
    ) {
        val navController = rememberNavController()
        val context = LocalContext.current

        NavHost(
            navController = navController,
            startDestination = "onboarding"
        ) {
            composable(
                "onboarding",
                enterTransition = { EnterTransition.None },
                popEnterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None },
                popExitTransition = { ExitTransition.None },
            ) {
                OnboardingScreen(
                    onNavigationToLogin = {
                        navController.navigateSingleTopTo("login")
                    },
                    onNavigationToCreateAccount = {
                        navController.navigateSingleTopTo("createAccount")
                    }
                )
            }

            composable(
                route = "login",
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(1000)
                    )
                },
                exitTransition = {
                    ExitTransition.None
                },
                popEnterTransition = {
                    EnterTransition.None
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(1000)
                    )
                }
            ) {
                val viewModel: ProfessorViewModel = viewModel(factory = professorViewModelFactory)
                LoginScreen(
                    viewModel = viewModel,
                    context = context,
                    onBack = {
                        navController.popBackStack()
                    },
                    onNavigateToCreateAccount = {
                        navController.navigateSingleTopTo("createAccount")
                    },
                    onLogin = {email ->
                        navController.navigateSingleTopTo("homeScreen/${Uri.encode(email)}")
                    },
                    onSetupAccount = { email ->
                        navController.navigateSingleTopTo("professorSetupScreen/${Uri.encode(email)}")
                    }
                )
            }

            composable(
                route = "createAccount",
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(1000)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(1000)
                    )
                },
                popEnterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(1000)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(1000)
                    )
                }
            ) {

                val viewModel: ProfessorViewModel = viewModel(factory = professorViewModelFactory)

                CreateAccountScreen(
                    viewModel = viewModel,
                    onBack = { navController.popBackStack() },
                    onCreateAccount = { email ->
                        navController.navigateSingleTopTo("professorSetupScreen/${Uri.encode(email)}")
                    }
                )
            }

            composable(
                route = "professorSetupScreen/{email}",
                arguments = listOf(
                    navArgument("email") { type = NavType.StringType}
                ),
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(1000)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(1000)
                    )
                },
                popEnterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(1000)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(1000)
                    )
                }
            ) {backStackEntry ->

                val viewModel: ProfessorViewModel = viewModel(factory = professorViewModelFactory)
                val email = backStackEntry.arguments?.getString("email") ?: ""
                Log.d("Email", "Email passed: $email")
                ProfessorSetupScreen(
                    email = email,
                    context = context,
                    viewModel = viewModel,
                    onBack = { navController.popBackStack() },
                )
            }

            composable(
                route = "homeScreen/{email}",
                arguments = listOf(
                    navArgument("email") { type = NavType.StringType}
                ),
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(1000)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(1000)
                    )
                },
                popEnterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(1000)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(1000)
                    )
                }
            ) {backStackEntry ->

                val viewModel: ProfessorViewModel = viewModel(factory = professorViewModelFactory)
                val email = backStackEntry.arguments?.getString("email") ?: ""
                Log.d("Email", "Email passed: $email")
                HomeScreen(
                    email = email,
                    viewModel = viewModel
                )
            }
        }
    }

    fun NavController.navigateSingleTopTo(route: String) {
        if (this.currentBackStackEntry?.destination?.route != route) {
            this.navigate(route) {
                launchSingleTop = true
            }
        }
    }
