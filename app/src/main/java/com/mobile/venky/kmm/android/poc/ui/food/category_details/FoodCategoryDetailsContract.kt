package com.mobile.venky.kmm.android.poc.ui.food.category_details

import com.mobile.venky.kmm.android.poc.base.ViewEvent
import com.mobile.venky.kmm.android.poc.base.ViewSideEffect
import com.mobile.venky.kmm.android.poc.base.ViewState
import com.mobile.venky.kmm.android.poc.model.FoodItem

/**
 * @author Venky Maganahalli updated on 08/29/2021
 */

class FoodCategoryDetailsContract {
    sealed class Event : ViewEvent

    data class State(
        val category: FoodItem?,
        val categoryFoodItems: List<FoodItem>
        ) : ViewState

    sealed class Effect : ViewSideEffect

}