package com.example.myreceipt.network

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myreceipt.model.Root
import com.example.myreceipt.model.Row
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RecipeRepository() {
    private val apiService: MyRecipeService = MyRecipeService
    private val _recipes = MutableLiveData<List<Row>>()
    val recipes: LiveData<List<Row>> get() = _recipes

    // API에서 레시피 데이터를 가져오는 함수
    fun fetchRecipes(startIdx: Int, endIdx:Int,category:String) {
        apiService.api.getRecipes(startIdx,endIdx,category).enqueue(object : Callback<Root> {
            override fun onResponse(call: Call<Root>, response: Response<Root>) {
                if (response.isSuccessful) {
                    val recipeList = response.body()?.COOKRCP01?.row
                    _recipes.value = recipeList
                } else {
                    // API 호출이 실패한 경우에 대한 처리를 여기에 추가

                }
            }

            override fun onFailure(call: Call<Root>, t: Throwable) {
                // 네트워크 오류나 예외 발생 시에 대한 처리를 여기에 추가

            }
        })
    }
}