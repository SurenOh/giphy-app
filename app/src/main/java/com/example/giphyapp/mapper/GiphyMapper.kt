package com.example.giphyapp.mapper

import com.example.giphyapp.model.GiphyModel
import com.example.giphyapp.network.dto.DataDto
import com.example.giphyapp.util.Mapper

class GiphyMapper : Mapper<DataDto, GiphyModel> {
    override fun getModelFromDto(dto: DataDto) = GiphyModel (
        userName = dto.username,
        title = dto.title,
        gifUrl = dto.images.original.url,
        date = dto.importDatetime
    )
}