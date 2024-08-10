package com.example.nonameapp.activity

import android.content.Intent
import android.view.View
import android.widget.ImageView
import androidx.navigation.findNavController
import com.example.nonameapp.R
import com.example.nonameapp.base.BaseActivity
import com.example.nonameapp.databinding.ActivityHomeBinding
import com.example.nonameapp.model.OnItemClickListener
import com.example.nonameapp.data.source.local.SharedPreferencesManager
import com.example.nonameapp.ui.HomeFragment

class HomeActivity : BaseActivity<ActivityHomeBinding>(ActivityHomeBinding::inflate),
    OnItemClickListener {
    private fun updateNewIcon() {
        var selectedIcon: String? = null

        binding.btnHome.setOnClickListener {
            selectedIcon = if (selectedIcon == "home") null else "home"
            updateIcons(selectedIcon = selectedIcon)

            HomeFragment.isHomeFragment = true
            findNavController(binding.fragmentAllFunction.id).navigate(R.id.homeFragment)
        }

        binding.btnTest.setOnClickListener {
            selectedIcon = if (selectedIcon == "document") null else "document"
            updateIcons(selectedIcon = selectedIcon)

            HomeFragment.isHomeFragment = false
            findNavController(binding.fragmentAllFunction.id).navigate(R.id.homeFragment)
        }

        binding.btnSearch.setOnClickListener {
            selectedIcon = if (selectedIcon == "search") null else "search"
            updateIcons(selectedIcon = selectedIcon)

            findNavController(binding.fragmentAllFunction.id).navigate(R.id.home_to_search)
        }

        binding.btnDetail.setOnClickListener {
            findNavController(binding.fragmentAllFunction.id).navigate(R.id.settingFragment)
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
            view = binding.btnTest,
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
        updateNewIcon()
    }

    override fun onItemClick(isVisible: Boolean) {
        if (!isVisible) {
            binding.linearLayout.visibility = View.GONE
        } else {
            binding.linearLayout.visibility = View.VISIBLE
        }
    }

}