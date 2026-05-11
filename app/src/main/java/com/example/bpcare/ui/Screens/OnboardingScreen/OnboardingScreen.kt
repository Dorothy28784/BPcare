package com.example.bpcare.ui.Screens.OnboardingScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun OnboardingScreen(
    navController: NavController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),

        verticalArrangement = Arrangement.Center,

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Welcome to BP Care",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = "✔ Track blood pressure readings"
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Text(
                    text = "✔ View blood pressure history"
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Text(
                    text = "✔ Monitor your health statistics"
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Text(
                    text = "✔ Store data securely using Supabase"
                )
            }
        }

        Spacer(
            modifier = Modifier.height(30.dp)
        )

        Button(
            onClick = {

                navController.navigate("login")

            },

            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "Get Started"
            )
        }
    }
}