package com.example.bpcare.data.Model

import kotlinx.serialization.Serializable

@Serializable
data class BloodPressure(

    val systolic: Int,

    val diastolic: Int,

    val pulse: Int,

    val status: String
)