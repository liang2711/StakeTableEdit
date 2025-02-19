package com.tianchi.staketableedit.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.tianchi.staketableedit.ui.files.files
import com.tianchi.staketableedit.ui.home.HomeRoute
import com.tianchi.staketableedit.ui.home.home
import com.tianchi.staketableedit.ui.info.info

@Composable
fun SharedNavHost(
    startDestination: Any = HomeRoute
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        home(navController)
        info(navController)
        files(navController)
    }
}