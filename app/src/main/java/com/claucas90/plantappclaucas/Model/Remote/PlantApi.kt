package com.claucas90.plantappclaucas.Model.Remote

import com.claucas90.plantappclaucas.Model.Remote.FromNet.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PlantAPI {
    @GET("plantas")
    suspend fun fetchPlantList(): Response<List<Plant>>

    @GET("plantas/{id}")
    suspend fun fetchPlantDetail(@Path("id")id: String): Response<PlantDetail>
}