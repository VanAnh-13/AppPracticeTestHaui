package com.example.nonameapp.adapter

import android.view.LayoutInflater
import com.example.nonameapp.base.BaseAdapter
import com.example.nonameapp.databinding.ItemTestBinding
import com.example.nonameapp.model.Test

class TestAdapter(
    private val onItemClick: (Test) -> Unit
) : BaseAdapter<Test, ItemTestBinding>(
    { inflater: LayoutInflater -> ItemTestBinding.inflate(inflater) }
) {
    override fun bindData(binding: ItemTestBinding, item: Test, position: Int) {
        binding.tvTestName.text = item.name
        binding.progressBar.progress = item.progress
        binding.tvPercentage.text = "${item.percentage}%"
    }

    override fun onItemClick(binding: ItemTestBinding, item: Test, position: Int) {
        binding.root.setOnClickListener { onItemClick(item) }
    }
}
