package com.example.nonameapp.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nonameapp.adapter.SubjectAdapter
import com.example.nonameapp.base.BaseFragment
import com.example.nonameapp.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val adapter by lazy { SubjectAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindData()
    }

    override fun initData() {

    }

    override fun bindData() {
        val listSubject = mutableListOf(
            "Chủ nghĩa xã hội khoa học",
            "Kinh tế chính trị Mác-Lênin",
            "Lịch sử Đảng Cộng sản Việt Nam",
            "Triết học Mác-Lênin",
            "Tư tưởng Hồ Chí Minh"
        )

//        adapter.setList(listSubject)
        binding.recyclerviewSubject.adapter = adapter
        binding.recyclerviewSubject.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun observeData() {

    }

    override fun setOnClick() {

    }

}