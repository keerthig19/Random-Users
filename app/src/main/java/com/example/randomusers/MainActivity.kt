package com.example.randomusers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.randomusers.repo.UsersRepo
import com.example.randomusers.ui.screens.home.HomeViewModel
import com.example.randomusers.ui.screens.navigations.AppNavDetails
import com.example.randomusers.ui.theme.RandomUsersTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repo = UsersRepo()
        val viewModel = HomeViewModel(repo)
        enableEdgeToEdge()
        setContent {
            RandomUsersTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavDetails(modifier = Modifier.padding(innerPadding), viewModel)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RandomUsersTheme {
//        Greeting("Android")
    }
}