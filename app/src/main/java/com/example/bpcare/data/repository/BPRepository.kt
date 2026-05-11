package com.example.bpcare.data.repository

import kotlinx.serialization.Serializable

@Serializable
data class BP(

    val systolic: Int,

    val diastolic: Int,

    val pulse: Int,

    val status: String
)
