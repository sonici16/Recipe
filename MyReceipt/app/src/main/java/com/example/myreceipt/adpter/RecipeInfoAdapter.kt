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
import com.example.myreceipt.adpter.holder.RecipeInfoHolder
import com.example.myreceipt.databinding.HolderMyrecipeBinding
import com.example.myreceipt.databinding.HolderRecipeInfoBinding
import com.example.myreceipt.model.ManualData
import com.example.myreceipt.model.Row

class RecipeInfoAdapter(val mContext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var manualDataList: ArrayList<ManualData?> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  RecyclerView.ViewHolder {
        return RecipeInfoHolder(HolderRecipeInfoBinding.inflate(LayoutInflater.from(mContext), parent, false),mContext)
    }

    override fun getItemCount(): Int {
        return manualDataList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        manualDataList[position]?.let { (holder as RecipeInfoHolder).setData(it) }
    }

    fun addList(row: Row) {
        for (i in 1..20) {
            if(i<10){
                val fieldName = "MANUAL0$i"
                val field = row.javaClass.getDeclaredField(fieldName)
                val fieldNameImg = "MANUAL_IMG0$i"
                val fieldImg = row.javaClass.getDeclaredField(fieldNameImg)
                field.isAccessible = true // private 필드에 접근 가능하도록 설정
                fieldImg.isAccessible = true // private 필드에 접근 가능하도록 설정
                val manual = field.get(row) as String
                val manualImage = fieldImg.get(row) as String

                //val manual = row.javaClass.getDeclaredField("MANUAL0$i").get(row) as String
                //val manualImage = row.javaClass.getDeclaredField("MANUAL_IMG0$i").get(row) as String
                if (manual.isNotBlank() && manualImage.isNotBlank()) {
                    val manualData = ManualData(manual, manualImage)
                    manualDataList.add(manualData)
                }
            } else {
                val fieldName = "MANUAL$i"
                val field = row.javaClass.getDeclaredField(fieldName)
                val fieldNameImg = "MANUAL_IMG$i"
                val fieldImg = row.javaClass.getDeclaredField(fieldNameImg)
                field.isAccessible = true // private 필드에 접근 가능하도록 설정
                fieldImg.isAccessible = true // private 필드에 접근 가능하도록 설정
                val manual = field.get(row) as String
                val manualImage = fieldImg.get(row) as String

                if (manual.isNotBlank() && manualImage.isNotBlank()) {
                    val manualData = ManualData(manual, manualImage)
                    manualDataList.add(manualData)
                }
            }
        }
        notifyDataSetChanged()
    }
}
