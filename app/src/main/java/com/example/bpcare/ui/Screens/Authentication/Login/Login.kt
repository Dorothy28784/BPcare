package com.example.bpcare.ui.Screens.Authentication.Login
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginScreenViewModel
) {

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val loading by viewModel
        .loading
        .collectAsState()

    val message by viewModel
        .message
        .collectAsState()

    val isLoggedIn by viewModel
        .isLoggedIn
        .collectAsState()

    LaunchedEffect(isLoggedIn) {

        if (isLoggedIn) {

            navController.navigate("home")

        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Login",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        OutlinedTextField(
            value = email,

            onValueChange = {
                email = it
            },

            label = {
                Text("Email")
            },

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        OutlinedTextField(
            value = password,

            onValueChange = {
                password = it
            },

            label = {
                Text("Password")
            },

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Button(
            onClick = {

                viewModel.login(
                    email,
                    password
                )

            },

            modifier = Modifier.fillMaxWidth(),

            enabled = !loading
        ) {

            Text("Login")

        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        if (loading) {

            CircularProgressIndicator()

        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Text(
            text = message
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        TextButton(
            onClick = {

                navController.navigate(
                    "forgot_password"
                )

            }
        ) {

            Text(
                text = "Forgot Password?"
            )
        }

        TextButton(
            onClick = {

                navController.navigate(
                    "register"
                )

            }
        ) {

            Text(
                text = "Create Account"
            )
        }
    }
}