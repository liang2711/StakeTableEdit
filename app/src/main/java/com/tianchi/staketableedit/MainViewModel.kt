package com.tianchi.staketableedit

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tianchi.staketableedit.data.PermissionState
import com.tianchi.staketableedit.di.PermissionStateFlow
import com.tianchi.staketableedit.domain.CheckPermissionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @PermissionStateFlow
    private val permissionState: MutableStateFlow<PermissionState>,
    private val checkPermissionUseCase: CheckPermissionUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(MainViewState())
    val state = _state.asStateFlow()

    init {
        permissionState.onEach {
            Log.d("MainViewModel","init permissionState ${it.toString()}")
            when (it) {
                PermissionState.None -> checkPermissionUseCase()
                PermissionState.Granted -> _state.update { it.copy(permissionDialogVisible = false) }
                PermissionState.Denied -> _state.update { it.copy(permissionDialogVisible = true) }
            }
        }.launchIn(viewModelScope)
    }

    fun onPermissionChange(value: Boolean) {
        checkPermissionUseCase()
    }
}

data class MainViewState(
    val permissionDialogVisible: Boolean = false
)