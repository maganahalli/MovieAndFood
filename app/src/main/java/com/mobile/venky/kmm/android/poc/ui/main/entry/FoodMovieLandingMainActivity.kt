package com.mobile.venky.kmm.android.poc.ui.main.entry

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.mobile.venky.kmm.android.poc.ui.food.categories.FoodCategoriesContract
import com.mobile.venky.kmm.android.poc.ui.food.categories.FoodCategoriesScreen
import com.mobile.venky.kmm.android.poc.ui.food.categories.FoodCategoriesViewModel
import com.mobile.venky.kmm.android.poc.ui.food.entry.FoodCategoryDetailsDestination
import com.mobile.venky.kmm.android.poc.ui.main.MainCategoriesContract
import com.mobile.venky.kmm.android.poc.ui.main.MainCategoriesScreen
import com.mobile.venky.kmm.android.poc.ui.main.MainCategoriesViewModel
import com.mobile.venky.kmm.android.poc.ui.main.entry.NavigationKeys.Arg.FOOD_CATEGORY_ID
import com.mobile.venky.kmm.android.poc.ui.movie.categories.MovieCategoriesContract
import com.mobile.venky.kmm.android.poc.ui.movie.categories.MovieCategoriesScreen
import com.mobile.venky.kmm.android.poc.ui.movie.categories.MovieCategoriesViewModel
import com.mobile.venky.kmm.android.poc.ui.movie.landing.MovieCategoryDetailsDestination
import com.mobile.venky.kmm.android.poc.ui.theme.MovieAndFoodTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Venky Maganahalli updated on 08/29/2021
 */

@AndroidEntryPoint
class FoodMovieLandingMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAndFoodTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                   MainApp()
                }
            }
        }
    }
}


@Composable
private fun MainApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = NavigationKeys.Route.MAIN_CATEGORIES_LIST) {

        composable(route = NavigationKeys.Route.MAIN_CATEGORIES_LIST) {
            MainScreen(navController)
        }

        composable(
            route = NavigationKeys.Route.MOVIE_CATEGORIES_LIST,
            arguments = listOf(navArgument(NavigationKeys.Arg.MOVIE_CATEGORY_ID) {
                type = NavType.StringType
            })
        ) {
            MovieCategoriesDestination(navController)
        }

        composable(
            route = NavigationKeys.Route.FOOD_CATEGORIES_LIST) {
            FoodCategoriesDestination(navController)
        }

        composable(
            route = NavigationKeys.Route.FOOD_CATEGORY_DETAILS,
            arguments = listOf(navArgument(FOOD_CATEGORY_ID) {
                type = NavType.StringType
            })
        ) {
            FoodCategoryDetailsDestination()
        }


        composable(
            route = NavigationKeys.Route.MOVIE_CATEGORY_DETAILS,
            arguments = listOf(navArgument(NavigationKeys.Arg.MOVIE_CATEGORY_ID) {
                type = NavType.StringType
            })
        ) {
            MovieCategoryDetailsDestination()
        }

        composable(route = "${NavigationKeys.Route.MAIN_CATEGORIES_LIST}/categoryId" ,
            arguments = listOf(navArgument("categoryId") {
                type = NavType.IntType
            })
        ) { navBackStackENtry ->
            UserDescionScreen(navBackStackENtry.arguments!!.getInt("categoryId"),navController)
        }
    }
}

@Composable
fun MainScreen(navController: NavHostController) {
    val viewModel: MainCategoriesViewModel = hiltViewModel()
    val state = viewModel.viewState.value
    MainCategoriesScreen(
        state = state,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is MainCategoriesContract.Effect.Navigation.ToCategoryDetails) {
                val destination = if (navigationEffect.categoryName == "0"){
                    NavigationKeys.Route.FOOD_CATEGORIES_LIST
                } else {
                    NavigationKeys.Route.MOVIE_CATEGORIES_LIST
                }
                navController.navigate(destination)
            }
        })

}

@Composable
fun UserDescionScreen(categoryId: Int, navController: NavHostController) {
    if(categoryId == 0){
        FoodCategoriesDestination(navController)
    }else{
       MovieCategoriesDestination(navController)
    }

}

@Composable
fun MovieCategoriesDestination(navController: NavHostController) {
    val viewModel: MovieCategoriesViewModel = hiltViewModel()
    val state = viewModel.viewState.value
    MovieCategoriesScreen(
        state = state,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) }
    ) { navigationEffect ->
        if (navigationEffect is MovieCategoriesContract.Effect.Navigation.ToCategoryDetails) {
            navController.navigate(NavigationKeys.Route.MOVIE_CATEGORIES_LIST)
        }
    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieAndFoodTheme {
        Greeting("Android")
    }

}

object NavigationKeys {

    object Arg {
        const val FOOD_CATEGORY_ID = "foodCategoryName"
        const val MOVIE_CATEGORY_ID = "foodCategoryName"
    }

    object Route {

        const val FOOD_CATEGORIES_LIST = "food_categories_list"
        const val FOOD_CATEGORY_DETAILS = "$FOOD_CATEGORIES_LIST/{$FOOD_CATEGORY_ID}"

        const val MAIN_CATEGORIES_LIST = "main_categories_list"

        const val MOVIE_CATEGORIES_LIST = "movie_categories_list"
        const val MOVIE_CATEGORY_DETAILS = "${NavigationKeys.Route.MOVIE_CATEGORIES_LIST}/{${NavigationKeys.Arg.MOVIE_CATEGORY_ID}}"

    }

}


@Composable
private fun FoodCategoriesDestination(navController: NavHostController) {
    val viewModel: FoodCategoriesViewModel = hiltViewModel()
    val state = viewModel.viewState.value
    FoodCategoriesScreen(
        state = state,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is FoodCategoriesContract.Effect.Navigation.ToCategoryDetails) {
                navController.navigate(NavigationKeys.Route.FOOD_CATEGORIES_LIST)
            }
        })
}