package com.example.myreceipt.adpter.holder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myreceipt.databinding.HolderMyrecipeBinding
import com.example.myreceipt.databinding.HolderRecipeInfoBinding
import com.example.myreceipt.model.ManualData
import com.example.myreceipt.model.Row

class RecipeInfoHolder (private val binding: HolderRecipeInfoBinding, var mContext: Context) :  RecyclerView.ViewHolder(binding.root) {

    fun setData(pData: ManualData) {
        pData?.let { data ->
            Glide.with(mContext)
                .load(data.manualImage.replace("http","https"))
                .into(binding.hIvRecipeinfo)
            binding.hTvRecipeinfo.text= data.manual
        }
    }

}
