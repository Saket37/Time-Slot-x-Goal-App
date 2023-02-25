package com.example.timeslotxgoalapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.timeslotxgoalapp.R
import com.example.timeslotxgoalapp.ui.theme.DescriptionTextColor

@Composable
fun Goals(tag: String, timerText: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Goal",
            fontWeight = FontWeight(400),
            color = DescriptionTextColor,
            fontSize = 16.sp
        )
        Text(
            text = stringResource(id = R.string.goals_text, tag, timerText),
            fontSize = 18.sp,
            fontWeight = FontWeight(700), color = DescriptionTextColor,
        )
    }
}