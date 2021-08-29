package com.mobile.venky.kmm.android.poc.ui.food.category_details

import androidx.lifecycle.SavedStateHandle

import androidx.lifecycle.viewModelScope

import com.mobile.venky.kmm.android.poc.base.BaseViewModel
import com.mobile.venky.kmm.android.poc.model.data.repository.FoodMenuRepository
import com.mobile.venky.kmm.android.poc.ui.food.entry.NavigationKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * @author Venky Maganahalli updated on 08/29/2021
 */

@HiltViewModel
class FoodCategoryDetailsViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle,
    private val repository: FoodMenuRepository
) : BaseViewModel<
        FoodCategoryDetailsContract.Event,
        FoodCategoryDetailsContract.State,
        FoodCategoryDetailsContract.Effect>() {

    init {
        viewModelScope.launch {
            val categoryId = stateHandle.get<String>(NavigationKeys.Arg.FOOD_CATEGORY_ID)
                ?: throw IllegalStateException("No categoryId was passed to destination.")
            val categories = repository.getFoodCategories()
            val category = categories.first { it.id == categoryId }
            setState { copy(category = category) }

            val foodItems = repository.getMealsByCategory(categoryId)
            setState { copy(categoryFoodItems = foodItems) }
        }
    }

    override fun setInitialState() = FoodCategoryDetailsContract.State(null, listOf())

    override fun handleEvents(event: FoodCategoryDetailsContract.Event) {}

}
