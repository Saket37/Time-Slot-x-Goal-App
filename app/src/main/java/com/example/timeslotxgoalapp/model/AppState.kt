package com.example.timeslotxgoalapp.model

data class AppState(
    val isRunning: Boolean = false,
    var seconds: Int = 0,
    var minutes: Int = 0,
    var hours: Int = 0,
    var progress: Float = 0f,
    var time: String = "00:00:00",
    var showDialog: Boolean = false,
) {
    fun isDisabled(): Boolean {
        return time != "00:00:00"
    }
}
