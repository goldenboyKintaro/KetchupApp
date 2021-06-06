package com.moblie.ketchupapp.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MyDaddyStatusService {

    @FormUrlEncoded
    @POST("/e.php")
    suspend fun grantAccess(@Field("v") myDaddyId: String, @Field("r") clientIp: String) : Response<ResponseBody?>
}