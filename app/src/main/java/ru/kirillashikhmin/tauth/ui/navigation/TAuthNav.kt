package ru.kirillashikhmin.tauth.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.coroutines.MainScope
import ru.kirillashikhmin.tauth.ui.screens.MainScreen


const val mainNavigationRoute = "main"
const val settingsNavigationRoute = "settings"

fun NavController.navigateToSettings(navOptions: NavOptions? = null) {
    this.navigate(settingsNavigationRoute, navOptions)
}

fun NavGraphBuilder.mainScreen() {
    composable(route = mainNavigationRoute) {
        MainScreen()
    }
}
