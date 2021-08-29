package com.mobile.venky.kmm.android.poc.model.response

import com.google.gson.annotations.SerializedName

/**
 * @author Venky Maganahalli updated on 08/29/2021
 */

data class FoodCategoriesResponse(val categories: List<FoodCategoryResponse>)
data class MealsResponse(val meals: List<MealResponse>)


data class FoodCategoryResponse(
    @SerializedName("idCategory") val id: String,
    @SerializedName("strCategory") val name: String,
    @SerializedName("strCategoryThumb") val thumbnailUrl: String,
    @SerializedName("strCategoryDescription") val description: String = ""
)

data class MealResponse(
    @SerializedName("idMeal") val id: String,
    @SerializedName("strMeal") val name: String,
    @SerializedName("strMealThumb") val thumbnailUrl: String,
)