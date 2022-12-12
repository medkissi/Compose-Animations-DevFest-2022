package com.medkissi.composeanimationsdevfest2022.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder


sealed class Screen(val route: String) {
    object List : Screen("/list")
    object Detail : Screen("/detail/{id}")
}


private fun NavController.routeWithArgs(screen: Screen, arg: String): String {
    return screen.route.replaceFirst("\\{[a-zA-Z]*\\}".toRegex(), arg)
}

fun NavController.navigate(screen: Screen, arg: String) {
    val screenRoute = routeWithArgs(screen, arg)
    navigate(screenRoute)
}
