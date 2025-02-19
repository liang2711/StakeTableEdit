package com.tianchi.staketableedit.ui.info

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.tianchi.staketableedit.bean.StakeInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val info = savedStateHandle.toRoute<StakeInfo>()
    private val _state = MutableStateFlow(InfoViewState(info = info))
    val state = _state.asStateFlow()
    val actions = InfoActions(
        onInfoChange = ::onInfoChange,
        onDialogStatu = ::onDialogStatu
    )

    private fun onInfoChange(value: StakeInfo) {
        _state.update { it.copy(info = value) }
    }
    private fun onDialogStatu(isShow:Boolean){
        _state.update { it.copy(showDialog = isShow) }
    }
}

data class InfoViewState(
    val info: StakeInfo = StakeInfo.Empty,
    val showDialog: Boolean = false,
)

data class InfoActions(
    val onInfoChange: (StakeInfo) -> Unit = {},
    val onDone: () -> Unit = {},
    val onCancel: () -> Unit = {},
    val onDialogStatu: (Boolean) -> Unit = {}
)