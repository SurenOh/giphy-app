package com.example.giphyapp.di

import com.example.giphyapp.App
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val applicationModule = module {
    single { androidApplication() as App }
}