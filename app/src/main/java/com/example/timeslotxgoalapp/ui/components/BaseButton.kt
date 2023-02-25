package com.example.timeslotxgoalapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.timeslotxgoalapp.R
import com.example.timeslotxgoalapp.ui.theme.ActiveButtonColor
import com.example.timeslotxgoalapp.ui.theme.DisabledButtonColor
import com.example.timeslotxgoalapp.ui.theme.EndButtonColor

@Composable
fun BaseButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enableStartButton: Boolean,
    isRunning: Boolean,
    buttonText: Int,
    hasCompleted: Boolean,
    newGoalClick: () -> Unit
) {

    val buttonColor = if (isRunning) {
        EndButtonColor
    } else ActiveButtonColor

    Button(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            buttonColor,
            disabledBackgroundColor = DisabledButtonColor
        ), onClick = {
            if (hasCompleted) newGoalClick() else
                onClick()
        }, enabled = enableStartButton
    ) {
        Text(
            modifier = Modifier.padding(vertical = 2.dp),
            text = stringResource(id = buttonText),
            fontSize = 16.sp,
            fontWeight = FontWeight(700),
            color = Color.White
        )
    }
}

@Preview
@Composable
fun BaseButtonPreview() {
    BaseButton(
        onClick = {},
        enableStartButton = false,
        isRunning = false,
        buttonText = R.string.start_button_text,
        hasCompleted = false,
        newGoalClick = {}
    )
}