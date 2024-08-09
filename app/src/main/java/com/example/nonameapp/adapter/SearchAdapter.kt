package com.example.nonameapp.adapter

import android.annotation.SuppressLint
import com.example.nonameapp.databinding.ItemSearchResultBinding
import com.example.nonameapp.base.BaseAdapter
import com.example.nonameapp.model.Question
import com.example.nonameapp.response.SearchResponse

class SearchAdapter : BaseAdapter<Question, ItemSearchResultBinding>(
    bindingInflater = ItemSearchResultBinding::inflate
) {

    override fun bindData(binding: ItemSearchResultBinding, item: Question, position: Int) {
        binding.question.text = item.content
    }

    override fun onItemClick(binding: ItemSearchResultBinding, item: Question, position: Int) {
        binding.root.setOnClickListener {
            // Handle item click
            // For example, show a Toast or navigate to another screen
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setResultList(dataList: SearchResponse) {
        this.dataList = dataList.data.toMutableList()
        notifyDataSetChanged() // Notify that the whole dataset has changed
    }
}
