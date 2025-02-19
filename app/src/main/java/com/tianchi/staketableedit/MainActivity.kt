package com.tianchi.staketableedit

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.tianchi.staketableedit.ui.SharedNavHost
import com.tianchi.staketableedit.ui.common.PermissionDialog
import com.tianchi.staketableedit.ui.theme.Test1229Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class]
    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContent {
            Test1229Theme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    SharedNavHost()
                }
            }
            val state by viewModel.state.collectAsState()
            PermissionDialog(
                visible = state.permissionDialogVisible,
                onPermissionChange = viewModel::onPermissionChange
            )
        }
    }
}
