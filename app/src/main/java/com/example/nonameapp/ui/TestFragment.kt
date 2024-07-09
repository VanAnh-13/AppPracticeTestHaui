package com.example.nonameapp.ui

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nonameapp.R
import com.example.nonameapp.adapter.TestAdapter
import com.example.nonameapp.base.BaseFragment
import com.example.nonameapp.databinding.FragmentTestBinding
import com.example.nonameapp.model.Test

class TestFragment() :
    BaseFragment<FragmentTestBinding>(FragmentTestBinding::inflate) {

    private lateinit var testAdapter: TestAdapter


    override fun initData() {
        val testData = mutableListOf(
            Test("Test 1", 50, 50),
            Test("Test 2", 100, 100),
            Test("Test 3", 70, 70),
            Test("Test 4", 10, 10),
            Test("Test 5", 100, 100)
        )

        testAdapter = TestAdapter { test ->
            navigateToQuestionsFragment(test)
        }

        testAdapter.setData(testData)
    }

    override fun bindData() {
        binding.recyclerViewTest.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewTest.adapter = testAdapter
    }

    override fun observeData() {
    }

    override fun setOnClick() {
    }
    private fun navigateToQuestionsFragment(test: Test) {
        // Navigate to QuestionsFragment and pass data if necessary
        val questionsFragment = QuestionsFragment.newInstance(test)
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentMainfunction, questionsFragment)
            .commit()
    }

}

