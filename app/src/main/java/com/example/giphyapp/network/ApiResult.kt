package com.example.giphyapp.network

sealed class ApiResult {
    class Success(val response: GiphyResponse) : ApiResult()
    data object Error : ApiResult()
}
