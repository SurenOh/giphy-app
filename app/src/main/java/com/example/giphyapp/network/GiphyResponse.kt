package com.example.giphyapp.network

import com.example.giphyapp.network.dto.DataDto
import com.example.giphyapp.network.dto.Meta
import com.example.giphyapp.network.dto.Pagination

data class GiphyResponse (
    val data: List<DataDto>,
    val pagination: Pagination,
    val meta: Meta
)