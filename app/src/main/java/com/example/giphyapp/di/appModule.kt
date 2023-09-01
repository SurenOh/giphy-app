package com.example.giphyapp.di

import com.example.giphyapp.App
import com.example.giphyapp.datastorage.NetworkDataStore
import com.example.giphyapp.mapper.GiphyMapper
import com.example.giphyapp.repository.NetworkRepository
import com.example.giphyapp.network.service.ApiService
import com.example.giphyapp.ui.home.HomeViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://api.giphy.com/v1/gifs/"

val applicationModule = module {
    single { androidApplication() as App }

    single {
        val client = OkHttpClient.Builder()
            .connectTimeout(25, TimeUnit.SECONDS)
            .readTimeout(25, TimeUnit.SECONDS)
            .writeTimeout(25, TimeUnit.SECONDS)
            .build()
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provideApi(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

    factory { provideApi(get()) }

    single { GiphyMapper() }

    single { NetworkRepository(get()) }

    single { NetworkDataStore(get(), get()) }

    viewModel { HomeViewModel(get()) }
}