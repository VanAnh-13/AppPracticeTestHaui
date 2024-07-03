package com.example.nonameapp.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import com.example.nonameapp.R
import com.example.nonameapp.base.BaseActivity
import com.example.nonameapp.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity<ActivityHomeBinding>(ActivityHomeBinding::inflate) {
    override fun initData() {
        supportFragmentManager.commit {
            replace(binding.fragmentContainer.id, TestFragment())
            setReorderingAllowed(true)
        }
    }

    override fun bindData() {
    }

    override fun setOnClick() {
    }

}