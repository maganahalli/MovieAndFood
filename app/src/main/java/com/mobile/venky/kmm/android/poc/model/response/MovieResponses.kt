package com.mobile.venky.kmm.android.poc.model.response

import com.google.gson.annotations.SerializedName

/**
 * @author Venky Maganahalli updated on 08/29/2021
 */

data class MoviesDataModel(
    @SerializedName("page")
    val page: Int,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("results")
    val movies: List<Movie>
)

data class Movie(
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdrop_path: String?,
    val budget: Long,
    val genres: List<Genre>,
    @SerializedName("id")
    val id: Long,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("poster_path")
    val poster_path: String?,
    val production_companies: List<ProductionCompanies>?,
    val production_countries: List<ProductionCountry>?,
    val spoken_languages: List<SpokenLanguage>,
    // TODO Can be date
    val release_date: String,
    val revenue: Long? = 0,
    val runtime: Int?,
    val tagline: String,
    val title: String,
    val vote_count: Long,
    val vote_average: Double,
    // TODO Can be enum
    val original_language: String,

    val genre_ids: List<Int>
) {
    companion object {
        val testData = Movie(
            adult = false,
            backdrop_path = null,
            budget = 100L,
            genre_ids = arrayListOf(),
            genres = arrayListOf(),
            id = 12L,
            original_language = "Hindi",
            overview = "Overview of the movie",
            popularity = 0.5,
            poster_path = null,
            production_companies = null,
            production_countries = null,
            release_date = "12-10-2012",
            revenue = 12L,
            runtime = 132,
            spoken_languages = arrayListOf(),
            tagline = "Wait till the end",
            title = "Why wait longer???",
            vote_average = 12.3,
            vote_count = 1321
        )
    }
}

data class Genre(
    val id: Long,
    val name: String
)

data class ProductionCompanies(
    val description: String,
    val headquarters: String,
    val homepage: String?,
    val id: Int,
    val logo_path: String?,
    val name: String,
    val origin_country: String
)

data class ProductionCountry(
    val iso_3166_1: String,
    val name: String
)

data class SpokenLanguage(
    val iso_639_1: String,
    val name: String
)