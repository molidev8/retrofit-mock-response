package com.molidev8.retrofit_mock_response.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Headers

interface MockApiService {

    @GET("articles")
    @Headers("$MOCK_RESPONSE_HEADER: getArticles.json")
    suspend fun getArticles(): List<String>
}

private const val BASE_URL = "https://molidevwrites.com/"
private val interceptor: FakeResponseInterceptor = FakeResponseInterceptor()
private val client: OkHttpClient = OkHttpClient.Builder().apply {
    this.addInterceptor(interceptor)
}.build()
private val retrofit =
    Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
        .client(client).build()

object MockApi {
    val retrofitService: MockApiService by lazy {
        retrofit.create<MockApiService>()
    }
}