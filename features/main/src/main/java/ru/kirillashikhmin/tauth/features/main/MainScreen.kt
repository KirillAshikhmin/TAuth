package ru.kirillashikhmin.tauth.features.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import org.orbitmvi.orbit.compose.collectAsState
import ru.kirillashikhmin.tauth.core.states.viewState.IStateModel
import ru.kirillashikhmin.tauth.main.theme.TAuthTheme


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    TAuthTheme {
        MainScreen()
    }
}


@Composable
fun MainScreen(
    viewModel : MainViewModel = hiltViewModel()
) {
    val state: IStateModel by viewModel.collectAsState()

    Column(
        modifier = Modifier.padding(16.dp).fillMaxWidth()
    ) {
        Item()
        Item()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Item() {
    Card(
        onClick = { /*TODO*/ }
    ) {
        Column(
            modifier = Modifier.padding(4.dp)) {
            Row {
                Icon(
                    imageVector = Icons.Filled.Face,
                    contentDescription = "",
                    modifier = Modifier.size(32.dp))
                Text(text = "Google")
            }
            Text(text = "admin@example.com")
        }
    }
}
