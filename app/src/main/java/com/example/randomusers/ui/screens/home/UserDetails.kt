package com.example.randomusers.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.randomusers.model.User

@Composable
fun UserDetails(
    viewModel: HomeViewModel, userId: String, navigateUp: () -> Unit
) {
    val list = viewModel.usersList.collectAsState()
    val user = list.value.find { it?.email == userId }

    Box {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            user?.let { ProductImageHeader(user = it, navigateUp) }
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = "${user?.name?.first} ${user?.name?.last ?: ""}",
                    fontSize = 30.sp,
                    modifier = Modifier.padding(10.dp, 10.dp, 10.dp, 0.dp)
                )
                Text(
                    text = "Phone Number: " + user?.phone.toString(),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp, 10.dp, 10.dp, 0.dp)
                )
                Text(
                    text = "Gender: " + user?.gender.toString(),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp, 10.dp, 10.dp, 0.dp)
                )
                Text(
                    text = "EmailId: " + user?.email.toString(),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp, 10.dp, 10.dp, 0.dp)
                )
                Text(
                    text = "Address: " + user?.location?.street?.number.toString() + ", " + user?.location?.street?.name.toString() + ", "
                            + user?.location?.city.toString() + ", " + user?.location?.state.toString(),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp, 10.dp, 10.dp, 0.dp)
                )
            }
        }
    }
}

@Composable
fun ProductImageHeader(user: User, navigateUp: () -> Unit) {

    Box(
        modifier = Modifier.padding(top = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(
                onClick = navigateUp,
            ) {
                Icon(
                    Icons.AutoMirrored.Outlined.ArrowBack,
                    contentDescription = "",
                )
            }
        }
    }

    Box(
        modifier = Modifier.padding(10.dp)
    ) {
        Icon(
            painter = rememberAsyncImagePainter(user.picture?.large),
            contentDescription = "User Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp),
            tint = Color.Unspecified
        )
    }
}