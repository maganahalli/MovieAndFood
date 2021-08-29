package com.mobile.venky.kmm.android.poc.ui.food.categories

import androidx.lifecycle.viewModelScope
import com.mobile.venky.kmm.android.poc.base.BaseViewModel
import com.mobile.venky.kmm.android.poc.model.data.repository.FoodMenuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Venky Maganahalli updated on 08/29/2021
 */

@HiltViewModel
class FoodCategoriesViewModel @Inject constructor(private val repository: FoodMenuRepository) :
    BaseViewModel<FoodCategoriesContract.Event, FoodCategoriesContract.State, FoodCategoriesContract.Effect>() {

    init {
        viewModelScope.launch { getFoodCategories() }
    }

    override fun setInitialState() =
        FoodCategoriesContract.State(categories = listOf(), isLoading = true)

    override fun handleEvents(event: FoodCategoriesContract.Event) {
        when (event) {
            is FoodCategoriesContract.Event.CategorySelection -> {
                setEffect { FoodCategoriesContract.Effect.Navigation.ToCategoryDetails(event.categoryName) }
            }
        }
    }

    private suspend fun getFoodCategories() {
        val categories = repository.getFoodCategories()
        setState {
            copy(categories = categories, isLoading = false)
        }
        setEffect { FoodCategoriesContract.Effect.DataWasLoaded }
    }

}
