package com.Color.Sphere.Challenge.gamecolor.presentation.navigation


import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.Color.Sphere.Challenge.gamecolor.data.Levels
import com.Color.Sphere.Challenge.gamecolor.data.Prefs
import com.Color.Sphere.Challenge.gamecolor.data.SoundManager
import com.Color.Sphere.Challenge.gamecolor.presentation.view.GameScreen
import com.Color.Sphere.Challenge.gamecolor.presentation.view.LevelScreen
import com.Color.Sphere.Challenge.gamecolor.presentation.view.MainMenuScreen
import com.Color.Sphere.Challenge.gamecolor.presentation.view.OptionsScreen
import com.Color.Sphere.Challenge.gamecolor.presentation.view.PolicyScreen
import com.Color.Sphere.Challenge.gamecolor.presentation.view.SplashScreen
import com.Color.Sphere.Challenge.gamecolor.presentation.view.WelcomeScreen


@Composable
fun NavigationScreen(
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = Destinations.SplashScreen.route,
        modifier = Modifier,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
    ) {

        composable(route = Destinations.SplashScreen.route) {
            SplashScreen {
                if (Prefs.startStepCompleted) {
                    navHostController.navigate(Destinations.MainMenuScreen.route) {
                        popUpTo(Destinations.SplashScreen.route) {
                            inclusive = true
                        }
                    }
                } else {
                    navHostController.navigate(Destinations.WelcomeScreen.route) {
                        popUpTo(Destinations.SplashScreen.route) {
                            inclusive = true
                        }
                    }
                }

            }
        }
        composable(route = Destinations.WelcomeScreen.route) {
            WelcomeScreen(

                onOkClicked = {
                    Prefs.startStepCompleted = true
                    navHostController.navigate(Destinations.MainMenuScreen.route) {
                        popUpTo(Destinations.WelcomeScreen.route) {
                            inclusive = true
                        }
                    }
                }, onPolicyClicked = {
                    navHostController.navigate(Destinations.PolicyScreen.route) {
                        popUpTo(Destinations.WelcomeScreen.route) {
                        }
                    }
                })


        }
        composable(route = Destinations.PolicyScreen.route) {
            PolicyScreen(
                onBackClicked = { navHostController.navigateUp() }
            )
        }
        composable(route = Destinations.MainMenuScreen.route) {
            MainMenuScreen(
                onPolicyClicked = {
                    navHostController.navigate(Destinations.PolicyScreen.route) {
                        popUpTo(Destinations.MainMenuScreen.route) {
                        }
                    }
                },
                onOptionClicked = {
                    navHostController.navigate(Destinations.OptionScreen.route) {
                        popUpTo(Destinations.MainMenuScreen.route) {
                        }
                    }
                },
                onStartClicked = {
                    navHostController.navigate(Destinations.LevelScreen.route) {
                        popUpTo(Destinations.MainMenuScreen.route) {
                        }
                    }
                }


            )
        }
        composable(route = Destinations.OptionScreen.route) {
            OptionsScreen(onBackClicked = { navHostController.navigateUp() })
        }
        composable(route = Destinations.LevelScreen.route) {
            LevelScreen(
                onLevelSelect = {


                    level ->
                    navHostController.navigate("${Destinations.GameScreen.route}/$level")


                },
                onBackClicked = { navHostController.navigateUp() }
            )
        }
        composable(
            route = "${Destinations.GameScreen.route}/{level}",
            arguments = listOf(navArgument("level") { type = NavType.IntType })
        ) { backStackEntry ->
            val level = backStackEntry.arguments?.getInt("level") ?: 1
            val levelEnum =
                Levels.entries[level - 1] // Преобразуем номер уровня в соответствующий элемент enum
            GameScreen(
                levelEnum,
                onLevelSelect = {
                    SoundManager.resumeSound()
                    SoundManager.pauseMusic()
                    navHostController.navigate(Destinations.LevelScreen.route) {
                        popUpTo(Destinations.MainMenuScreen.route) {
                        }
                    }

                },
                onLevelClick = {
                    SoundManager.resumeSound()
                    SoundManager.pauseMusic()
                    navHostController.navigateUp()
                }
            )
        }
    }
}