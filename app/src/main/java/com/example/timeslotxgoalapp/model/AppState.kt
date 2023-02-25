package com.example.timeslotxgoalapp.model

data class AppState(
    val isRunning: Boolean = false,
    var seconds: Int = 0,
    var minutes: Int = 0,
    var hours: Int = 0,
    val progress: Float = 0f,
    var time: String = "00:00:00",
    var showDialog: Boolean = false,
    var tags: String = "Select Tag",
    val buttonText: String = "START",
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
