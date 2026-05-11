package com.example.bpcare.ui.Screens.AddScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bpcare.ui.theme.BpCareTheme
import com.example.bpcare.data.Model.BloodPressure
import com.example.bpcare.data.Utils.getBPStatus
import com.example.bpcare.data.ViewModel.BloodPressureViewModel

@Composable
fun AddScreen(
    viewModel: BloodPressureViewModel,
    navController: NavController
) {
    AddScreenContent(
        onSave = { sys, dia, pulse ->
            val bp = BloodPressure(
                systolic = sys,
                diastolic = dia,
                pulse = pulse,
                status = getBPStatus(sys, dia)
            )
            viewModel.saveReading(bp)
            navController.popBackStack()
        },
        onBack = {
            navController.popBackStack()
        }
    )
}

@Composable
fun AddScreenContent(
    onSave: (Int, Int, Int) -> Unit,
    onBack: () -> Unit
) {
    var systolic by remember {
        mutableStateOf("")
    }

    var diastolic by remember {
        mutableStateOf("")
    }

    var pulse by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
                Text(
                    text = "Add Reading",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Enter your blood pressure details below",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = systolic,
                onValueChange = { systolic = it },
                label = { Text("Systolic (mmHg)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = diastolic,
                onValueChange = { diastolic = it },
                label = { Text("Diastolic (mmHg)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = pulse,
                onValueChange = { pulse = it },
                label = { Text("Pulse (bpm)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    val sys = systolic.toIntOrNull() ?: 0
                    val dia = diastolic.toIntOrNull() ?: 0
                    val p = pulse.toIntOrNull() ?: 0
                    onSave(sys, dia, p)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Save Reading")
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AddScreenPreview() {
    BpCareTheme {
        AddScreenContent(onSave = { _, _, _ -> }, onBack = {})
    }
}
