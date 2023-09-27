package com.example.myreceipt.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MyRecipeService {
    private const val BASE_URL = "https://openapi.foodsafetykorea.go.kr/api/apikey!!!!/COOKRCP01/json/"

    val gson : Gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(CoroutineCallAdapterFactory()) // CoroutineCallAdapterFactory 추가
        .build()


    val api: MyRecipeApi = retrofit.create(MyRecipeApi::class.java)
}


