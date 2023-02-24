package com.example.timeslotxgoalapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.timeslotxgoalapp.ui.theme.DescriptionTextColor

@Composable
fun Goals() {
    Column {
        Text(
            text = "Goal",
            fontWeight = FontWeight(400),
            color = DescriptionTextColor,
            fontSize = 16.sp
        )
        // TODO build annotated string
        //Text(text = )
    }
}