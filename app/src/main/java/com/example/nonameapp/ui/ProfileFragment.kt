package com.example.nonameapp.ui

import androidx.lifecycle.ViewModelProvider
import com.example.nonameapp.base.BaseFragment
import com.example.nonameapp.databinding.FragmentProfileBinding
import com.example.nonameapp.data.source.local.SharedPreferencesManager
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.nonameapp.R

class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {
    override val viewModel: ProfileViewModel
        get() = ViewModelProvider(this)[ProfileViewModel::class.java]

    override fun initData() {
        SharedPreferencesManager.getUserInfo(requireContext())
            ?.let { user ->
                binding.profileImage.setImageResource(user.avatar.toInt())
                binding.username.text = user.fullName
                binding.bio.text = user.email
            }
    }

    override fun bindData() {
        Glide.with(requireContext())
            .load(SharedPreferencesManager.getUserInfo(requireContext())?.avatar)
            .into(binding.profileImage)
    }

    override fun observeData() {

    }

    override fun setOnClick() {
        binding.editProfileButton.setOnClickListener {
            findNavController().navigate(R.id.profile_to_edit)
        }
    }
}