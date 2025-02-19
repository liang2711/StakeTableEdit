package com.tianchi.staketableedit.ui.common

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun SimpleDialogView(visible : Boolean,
                     dialogTitle: String,
                     dialogText: String,
                     onDismissRequest: () -> Unit,
                     onConfirmation: () -> Unit){
    if (visible) {
        AlertDialog(
            onDismissRequest = {
                onDismissRequest()
            },
            title = {
                Text(dialogTitle)
            },
            text = {
                Text(dialogText)
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirmation()
                    }
                ) {
                    Text("确定")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onDismissRequest()
                    }
                ) {
                    Text("取消")
                }
            }
        )
    }
}
@Composable
fun SimpleDialogViewSingle(visible : Boolean,
                     dialogTitle: String,
                     dialogText: String,
                     onConfirmation: () -> Unit){
    if (visible) {
        AlertDialog(
            onDismissRequest = {
                onConfirmation()
            },
            title = {
                Text(dialogTitle)
            },
            text = {
                Text(dialogText)
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirmation()
                    }
                ) {
                    Text("确定")
                }
            }
        )
    }
}