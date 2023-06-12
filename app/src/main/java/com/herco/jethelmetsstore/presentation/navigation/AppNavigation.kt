package com.herco.jethelmetsstore.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.herco.jethelmetsstore.di.UseCaseModule
import com.herco.jethelmetsstore.presentation.screen.details.HelmetDetailScreen
import com.herco.jethelmetsstore.presentation.screen.details.HelmetDetailViewModel
import com.herco.jethelmetsstore.presentation.screen.home.HomeScreen
import com.herco.jethelmetsstore.presentation.screen.home.HomeViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = HomeRoute.route) {
        composable(
            route = HomeRoute.route,
        ) {

            val homeViewModel = HomeViewModel(UseCaseModule.provideGetPopularHelmetsUseCase())
            HomeScreen(navController, viewModel = homeViewModel)
        }
        composable(
            route = HelmetDetailRoute.route,
            arguments = listOf(navArgument(HelmetDetailRoute.argument) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString(HelmetDetailRoute.argument)
            val helmetDetailsViewModel =
                HelmetDetailViewModel(UseCaseModule.provideGetProductUseCase())

            HelmetDetailScreen(
                navController,
                productId = productId,
                viewModel = helmetDetailsViewModel
            )
        }
    }
}