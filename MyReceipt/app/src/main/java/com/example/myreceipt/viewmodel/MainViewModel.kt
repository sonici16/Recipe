package com.example.myreceipt.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myreceipt.model.Row
import com.example.myreceipt.network.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainViewModel() : ViewModel() {
    private val repository: RecipeRepository = RecipeRepository()

    fun getRecipes(): LiveData<List<Row>> {
        return repository.recipes
    }

    // ViewModel 내부에서 API 호출을 처리하는 함수 추가
    fun fetchRecipes(startIdx: Int, endIdx:Int,category:String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchRecipes(startIdx,endIdx,category)
        }
    }

    fun onScrolledToBottom(startIdx: Int, endIdx:Int,category:String) {
        // 스크롤 API 호출 로직
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchRecipes(startIdx,endIdx,category)
        }
    }
}

