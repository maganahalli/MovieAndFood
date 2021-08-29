package com.mobile.venky.kmm.android.poc.ui.movie.category

import com.mobile.venky.kmm.android.poc.base.ViewEvent
import com.mobile.venky.kmm.android.poc.base.ViewSideEffect
import com.mobile.venky.kmm.android.poc.base.ViewState
import com.mobile.venky.kmm.android.poc.model.FoodItem
import com.mobile.venky.kmm.android.poc.model.MovieItem
/**
 * @author Venky Maganahalli updated on 08/29/2021
 */

class MovieCategoryDetailsContract {
    sealed class Event : ViewEvent

    data class State(
        val category: MovieItem?,
        val categoryMovieItems: List<MovieItem>
    ) : ViewState

    sealed class Effect : ViewSideEffect
}