package com.tianchi.staketableedit.ui.common

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tianchi.staketableedit.ui.theme.Test1229Theme

@Composable
fun PreviewSurface(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Test1229Theme {
        Surface(
            modifier = modifier,
            content = content
        )
    }
}