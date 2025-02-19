package com.tianchi.staketableedit.ui.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FloatingActionButton(
    onImport: () -> Unit,
    onExport: () -> Unit,
    onCreate: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isShow by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.padding(end = 16.dp, bottom = 8.dp)
    ) {
        AnimatedVisibility(
            visible = isShow,
            enter = fadeIn() + slideInVertically(initialOffsetY = { it }) // Animation for appearing
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                FloatingActionButton(
                    elevation = FloatingActionButtonDefaults.elevation(0.dp),
                    shape = CircleShape,
                    onClick = onImport,
                    modifier = Modifier.size(66.dp),
                ) {
                    Text("导入", color = Color.White)
                }
                FloatingActionButton(
                    elevation = FloatingActionButtonDefaults.elevation(0.dp),
                    shape = CircleShape,
                    onClick = onExport,
                    modifier = Modifier.size(66.dp),
                ) {
                    Text("导出", color = Color.White)
                }
                FloatingActionButton(
                    elevation = FloatingActionButtonDefaults.elevation(0.dp),
                    shape = CircleShape,
                    onClick = onCreate,
                    modifier = Modifier.size(66.dp),
                ) {
                    Text("新增", color = Color.White)
                }
            }
        }
        FloatingActionButton(
            elevation = FloatingActionButtonDefaults.elevation(0.dp),
            shape = CircleShape,
            onClick = {
                isShow = !isShow
            },
            modifier = Modifier.size(66.dp),
        ) {
            var str = if (isShow) "-" else "+"
            Text(str, color = Color.White, style = TextStyle(fontSize = 26.sp))
        }
    }
}