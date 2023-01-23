package ru.kirillashikhmin.tauth.features.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import ru.kirillashikhmin.tauth.core.Destinations
import ru.kirillashikhmin.tauth.core.find
import ru.kirillashikhmin.tauth.features.main.api.MainEntry
import javax.inject.Inject

class MainScreenEntry @Inject constructor() : MainEntry() {

    @Composable
    override fun Composable(navController: NavHostController, backStackEntry: NavBackStackEntry) {
/*
        val destination = destinations
            .find<MainEntry>()
            .destination(movie.id)
        navController.navigate(destination)
        */
        MainScreen()
    }
}
