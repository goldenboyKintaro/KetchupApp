package com.moblie.ketchupapp.api

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HQVideoService {
    @GET("/hdporn/{pageNumber}")
    suspend fun getPage(@Path("pageNumber") pageNumber: Int): VideoPage

    @GET("/")
    suspend fun search(@Query("q") query: String, @Query("p") pageNumber: Int = 1) : VideoPage


    @GET("/top/{pageNumber}")
    suspend fun top(@Path("pageNumber") pageNumber: Int): VideoPage

    @GET("/top/week/{pageNumber}")
    suspend fun topWeek(@Path("pageNumber") pageNumber: Int): VideoPage


    @GET("/top/month/{pageNumber}")
    suspend fun topMonth(@Path("pageNumber") pageNumber: Int): VideoPage

    @GET("/girls")
    suspend fun getGirls(): SimplePage

    @GET("/categories")
    suspend fun getCategories(): SimplePage
}