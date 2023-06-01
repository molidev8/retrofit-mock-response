package com.molidev8.retrofit_mock_response.koin

import android.content.res.AssetManager
import com.molidev8.retrofit_mock_response.retrofit.JsonReader
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val mockModule = module {
    single<AssetManager> { androidApplication().assets }
    single<JsonReader> { JsonReader(get()) }
}