package com.example.bpcare.ui.Screens.Authentication.Logout

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bpcare.ui.Navigation.ROUTES
import com.example.bpcare.ui.Screens.Authentication.Login.LoginScreenViewModel

@Composable
fun LogoutScreen(
    navController: NavController,
    viewModel: LoginScreenViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.logout()
        navController.navigate(ROUTES.Login.name) {
            popUpTo(0) { inclusive = true }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
        Spacer(modifier = Modifier.height(16.dp))
        Text("Logging out...")
    }
}
