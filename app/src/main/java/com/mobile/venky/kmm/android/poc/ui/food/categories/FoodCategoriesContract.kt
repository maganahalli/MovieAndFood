package com.mobile.venky.kmm.android.poc.ui.food.categories


import com.mobile.venky.kmm.android.poc.base.ViewEvent
import com.mobile.venky.kmm.android.poc.base.ViewSideEffect
import com.mobile.venky.kmm.android.poc.base.ViewState
import com.mobile.venky.kmm.android.poc.model.FoodItem

/**
 * @author Venky Maganahalli updated on 08/29/2021
 */
class FoodCategoriesContract {
    sealed class Event : ViewEvent {
        data class CategorySelection(val categoryName: String) : Event()
    }

    data class State(val categories: List<FoodItem> = listOf(), val isLoading: Boolean = false) :
        ViewState

    sealed class Effect : ViewSideEffect {
        object DataWasLoaded : Effect()

        sealed class Navigation : Effect() {
            data class ToCategoryDetails(val categoryName: String) : Navigation()
        }
    }

}