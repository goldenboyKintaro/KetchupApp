package com.moblie.ketchupapp.api.data

import androidx.annotation.Keep
import pl.droidsonroids.jspoon.annotation.Selector


class PageItem @Keep constructor(){

     @Keep @Selector("section > h3 > a") lateinit var title: String
     @Keep @Selector("section > a", attr = "href" ) lateinit var url: String
     @Keep @Selector("section > a > img", attr = "src") lateinit var thumbnail: String

    fun isInitialized(): Boolean {
        return this::title.isInitialized && this::url.isInitialized && this::thumbnail.isInitialized
    }
}