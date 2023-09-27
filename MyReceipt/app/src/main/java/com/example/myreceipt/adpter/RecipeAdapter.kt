package com.example.myreceipt.adpter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myreceipt.activity.RecipeInfoActivity
import com.example.myreceipt.adpter.holder.MyRecipeHolder
import com.example.myreceipt.databinding.HolderMyrecipeBinding
import com.example.myreceipt.model.Row

class RecipeAdapter(val mContext: Context) : ListAdapter<Row, RecyclerView.ViewHolder>(MessageDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  RecyclerView.ViewHolder {
        return MyRecipeHolder(HolderMyrecipeBinding.inflate(LayoutInflater.from(mContext), parent, false),mContext)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MyRecipeHolder).apply{
            setData(getItem(position))
            itemView.setOnClickListener {
                mContext.startActivity(Intent(mContext, RecipeInfoActivity::class.java).apply {
                    putExtra("recipeItem", getItem(position))
                })
            }
        }

    }


    class MessageDiffCallback :
        DiffUtil.ItemCallback<Row>() {
        override fun areItemsTheSame(
            oldItem: Row,
            newItem: Row
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Row,
            newItem: Row
        ): Boolean {
            return oldItem.ATT_FILE_NO_MAIN  == newItem.ATT_FILE_NO_MAIN
        }
    }
}
