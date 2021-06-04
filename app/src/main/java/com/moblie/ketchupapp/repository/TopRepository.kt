package com.moblie.ketchupapp.repository

import androidx.lifecycle.LiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.moblie.ketchupapp.model.VideoModel
import com.moblie.ketchupapp.paging.TopRemoteMediator

interface TopRepository {
    @ExperimentalPagingApi
    fun getVideos(category: TopRemoteMediator.Category): LiveData<PagingData<VideoModel>>
}