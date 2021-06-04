package com.moblie.ketchupapp.repository

import androidx.lifecycle.LiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.moblie.ketchupapp.model.VideoModel

interface HomeRepository {
    @ExperimentalPagingApi
    fun getVideos(): LiveData<PagingData<VideoModel>>
}