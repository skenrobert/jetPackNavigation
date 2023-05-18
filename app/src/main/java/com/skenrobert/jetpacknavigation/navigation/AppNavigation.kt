package com.skenrobert.jetpacknavigation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.skenrobert.jetpacknavigation.screens.FirstScreen
import com.skenrobert.jetpacknavigation.screens.SecondScreen


@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = AppScreens.FirstScreen.route){
            composable(route = AppScreens.FirstScreen.route){
                FirstScreen(navController)
            }
            composable(route = AppScreens.SecondScreen.route + "/{name}",
                arguments = listOf(navArgument(name = "name"){
                    type = NavType.StringType
                })){
                SecondScreen (navController, it.arguments?.getString("name"))
            }
    }

}


