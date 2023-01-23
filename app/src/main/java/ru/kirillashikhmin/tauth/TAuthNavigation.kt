package ru.kirillashikhmin.tauth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import ru.kirillashikhmin.tauth.INavigationManager
import ru.kirillashikhmin.tauth.NavigationModule
import ru.kirillashikhmin.tauth.core.ComposableFeatureEntry

@Composable
fun TAuthNavHost(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier,
    navigationManager: INavigationManager
) {

    Box(modifier.fillMaxSize()) {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier,
        ) {
            navigationManager.composableFeatures.forEach { composableEntry ->
                with(composableEntry) {
                    composable(navController)
                }
            }
        }
    }
}
