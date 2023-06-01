package com.molidev8.retrofit_mock_response.retrofit

import com.molidev8.retrofit_mock_response.BuildConfig
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.koin.java.KoinJavaComponent.inject

const val MOCK_RESPONSE_HEADER = "MOCK_RESPONSE"

const val SUCCESS_CODE = 200

class FakeResponseInterceptor : Interceptor {

    private val assetReader: JsonReader by inject(JsonReader::class.java)

    override fun intercept(chain: Interceptor.Chain): Response {
        return if (BuildConfig.DEBUG) {
            handleMockResponse(chain)
        } else {
            chain.proceed(
                chain.request()
                    .newBuilder()
                    .removeHeader(MOCK_RESPONSE_HEADER)
                    .build()
            )
        }
    }

    private fun handleMockResponse(chain: Interceptor.Chain): Response {
        val headers = chain.request().headers
        val responseString = assetReader.getJsonAsString(headers[MOCK_RESPONSE_HEADER])

        return chain.proceed(chain.request())
            .newBuilder()
            .code(SUCCESS_CODE)
            .message(responseString)
            .body(
                responseString.toByteArray().toResponseBody("application/json".toMediaTypeOrNull())
            )
            .addHeader("content-type", "application/json")
            .build()
    }
}
