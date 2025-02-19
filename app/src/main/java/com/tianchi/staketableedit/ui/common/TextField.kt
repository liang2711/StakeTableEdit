package com.tianchi.staketableedit.ui.common

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun EditableTextField(
    label: String,
    content: String,
    onPipeLineChanged: (String) -> Unit, modifier: Modifier = Modifier
) {
    TextField(
        modifier = modifier,
        value = content,
        onValueChange = {
            onPipeLineChanged(it)
        },
        singleLine = true,
        label = {
            Text(label)
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent
        )
    )
}

@Composable
fun EditableTextFieldInt(
    label: String,
    content: String,
    onPipeLineChanged: (Double) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        modifier = modifier,
        value = content,
        onValueChange = {
            val newValue = it.toDoubleOrNull() // 将输入的字符串转换为 Double
            if (newValue != null) {
                onPipeLineChanged(newValue) // 如果转换成功，调用 onPipeLineChanged
            }
        },
        singleLine = true,
        label = {
            Text(label)
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number, // 设置为数字键盘
            imeAction = ImeAction.Done
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent
        )
    )
}