package com.example.bpcare.ui.Screens.HomeScreen

import android.annotation.SuppressLint
import com.example.bpcare.data.Model.BloodPressure
import com.example.bpcare.ui.Screens.Authentication.Login.LoginScreenViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
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


@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun HomeScreen(
    navController: NavController
) {

    var latestReading by remember {
        mutableStateOf<BloodPressure?>(null)
    }

    val loginViewModel =
        LoginScreenViewModel()

    val message by loginViewModel
        .message
        .collectAsState()

    LaunchedEffect(Unit) {

        val repository = BPRepository()

        val readings =
            repository.getReadings()

        if (readings.isNotEmpty()) {

            latestReading =
                readings.first()

        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

        verticalArrangement =
            Arrangement.Center
    ) {

        Text(
            text = "BP Care",
            style = MaterialTheme
                .typography
                .headlineMedium
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        latestReading?.let { bp ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {

                    Text(
                        text =
                            "Latest Reading",
                        style = MaterialTheme
                            .typography
                            .titleMedium
                    )

                    Spacer(
                        modifier = Modifier
                            .height(10.dp)
                    )

                    Text(
                        text =
                            "BP: ${bp.systolic}/${bp.diastolic}"
                    )

                    Text(
                        text =
                            "Pulse: ${bp.pulse}"
                    )

                    Text(
                        text =
                            "Status: ${bp.status}"
                    )
                }
            }
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Button(
            onClick = {

                navController.navigate(
                    "add"
                )

            },

            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "Add Reading"
            )
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Button(
            onClick = {

                navController.navigate(
                    "history"
                )

            },

            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "View History"
            )
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Button(
            onClick = {

                navController.navigate(
                    "statistics"
                )

            },

            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "Statistics"
            )
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        TextButton(
            onClick = {

                loginViewModel.logout()

                navController.navigate(
                    "login"
                ) {

                    popUpTo("home") {

                        inclusive = true

                    }
                }
            }
        ) {

            Text(
                text = "Logout"
            )
        }

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        Text(
            text = message
        )
    }
}