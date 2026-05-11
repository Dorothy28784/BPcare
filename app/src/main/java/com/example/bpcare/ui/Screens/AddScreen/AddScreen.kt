package com.example.bpcare.ui.Screens.AddScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bpcare.data.Model.BloodPressure
import com.example.bpcare.data.ViewModel.BloodPressureViewModel

@Composable
fun AddScreen(

    viewModel: BloodPressureViewModel

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

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

        verticalArrangement = Arrangement.spacedBy(12.dp)

    ) {

        Text(
            text = "Add Blood Pressure",
            style = MaterialTheme.typography.headlineSmall
        )

        OutlinedTextField(
            value = systolic,

            onValueChange = {
                systolic = it
            },

            label = {
                Text("Systolic")
            },

            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = diastolic,

            onValueChange = {
                diastolic = it
            },

            label = {
                Text("Diastolic")
            },

            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = pulse,

            onValueChange = {
                pulse = it
            },

            label = {
                Text("Pulse")
            },

            modifier = Modifier.fillMaxWidth()
        )

        Button(

            onClick = {

                val sys = systolic.toInt()

                val dia = diastolic.toInt()

                val bp = BloodPressure(

                    systolic = sys,

                    diastolic = dia,

                    pulse = pulse.toInt(),

                    status = getBPStatus(sys, dia)

                )

                viewModel.saveReading(bp)

            },

            modifier = Modifier.fillMaxWidth()

        ) {

            Text("Save Reading")

        }
    }
}
