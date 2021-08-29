package com.mobile.venky.kmm.android.poc.ui.movie.categories

import androidx.lifecycle.viewModelScope
import com.mobile.venky.kmm.android.poc.base.BaseViewModel
import com.mobile.venky.kmm.android.poc.model.data.repository.FoodMenuRepository
import com.mobile.venky.kmm.android.poc.model.data.repository.MovieCategoriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Venky Maganahalli updated on 08/29/2021
 */
@HiltViewModel
class MovieCategoriesViewModel @Inject constructor(private val repository: MovieCategoriesRepository) :
    BaseViewModel<MovieCategoriesContract.Event, MovieCategoriesContract.State,MovieCategoriesContract.Effect>() {

    init {
        viewModelScope.launch { geMovieCategories() }
    }

    override fun setInitialState() =
        MovieCategoriesContract.State(categories = listOf(), isLoading = true)

    override fun handleEvents(event: MovieCategoriesContract.Event) {
        when (event) {
            is MovieCategoriesContract.Event.CategorySelection -> {
                setEffect { MovieCategoriesContract.Effect.Navigation.ToCategoryDetails(event.categoryName) }
            }
        }
    }

    private suspend fun geMovieCategories() {
        val categories = repository.getMovieCategories()
        setState {
            copy(categories = categories, isLoading = false)
        }
        setEffect { MovieCategoriesContract.Effect.DataWasLoaded }
    }

}
