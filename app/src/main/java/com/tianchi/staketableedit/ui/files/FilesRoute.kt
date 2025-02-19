package com.tianchi.staketableedit.ui.files

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

fun NavGraphBuilder.files(navController: NavHostController) = composable<FilesRoute> {
    val viewModel: FilesViewModel = hiltViewModel()
    FilesScreen(
        fileState = viewModel.fileState,
        onFileSelected = {
            navController.previousBackStackEntry?.savedStateHandle?.set(FileSavedStateKey, it)
            navController.navigateUp()
        },
        onCancel = navController::navigateUp
    )
}

fun NavHostController.navigateToFiles() {
    navigate(FilesRoute)
}

const val FileSavedStateKey = "file"

@Serializable
data object FilesRoute