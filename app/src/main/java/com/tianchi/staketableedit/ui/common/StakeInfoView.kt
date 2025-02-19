package com.tianchi.staketableedit.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tianchi.staketableedit.bean.Constant
import com.tianchi.staketableedit.bean.StakeInfo

@Composable
fun StakeInfoView(
    isShow: Boolean,
    stakeInfo: StakeInfo,
    onPipeLineChanged: (StakeInfo) -> Unit,
    onDeleteItem: () -> Unit
) {
    if (!isShow) {
        return
    }
    Surface(
        shape = RoundedCornerShape(8.dp),
        shadowElevation = 10.dp,
    ) {
        Column {
            EditableTextField(
                "管线",
                stakeInfo.pipeLine,
                modifier = Modifier.fillMaxWidth(),
                onPipeLineChanged = {
                    onPipeLineChanged(stakeInfo.copy(pipeLine = it))
                }
            )
            EditableExposedDropdownMenuSample(
                Constant.stakeTypeArray,
                "桩类型",
                stakeInfo.stakeType,
                modifier = Modifier.fillMaxWidth(),
                onPipeLineChanged = {
                    onPipeLineChanged(stakeInfo.copy(stakeType = it))
                }
            )
            EditableExposedDropdownMenuSample(
                Constant.depthArray,
                "管中埋深(米)",
                stakeInfo.pipeDepth,
                modifier = Modifier.fillMaxWidth(),
                onPipeLineChanged = {
                    onPipeLineChanged(stakeInfo.copy(pipeDepth = it))
                }
            )
            EditableTextField(
                "里程",
                stakeInfo.mileage,
                modifier = Modifier.fillMaxWidth(),
                onPipeLineChanged = {
                    onPipeLineChanged(stakeInfo.copy(mileage = it))
                }
            )
            EditableTextField(
                "边距",
                stakeInfo.setover,
                modifier = Modifier.fillMaxWidth(),
                onPipeLineChanged = {
                    onPipeLineChanged(stakeInfo.copy(setover = it))
                }
            )
            EditableExposedDropdownMenuSample(
                Constant.BuriedTechnologyArray,
                "埋设工艺",
                stakeInfo.buryTech,
                modifier = Modifier.fillMaxWidth(),
                onPipeLineChanged = {
                    onPipeLineChanged(stakeInfo.copy(buryTech = it))
                }
            )
            ExposedDropdownMenuSample(
                "是否出入土点",
                stakeInfo.isInPoint,
                modifier = Modifier.fillMaxWidth(),
                onPipeLineChanged = {
                    onPipeLineChanged(stakeInfo.copy(isInPoint = it))
                }
            )
            EditableExposedDropdownMenuSample(
                Constant.terrainArray,
                "地形",
                stakeInfo.terrain,
                modifier = Modifier.fillMaxWidth(),
                onPipeLineChanged = {
                    onPipeLineChanged(stakeInfo.copy(terrain = it))
                }
            )
            Row {
                EditableTextFieldInt("x", "${stakeInfo.latitude}", modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), onPipeLineChanged = {
                    onPipeLineChanged(stakeInfo.copy(latitude = it))
                })
                EditableTextFieldInt(
                    "y",
                    "${stakeInfo.longitude}",
                    onPipeLineChanged = {
                        onPipeLineChanged(stakeInfo.copy(longitude = it))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                )
            }
            EditableTextFieldInt(
                "Z(85高程，桩根部)",
                "${stakeInfo.z}",
                onPipeLineChanged = {
                    onPipeLineChanged(stakeInfo.copy(z = it))
                }, modifier = Modifier
                    .fillMaxWidth()

            )
            EditableTextField(
                "照片名(默认为设桩号)",
                stakeInfo.imageName,
                modifier = Modifier.fillMaxWidth(),
                onPipeLineChanged = {
                    onPipeLineChanged(stakeInfo.copy(imageName = it))
                }
            )
            EditableTime(
                "采集时间",
                stakeInfo.collectDate,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 6.dp, end = 6.dp, top = 18.dp, bottom = 12.dp),
                onPipeLineChanged = {
                    onPipeLineChanged(stakeInfo.copy(collectDate = it))
                }
            )
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        onDeleteItem()
                    }
                ) {
                    Text(text = "删除")
                }
            }
        }
    }
}