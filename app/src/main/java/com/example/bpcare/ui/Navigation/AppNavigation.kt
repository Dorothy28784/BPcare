package com.example.bpcare.ui.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

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
            OnboardingScreen(onFinished = {
                navController.navigate(ROUTES.Login.name) {
                    popUpTo(ROUTES.Onboarding.name) { inclusive = true }
                }
            })
        }
        composable(ROUTES.Login.name) {
            LoginScreen(
                navController = navController,
                modifier = Modifier
            )
        }
        composable(ROUTES.ForgotPassword.name) {
            ForgotPasswordScreen(
                navController = navController,
                modifier = Modifier
            )
        }
        composable(ROUTES.Register.name) { RegisterScreen(navController = navController) }
        composable(ROUTES.Logout.name) { LogoutScreen(navController = navController) }
        composable(ROUTES.Home.name) { HomeScreen(navController = navController) }
        composable(ROUTES.Add.name) {}
        composable(ROUTES.History.name) {}
        composable(ROUTES.Statistics.name) {}


    }
}
