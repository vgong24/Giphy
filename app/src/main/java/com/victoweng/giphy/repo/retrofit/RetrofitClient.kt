package com.victoweng.giphy.repo.retrofit

import android.content.Context
import com.victoweng.giphy.R
import com.victoweng.giphy.repo.GiphyApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient(val context: Context) {

    private val BASE_URL = "https://api.giphy.com/"
    private val API_KEY_ID = "api_key"

    val webService: Retrofit by lazy {

        val httpClient = OkHttpClient.Builder()
        httpClient.networkInterceptors().add(Interceptor {
            val original = it.request()
            val originalUrl = original.url()
            val url =
                originalUrl.newBuilder().addQueryParameter(API_KEY_ID, context.getString(R.string.api_key)).build()
            val requestBuilder = original.newBuilder().url(url)
            it.proceed(requestBuilder.build())
        })

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun giphyApi(): GiphyApi {
        return webService.create(GiphyApi::class.java)
    }
}