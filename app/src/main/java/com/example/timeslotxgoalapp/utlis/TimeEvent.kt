package com.example.timeslotxgoalapp.utlis

sealed class TimeEvent {
    object OnShowTimerDialog : TimeEvent()
    data class OnCancelTimeDialog(val hour: Int, val minutes: Int, val seconds: Int) : TimeEvent()
    object StartTimer : TimeEvent()
}
