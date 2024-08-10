package com.example.nonameapp.ui

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.nonameapp.R
import com.example.nonameapp.activity.HomeActivity
import com.example.nonameapp.activity.MainActivity
import com.example.nonameapp.base.BaseFragment
import com.example.nonameapp.databinding.FragmentSettingBinding

class SettingFragment() : BaseFragment<FragmentSettingBinding>(FragmentSettingBinding::inflate) {

    override val viewModel: SettingViewModel
        get() = ViewModelProvider(this)[SettingViewModel::class.java]

    override fun initData() {
    }

    override fun bindData() {
    }

    override fun observeData() {
    }

    override fun setOnClick() {
        binding.btnLogout.setOnClickListener {
            showLogoutDialog()

        }
        binding.settingAccount.setOnClickListener {
            findNavController().navigate(R.id.setting_to_profile)
        }
    }

    private fun showLogoutDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Đăng xuất")
        builder.setMessage("Bạn có muốn đăng xuất?")

        builder.setPositiveButton("Đăng xuất") { dialog, _ ->
            logout(requireContext())
            dialog.dismiss()
        }

        builder.setNegativeButton("Hủy") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun logout(context: Context) {
        val sharedPreferences =
            context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            remove("access_token")
            apply()
        }

        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)

        Toast.makeText(context, "Đã đăng xuất", Toast.LENGTH_SHORT).show()
    }
}