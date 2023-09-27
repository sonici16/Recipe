package com.example.myreceipt.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.myreceipt.R
import com.example.myreceipt.adpter.PagerFragmentStateAdapter
import com.example.myreceipt.adpter.RecipeAdapter
import com.example.myreceipt.databinding.FragmentRecipeBinding
import com.example.myreceipt.decoration.ItemDecoration
import com.example.myreceipt.model.Row
import com.example.myreceipt.viewmodel.MainViewModel

class RecipeFragment : Fragment() {
    private val viewModel = MainViewModel()
    var contentType = 0
    var startIdx = 1
    var endIdx = 100
    private lateinit var mAdapter: RecipeAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRecipeBinding.inflate(inflater, container, false)

        contentType = arguments?.getInt("MENUTYPE", 0)!!
        mAdapter = RecipeAdapter(requireContext())

        binding.fRvRecipe.apply {
            adapter = mAdapter
            when(contentType) {
                1 -> {
                    layoutManager = StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL)
                    addItemDecoration(ItemDecoration(2,10))
                }
                2 -> {
                    layoutManager = StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL)
                    addItemDecoration(ItemDecoration(2,10))

                }
                3 -> {
                    layoutManager = StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL)
                    addItemDecoration(ItemDecoration(2,10))

                }
               4 -> {
                   layoutManager = StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL)
                   addItemDecoration(ItemDecoration(2,10))
                }
            }
        }

        binding.fRvRecipe.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (isLastItemVisible(recyclerView)) {
                    // 스크롤 시 마지막 아이템이 보일 때 ViewModel의 onScrolledToBottom() 호출
                    startIdx = endIdx +1
                    endIdx += 100
                    viewModel.onScrolledToBottom(startIdx,endIdx, when(contentType){1-> "밥" 2-> "반찬" 3-> "국" 4-> "후식" else -> "밥" })
                }
            }
        })

        viewModel.fetchRecipes(startIdx,endIdx,
            when(contentType){
                1-> "밥"
                2-> "반찬"
                3-> "국"
                4-> "후식"
                else -> "밥"
            }
        )


        viewModel.getRecipes().observe(viewLifecycleOwner, Observer { data ->
            if(mAdapter.itemCount != 0 && data != null) {
                val currentData = mAdapter.currentList.toMutableList()
                currentData.addAll(data)
                mAdapter.submitList(currentData)
            }
            else if( data != null)
                mAdapter.submitList(data)
        })

        return binding.root
    }

    private fun isLastItemVisible(recyclerView: RecyclerView): Boolean {
        val layoutManager = recyclerView.layoutManager as? StaggeredGridLayoutManager
        val lastVisibleItemPositions = layoutManager?.findLastCompletelyVisibleItemPositions(null)
        val itemCount = recyclerView.adapter?.itemCount ?: 0

        return lastVisibleItemPositions?.any { it == itemCount - 1 } == true
    }
}