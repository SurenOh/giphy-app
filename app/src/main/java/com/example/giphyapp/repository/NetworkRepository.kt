package com.example.giphyapp.repository

import com.example.giphyapp.network.ApiResult
import com.example.giphyapp.network.service.ApiService

class NetworkRepository(private val apiService: ApiService) {
    suspend fun getGiphyData(offset: Int): ApiResult {
        /*return try {
            val data = apiService.getData(
                apiKey = API_KEY,
                query = QUERY,
                limit = LIMIT,
                offset = offset,
                lang = LANG
            )
            if (data.meta.status in 200..299) ApiResult.Success(data) else ApiResult.Error(data.meta.status)
        } catch (e: Exception) {
            ApiResult.Error()
        }*/
        return ApiResult.Success(apiService.getData(
                apiKey = API_KEY,
                query = QUERY,
                limit = LIMIT,
                offset = offset,
                lang = LANG)
        )
    }

    companion object {
        const val API_KEY = "zWitWq5wBT6DreXEeoGnyayOBalyu1bs"
        const val QUERY = "popcorn"
        const val LIMIT = 25
        const val LANG = "en"
    }
}