package com.mobile.venky.kmm.android.poc.ui.movie.landing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.mobile.venky.kmm.android.poc.ui.movie.categories.MovieCategoriesContract
import com.mobile.venky.kmm.android.poc.ui.movie.categories.MovieCategoriesScreen
import com.mobile.venky.kmm.android.poc.ui.movie.categories.MovieCategoriesViewModel
import com.mobile.venky.kmm.android.poc.ui.movie.category_details.MovieCategoryDetailsScreen
import com.mobile.venky.kmm.android.poc.ui.movie.category_details.MovieCategoryDetailsViewModel
import com.mobile.venky.kmm.android.poc.ui.movie.landing.NavigationKeys.Arg.MOVIE_CATEGORY_ID
import com.mobile.venky.kmm.android.poc.ui.theme.ComposeSampleTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Venky Maganahalli updated on 08/29/2021
 */

// Single Activity per app
@AndroidEntryPoint
class MovieLandingActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeSampleTheme {
                MovieApp()
            }
        }
    }

}

@Composable
private fun MovieApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = NavigationKeys.Route.MOVIE_CATEGORIES_LIST) {
        composable(route = NavigationKeys.Route.MOVIE_CATEGORIES_LIST) {
            MovieCategoriesDestination(navController)
        }
        composable(
            route = NavigationKeys.Route.MOVIE_CATEGORY_DETAILS,
            arguments = listOf(navArgument(NavigationKeys.Arg.MOVIE_CATEGORY_ID) {
                type = NavType.StringType
            })
        ) {
            MovieCategoryDetailsDestination()
        }
    }
}

@Composable
private fun MovieCategoriesDestination(navController: NavHostController) {
    val viewModel: MovieCategoriesViewModel = hiltViewModel()
    val state = viewModel.viewState.value
    MovieCategoriesScreen(
        state = state,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is MovieCategoriesContract.Effect.Navigation.ToCategoryDetails) {
                navController.navigate("${NavigationKeys.Route.MOVIE_CATEGORIES_LIST}/${navigationEffect.categoryName}")
            }
        })
}

@Composable
fun MovieCategoryDetailsDestination() {
    val viewModel: MovieCategoryDetailsViewModel = hiltViewModel()
    val state = viewModel.viewState.value
    MovieCategoryDetailsScreen(state)
}

object NavigationKeys {

    object Arg {
        const val MOVIE_CATEGORY_ID = "movieCategoryName"
    }

    object Route {
        const val MOVIE_CATEGORIES_LIST = "movie_categories_list"
        const val MOVIE_CATEGORY_DETAILS = "$MOVIE_CATEGORIES_LIST/{$MOVIE_CATEGORY_ID}"
    }

}