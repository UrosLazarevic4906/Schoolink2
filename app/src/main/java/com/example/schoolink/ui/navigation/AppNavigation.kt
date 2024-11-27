package com.example.schoolink.ui.navigation

import android.net.Uri
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
import com.example.schoolink.ui.screens.LessonTestScreen
import com.example.schoolink.ui.screens.authentication.screen.AccountCreationFinishedScreen
import com.example.schoolink.ui.screens.authentication.screen.CreateAccountScreen
import com.example.schoolink.ui.screens.authentication.screen.GroupCreationScreen
import com.example.schoolink.ui.screens.authentication.screen.LoginScreen
import com.example.schoolink.ui.screens.authentication.screen.ProfessorSetupScreen
import com.example.schoolink.ui.screens.authentication.screen.StudentCreationScreen
import com.example.schoolink.ui.screens.main.MainScreen
import com.example.schoolink.ui.screens.main.Screen
import com.example.schoolink.ui.screens.management.screen.GroupManagementScreen
import com.example.schoolink.ui.screens.management.screen.StudentManagementScreen
import com.example.schoolink.ui.screens.onboarding.OnboardingScreen
import com.example.schoolink.ui.screens.profile.PrivacyPolicyScreen
import com.example.schoolink.ui.screens.profile.ProfessorInformationScreen
import com.example.schoolink.ui.screens.profile.ProfileScreen
import com.example.schoolink.ui.screens.profile.TermsAndConditionsScreen
import com.example.schoolink.ui.viewmodels.GroupProfessorViewModel
import com.example.schoolink.ui.viewmodels.GroupStudentViewModel
import com.example.schoolink.ui.viewmodels.GroupViewModel
import com.example.schoolink.ui.viewmodels.LessonGroupViewModel
import com.example.schoolink.ui.viewmodels.LessonProfessorViewModel
import com.example.schoolink.ui.viewmodels.LessonViewModel
import com.example.schoolink.ui.viewmodels.ProfessorStudentViewModel
import com.example.schoolink.ui.viewmodels.ProfessorViewModel
import com.example.schoolink.ui.viewmodels.StudentViewModel
import com.example.schoolink.ui.viewmodels.factory.GroupProfessorViewModelFactory
import com.example.schoolink.ui.viewmodels.factory.GroupStudentViewModelFactory
import com.example.schoolink.ui.viewmodels.factory.GroupViewModelFactory
import com.example.schoolink.ui.viewmodels.factory.LessonGroupViewModelFactory
import com.example.schoolink.ui.viewmodels.factory.LessonProfessorViewModelFactory
import com.example.schoolink.ui.viewmodels.factory.LessonViewModelFactory
import com.example.schoolink.ui.viewmodels.factory.ProfessorStudentViewModelFactory
import com.example.schoolink.ui.viewmodels.factory.ProfessorViewModelFactory
import com.example.schoolink.ui.viewmodels.factory.StudentViewModelFactory

@Composable
fun AppNavigation(
    professorViewModelFactory: ProfessorViewModelFactory,
    studentViewModelFactory: StudentViewModelFactory,
    professorStudentViewModelFactory: ProfessorStudentViewModelFactory,
    groupViewModelFactory: GroupViewModelFactory,
    groupProfessorViewModelFactory: GroupProfessorViewModelFactory,
    groupStudentViewModelFactory: GroupStudentViewModelFactory,
    lessonViewModelFactory: LessonViewModelFactory,
    lessonProfessorViewModelFactory: LessonProfessorViewModelFactory,
    lessonGroupViewModelFactory: LessonGroupViewModelFactory
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
                },
                onNavigateBack = {}
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
                onLogin = { email ->
                    navController.navigateSingleTopTo("mainScreen/${Uri.encode(email)}/Home")
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

            CreateAccountScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onCreateAccount = { email ->
                    navController.navigateSingleTopTo("professorSetupScreen/${Uri.encode(email)}")
                },
                onTermsAndConditions = {
                    navController.navigateSingleTopTo("termsAndConditions")
                },
                onPrivacyPolicy = {
                    navController.navigateSingleTopTo("privacyPolicy")
                }
            )
        }

        composable(
            route = "professorSetupScreen/{email}",
            arguments = listOf(
                navArgument("email") { type = NavType.StringType }
            ),
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
        ) { backStackEntry ->

            val viewModel: ProfessorViewModel = viewModel(factory = professorViewModelFactory)
            val email = backStackEntry.arguments?.getString("email") ?: ""

            ProfessorSetupScreen(
                email = email,
                context = context,
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onAddStudents = {
                    navController.navigateSingleTopTo("studentCreationScreen/${Uri.encode(email)}")
                }
            )
        }

        composable(
            route = "lessons/{email}",
            arguments = listOf(
                navArgument("email") { type = NavType.StringType }
            ),
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
        ) { backStackEntry ->
            val lessonViewModel: LessonViewModel = viewModel(factory = lessonViewModelFactory)
            val lessonProfessorViewModel: LessonProfessorViewModel =
                viewModel(factory = lessonProfessorViewModelFactory)
            val professorViewModel: ProfessorViewModel =
                viewModel(factory = professorViewModelFactory)
            val lessonGroupViewModel: LessonGroupViewModel =
                viewModel(factory = lessonGroupViewModelFactory)
            val email = backStackEntry.arguments?.getString("email") ?: ""

            LessonTestScreen(
                email = email,
                professorViewModel = professorViewModel,
                lessonViewModel = lessonViewModel,
                lessonGroupViewModel = lessonGroupViewModel,
                lessonProfessorViewModel = lessonProfessorViewModel,
                onLessonCreated = {}
            )
        }

        composable(
            route = "studentCreationScreen/{email}",
            arguments = listOf(
                navArgument("email") { type = NavType.StringType }
            ),
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
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""

            val professorViewModel: ProfessorViewModel =
                viewModel(factory = professorViewModelFactory)
            val studentViewModel: StudentViewModel = viewModel(factory = studentViewModelFactory)
            val professorStudentViewModel: ProfessorStudentViewModel =
                viewModel(factory = professorStudentViewModelFactory)

            StudentCreationScreen(
                email = email,
                context = context,
                professorViewModel = professorViewModel,
                studentViewModel = studentViewModel,
                professorStudentViewModel = professorStudentViewModel,
                onNext = {
                    navController.navigateSingleTopTo("groupCreationScreen/${Uri.encode(email)}")
                },
                onBack = {}
            )
        }

        composable(
            route = "groupCreationScreen/{email}",
            arguments = listOf(
                navArgument("email") { type = NavType.StringType }
            ),
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
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""

            val professorViewModel: ProfessorViewModel =
                viewModel(factory = professorViewModelFactory)
            val professorStudentViewModel: ProfessorStudentViewModel =
                viewModel(factory = professorStudentViewModelFactory)
            val groupViewModel: GroupViewModel = viewModel(factory = groupViewModelFactory)
            val groupProfessorViewModel: GroupProfessorViewModel =
                viewModel(factory = groupProfessorViewModelFactory)
            val groupStudentViewModel: GroupStudentViewModel =
                viewModel(factory = groupStudentViewModelFactory)

            GroupCreationScreen(
                email = email,
                context = context,
                onNext = {
                    navController.navigateSingleTopTo("accountCreationFinished")
                },
                onBack = {
                    navController.popBackStack()
                },
                professorViewModel = professorViewModel,
                groupViewModel = groupViewModel,
                groupProfessorViewModel = groupProfessorViewModel,
                groupStudentViewModel = groupStudentViewModel,
                professorStudentViewModel = professorStudentViewModel
            )
        }

        composable(
            route = "accountCreationFinished",
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
            AccountCreationFinishedScreen(
                onFinish = {
                    navController.navigateSingleTopTo("onboarding")
                },
                onNavigateBack = {

                }
            )
        }

        composable(
            route = "mainScreen/{email}/{selectedTab}",
            arguments = listOf(
                navArgument("email") { type = NavType.StringType },
                navArgument("selectedTab") { type = NavType.StringType }
            ),
            enterTransition = {
                EnterTransition.None
            },
            exitTransition = {
                ExitTransition.None
            },
            popEnterTransition = {
                EnterTransition.None
            },
            popExitTransition = {
                ExitTransition.None
            }
        ) { backStackEntry ->

            val email = backStackEntry.arguments?.getString("email") ?: ""
            val selectedTab = backStackEntry.arguments?.getString("selectedTab") ?: "Home"
            val professorViewModel: ProfessorViewModel =
                viewModel(factory = professorViewModelFactory)

            MainScreen(
                email = email,
                professorViewModel = professorViewModel,
                selectedTab = Screen.fromNavTitle(selectedTab),
                onStudent = {
                    navController.navigateSingleTopTo("studentManagementScreen/$email/Manage")
                },
                onGroup = {
                    navController.navigateSingleTopTo("groupManagementScreen/$email/Manage")
                },
                onProfile = {
                    navController.navigateSingleTopTo("profileScreen/${Uri.encode(email)}")
                }
            )
        }

        composable(
            route = "groupManagementScreen/{email}/{selectedTab}",
            arguments = listOf(
                navArgument("email") { type = NavType.StringType },
                navArgument("selectedTab") { type = NavType.StringType }
            ),
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
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            val selectedTab = backStackEntry.arguments?.getString("selectedTab") ?: "Home"

            val professorViewModel: ProfessorViewModel =
                viewModel(factory = professorViewModelFactory)
            val professorStudentViewModel: ProfessorStudentViewModel =
                viewModel(factory = professorStudentViewModelFactory)
            val groupViewModel: GroupViewModel = viewModel(factory = groupViewModelFactory)
            val groupProfessorViewModel: GroupProfessorViewModel =
                viewModel(factory = groupProfessorViewModelFactory)
            val groupStudentViewModel: GroupStudentViewModel =
                viewModel(factory = groupStudentViewModelFactory)

            GroupManagementScreen(
                email = email,
                context = context,
                onBack = {
                    navController.navigateSingleTopTo("mainScreen/$email/$selectedTab")
                },
                professorViewModel = professorViewModel,
                groupViewModel = groupViewModel,
                groupProfessorViewModel = groupProfessorViewModel,
                groupStudentViewModel = groupStudentViewModel,
                professorStudentViewModel = professorStudentViewModel
            )
        }

        composable(
            route = "studentManagementScreen/{email}/{selectedTab}",
            arguments = listOf(
                navArgument("email") { type = NavType.StringType },
                navArgument("selectedTab") { type = NavType.StringType }
            ),
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
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            val selectedTab = backStackEntry.arguments?.getString("selectedTab") ?: "Home"

            val professorViewModel: ProfessorViewModel =
                viewModel(factory = professorViewModelFactory)
            val studentViewModel: StudentViewModel = viewModel(factory = studentViewModelFactory)
            val professorStudentViewModel: ProfessorStudentViewModel =
                viewModel(factory = professorStudentViewModelFactory)

            StudentManagementScreen(
                email = email,
                context = context,
                professorViewModel = professorViewModel,
                studentViewModel = studentViewModel,
                professorStudentViewModel = professorStudentViewModel,
                onBack = {
                    navController.navigateSingleTopTo("mainScreen/$email/$selectedTab")
                }
            )
        }

        composable(
            route = "profileScreen/{email}",
            arguments = listOf(
                navArgument("email") { type = NavType.StringType }
            ),
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Up,
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
                    AnimatedContentTransitionScope.SlideDirection.Down,
                    animationSpec = tween(1000)
                )
            }
        ) {backStackEntry ->

            val email = backStackEntry.arguments?.getString("email") ?: ""

            val professorViewModel: ProfessorViewModel = viewModel(factory = professorViewModelFactory)

            ProfileScreen(
                email = email,
                professorViewModel = professorViewModel,
                onBack = {
                    navController.popBackStack()
                },
                onLogOut = {
                    navController.navigateSingleTopTo("onboarding")
                },
                onTermsAndConditions = {
                    navController.navigateSingleTopTo("termsAndConditions")
                },
                onPrivacyPolicy = {
                    navController.navigateSingleTopTo("privacyPolicy")
                },
                onPersonalInformation = {
                    navController.navigateSingleTopTo("personalInformationScreen/${Uri.encode(email)}")
                }
            )

        }

        composable(
            route = "personalInformationScreen/{email}",
            arguments = listOf(
                navArgument("email") { type = NavType.StringType }
            ),
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Up,
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
                    AnimatedContentTransitionScope.SlideDirection.Down,
                    animationSpec = tween(1000)
                )
            }
        ) {backStackEntry ->

            val email = backStackEntry.arguments?.getString("email") ?: ""

            val professorViewModel: ProfessorViewModel = viewModel(factory = professorViewModelFactory)


            ProfessorInformationScreen(
                email = email,
                professorViewModel = professorViewModel,
                context = context,
                onBack = {
                    navController.popBackStack()
                },
                onSave = {
                    navController.popBackStack()
                }
            )

        }

        composable(
            route = "termsAndConditions",
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

            TermsAndConditionsScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = "privacyPolicy",
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

            PrivacyPolicyScreen(
                onBack = {
                    navController.popBackStack()
                }
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