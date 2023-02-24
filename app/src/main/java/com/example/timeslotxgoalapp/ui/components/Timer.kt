package com.example.timeslotxgoalapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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

@Composable
fun Timer(
    modifier: Modifier = Modifier,
    onclick: () -> Unit,
    timerText: String,
    timerProgress: Float
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
        //.padding(40.dp)
        ,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(210.dp)
                .clickable { onclick() },
            color = ActiveButtonColor,
            progress = timerProgress,
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
fun TimerText(isEnabled: Boolean, hasStarted: Boolean, timePercentage: String) {
    Text(text = stringResource(id = R.string.disabled_timer_text))
}

@Preview
@Composable
fun TimerPreview() {
    Timer(onclick = {}, timerText = "00:00:00", timerProgress = .3f)
}