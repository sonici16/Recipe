package com.example.myreceipt.ui

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import com.example.myreceipt.databinding.DialogSimpleProgressBinding
import com.example.myreceipt.fragment.RecipeFragment

/**
 * Created by user on 2021-11-03.
 */

class SimpleProgressDialog(context: Context) : Dialog(context) {

    private val binding = DialogSimpleProgressBinding.inflate(LayoutInflater.from(context))

    override fun onCreate(savedInstanceState: Bundle?) {
        window!!.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        setCancelable(false)
        setContentView(binding.root)
    }

    override fun show() {
        try {
            super.show()
            if (!binding.dialogProgresswheel.isSpinning) {
                binding.dialogProgresswheel.spin()
            }
        } catch (e: Exception) {
        }
    }

    override fun hide() {
        try {
            super.hide()
            if (binding.dialogProgresswheel.isSpinning) {
                binding.dialogProgresswheel.stopSpinning()
            }
        } catch (e: Exception) {
        }
    }

    override fun dismiss() {
        try {
            super.dismiss()
            if (binding.dialogProgresswheel.isSpinning) {
                binding.dialogProgresswheel.stopSpinning()
            }
        } catch (e: Exception) {
        }
    }
}
