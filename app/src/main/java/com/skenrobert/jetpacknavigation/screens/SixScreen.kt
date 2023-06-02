package com.skenrobert.jetpacknavigation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen(navController: NavController, name: String?){
    Scaffold(contentWindowInsets = WindowInsets(16.dp),
        topBar = {
            TopAppBar(
                title  = { Text(text = "Back");
                    Icon(imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow Back",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        });
                    Spacer(modifier = Modifier.width(16.dp))
                })
        }
    ) {
        SecondBodyContent(navController, name)
    }
}

@Composable
fun SecondBodyContent(navController: NavController, name: String?) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(text = "hi second navigation")
        Button(onClick = {
//            navController.navigate(route = AppScreens.FirstScreen.route)
            navController.popBackStack()
        }) {
            Text(text = "Back nave")

//            if (name != null) {
//                Text(text = name)
//            }
        }

        name?.let {
            Text(it)
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun SecondDefaultPreview(){
//    SecondScreen()
//}