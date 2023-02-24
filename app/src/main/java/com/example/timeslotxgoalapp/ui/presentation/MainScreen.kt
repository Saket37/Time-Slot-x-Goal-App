package com.example.timeslotxgoalapp.ui.presentation

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.timeslotxgoalapp.model.AppState
import com.example.timeslotxgoalapp.ui.components.*
import com.example.timeslotxgoalapp.utlis.TimeEvent
import com.example.timeslotxgoalapp.viewModel.MainViewModel

@Composable
fun MainScreen() {
    val viewModel: MainViewModel = viewModel()
    MainScreenContent(
        uiState = viewModel.uiState.collectAsStateWithLifecycle().value,
        handleEvent = viewModel::handleEvent
    )
}

@Composable
fun MainScreenContent(uiState: AppState, handleEvent: (event: TimeEvent) -> Unit) {
    if (uiState.showDialog) {
        TimePickerDialog(setShowDialog = { uiState.showDialog = it }, onStartClicked = {
            handleEvent(
                TimeEvent.OnCancelTimeDialog(
                    hour = it[0],
                    minutes = it[1],
                    seconds = it[2]
                )
            )
            Log.d("TIME", it.toString())
        })
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            Timer(
                onclick = { if (!uiState.isRunning) handleEvent(TimeEvent.OnShowTimerDialog) },
                timerText = uiState.time,
                timerProgress = uiState.progress
            )
            Spacer(modifier = Modifier.height(24.dp))
            if (uiState.hasFinished) {
                CompletedTimerText()
            } else {
                TimerText(
                    isEnabled = uiState.isDisabled(),
                    hasStarted = uiState.isRunning,
                    timePercentage = uiState.progress,
                    tagText = uiState.tags,
                    timerText = uiState.initialStartTime,
                )
            }
            Spacer(modifier = Modifier.height(48.dp))
            if (uiState.isRunning || uiState.hasFinished) {
                Goals(tag = uiState.tags, timerText = uiState.initialStartTime)
            } else {
                TagDescriptionText()
                Spacer(modifier = Modifier.height(16.dp))
                Tags(tagText = { handleEvent(TimeEvent.OnTagChanged(it)) })
            }
        }
        BaseButton(
            modifier = Modifier.padding(bottom = 24.dp, start = 24.dp, end = 24.dp),
            buttonText = uiState.buttonText,
            onClick = {
                if (!((uiState.seconds ?: 0) == 0 && (uiState.minutes ?: 0) == 0 && (uiState.hours
                        ?: 0) == 0)
                ) {
                    if (!uiState.isRunning) {
                        handleEvent(TimeEvent.StartTimer)
                    } else handleEvent(TimeEvent.CancelTimer)
                }
            },
            enableStartButton = uiState.isDisabled(),
            isRunning = uiState.isRunning, hasCompleted = uiState.hasFinished
        )
    }
}