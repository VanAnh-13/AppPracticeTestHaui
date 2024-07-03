package com.example.nonameapp.ui

import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nonameapp.adapter.TestAdapter
import com.example.nonameapp.base.BaseFragment
import com.example.nonameapp.base.BaseViewModel
import com.example.nonameapp.databinding.FragmentTestBinding
import com.example.nonameapp.model.Test

class TestFragment() :
    BaseFragment<FragmentTestBinding>(FragmentTestBinding::inflate) {

    private val myViewModel: BaseViewModel by viewModels()

    private lateinit var testAdapter: TestAdapter
    override val viewModel: BaseViewModel
        get() = myViewModel
    override fun initData() {
        val testData = mutableListOf(
            Test("Test 1", 50, 50),
            Test("Test 2", 70, 70),
        )

        testAdapter = TestAdapter { test ->
            // Handle item click if needed
        }

        testAdapter.setData(testData)
    }

    override fun bindData() {
        binding.recyclerViewTests.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewTests.adapter = testAdapter
    }

    override fun observeData() {
    }

    override fun setOnClick() {
    }

}