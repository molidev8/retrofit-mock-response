package com.molidev8.retrofit_mock_response

import android.app.Application
import com.molidev8.retrofit_mock_response.koin.mockModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MockApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MockApplication)
            modules(mockModule)
        }
    }
}