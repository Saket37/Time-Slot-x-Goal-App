package com.example.timeslotxgoalapp.utlis

sealed class TimeEvent {
    object OnShowTimerDialog : TimeEvent()
    data class OnCancelTimeDialog(val hour: Int, val minutes: Int, val seconds: Int) : TimeEvent()
    object StartTimer : TimeEvent()
    object CancelTimer : TimeEvent()
    data class OnTagChanged(val tag: String) : TimeEvent()
    object OnNewGoalClicked : TimeEvent()
}
