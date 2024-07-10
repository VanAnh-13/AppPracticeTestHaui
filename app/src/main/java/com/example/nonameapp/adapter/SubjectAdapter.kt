package com.example.nonameapp.adapter

import android.view.View
import android.widget.LinearLayout
import com.example.nonameapp.R
import com.example.nonameapp.base.BaseAdapter
import com.example.nonameapp.databinding.ItemSubjectBinding
import com.example.nonameapp.model.Subject

class SubjectAdapter(
    private val liner: LinearLayout
) : BaseAdapter<Subject, ItemSubjectBinding>(
    bindingInflater = ItemSubjectBinding::inflate
) {
    override fun bindData(binding: ItemSubjectBinding, item: Subject, position: Int) {
        binding.tvSubject.text = item.name
        binding.btnNext.setImageResource(R.drawable.right)
    }

    override fun onItemClick(binding: ItemSubjectBinding, item: Subject, position: Int) {
        binding.itemLayout.setOnClickListener {
            liner.visibility = View.GONE
        }
    }

    fun setSubjectList(dataList: List<Subject>) {
        this.dataList = dataList.toMutableList()
        notifyDataSetChanged()
    }
}