package com.example.giphyapp.datastorage

import com.example.giphyapp.mapper.GiphyMapper
import com.example.giphyapp.model.GiphyModel
import com.example.giphyapp.network.ApiResult
import com.example.giphyapp.repository.NetworkRepository

class NetworkDataStore(private val repository: NetworkRepository, private val mapper: GiphyMapper) {

    suspend fun getUserData(offset: Int): List<GiphyModel>? {
        return when(val apiResponse = repository.getGiphyData(offset)) {
            is ApiResult.Success -> mapper.getListModelsFromDto(apiResponse.response.data)
            is ApiResult.Error -> null
        }
    }

}