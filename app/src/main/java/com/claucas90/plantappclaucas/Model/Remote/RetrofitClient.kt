package com.claucas90.plantappclaucas.Model.Remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        private const val BASE_URL = "https://my-json-server.typicode.com/mauricioponce/TDApi/"

        lateinit var retrofitInstance: Retrofit

        fun retrofitInstance(): PlantAPI {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(PlantAPI::class.java)
        }
    }
}