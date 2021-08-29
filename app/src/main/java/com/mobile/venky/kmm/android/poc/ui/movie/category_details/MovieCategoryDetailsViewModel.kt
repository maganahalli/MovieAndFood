package com.mobile.venky.kmm.android.poc.ui.movie.category_details

import androidx.lifecycle.SavedStateHandle

import androidx.lifecycle.viewModelScope

import com.mobile.venky.kmm.android.poc.base.BaseViewModel
import com.mobile.venky.kmm.android.poc.model.MovieItem
import com.mobile.venky.kmm.android.poc.model.data.repository.MovieCategoriesRepository
import com.mobile.venky.kmm.android.poc.ui.movie.category.MovieCategoryDetailsContract
import com.mobile.venky.kmm.android.poc.ui.movie.landing.NavigationKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * @author Venky Maganahalli updated on 08/29/2021
 */

@HiltViewModel
class MovieCategoryDetailsViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle,
    private val repository: MovieCategoriesRepository
) : BaseViewModel<
        MovieCategoryDetailsContract.Event,
        MovieCategoryDetailsContract.State,
        MovieCategoryDetailsContract.Effect>() {

    init {
        viewModelScope.launch {
            val categoryId = stateHandle.get<String>(NavigationKeys.Arg.MOVIE_CATEGORY_ID)
                ?: throw IllegalStateException("No MovieId was passed to destination.")
            val categories = repository.getMovieCategories()
            val category = categories.first { it.id == categoryId }
            setState { copy(category = category) }

            val movieItem = repository.getMovieById(categoryId)
            val items  = mutableListOf(emptyList<MovieItem>())
                items.add(listOf(movieItem))

            setState { copy(categoryMovieItems = items.last()) }
        }
    }

    override fun setInitialState() = MovieCategoryDetailsContract.State(null,  listOf())

    override fun handleEvents(event: MovieCategoryDetailsContract.Event) {}

}
