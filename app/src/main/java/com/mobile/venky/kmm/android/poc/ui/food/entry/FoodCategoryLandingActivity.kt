package com.mobile.venky.kmm.android.poc.ui.food.entry

import android.os.Bundle
import android.util.Log
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
import com.mobile.venky.kmm.android.poc.ui.food.categories.FoodCategoriesContract
import com.mobile.venky.kmm.android.poc.ui.food.categories.FoodCategoriesScreen
import com.mobile.venky.kmm.android.poc.ui.food.categories.FoodCategoriesViewModel
import com.mobile.venky.kmm.android.poc.ui.food.category_details.FoodCategoryDetailsScreen
import com.mobile.venky.kmm.android.poc.ui.food.category_details.FoodCategoryDetailsViewModel
import com.mobile.venky.kmm.android.poc.ui.food.entry.NavigationKeys.Arg.FOOD_CATEGORY_ID
import com.mobile.venky.kmm.android.poc.ui.theme.ComposeSampleTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Venky Maganahalli updated on 08/29/2021
 */

// Single Activity per app
@AndroidEntryPoint
class FoodCategoryLandingActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeSampleTheme {
                FoodApp()
            }
        }
    }

}

@Composable
private fun FoodApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = NavigationKeys.Route.FOOD_CATEGORIES_LIST) {

        composable(route = NavigationKeys.Route.FOOD_CATEGORIES_LIST) {
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
                Log.d("Food Main Screen", navigationEffect.categoryName)
                navController.navigate("${NavigationKeys.Route.FOOD_CATEGORIES_LIST}/${navigationEffect.categoryName}")
            }
        })
}

@Composable
fun FoodCategoryDetailsDestination() {
    val viewModel: FoodCategoryDetailsViewModel = hiltViewModel()
    val state = viewModel.viewState.value
    FoodCategoryDetailsScreen(state)
}

object NavigationKeys {

    object Arg {
        const val FOOD_CATEGORY_ID = "foodCategoryName"
    }

    object Route {
        const val FOOD_CATEGORIES_LIST = "food_categories_list"
        const val FOOD_CATEGORY_DETAILS = "$FOOD_CATEGORIES_LIST/{$FOOD_CATEGORY_ID}"
    }

}