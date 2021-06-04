package com.moblie.ketchupapp.repository

import androidx.lifecycle.LiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.moblie.ketchupapp.model.VideoModel

interface SearchRepository {
    @ExperimentalPagingApi
    fun getVideos(query: String): LiveData<PagingData<VideoModel>>
}