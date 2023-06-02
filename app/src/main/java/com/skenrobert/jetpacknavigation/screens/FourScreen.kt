package com.skenrobert.jetpacknavigation.screens

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import com.skenrobert.jetpacknavigation.R
import androidx.compose.runtime.getValue// change variable in time execution in jetpack
import androidx.compose.runtime.setValue// change variable in time execution in jetpack

data class MyMessage(val title: String, val body: String)

private val messages: List<MyMessage> = listOf(
    MyMessage("first","test body loren"),
    MyMessage("second","Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona que se dedica a la imprenta) desconocido usó una galería de textos y los mezcló de tal manera que logró hacer un libro de textos especimen. No sólo sobrevivió 500 años,"),
    MyMessage("three","Es un hecho establecido hace demasiado tiempo que un lector se distraerá con el contenido del texto de un sitio mientras que mira su diseño."),
    MyMessage("three","test body"),
    MyMessage("three","Es un hecho establecido hace demasiado tiempo que un lector se distraerá con el contenido del texto de un sitio mientras que mira su diseño."),
    MyMessage("three","test body"),
    MyMessage("three","test body"),
    MyMessage("three","Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona que se dedica a la imprenta) desconocido usó una galería de textos y los mezcló de tal manera que logró hacer un libro de textos especimen. No sólo sobrevivió 500 años,"),
    MyMessage("three","test body"),
    MyMessage("three","Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona que se dedica a la imprenta) desconocido usó una galería de textos y los mezcló de tal manera que logró hacer un libro de textos especimen. No sólo sobrevivió 500 años,"),
    MyMessage("three","test body"),
    MyMessage("three","Es un hecho establecido hace demasiado tiempo que un lector se distraerá con el contenido del texto de un sitio mientras que mira su diseño."),
    MyMessage("three","test body"),
    MyMessage("last text","test body"))


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThreeScreen(navController: NavController){
    //val snackbarHostState = remember { SnackbarHostState() }
    // val scope = rememberCoroutineScope()

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
//        BodyContent(navController)
        //Text("Show snackbar")

//        val scrollState = rememberScrollState()

//        Column (modifier = Modifier.verticalScroll(scrollState)) {
            MyMessages(messages)
//        }

    }
}

@Composable
fun MyMessages(messages: List<MyMessage>){
    LazyColumn{
        items(messages){ message->
            MyComponent(message)
        }
    }
}

@Composable
fun MyComponent(message: MyMessage) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(start = 8.dp)
    ) {// call (modifier) for apply style in you view
        MyTexts(message)
        Spacer(modifier = Modifier.height(36.dp))//change height that row
        MyImage()
    }
}

@Composable //graphic element
fun MyTexts(message: MyMessage) {
    var expanded by remember { mutableStateOf(false) } // change variable in time execution in jetpack
    Column (
        modifier = Modifier
            .padding(start = 8.dp)
            .clickable {//in case the click expanded text
                expanded = !expanded
            }){
        MyText(message.title,
            MaterialTheme.colorScheme.primary,
            MaterialTheme.typography.bodyLarge)
        MyText(message.body,
            MaterialTheme.colorScheme.secondary,
            MaterialTheme.typography.displaySmall,
            if (expanded) Int.MAX_VALUE else 1)
    }
}

@Composable //graphic element
fun MyText(text: String, color: Color, style: TextStyle, lines: Int = Int.MAX_VALUE) {
    Text(text, color = color, style = style, maxLines = lines)
}

@Composable
fun MyImage() {
    Image(
        painterResource(R.drawable.ic_android_black), "My Image of test",
        modifier = Modifier
            .size(84.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary)//circleShape change image in circle
//            .background(Color.Gray)//circleShape change image in circle
    )
}

@Preview(showSystemUi = true)// preview size screen
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)//preview dark mode
@Composable
fun PreviewComponent() {
    MyMessages(messages)
}