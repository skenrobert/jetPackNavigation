package com.skenrobert.jetpacknavigation.navigation

sealed class AppScreens (val route: String) {
    object FirstScreen: AppScreens("first_screen")
    object SecondScreen: AppScreens("second_screen")
    object ThreeScreen: AppScreens("three_screen")
    object LoginScreen: AppScreens("Login_screen")
}