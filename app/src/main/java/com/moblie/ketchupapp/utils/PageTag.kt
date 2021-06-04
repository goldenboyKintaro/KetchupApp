package com.moblie.ketchupapp.utils

import androidx.paging.ExperimentalPagingApi
import com.moblie.ketchupapp.paging.TopRemoteMediator

object PageTag {
    const val HOME = "home"

    @JvmStatic fun SEARCH(query : String) = "search/$query"

    @ExperimentalPagingApi
    fun TOP(category: TopRemoteMediator.Category) = "top/$category"
}