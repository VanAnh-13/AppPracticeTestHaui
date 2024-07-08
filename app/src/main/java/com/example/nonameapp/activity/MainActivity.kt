package com.example.nonameapp.activity

import android.os.Bundle
import android.widget.ImageView
import com.example.nonameapp.R
import com.example.nonameapp.base.BaseActivity
import com.example.nonameapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleEvent()
    }

    private fun handleEvent() {
        updateNewIcon()
    }

    private fun updateNewIcon() {
        var selectedIcon: String? = null

        binding.btnHome.setOnClickListener {
            selectedIcon = if (selectedIcon == "home") null else "home"
            updateIcons(selectedIcon = selectedIcon)
        }

        binding.btnDocument.setOnClickListener {
            selectedIcon = if (selectedIcon == "document") null else "document"
            updateIcons(selectedIcon = selectedIcon)
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

}