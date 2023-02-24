package com.example.timeslotxgoalapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.timeslotxgoalapp.ui.theme.DescriptionTextColor

val list = listOf("Study Physics", "Study Chemistry", "Study Maths", "Study English")

@Composable
fun TagDescriptionText() {
    Text(
        text = "Select a tag for your time slot:",
        fontSize = 16.sp,
        fontWeight = FontWeight(400),
        color = DescriptionTextColor
    )
}

@Composable
fun Tags() {
    val expanded = remember {
        mutableStateOf(false)
    }
    val currentValue = remember {
        mutableStateOf("Select Tag")
    }

    Row(modifier = Modifier.clickable { expanded.value = !expanded.value }
    ) {
        Text(text = currentValue.value, fontSize = 18.sp, fontWeight = FontWeight(700))
        Spacer(modifier = Modifier.width(8.dp))
        Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null)
        DropdownMenu(expanded = expanded.value, onDismissRequest = { expanded.value = false }) {
            list.forEach {
                DropdownMenuItem(onClick = {
                    currentValue.value = it
                    expanded.value = false
                }) {
                    Text(text = it)
                }
            }
        }
    }
}