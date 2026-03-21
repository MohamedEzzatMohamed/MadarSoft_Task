package com.madarsofttask.feature.all_users_list_screen.presentation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.madarsofttask.feature.add_user_screen.presentation.graph.AddUserScreen
import com.madarsofttask.feature.all_users_list_screen.presentation.AllUsersListScreen
import kotlinx.serialization.Serializable

@Serializable
data object AllUsersListScreen

fun NavGraphBuilder.allUsersListGraph(navController: NavController) {
    composable<AllUsersListScreen> {
        AllUsersListScreen(
            onBackClicked = navController::popBackStack
        )
    }
}


fun NavController.navigateToAllUsersListGraph() {
    navigate(route = AllUsersListScreen) {
        popUpTo(route = AddUserScreen) { inclusive = false }
    }
}