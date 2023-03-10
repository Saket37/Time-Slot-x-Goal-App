package com.example.timeslotxgoalapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.timeslotxgoalapp.ui.theme.ActiveButtonColor
import com.example.timeslotxgoalapp.ui.theme.DescriptionTextColor

@Composable
fun TimePicker(onStart: (it: List<Int>) -> Unit) {

    var input by remember {
        mutableStateOf(listOf<Int>())
    }

    val (hou, min, sec) = remember(input) {
        mutableListOf<Int>().run {
            repeat(6 - input.size) {
                add(0)
            }
            addAll(input)

            Triple(
                "${get(0)}${get(1)}", "${get(2)}${get(3)}", "${get(4)}${get(5)}"
            )
        }
    }

    val hasCountdownValue = remember(input) {
        input.isNotEmpty()
    }

    Column(modifier = Modifier) {

        Row(
            Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(start = 6.dp, end = 6.dp)
        ) {
            listOf(hou to "h", min to "m", sec to "s").forEach {
                DisplayTime(it.first, it.second, hasCountdownValue)
            }

            Image(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clickable(enabled = hasCountdownValue) {
                        input = input
                            .toMutableList()
                            .apply { removeLast() }
                    },
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                colorFilter = ColorFilter.tint(
                    Color.Unspecified.copy(
                        if (hasCountdownValue) 1.0f else 0.5f
                    )
                )
            )
        }

        Divider(
            modifier = Modifier.padding(20.dp), color = Color.LightGray, thickness = 0.5.dp
        )

        Column(
            Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)
        ) {

            val onClick = remember {
                { it: Int ->
                    if (input.size < 6) {
                        input = input.toMutableList() + it
                    }
                }
            }

            listOf((1..3), (4..6), (7..9)).forEach {
                Row(
                    modifier = Modifier.padding(10.dp)
                ) {
                    it.forEach {
                        InputButton(it) { it1 -> onClick(it1) }
                    }
                }
            }

            Row(
                modifier = Modifier.padding(10.dp)
            ) {
                InputButton(0) {
                    if (hasCountdownValue) {
                        onClick(0)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.size(30.dp))


        Image(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(70.dp)
                .shadow(30.dp, shape = CircleShape)
                .clickable {
                    if (hasCountdownValue) {
                        onStart(
                            (listOf(hou.toInt(), min.toInt(), sec.toInt()))
                        )
                    }
                },
            imageVector = Icons.Default.PlayArrow,
            contentDescription = null,
            colorFilter = ColorFilter.tint(ActiveButtonColor)
        )
    }
}


@Composable
fun RowScope.DisplayTime(
    num: String,
    label: String = "",
    heightLight: Boolean = true,
    fontSize: TextUnit = displayFontSize,
    labelSize: TextUnit = TextUnit.Unspecified,
    textAlign: TextAlign = TextAlign.End
) {

    val textColor = if (heightLight) DescriptionTextColor
    else Color.Unspecified.copy(alpha = 0.5f)

    Text(
        num,
        Modifier
            .weight(1f)
            .align(Alignment.CenterVertically),
        textAlign = textAlign,
        fontSize = fontSize,
        fontFamily = FontFamily.Cursive,
        color = textColor,
        style = typography.subtitle1
    )
    if (label.isNotEmpty()) {
        Text(
            modifier = Modifier
                .padding(top = 20.dp)
                .align(Alignment.CenterVertically),
            text = label,
            fontSize = labelSize,
            color = textColor
        )
        Spacer(modifier = Modifier.width(15.dp))
    }
}

@Composable
fun RowScope.InputButton(num: Int, onClick: (Int) -> Unit) {
    Text(
        num.toString(),
        Modifier
            .weight(1f)
            .clickable { onClick(num) },
        fontSize = inputFontSize,
        textAlign = TextAlign.Center,
        style = typography.subtitle1
    )
}


val inputFontSize = 35.sp
val displayFontSize = 50.sp