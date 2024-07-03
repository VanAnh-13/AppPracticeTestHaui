package com.example.nonameapp.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nonameapp.activity.MainActivity
import com.example.nonameapp.adapter.SubjectAdapter
import com.example.nonameapp.base.BaseFragment
import com.example.nonameapp.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val mainActivity by lazy { activity as MainActivity }
    private val adapter by lazy { SubjectAdapter(mainActivity.linerLayout) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindData()
    }

//    override val viewModel: BaseViewModel
//        get() = TODO("Not yet implemented")

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

        adapter.setList(listSubject)
        binding.recyclerviewSubject.adapter = adapter
        binding.recyclerviewSubject.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun observeData() {

    }

    override fun setOnClick() {

    }
}