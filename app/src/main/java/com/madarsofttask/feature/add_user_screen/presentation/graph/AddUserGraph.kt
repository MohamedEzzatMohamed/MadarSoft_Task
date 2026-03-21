package com.madarsofttask.feature.add_user_screen.presentation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.madarsofttask.feature.add_user_screen.presentation.AddUserScreen
import com.madarsofttask.feature.all_users_list_screen.presentation.graph.navigateToAllUsersListGraph
import com.madarsofttask.nav_host.MainGraph
import kotlinx.serialization.Serializable

@Serializable
data object AddUserScreen{

}

fun NavGraphBuilder.addUserGraph(navController: NavController) {
    composable<AddUserScreen> {
        AddUserScreen(
            onNavigateToAllUsers = navController::navigateToAllUsersListGraph
        )
    }
}


fun NavController.navigateToAddUserGraph() {
    navigate(route = AddUserScreen) {
        popUpTo(route = MainGraph) { inclusive = true }
    }
}