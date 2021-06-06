package com.moblie.ketchupapp.api

import com.moblie.ketchupapp.api.data.MyDaddyVideo
import retrofit2.http.GET
import retrofit2.http.Path

interface MyDaddyService {

    @GET("/video/{id}")
    suspend fun getVideo(@Path("id") id: String) : MyDaddyVideo

}