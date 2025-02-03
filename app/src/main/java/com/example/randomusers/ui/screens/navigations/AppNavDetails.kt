package com.example.randomusers.ui.screens.navigations

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.randomusers.ui.screens.home.HomeScreen
import com.example.randomusers.ui.screens.home.HomeViewModel
import com.example.randomusers.ui.screens.home.RequestForUsers
import com.example.randomusers.ui.screens.home.UserDetails
import com.example.randomusers.ui.screens.navigations.RouteConfig.REQUEST_USERS_ROUTE
import com.example.randomusers.ui.screens.navigations.RouteConfig.USERS_LIST_ROUTE
import com.example.randomusers.ui.screens.navigations.RouteConfig.USER_DETAILS_ROUTE

@Composable
fun AppNavDetails(
    modifier: Modifier,
    viewModel: HomeViewModel,
    startDestination: String = REQUEST_USERS_ROUTE
) {
    val navController = rememberNavController()
    val actions = remember(navController) {
        AppActions(navController)
    }

    NavHost(navController = navController, startDestination = startDestination) {
        composable(REQUEST_USERS_ROUTE) {
            RequestForUsers(viewModel = viewModel, selectedUser = actions.selectedUser)
        }
        composable(USERS_LIST_ROUTE) {
            HomeScreen(
                viewModel = viewModel,
                selectedUser = actions.selectedUser,
                requestCount = viewModel.requestUsersCount.value.text.toInt()
            )
        }
        composable("${USER_DETAILS_ROUTE}/{userEmail}",
            arguments = listOf(
                navArgument("userEmail") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val userEmail = arguments.getString("userEmail") ?: ""
            if (userEmail.isNotEmpty()) {
                UserDetails(
                    userId = userEmail,
                    navigateUp = actions.navigateUp,
                    viewModel = viewModel,
                )
            }
        }
    }
}

class AppActions(navController: NavController) {
    val selectedUser: (String) -> Unit = { email: String ->
        navController.navigate("${USER_DETAILS_ROUTE}/$email")
    }
    val navigateUp: () -> Unit = {
        navController.navigate(USERS_LIST_ROUTE) {
            popUpTo(USERS_LIST_ROUTE) { inclusive = true }
        }
    }

}