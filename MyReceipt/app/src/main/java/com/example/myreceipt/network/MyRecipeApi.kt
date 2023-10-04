package com.example.myreceipt.network

import com.example.myreceipt.model.Root
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*

interface MyRecipeApi {
    @GET("{startIdx}/{endIdx}/RCP_PAT2={category}")
    fun getRecipes(
        @Path("startIdx") startIdx: Int,
        @Path("endIdx") endIdx: Int,
        @Path("category") category: String
    ): Single<Root>
}