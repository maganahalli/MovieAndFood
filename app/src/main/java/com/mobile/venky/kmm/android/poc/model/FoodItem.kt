package com.mobile.venky.kmm.android.poc.model

/**
 * @author Venky Maganahalli updated on 08/29/2021
 */
data class FoodItem(
    val id: String,
    val name: String,
    val thumbnailUrl: String,
    val description: String = ""
)