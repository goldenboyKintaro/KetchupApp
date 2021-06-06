package com.moblie.ketchupapp.api

import com.moblie.ketchupapp.api.data.SimplePage
import com.moblie.ketchupapp.api.data.VideoPage
import retrofit2.http.Path
import retrofit2.http.Query


interface MockedApi : HQVideoService{
    @co.infinum.retromock.meta.Mock
    @co.infinum.retromock.meta.MockResponse(body = "home.html")
    override suspend fun getPage(@Path("pageNumber") pageNumber: Int): VideoPage

    @co.infinum.retromock.meta.Mock
    @co.infinum.retromock.meta.MockResponse(body = "smith.json")
    override suspend fun search(@Query("q") query: String, @Query("p") pageNumber: Int) : VideoPage

    @co.infinum.retromock.meta.Mock
    @co.infinum.retromock.meta.MockResponse(body = "smith.json")
    override suspend fun top(@Path(value = "pageNumber") pageNumber: Int): VideoPage

    @co.infinum.retromock.meta.Mock
    @co.infinum.retromock.meta.MockResponse(body = "smith.json")
    override suspend fun topWeek(@Path(value = "pageNumber") pageNumber: Int): VideoPage

    @co.infinum.retromock.meta.Mock
    @co.infinum.retromock.meta.MockResponse(body = "smith.json")
    override suspend fun topMonth(@Path(value = "pageNumber") pageNumber: Int): VideoPage

    @co.infinum.retromock.meta.Mock
    @co.infinum.retromock.meta.MockResponse(body = "girls.html")
    override suspend fun getGirls(): SimplePage

    @co.infinum.retromock.meta.Mock
    @co.infinum.retromock.meta.MockResponse(body = "categories.html")
    override suspend fun getCategories(): SimplePage
}

