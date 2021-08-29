package com.mobile.venky.kmm.android.poc.model.data.repository


import com.mobile.venky.kmm.android.poc.model.data.api.WebServiceApi
import javax.inject.Inject
import javax.inject.Singleton


/**
 * @author Venky Maganahalli updated on 08/29/2021
 */

@Singleton
class MainCategoriesRepository @Inject constructor(private val service: WebServiceApi) {


    fun getMainCategories(): List<MainCategory> {
        return service.getMainCategoriesList()
    }

}