package com.example.timeslotxgoalapp.helper

import android.content.Context
import android.media.MediaPlayer
import com.example.timeslotxgoalapp.R

class PlayCompletedAudio(private val context: Context) {
    fun playSound() {
        val mediaPlayer = MediaPlayer.create(context, R.raw.success)
        mediaPlayer.start()
    }
}

