package com.moblie.ketchupapp.api

import androidx.room.Entity
import androidx.room.PrimaryKey
import pl.droidsonroids.jspoon.annotation.Selector

class PageItem {

    @Selector("section > h3 > a") lateinit var title: String
    @Selector("section > a", attr = "href" ) lateinit var url: String
    @Selector("section > a > img", attr = "src") lateinit var thumbnail: String

    fun isInitialized(): Boolean {
        return this::title.isInitialized && this::url.isInitialized && this::thumbnail.isInitialized
    }
}