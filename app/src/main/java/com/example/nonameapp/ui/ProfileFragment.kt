package com.example.nonameapp.ui

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.nonameapp.base.BaseFragment
import com.example.nonameapp.databinding.FragmentProfileBinding
import com.example.nonameapp.data.source.local.SharedPreferencesManager
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.nonameapp.R
import com.example.nonameapp.activity.HomeActivity

class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    override val viewModel: ProfileViewModel
        get() = ViewModelProvider(this)[ProfileViewModel::class.java]

    private lateinit var homeActivity: HomeActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        homeActivity = activity as HomeActivity
        homeActivity.onItemClick(false)
    }

    override fun onDetach() {
        super.onDetach()
        homeActivity.onItemClick(true)
    }

    override fun initData() {
    }

    override fun bindData() {
    }

    override fun observeData() {
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun setOnClick() {
        val user = SharedPreferencesManager.getUserInfo(requireContext())

        if (user != null) {
            val avatarUrl = user.avatar

            if (avatarUrl.isNotEmpty()) {
                Glide.with(requireContext())
                    .load(avatarUrl)
                    .placeholder(R.drawable.user_img) // Hình ảnh mặc định khi ảnh chưa được tải
                    .error(R.drawable.user_img) // Hình ảnh hiển thị khi có lỗi tải ảnh
                    .into(binding.profileImage)

            } else {
                // Nếu không có avatar, đặt hình ảnh mặc định
                binding.profileImage.setImageResource(R.drawable.user_img)
            }

            binding.username.text = user.fullName
            binding.bio.text = user.email
        }
        binding.editProfileButton.setOnClickListener {
            findNavController().navigate(R.id.profile_to_edit)
            onAttach(requireContext())
        }
    }
}
