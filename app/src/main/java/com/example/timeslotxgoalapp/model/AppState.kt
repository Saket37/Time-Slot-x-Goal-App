package com.example.timeslotxgoalapp.model

import androidx.annotation.StringRes
import com.example.timeslotxgoalapp.R

data class AppState(
    val isRunning: Boolean = false,
    var seconds: Int = 0,
    var minutes: Int = 0,
    var hours: Int = 0,
    val progress: Float = 0f,
    var time: String = "00:00:00",
    var showDialog: Boolean = false,
    var tags: String = "Select Tag",
    @StringRes val buttonText: Int = R.string.start_button_text,
    val hasFinished: Boolean = false,
    val initialStartTime: String = ""
) {
    fun isDisabled(): Boolean {
        return time != "00:00:00" && tags != "Select Tag" || hasFinished
    }

    fun disableTimerClick(): Boolean {
        return isRunning || (!isRunning && hasFinished)
    }
}
