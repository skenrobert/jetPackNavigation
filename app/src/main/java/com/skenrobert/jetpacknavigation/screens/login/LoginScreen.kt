package com.skenrobert.jetpacknavigation.screens.login

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch



import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.* //mutable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.skenrobert.jetpacknavigation.R


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel){
    //val snackbarHostState = remember { SnackbarHostState() }
    // val scope = rememberCoroutineScope()

    Box(//allow center element
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Login(Modifier.align(Alignment.Center), viewModel)// call fun login, logical LoginViewModel, view is LoginScreen
    }

//    Scaffold(contentWindowInsets = WindowInsets(16.dp),
//        topBar = {
//            TopAppBar(
//                title  = { Text(text = "Back");
//                        Icon(imageVector = Icons.Default.ArrowBack,
//                        contentDescription = "Arrow Back",
//                        modifier = Modifier.clickable {
//                            navController.popBackStack()
//                        });
//                    Spacer(modifier = Modifier.width(16.dp))
//                })
//        }
//    ) {
////        BodyContent(navController)
//        //Text("Show snackbar")
//    }
}



@Composable
fun Login(modifier: Modifier, viewModel: LoginViewModel) {

    //declare all observe what change and automate send change
    val email: String by viewModel.email.observeAsState(initial = "") //application notification every change in the view, application life data
    val password: String by viewModel.password.observeAsState(initial = "")
    val loginEnable: Boolean by viewModel.loginEnable.observeAsState(initial = false)
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()

    if (isLoading) { //check observe declare behind, is change in the login view model, when click in button login execute delay hide screen
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center)) //show loading
        }
    } else { //component main is show after delay when click in button login
        //the modifier only change element same level
        Column(modifier = modifier) {// modifier is position element in the screen
            HeaderImage(Modifier.align(Alignment.CenterHorizontally))// load image
            Spacer(modifier = Modifier.padding(16.dp))//spacer between element
            EmailField(email) { viewModel.onLoginChanged(it, password) } // onLoginChanged include validate
            Spacer(modifier = Modifier.padding(4.dp))//spacer between element
            PasswordField(password) { viewModel.onLoginChanged(email, it) } // onLoginChanged include validate
            Spacer(modifier = Modifier.padding(8.dp))//spacer between element
            ForgotPassword(Modifier.align(Alignment.End))// element alignment end
            Spacer(modifier = Modifier.padding(16.dp))//spacer between element
            LoginButton(loginEnable) {
                coroutineScope.launch {// execute in thread second
                    viewModel.onLoginSelected() // call function Login view model with the logic, I need change for better next screen
                }
            }
        }
    }
}

@Composable
fun LoginButton(loginEnable: Boolean, onLoginSelected: () -> Unit) {
    Button(
        onClick = { onLoginSelected() },//need search variable in this function onclick
        modifier = Modifier
            .fillMaxWidth()// take all width
            .height(48.dp), // height button
        colors = ButtonDefaults.buttonColors(// perform button
            backgroundColor = Color(0xFFFF4303),
            disabledBackgroundColor = Color(0xFFF78058),
            contentColor = Color.White,
            disabledContentColor = Color.White
        ), enabled = loginEnable // observer change of true
    ) {
        Text(text = "Log in")
    }
}

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        text = "Olvidaste la contraseña?",
        modifier = modifier.clickable { },//search click other view
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFFFB9600)
    )
}

@Composable
fun PasswordField(password: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = password, onValueChange = { onTextFieldChanged(it) },
        placeholder = { Text(text = "Contraseña") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF636262),
            backgroundColor = Color(0xFFDEDDDD),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

//@Preview(showBackground = true, showSystemUi = true) //no work if element receive variable
@Composable
fun EmailField(email: String, onTextFieldChanged: (String) -> Unit) { // life data observer change in the input

//    var email by remember { //other form, recommend  used life data
//        mutableStateOf("")
//    }

    TextField(
        value = email, onValueChange = { onTextFieldChanged(it) },// life data applicable directly element
        modifier = Modifier.fillMaxWidth(),// all width
        placeholder = { Text(text = "Email") },// show text
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),// type input
        singleLine = true,// block enter
        maxLines = 1,//only 1 line
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF636262),// text color
            backgroundColor = Color(0xFFDEDDDD),// background
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HeaderImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.ls_android),
        contentDescription = "Header",
        modifier = modifier
    )
}