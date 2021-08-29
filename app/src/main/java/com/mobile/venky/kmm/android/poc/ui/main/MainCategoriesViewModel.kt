package com.mobile.venky.kmm.android.poc.ui.main

import androidx.lifecycle.viewModelScope
import com.mobile.venky.kmm.android.poc.base.BaseViewModel
import com.mobile.venky.kmm.android.poc.model.data.repository.MainCategoriesRepository
import com.mobile.venky.kmm.android.poc.model.data.repository.availableCategoryList
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Venky Maganahalli updated on 08/29/2021
 */
@HiltViewModel
class MainCategoriesViewModel  @Inject constructor(private val repository:MainCategoriesRepository):
    BaseViewModel<MainCategoriesContract.Event, MainCategoriesContract.State, MainCategoriesContract.Effect>() {

    init {
        viewModelScope.launch { getMainCategories() }
    }

    override fun setInitialState() =
        MainCategoriesContract.State(categories = listOf(), isLoading = true)

    override fun handleEvents(event: MainCategoriesContract.Event) {
        when (event) {
            is MainCategoriesContract.Event.CategorySelection -> {
                setEffect { MainCategoriesContract.Effect.Navigation.ToCategoryDetails(event.categoryName) }
            }
        }
    }

    private fun getMainCategories() {
        val categories = availableCategoryList
        setState {
            copy(categories = categories, isLoading = false)
        }
        setEffect { MainCategoriesContract.Effect.DataWasLoaded }
    }

}