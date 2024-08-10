package com.example.nonameapp.activity

import android.content.Intent
import com.example.nonameapp.base.BaseActivity
import com.example.nonameapp.data.source.local.SharedPreferencesManager
import com.example.nonameapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun initData() {
        val token = SharedPreferencesManager.getToken(this)

        if (token != null) {
            startActivity(Intent(this, HomeActivity::class.java))
        } else {
            // Check if MainActivity has already started
            val sharedPreferences = getSharedPreferences("app_prefs", MODE_PRIVATE)
            val hasLaunched = sharedPreferences.getBoolean("hasLaunchedMainActivity", false)

            if (!hasLaunched) {
                startActivity(Intent(this, MainActivity::class.java))

                // Mark MainActivity as launched
                sharedPreferences.edit().putBoolean("hasLaunchedMainActivity", true).apply()
            }
        }
    }

    override fun bindData() {
    }

    override fun setOnClick() {
    }
}
