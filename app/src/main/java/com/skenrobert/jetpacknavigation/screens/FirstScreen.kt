package com.skenrobert.jetpacknavigation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.skenrobert.jetpacknavigation.navigation.AppScreens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstScreen(navController: NavController){
    //val snackbarHostState = remember { SnackbarHostState() }
   // val scope = rememberCoroutineScope()

    Scaffold(contentWindowInsets = WindowInsets(16.dp),
            topBar = {
                TopAppBar(
                    title  = { Text(text = "Back");
//                        Icon(imageVector = Icons.Default.ArrowBack,
//                        contentDescription = "Arrow Back",
//                        modifier = Modifier.clickable {
//                            navController.popBackStack()
//                        });
                        Spacer(modifier = Modifier.width(16.dp))
                    })
            }
        ) {
        BodyContent(navController)
        //Text("Show snackbar")
    }
}

@Composable
fun BodyContent(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(text = "hi first navigation")
        Button(onClick = {
            navController.navigate(route = AppScreens.SecondScreen.route + "/robert")
        }) {
            Text(text = "Nave")
        }
    }
}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview(){
//    FirstScreen()
//}