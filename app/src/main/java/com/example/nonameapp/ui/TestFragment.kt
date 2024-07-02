package com.example.nonameapp.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.example.nonameapp.base.BaseFragment
import com.example.nonameapp.base.BaseViewModel
import com.example.nonameapp.databinding.FragmentTestBinding

class TestFragment(override val viewModel: BaseViewModel) :
    BaseFragment<FragmentTestBinding>(FragmentTestBinding::inflate) {

    private lateinit var progressBar: ProgressBar
    private lateinit var tvPercentage: TextView
    private var correctAnswers = 0
    private val totalQuestions = 10

    override fun initData() {
    }

    override fun bindData() {
    }

    override fun observeData() {
    }

    override fun setOnClick() {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = binding.progressBar
        tvPercentage = binding.tvPercentage

        // Initialize progress
        updateProgress()

        // For demonstration purposes, simulate correct answers
        binding.root.postDelayed({
            onCorrectAnswer()
        }, 1000)
    }
    private fun updateProgress() {
        val progress = (correctAnswers * 100) / totalQuestions
        progressBar.progress = progress
        tvPercentage.text = "$progress%"
    }

    // Call this method when the user answers a question correctly
    private fun onCorrectAnswer() {
        if (correctAnswers < totalQuestions) {
            correctAnswers++
            updateProgress()
        }
    }
}