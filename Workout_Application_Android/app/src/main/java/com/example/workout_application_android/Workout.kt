package com.example.workout_application_android

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable


@Serializable
@Parcelize
data class Workout (
    val icon: Int,
    val title: String,
    val detail: String,
    val exercises: List<String>
): Parcelable