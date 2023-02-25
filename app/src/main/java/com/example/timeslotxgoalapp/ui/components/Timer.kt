package com.example.timeslotxgoalapp.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.timeslotxgoalapp.ui.theme.ActiveButtonColor
import com.example.timeslotxgoalapp.ui.theme.DescriptionTextColor
import com.example.timeslotxgoalapp.R
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

@Composable
fun Timer(
    modifier: Modifier = Modifier, onclick: () -> Unit, timerText: String, timerProgress: Float
) {

    val animatedProgress by animateFloatAsState(
        targetValue = timerProgress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(250.dp)
                .clickable { onclick() },
            color = ActiveButtonColor,
            progress = animatedProgress,
            strokeWidth = 6.dp,
            backgroundColor = DescriptionTextColor.copy(.2f)
        )
        Text(
            text = timerText,
            fontSize = 24.sp,
            fontWeight = FontWeight(700),
            color = DescriptionTextColor
        )
    }
}

@Composable
fun TimerText(
    isEnabled: Boolean,
    hasStarted: Boolean,
    timePercentage: Float,
    tagText: String,
    timerText: String,
) {
    val tag = tagText.split(" ").last()
    val percentage = (timePercentage * 100).roundToInt().toString() + "%"
    val text =
        if (!isEnabled) stringResource(id = R.string.disabled_timer_text) else if (hasStarted) stringResource(
            id = R.string.started_timer_text, percentage
        ) else stringResource(
            id = R.string.enabled_timer_text, tag, timerText
        )
    Text(text = text)

}

@Composable
fun CompletedTimerText() {
    Text(text = stringResource(id = R.string.completed_timer_goal))
}

@Preview
@Composable
fun TimerPreview() {
    Timer(onclick = {}, timerText = "00:00:00", timerProgress = .3f)
}