package com.example.giphyapp.network.service

import com.example.giphyapp.network.GiphyResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("search")
    suspend fun getData(
        @Query("api_key") apiKey: String,
        @Query("q") query: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("lang") lang: String,
    ): GiphyResponse
}