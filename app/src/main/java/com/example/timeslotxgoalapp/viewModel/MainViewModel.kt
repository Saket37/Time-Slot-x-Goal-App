package com.example.timeslotxgoalapp.viewModel

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import com.example.timeslotxgoalapp.model.AppState
import com.example.timeslotxgoalapp.utlis.TimeEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private var countDownTimer: CountDownTimer? = null
    //var totalTime = 0L

    private val _uiState = MutableStateFlow(AppState())
    val uiState get() = _uiState

    private fun startCountDown() {
        if (countDownTimer != null) {
            cancelTimer()
        }
        val totalTime = (getSeconds() * 1000).toLong()

        countDownTimer = object : CountDownTimer(totalTime, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secs = (millisUntilFinished / MSECS_IN_SEC % SECS_IN_MINUTES).toInt()
                if (secs != _uiState.value.seconds) {
                    _uiState.value = _uiState.value
                }
                // Minutes
                val minutes =
                    (millisUntilFinished / MSECS_IN_SEC / SECS_IN_MINUTES % SECS_IN_MINUTES).toInt()
                if (minutes != _uiState.value.minutes) {
                    _uiState.value = _uiState.value.copy(minutes = minutes)
                }
                // Hours
                val hours =
                    (millisUntilFinished / MSECS_IN_SEC / MINUTES_IN_HOUR / SECS_IN_MINUTES).toInt()
                if (hours != _uiState.value.hours) {
                    _uiState.value = _uiState.value.copy(hours = hours)
                }

                _uiState.value =
                    _uiState.value.copy(
                        isRunning = true,
                        progress = 1f - millisUntilFinished.toFloat() / totalTime.toFloat(),
                        time = formatHourMinuteSecond(hours, minutes, secs)
                    )
            }

            override fun onFinish() {
                _uiState.value = _uiState.value.copy(isRunning = false, progress = 1f)
            }

        }.start()
    }

    private fun formatHourMinuteSecond(hours: Int, minutes: Int, seconds: Int) =
        String.format("%02d:%02d:%02d", hours, minutes, seconds)

    private fun cancelTimer() {
        countDownTimer?.cancel()
        _uiState.value = _uiState.value.copy(isRunning = false)
    }

    private fun getSeconds() =
        ((_uiState.value.hours ?: 0) * MINUTES_IN_HOUR * SECS_IN_MINUTES) + ((_uiState.value.minutes
            ?: 0) * SECS_IN_MINUTES) + (_uiState.value.seconds ?: 0)

    override fun onCleared() {
        super.onCleared()
        cancelTimer()
    }

    private fun showDialog() {
        _uiState.value = _uiState.value.copy(showDialog = true)
    }

    private fun cancelDialog(hour: Int, minutes: Int, seconds: Int) {
        _uiState.value = _uiState.value.copy(
            showDialog = false,
            hours = hour,
            minutes = minutes,
            seconds = seconds, time = formatHourMinuteSecond(hour, minutes, seconds)
        )
    }

    fun handleEvent(event: TimeEvent) {
        when (event) {
            is TimeEvent.OnShowTimerDialog -> showDialog()
            is TimeEvent.OnCancelTimeDialog -> cancelDialog(
                event.hour,
                event.minutes,
                event.seconds
            )
            TimeEvent.StartTimer -> {
                startCountDown()
            }
        }
    }

    companion object {
        const val MINUTES_IN_HOUR = 60
        const val SECS_IN_MINUTES = 60
        const val MSECS_IN_SEC = 1000
    }
}
