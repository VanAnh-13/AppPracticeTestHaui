package com.example.nonameapp.adapter

import com.example.nonameapp.R
import com.example.nonameapp.base.BaseAdapter
import com.example.nonameapp.databinding.ItemSubjectBinding

class SubjectAdapter : BaseAdapter<String, ItemSubjectBinding>(ItemSubjectBinding::inflate) {
    override fun bindData(binding: ItemSubjectBinding, item: String, position: Int) {
        binding.tvSubject.text = item
        binding.btnNext.setImageResource(R.drawable.right)
    }

    override fun onItemClick(binding: ItemSubjectBinding, item: String, position: Int) {

    }
}