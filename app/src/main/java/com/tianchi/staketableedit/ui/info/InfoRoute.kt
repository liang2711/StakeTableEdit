package com.tianchi.staketableedit.ui.info

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.tianchi.staketableedit.bean.StakeInfo
import com.tianchi.staketableedit.ui.home.HomeViewModel
import io.ktor.util.valuesOf
import kotlinx.serialization.Serializable

fun NavGraphBuilder.info(navController: NavHostController) = composable<StakeInfo> {
    val viewModel: InfoViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()
    val infos = navController.previousBackStackEntry?.savedStateHandle?.get<List<StakeInfo>>("infos") ?: emptyList()
    val info = state.info
    val actions = remember(viewModel.actions) {
        viewModel.actions.copy(
            onDone = {
                var isRepeated = false
                if (info.tempNo != state.info.tempNo)
                    isRepeated = infos.any{ it.tempNo == state.info.tempNo }
                if (isRepeated)
                    viewModel.actions.onDialogStatu(true)
                else {
                    navController.previousBackStackEntry?.savedStateHandle?.set(
                        StakeInfoSavedStateKey,
                        state.info
                    )
                    navController.navigateUp()
                }
            },
            onCancel = navController::navigateUp,
        )
    }
    InfoScreen(state = state, actions = actions)

}

fun NavHostController.navigateToInfo(info: StakeInfo, infos: List<StakeInfo>) {
    this.currentBackStackEntry?.savedStateHandle?.set("infos", infos)
    navigate(info)
}

const val StakeInfoSavedStateKey = "stake_info"

@Serializable
data class InfoRoute(
    val info: StakeInfo
)