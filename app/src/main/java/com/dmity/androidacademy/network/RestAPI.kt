package com.dmity.androidacademy.network

import com.dmity.androidacademy.BuildConfig
import com.dmity.androidacademy.features.newsList.model.dto.NewsResponseDTO
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RestAPI {

    private const val BASE_URL = "https://api.nytimes.com/svc/topstories/v2/"
    private const val API_KEY = "567mSg0NfYj5GwXBOaYtovgZef0tXHCs"
    private const val TIMEOUT_IN_SECONDS = 2L

    private val nyTimesService: NyTimesService

    init {
        val httpClient = buildOkHttpClient()
        val retrofit = buildRetrofitClient(httpClient)
        nyTimesService = retrofit.create(NyTimesService::class.java)
    }

    fun getNews(category: String): Single<NewsResponseDTO> {
        return nyTimesService.getNews(category)
    }

    private fun buildRetrofitClient(client: OkHttpClient): Retrofit {
       return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }


    private fun buildOkHttpClient(): OkHttpClient {
        val networkLogInterceptor = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG) {
            networkLogInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
                .addInterceptor(ApiKeyInterceptor(API_KEY))
                .addInterceptor(networkLogInterceptor)
                .connectTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                .build()
    }

}