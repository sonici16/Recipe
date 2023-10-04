package com.example.myreceipt.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myreceipt.model.Row
import com.example.myreceipt.network.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.SingleSubject


class MainViewModel() : ViewModel() {
    private val repository: RecipeRepository = RecipeRepository()

    private val _recipes = MutableLiveData<List<Row>>()
    val recipes: LiveData<List<Row>> get() = _recipes

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    var totalCount = 0

    // CompositeDisposable을 사용하여 비동기 작업 관리
    private val disposables = CompositeDisposable()


    // ViewModel 내부에서 API 호출을 처리하는 함수 추가
    fun fetchRecipes(startIdx: Int, endIdx: Int, category: String) {
        val disposable = repository.fetchRecipes(startIdx, endIdx, category)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                _isLoading.value = true // 로딩 상태를 true로 설정
            }
            .doFinally {
                _isLoading.value = false // 로딩 상태를 false로 설정 (로딩 완료)
            }
            .subscribe({ response ->
                _recipes.value = response.COOKRCP01.row // API 응답을 LiveData에 할당
                totalCount = response.COOKRCP01.total_count.toInt()
            }, { error ->
                // 오류 처리
                _isLoading.value = false // 로딩 상태를 false로 설정 (로딩 완료)
            })

        disposables.add(disposable) // CompositeDisposable에 추가
    }

    fun onScrolledToBottom(startIdx: Int, endIdx: Int, category: String) {
        // 스크롤 API 호출 로직
        fetchRecipes(startIdx, endIdx, category)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear() // ViewModel이 소멸될 때 CompositeDisposable을 정리
    }
}

