package com.example.timeslotxgoalapp.helper

fun formatHourMinuteSecond(hours: Int, minutes: Int, seconds: Int) =
    String.format("%02d:%02d:%02d", hours, minutes, seconds)