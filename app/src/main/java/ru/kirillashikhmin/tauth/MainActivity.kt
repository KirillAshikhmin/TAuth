package ru.kirillashikhmin.tauth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ru.kirillashikhmin.tauth.common.Logger
import ru.kirillashikhmin.tauth.ui.App

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    App()
}
