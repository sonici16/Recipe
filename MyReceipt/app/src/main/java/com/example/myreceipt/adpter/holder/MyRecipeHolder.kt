package com.example.myreceipt.adpter.holder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myreceipt.databinding.HolderMyrecipeBinding
import com.example.myreceipt.model.Row

class MyRecipeHolder (private val binding: HolderMyrecipeBinding,var mContext: Context) :  RecyclerView.ViewHolder(binding.root) {

    fun setData(pData: Row) {
        pData?.let { data ->
            Glide.with(mContext)
                .load(data.ATT_FILE_NO_MAIN.replace("http","https"))
                .into(binding.hIvThumb)
            binding.hTvName.text= data.RCP_NM
        }
    }

}
