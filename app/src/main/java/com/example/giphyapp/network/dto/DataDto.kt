package com.example.giphyapp.network.dto

import com.google.gson.annotations.SerializedName

data class DataDto(
    val username: String,
    val title: String,
    @SerializedName("import_datetime")
    val importDatetime: String,
    val images: ImagesDto
)
