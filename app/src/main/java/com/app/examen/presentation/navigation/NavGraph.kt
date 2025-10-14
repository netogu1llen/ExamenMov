package com.app.examen.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.examen.presentation.screens.detail.CountryDetailScreen
import com.app.examen.presentation.screens.home.HomeScreen

sealed class Screen(
    val route: String,
) {
    object Home : Screen("home")

    object Detail : Screen("country/{countryName}") {
        fun createRoute(countryName: String) = "country/$countryName"
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun CountryNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier,
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(
                onCountryClick = { countryName ->
                    navController.navigate(Screen.Detail.createRoute(countryName))
                },
            )
        }

        composable(
            route = Screen.Detail.route,
            arguments =
                listOf(
                    navArgument("countryName") {
                        type = NavType.StringType
                    },
                ),
        ) { backStackEntry ->
            val countryName = backStackEntry.arguments?.getString("countryName") ?: ""
            CountryDetailScreen(
                countryName = countryName,
                onBackClick = { navController.popBackStack() },
            )
        }
    }
}
