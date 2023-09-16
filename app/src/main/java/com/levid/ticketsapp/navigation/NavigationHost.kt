package com.levid.ticketsapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.levid.ticketsapp.ui.client.ClientScreen
import com.levid.ticketsapp.ui.client.ClientsListScreen

@Composable
fun NavigationHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destination.ClientsListScreen.route
    ){
        composable(Destination.ClientsListScreen.route){
            ClientsListScreen(navController = navController)
        }
        composable(Destination.ClientScreen.route){
            ClientScreen(navController = navController)
        }
    }
}