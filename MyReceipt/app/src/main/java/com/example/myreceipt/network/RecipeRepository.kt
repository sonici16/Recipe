package com.example.myreceipt.network

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myreceipt.model.Root
import com.example.myreceipt.model.Row
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RecipeRepository() {
    private val apiService: MyRecipeService = MyRecipeService

    // API에서 레시피 데이터를 가져오는 함수
    fun fetchRecipes(startIdx: Int, endIdx: Int, category: String): Single<Root> {
        return Single.defer {
            apiService.api.getRecipes(startIdx, endIdx, category)
        }
    }
}