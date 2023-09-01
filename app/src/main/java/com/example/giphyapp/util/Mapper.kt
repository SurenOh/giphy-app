package com.example.giphyapp.util

interface Mapper<Dto, Model> {
    fun getModelFromDto(dto: Dto): Model

    fun getListModelsFromDto(list: List<Dto>) = list.map { getModelFromDto(it) }
}