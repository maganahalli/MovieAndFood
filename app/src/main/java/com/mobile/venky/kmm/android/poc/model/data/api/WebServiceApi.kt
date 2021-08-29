package com.mobile.venky.kmm.android.poc.model.data.api

import com.mobile.venky.kmm.android.poc.model.data.repository.MainCategory
import com.mobile.venky.kmm.android.poc.model.data.repository.availableCategoryList
import com.mobile.venky.kmm.android.poc.model.response.FoodCategoriesResponse
import com.mobile.venky.kmm.android.poc.model.response.MealsResponse
import com.mobile.venky.kmm.android.poc.model.response.Movie
import com.mobile.venky.kmm.android.poc.model.response.MoviesDataModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url
import javax.inject.Inject
import javax.inject.Singleton


/**
 * @author Venky Maganahalli updated on 08/29/2021
 */

@Singleton
class WebServiceApi @Inject constructor(private val service: Service) {


    fun getMainCategoriesList() : List<MainCategory>{
        return service.getMainCategories()
    }
    suspend fun getPopularMovies(): MoviesDataModel {
        return service.getPopularMovies()
    }
    suspend fun getMovie(movieId: Long): Movie =
        service.getMovie(movieId)

    suspend fun getFoodCategories(): FoodCategoriesResponse = service.getFoodCategories()
    suspend fun getMealsByCategory(categoryId: String): MealsResponse =
        service.getMealsByCategory(categoryId)


    interface Service {
        // Movie related
        @GET("$API_MOVIE_BASE_URL/movie/popular?$API_KEY")
        suspend fun getPopularMovies(): MoviesDataModel

        @GET("movie/{id}?$API_KEY")
        suspend fun getMovie(@Path("id") id: Long): Movie

        @GET("search/movie?$API_KEY")
        suspend fun getMovies(@Query("query") query: String): MoviesDataModel


        // Food related
        @GET("categories.php")
        suspend fun getFoodCategories(): FoodCategoriesResponse

        @GET("filter.php")
        suspend fun getMealsByCategory(@Query("c") categoryId: String): MealsResponse


        fun getMainCategories(): List<MainCategory> {
          return availableCategoryList
        }
    }

    companion object {
        const val API_MOVIE_BASE_URL = "https://api.themoviedb.org/3"
        const val API_KEY ="api_key=7652af6f4c54c5b64bd1f568c9efb987"
        const val API_FOOD_BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
    }
}