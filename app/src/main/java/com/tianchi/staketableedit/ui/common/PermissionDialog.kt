package com.tianchi.staketableedit.ui.common

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Environment
import android.provider.Settings
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.tianchi.staketableedit.ktx.toast

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun PermissionDialog(
    visible: Boolean,
    onPermissionChange: (Boolean) -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(Unit) {
        val observer = LifecycleEventObserver { source, event ->
            Log.d("MainActivity", "onStateChanged: $event")
            when (event) {
                Lifecycle.Event.ON_RESUME -> {
                    onPermissionChange(Environment.isExternalStorageManager())
                }
                else -> Unit
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    PermissionDialog(
        visible = visible,
        onConfirm = {
            val intent = Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION)
            context.startActivity(intent)
        },
        onDismissRequest = {
            context.toast("权限取消授权")
            (context as? Activity)?.finish()
        }
    )
}

@Composable
fun PermissionDialog(
    visible: Boolean,
    onConfirm: () -> Unit,
    onDismissRequest: () -> Unit,
) {
    if (visible) AlertDialog(
        title = {
            Text(text = "管理所有文件")
        }, text = {
            Text(text = "授予app管理存储设备上的所有文件的权限")
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm()
                }
            ) {
                Text(text = "确定")
            }
        }, dismissButton = {
            TextButton(
                onClick = onDismissRequest
            ) {
                Text(text = "取消")
            }
        }, onDismissRequest = onDismissRequest
    )
}