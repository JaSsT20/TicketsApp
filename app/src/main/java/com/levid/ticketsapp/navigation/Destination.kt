package com.levid.ticketsapp.navigation

sealed class Destination(val route: String){
    object ClientScreen: Destination("clientScreen")
    object ClientsListScreen: Destination("clientListScreen")
}
