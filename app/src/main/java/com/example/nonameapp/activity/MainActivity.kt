package com.example.nonameapp.activity

import android.os.Bundle
import android.widget.ImageView
import com.example.nonameapp.R
import com.example.nonameapp.base.BaseActivity
import com.example.nonameapp.databinding.ActivityMainBinding
import com.example.nonameapp.fragment.HomeFragment
import com.example.nonameapp.ui.TestFragment

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleEvent()
        showDefaultFragment()
    }

    private fun handleEvent() {
        updateNewIcon()
    }

    private fun updateNewIcon() {
        var selectedIcon: String? = null

        binding.btnHome.setOnClickListener {
            selectedIcon = if (selectedIcon == "home") null else "home"
            updateIcons(selectedIcon = selectedIcon)
            if (selectedIcon == "home") {
                showHomeFragment()
            }
        }

        binding.btnDocument.setOnClickListener {
            selectedIcon = if (selectedIcon == "document") null else "document"
            updateIcons(selectedIcon = selectedIcon)
            if(selectedIcon == "document"){
                showTestFragment()
            }
        }

        binding.btnSearch.setOnClickListener {
            selectedIcon = if (selectedIcon == "search") null else "search"
            updateIcons(selectedIcon = selectedIcon)
        }
    }

    private fun updateIcons(selectedIcon: String?) {
        changeIcon(
            view = binding.btnHome,
            isSelected = selectedIcon == "home",
            oldIcon = R.drawable.vector,
            newIcon = R.drawable.group_14,
        )

        changeIcon(
            view = binding.btnDocument,
            isSelected = selectedIcon == "document",
            oldIcon = R.drawable.document,
            newIcon = R.drawable.vector__1_,
        )

        changeIcon(
            view = binding.btnSearch,
            isSelected = selectedIcon == "search",
            oldIcon = R.drawable.prime_search,
            newIcon = R.drawable.mingcute_search_fill,
        )
    }

    private fun changeIcon(view: ImageView, isSelected: Boolean, oldIcon: Int, newIcon: Int) {
        view.setImageResource(if (isSelected) newIcon else oldIcon)
    }

    override fun initData() {

    }

    override fun bindData() {
    }

    override fun setOnClick() {
    }

    private fun showDefaultFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentMainfunction, HomeFragment())
        fragmentTransaction.commit()
    }

    private fun showHomeFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentMainfunction, HomeFragment())
        fragmentTransaction.commit()
    }

    private fun showTestFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentMainfunction, TestFragment())
        fragmentTransaction.commit()
    }


}