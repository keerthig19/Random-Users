package com.example.randomusers.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.randomusers.model.User
import com.example.randomusers.ui.screens.components.SearchView

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    selectedUser: (String) -> Unit
) {
    val isDataLoaded = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = isDataLoaded.value) {
        if (!isDataLoaded.value) {
            viewModel.getUsers()
            isDataLoaded.value = true
        }
    }
    val searchQuery = remember { mutableStateOf(TextFieldValue("")) }
    val usersList = viewModel.usersList.collectAsState().value.filter {
        it?.phone?.trim()?.contains(searchQuery.value.text, ignoreCase = true) == true
    }

    Column(modifier = Modifier.padding(top = 16.dp)) {

        Column(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)) {

            /*Text(
                text = "Users",
                color = black,
                modifier = Modifier.padding(start = 16.dp, top = 8.dp)
            )*/

            SearchView(
                searchQuery,
                "Search",
            )
        }

        LazyColumn {
            if (usersList.isEmpty()) {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(align = Alignment.Center)
                    )
                }
            }

            items(usersList.size ?: 0) {
                val user = usersList[it]
                UserItem(user = user, selectedUser)
            }

        }
    }

}

@Composable
fun UserItem(user: User?, selectedProduct: (String) -> Unit) {
    user?.let {
        Card(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.padding(
                    start = 5.dp
                ).clickable(onClick = {
                    selectedProduct(user.email ?: "")
                })
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(color = Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = rememberAsyncImagePainter(it?.picture?.thumbnail),
                        contentDescription = null,
                        modifier = Modifier
                            .size(86.dp)
                            .clip(RoundedCornerShape(corner = CornerSize(16.dp))),
                        tint = Color.Unspecified
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .padding(8.dp)
                ) {
                    Text(
                        text = "Name: ${it.name?.first} ${it.name?.last ?: ""}",
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = "Phone Number: ${it.phone ?: "N/A"}",
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = "Gender: ${it.gender ?: "N/A"}",
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    /*Text(
                        text = "Email: ${it.email ?: "N/A"}",
                    )*/
                }
            }
        }
    }
}