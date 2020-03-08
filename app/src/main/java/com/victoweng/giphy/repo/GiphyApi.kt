package com.victoweng.giphy.repo

import com.victoweng.giphy.dataModels.search.GifResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface GiphyApi {

    @GET("v1/gifs/search")
    suspend fun fetchGifs(@QueryMap options: Map<String, String>): GifResponse
}