package ru.kirillashikhmin.tauth.features.main.di

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import ru.kirillashikhmin.tauth.core.Destinations
import ru.kirillashikhmin.tauth.features.main.MainScreen
import ru.kirillashikhmin.tauth.features.main.api.MainEntry
import javax.inject.Inject

class MainEntryImpl @Inject constructor() : MainEntry() {

    @Composable
    override fun Composable(
        navController: NavHostController,
        backStackEntry: NavBackStackEntry
    ) {
        MainScreen()

        /*onExpandMovieDetails = { movie ->
            val destination = destinations
                .find<MainEntry>()
                .destination(movie.id)
            navController.navigate(destination)
        })
        */
    }
}
