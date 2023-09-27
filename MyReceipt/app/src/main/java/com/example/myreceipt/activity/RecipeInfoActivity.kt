package com.example.myreceipt.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.myreceipt.adpter.PagerFragmentStateAdapter
import com.example.myreceipt.adpter.RecipeInfoAdapter
import com.example.myreceipt.databinding.ActivityRecipeInfoBinding
import com.example.myreceipt.model.Row
import com.example.myreceipt.viewmodel.MainViewModel

class RecipeInfoActivity : AppCompatActivity() {

    private val viewModel = MainViewModel()
    private lateinit var mAdapter: RecipeInfoAdapter
    var recipeItem: Row? = null

    private val binding by lazy{
        ActivityRecipeInfoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        recipeItem = intent.getParcelableExtra("recipeItem")

        mAdapter = RecipeInfoAdapter(this)

        binding.aVpRecipeinfo.apply {
            adapter = mAdapter
            if (recipeItem != null) {
                (adapter as RecipeInfoAdapter).addList(recipeItem!!)
            }
        }


        binding.aTvRecipeinfoTitle.text = recipeItem!!.RCP_NM

        binding.aTvEngRecipeinfo.text =  "열량: ${recipeItem!!.INFO_ENG}"
        binding.aTvCarRecipeinfo.text =  "탄수화물: ${recipeItem!!.INFO_CAR}"
        binding.aTvFatRecipeinfo.text = "지방: ${recipeItem!!.INFO_FAT}"
        binding.aTvNaRecipeinfo.text = "나트륨: ${recipeItem!!.INFO_NA}"
        binding.aTvProRecipeinfo.text = "단백질: ${recipeItem!!.INFO_PRO}"

    }
}