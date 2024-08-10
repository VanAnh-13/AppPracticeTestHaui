package com.example.nonameapp.activity

import android.content.Intent
import androidx.navigation.findNavController
import com.example.nonameapp.R
import com.example.nonameapp.base.BaseActivity
import com.example.nonameapp.data.source.local.SharedPreferencesManager
import com.example.nonameapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun initData() {
        val token = SharedPreferencesManager.getToken(this)
        if (token != null) {
            // Navigate to main screen
            startActivity(Intent(this, HomeActivity::class.java))
        } else {
            // Navigate to login screen
            //startActivity(Intent(this, MainActivity::class.java))
        }
        finish()
    }

    override fun bindData() {
    }

    override fun setOnClick() {
    }
}