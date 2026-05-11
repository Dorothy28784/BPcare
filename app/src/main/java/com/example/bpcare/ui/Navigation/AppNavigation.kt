package com.example.bpcare.ui.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bpcare.data.ViewModel.BloodPressureViewModel
import com.example.bpcare.ui.Screens.AddScreen.AddScreen
import com.example.bpcare.ui.Screens.Authentication.ForgotPassword.ForgotPasswordScreen
import com.example.bpcare.ui.Screens.Authentication.Login.LoginScreen
import com.example.bpcare.ui.Screens.Authentication.Logout.LogoutScreen
import com.example.bpcare.ui.Screens.Authentication.Register.RegisterScreen
import com.example.bpcare.ui.Screens.historyScreen.HistoryScreen
import com.example.bpcare.ui.Screens.HomeScreen.HomeScreen
import com.example.bpcare.ui.Screens.OnboardingScreen.OnboardingScreen
import com.example.bpcare.ui.Screens.StatisticsScreen.StatisticsScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = ROUTES.Onboarding.name,
        modifier = modifier
    ) {
        composable(ROUTES.Onboarding.name) {
            OnboardingScreen(navController = navController)
        }
        composable(ROUTES.Login.name) {
            LoginScreen(
                navController = navController
            )
        }
        composable(ROUTES.ForgotPassword.name) {
            ForgotPasswordScreen(
                navController = navController
            )
        }
        composable(ROUTES.Register.name) { 
            RegisterScreen(navController = navController) 
        }
        composable(ROUTES.logout.name) { 
            LogoutScreen(navController = navController) 
        }
        composable(ROUTES.Home.name) { 
            HomeScreen(navController = navController) 
        }
        composable(ROUTES.Add.name) {
            val bpViewModel: BloodPressureViewModel = viewModel()
            AddScreen(viewModel = bpViewModel, navController = navController)
        }
        composable(ROUTES.History.name) {
            HistoryScreen(navController = navController)
        }
        composable(ROUTES.Statistics.name) {
            StatisticsScreen()
        }
    }
}
