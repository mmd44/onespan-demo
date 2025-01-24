package com.onespan.android.interview.features.cats.api

import retrofit2.http.GET
import retrofit2.http.Query

interface CatService {

    @GET("breeds")
    suspend fun getBreeds(
        @Query("page") page: Int
    ): ApiBreedResponse
}
