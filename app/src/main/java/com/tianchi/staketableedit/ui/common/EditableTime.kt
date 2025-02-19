package com.tianchi.staketableedit.ui.common

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditableTime(
    label: String,
    date: String,
    onPipeLineChanged: (String) -> Unit, modifier: Modifier = Modifier
) {
    var content = date
    if (content.isEmpty()) {
        content = "**-**-**"
    }

    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
        var showTimePicker by remember { mutableStateOf(false) }
        Text(text = label)
        Text(text = content,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.clickable {
                showTimePicker = true
            }
        )
        val datePickerState = rememberDatePickerState()
        if (showTimePicker) {
            DatePickerDialog(
                onDismissRequest = { showTimePicker = false; },
                confirmButton = {
                    TextButton(onClick = {
                        val formattedDate = datePickerState.selectedDateMillis?.let {
                            val date = Date(it)
                            val format =
                                SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) // 设置日期格式
                            format.format(date)
                        } ?: content
                        onPipeLineChanged(formattedDate)
                        Log.d("DatePickerDialog", formattedDate)
                        showTimePicker = false;
                    }) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showTimePicker = false; }) {
                        Text("Cancel")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
    }
}