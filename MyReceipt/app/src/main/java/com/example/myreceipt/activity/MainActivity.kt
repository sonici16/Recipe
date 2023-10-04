package com.example.myreceipt.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myreceipt.adpter.PagerFragmentStateAdapter
import com.example.myreceipt.databinding.ActivityMainBinding
import com.example.myreceipt.fragment.RecipeFragment
import com.example.myreceipt.viewmodel.MainViewModel
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {

    private lateinit var mAdapter: PagerFragmentStateAdapter
    private lateinit var receiptViewModel: MainViewModel

    private val tabTextList = arrayListOf("밥", "반찬", "국/찌개", "후식")

    private val firstFragment = RecipeFragment()
    private val secondFragment = RecipeFragment()
    private val thirdragment = RecipeFragment()
    private val fourthFragment = RecipeFragment()

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // RecipeViewModel 초기화
        receiptViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mAdapter = PagerFragmentStateAdapter(this)

        mAdapter.apply {
            addFragment(firstFragment.apply {
                val pEditBundle = Bundle()
                pEditBundle.putInt("MENUTYPE", 1)
                arguments = pEditBundle
            })
            addFragment(secondFragment.apply {
                val pServiceBundle = Bundle()
                pServiceBundle.putInt("MENUTYPE", 2)
                arguments = pServiceBundle
            })
            addFragment(thirdragment.apply {
                val pEditBundle = Bundle()
                pEditBundle.putInt("MENUTYPE", 3)
                arguments = pEditBundle
            })
            addFragment(fourthFragment.apply {
                val pServiceBundle = Bundle()
                pServiceBundle.putInt("MENUTYPE", 4)
                arguments = pServiceBundle
            })
        }

        binding.aMainVp.apply {
            adapter = mAdapter
            offscreenPageLimit = 1
            isUserInputEnabled = true
            setCurrentItem(0, false)
        }


        TabLayoutMediator(binding.aMainTl, binding.aMainVp) { tab, position ->
            tab.text = tabTextList[position]
        }.attach()
    }
}


