package com.example.bpcare.data.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bpcare.data.Model.BloodPressure
import com.example.bpcare.data.repository.BPRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class BloodPressureViewModel : ViewModel() {

    private val repository = BPRepository

    val readings: StateFlow<List<BloodPressure>> = repository.readings

    val latestReading: StateFlow<BloodPressure?> = repository.readings
        .map { it.firstOrNull() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    fun saveReading(bloodPressure: BloodPressure) {
        repository.saveReading(bloodPressure)
    }
}
