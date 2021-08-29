package com.mobile.venky.kmm.android.poc.model

/**
 * @author Venky Maganahalli updated on 08/29/2021
 */

data class MovieItem(
    val id: String,
    val name: String,
    var thumbnailUrl: String?,
    val description: String = ""
){
    fun withThumbnailUrl(imageUrl: String): MovieItem {
        if(thumbnailUrl?.contains("w500") == true){
            return this
        }
        this.thumbnailUrl = "https://image.tmdb.org/t/p/w500$imageUrl"
        return this
    }
}