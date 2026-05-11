package com.example.bpcare.ui.Screens.HomeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bpcare.data.Model.BloodPressure
import com.example.bpcare.data.repository.BPRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {

    private val repository = BPRepository

    // Latest BP Reading
    private val _latestReading =
        MutableStateFlow<BloodPressure?>(null)

    val latestReading:
            StateFlow<BloodPressure?> =
        _latestReading

    // Loading State
    private val _loading =
        MutableStateFlow(false)

    val loading:
            StateFlow<Boolean> =
        _loading

    // Message State
    private val _message =
        MutableStateFlow("")

    val message:
            StateFlow<String> =
        _message

    init {

        loadLatestReading()

    }

    fun loadLatestReading() {

        viewModelScope.launch {

            _loading.value = true

            try {

                val readings =
                    repository.getReadings()

                if (readings.isNotEmpty()) {

                    _latestReading.value =
                        readings.first()

                } else {

                    _message.value =
                        "No readings available"

                }

            } catch (e: Exception) {

                _message.value =
                    e.message ?: "Failed to load data"

            }

            _loading.value = false
        }
    }

    fun refresh() {

        loadLatestReading()

    }
}