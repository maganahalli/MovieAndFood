package com.mobile.venky.kmm.android.poc.model.data.repository

import com.mobile.venky.kmm.android.poc.model.FoodItem
import com.mobile.venky.kmm.android.poc.model.data.api.WebServiceApi
import com.mobile.venky.kmm.android.poc.model.response.FoodCategoriesResponse
import com.mobile.venky.kmm.android.poc.model.response.MealsResponse
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Venky Maganahalli updated on 08/29/2021
 */
@Singleton
class FoodMenuRepository @Inject constructor(private val foodMenuApi: WebServiceApi) {

    private var cachedCategories: List<FoodItem>? = null

    suspend fun getFoodCategories(): List<FoodItem> {
        var cachedCategories = cachedCategories
        if (cachedCategories == null) {
            cachedCategories = foodMenuApi.getFoodCategories().mapCategoriesToItems()
            this.cachedCategories = cachedCategories
        }
        return cachedCategories
    }

    suspend fun getMealsByCategory(categoryId: String): List<FoodItem> {
        val categoryName = getFoodCategories().first { it.id == categoryId }.name
        return foodMenuApi.getMealsByCategory(categoryName).mapMealsToItems()
    }


    private fun FoodCategoriesResponse.mapCategoriesToItems(): List<FoodItem> {
        return this.categories.map { category ->
            FoodItem(
                id = category.id,
                name = category.name,
                description = category.description,
                thumbnailUrl = category.thumbnailUrl
            )
        }
    }

    private fun MealsResponse.mapMealsToItems(): List<FoodItem> {
        return this.meals.map { category ->
            FoodItem(
                id = category.id,
                name = category.name,
                thumbnailUrl = category.thumbnailUrl
            )
        }
    }

}