package com.mobile.venky.kmm.android.poc.model.data.repository

/**
 * @author Venky Maganahalli updated on 08/29/2021
 */

data class MainCategory constructor(
    val id: Int,
    val name: String,
    val pictureUrl: String,
    val description: String
)

val availableCategoryList = arrayListOf(
    MainCategory(
        id = 0,
        name = "Food",
        "https://www.themealdb.com/images/category/beef.png",
        description = "Popular Food "
    ),
    MainCategory(
        id = 1,
        name = "Movie",
        "https://image.tmdb.org/t/p/w500/7WJjFviFBffEJvkAms4uWwbcVUk.jpg",
        description = " Popular Movie"
    )
)