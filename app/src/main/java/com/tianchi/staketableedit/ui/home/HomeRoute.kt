package com.tianchi.staketableedit.ui.home

import android.app.Activity
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.tianchi.staketableedit.bean.StakeInfo
import com.tianchi.staketableedit.ktx.toast
import com.tianchi.staketableedit.ui.files.FileSavedStateKey
import com.tianchi.staketableedit.ui.files.navigateToFiles
import com.tianchi.staketableedit.ui.info.StakeInfoSavedStateKey
import com.tianchi.staketableedit.ui.info.navigateToInfo
import kotlinx.serialization.Serializable

fun NavGraphBuilder.home(navController: NavHostController) = composable<HomeRoute> {
    val file = it.savedStateHandle.get<String>(FileSavedStateKey)
    val stakeInfo = it.savedStateHandle.get<StakeInfo>(StakeInfoSavedStateKey)
    val context = LocalContext.current
    val viewModel: HomeViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()
    var lastBackPressedTime: Long by remember { mutableLongStateOf(0) }
    val backPressInterval: Long = 2000
    val actions = remember(viewModel.actions) {
        viewModel.actions.copy(
            onFinishedActivity = {
                val currentTime = System.currentTimeMillis()
                if (currentTime - lastBackPressedTime < backPressInterval) {
                    (context as? Activity)?.finish()
                } else {
                    context.toast("再按一次退出")
                    lastBackPressedTime = currentTime
                }
            }, navigateToInfo = {
                navController.navigateToInfo(it ?: StakeInfo.Empty,viewModel.state.value.items)
            }, navigateToFiles = navController::navigateToFiles
        )
    }

    LaunchedEffect(file) {
        file?.let(actions.onFileSelected)
        it.savedStateHandle[FileSavedStateKey] = null
    }

    LaunchedEffect(stakeInfo) {
        stakeInfo?.let { actions.onPipeLineChanged(state.editingIndex, it) }
        it.savedStateHandle[StakeInfoSavedStateKey] = null
    }

    HomeScreen(state = state, actions = actions)
}

@Serializable
data object HomeRoute