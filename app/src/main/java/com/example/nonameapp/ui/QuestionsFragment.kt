package com.example.nonameapp.ui

import android.os.Bundle
import androidx.fragment.app.viewModels
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
    }

    override fun observeData() {
    }

    override fun setOnClick() {
    }
    companion object {
        private const val ARG_TEST = "arg_test"

        fun newInstance(test: Test): QuestionsFragment {
            val fragment = QuestionsFragment()
            val args = Bundle()
            args.putParcelable(ARG_TEST, test)
            fragment.arguments = args
            return fragment
        }
    }
}