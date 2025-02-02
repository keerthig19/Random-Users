package com.example.randomusers.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun RequestForUsers(
    viewModel: HomeViewModel,
    selectedUser: (String) -> Unit
) {
    val requestUsersCount = viewModel.requestUsersCount
    val buttonClickedState = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        if (!buttonClickedState.value) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            ) {
                TextField(
                    value = requestUsersCount.value,
                    onValueChange = { newText -> requestUsersCount.value = newText },
                    label = { Text("Enter number of users to load") },
                    modifier = Modifier.padding(bottom = 16.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Button(
                    onClick = {
                        buttonClickedState.value = true
                    }, enabled = if (requestUsersCount.value.text.isNotEmpty()) {
                        requestUsersCount.value.text.toInt() in 1..5000
                    } else false
                )
                {
                    Text("Submit")
                }

                if (requestUsersCount.value.text.isNotEmpty() && requestUsersCount.value.text.toInt() > 5000) {
                    Text(text = "Note: You can request to load users up to 5000 ", modifier = Modifier.padding(30.dp))
                }
            }
        }
    }
    if (buttonClickedState.value) {
        HomeScreen(
            viewModel = viewModel,
            selectedUser = selectedUser,
            requestUsersCount.value.text.toInt()
        )
    }
}