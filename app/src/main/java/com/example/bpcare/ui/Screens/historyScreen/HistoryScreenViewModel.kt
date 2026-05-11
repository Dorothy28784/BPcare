package com.example.bpcare.ui.Screens.historyScreen

import androidx.lifecycle.ViewModel
import com.example.bpcare.data.Model.BloodPressure
import com.example.bpcare.data.repository.BPRepository
import kotlinx.coroutines.flow.StateFlow

class HistoryScreenViewModel : ViewModel() {
    private val repository = BPRepository

    val readings: StateFlow<List<BloodPressure>> = repository.readings
}
