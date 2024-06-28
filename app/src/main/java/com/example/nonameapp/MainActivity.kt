package com.example.nonameapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import com.example.nonameapp.base.BaseActivity
import com.example.nonameapp.databinding.ActivityMainBinding
import com.example.nonameapp.ui.LoginFragment

class MainActivity : BaseActivity<ActivityMainBinding, Any?>(ActivityMainBinding::inflate) {
    override fun initData() {
        supportFragmentManager.commit {
            replace(binding.fragmentContainer.id, LoginFragment())
            setReorderingAllowed(true)
        }
    }

    override fun bindData() {
    }

    override fun setOnClick() {
    }
}