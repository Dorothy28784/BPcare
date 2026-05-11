package com.example.bpcare.data.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bpcare.data.Model.BloodPressure
import kotlinx.coroutines.launch


class BloodPressureViewModel : ViewModel() {

    private val repository = BloodPressureViewModel()

    fun saveReading(
        bloodPressure: BloodPressure
    ) {

        viewModelScope.launch {

            repository.saveReading(bloodPressure)

        }
    }
}