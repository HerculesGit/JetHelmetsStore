package com.herco.jethelmetsstore.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.herco.jethelmetsstore.presentation.screen.HelmetDetailScreen
import com.herco.jethelmetsstore.presentation.screen.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = HomeRoute.route) {
        composable(
            route = HomeRoute.route,
        ) {
            HomeScreen(navController)
        }
        composable(
            route = HelmetDetailRoute.route,
            arguments = listOf(navArgument(HelmetDetailRoute.argument) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString(HelmetDetailRoute.argument)
            HelmetDetailScreen(navController, productId = productId)
        }
    }
}