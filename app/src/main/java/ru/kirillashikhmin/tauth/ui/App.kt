package ru.kirillashikhmin.tauth.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import ru.kirillashikhmin.tauth.INavigationManager
import ru.kirillashikhmin.tauth.TAuthNavHost
import ru.kirillashikhmin.tauth.core.ui.StatusBar
import ru.kirillashikhmin.tauth.main.theme.TAuthTheme


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun App(navigationManager: INavigationManager? = null) {
    TAuthTheme {
        val navController = rememberNavController()
        //StatusBar(window, color = MaterialTheme.colors.background)
        Scaffold(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "TAuth")
                    },
                    navigationIcon = {
                        IconButton(onClick = { }) {
                            Icon(Icons.Filled.Menu, "")
                        }
                    },
                    actions = {
                        IconButton(onClick = {/* Do Something*/ }) {
                            Icon(Icons.Filled.Settings, null)
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary,
                        actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {/* Do Something*/ },
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ) {
                    Icon(Icons.Filled.Add, null)
                }
            },
            content = { padding ->
                Box(
                    modifier = Modifier.padding(padding)
                ) {
                    navigationManager?.let { navManager ->
                        TAuthNavHost(
                            navController = navController,
                            startDestination = "main",
                            Modifier,
                            navigationManager = navManager
                        )
                    }
                }
            }
        )
        //NavigationBar(window, color = AppBlack)
    }
}
