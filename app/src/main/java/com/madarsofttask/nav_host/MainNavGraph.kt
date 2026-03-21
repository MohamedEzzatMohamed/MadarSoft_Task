package com.madarsofttask.nav_host

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.madarsofttask.feature.add_user_screen.presentation.graph.AddUserScreen
import com.madarsofttask.feature.add_user_screen.presentation.graph.addUserGraph
import com.madarsofttask.feature.all_users_list_screen.presentation.graph.allUsersListGraph
import kotlinx.serialization.Serializable

@Serializable
data object MainGraph {
}

@Composable
fun MainNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AddUserScreen::class,
        route = MainGraph::class
    ) {
        addUserGraph(navController = navController)
        allUsersListGraph(navController = navController)
    }
}