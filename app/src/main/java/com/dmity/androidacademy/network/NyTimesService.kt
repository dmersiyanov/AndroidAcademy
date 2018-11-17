package com.dmity.androidacademy.network

import com.dmity.androidacademy.features.newsList.model.dto.NewsResponseDTO
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface NyTimesService {

    @GET("{category}.json")
    fun getNews(@Path("category") category: String): Single<NewsResponseDTO>
}