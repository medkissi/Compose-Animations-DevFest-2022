package com.medkissi.composeanimationsdevfest2022.ui

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.medkissi.composeanimationsdevfest2022.ui.component.ListScreen
import com.medkissi.composeanimationsdevfest2022.ui.navigation.Screen
import com.medkissi.composeanimationsdevfest2022.ui.navigation.navigate
import com.medkissi.composeanimationsdevfest2022.ui.screens.DetailScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DevFestApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.List.route){
        val onNavigate: (Screen?) -> Unit = {
            if (it == null) {
                navController.popBackStack()
            } else {
                navController.navigate(it.route)
            }
        }
        composable(Screen.List.route) {

            ListScreen {
                navController.navigate(Screen.Detail, it)
            }
        }
        composable(
            Screen.Detail.route,
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            DetailScreen(id, onNavigate)
        }
    }

    }

