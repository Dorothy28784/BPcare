package com.example.bpcare.data.repository

import com.example.bpcare.data.Model.BloodPressure
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object BPRepository {
    private val _readings = MutableStateFlow<List<BloodPressure>>(emptyList())
    val readings: StateFlow<List<BloodPressure>> = _readings.asStateFlow()

    fun getReadings(): List<BloodPressure> {
        return _readings.value
    }

    fun saveReading(bloodPressure: BloodPressure) {
        val currentList = _readings.value.toMutableList()
        currentList.add(0, bloodPressure) // Add to the top
        _readings.value = currentList
    }
}
