package com.example.bpcare.data.Utils

fun getBPStatus(
    systolic: Int,
    diastolic: Int
): String {

    return when {

        systolic < 90 ||
                diastolic < 60 ->
            "Low"

        systolic < 120 &&
                diastolic < 80 ->
            "Normal"

        systolic in 120..139 ||
                diastolic in 80..89 ->
            "Elevated"

        else ->
            "High"
    }
}
