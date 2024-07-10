package com.example.nonameapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.nonameapp.R
import com.example.nonameapp.adapter.TestAdapter
import com.example.nonameapp.base.BaseFragment
import com.example.nonameapp.base.BaseViewModel
import com.example.nonameapp.databinding.FragmentQuestionBinding
import com.example.nonameapp.model.Test

class QuestionsFragment() :
    BaseFragment<FragmentQuestionBinding>(FragmentQuestionBinding::inflate) {

    override fun initData() {
    }

    override fun bindData() {
        binding.textDone.setOnClickListener {
            navigateToResultFragment()
        }
    }

    override fun observeData() {
    }

    override fun setOnClick() {
        binding.icArrowBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        binding.icArrowBack.setOnClickListener {
            navigateToResultFragment()
        }
    }
    companion object {
        private const val ARG_TEST = "arg_test"

        @JvmStatic
        fun newInstance(test: Test): QuestionsFragment {
            val fragment = QuestionsFragment()
            val args = Bundle()
            args.putParcelable(ARG_TEST, test)
            fragment.arguments = args
            return fragment
        }
    }

    private fun navigateToResultFragment() {
        // Lấy dữ liệu Test object từ arguments
        val test = arguments?.getParcelable<Test>(ARG_TEST)

        // Kiểm tra nếu test không null thì chuyển tới ResultTestFragment
        test?.let {
            val resultFragment = ResultTestFragment.newInstance(it)
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentMainfunction, resultFragment)
                .addToBackStack(null)  // Nếu muốn thêm fragment vào back stack
                .commit()
        }
    }
}