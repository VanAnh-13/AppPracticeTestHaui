package com.example.nonameapp.ui

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.nonameapp.R
import com.example.nonameapp.base.BaseFragment
import com.example.nonameapp.databinding.FragmentTestBinding
import com.example.nonameapp.model.Question
import com.example.nonameapp.model.QuestionsT
import com.example.nonameapp.model.Test

class TestFragment :
    BaseFragment<FragmentTestBinding>(FragmentTestBinding::inflate) {
    override val viewModel: TestViewModel
        get() = ViewModelProvider(this)[TestViewModel::class.java]

    private lateinit var listQuestionsT: List<QuestionsT>
    private var currentQuestionTIndex = 0

    private val testId = "6691d7be91302747725eecc0"



    override fun initData() {
        viewModel.getQuestionsT(testId)
    }

    override fun bindData() {

    }

    override fun observeData() {
        // Quan sát tên bài test
        viewModel.testName.observe(viewLifecycleOwner) { testName ->
            binding.txtTestName.text = testName
        }

        // Quan sát danh sách câu hỏi
        viewModel.questions.observe(viewLifecycleOwner) { questions ->
            listQuestionsT = questions
            if (listQuestionsT.isNotEmpty()) {
                displayQuestion(listQuestionsT[currentQuestionTIndex])
            }
        }

        // Quan sát lỗi nếu có
        viewModel.error.observe(viewLifecycleOwner) { error ->
            // Xử lý lỗi
        }
    }

    override fun setOnClick() {
        binding.icArrowBack.setOnClickListener {
            // Quay lại màn hình Home
//            findNavController().popBackStack()
        }

        binding.textDone.setOnClickListener {
            // Chuyển đến TestResultFragment
//            findNavController().navigate(R.id.action_testFragment_to_testResultFragment)
        }

        binding.btnNext.setOnClickListener {
            // Hiển thị câu hỏi tiếp theo
            if (currentQuestionTIndex < listQuestionsT.size - 1) {
                currentQuestionTIndex++
                displayQuestion(listQuestionsT[currentQuestionTIndex])
            }
        }
    }

    private fun displayQuestion(question: QuestionsT) {
        binding.tvQuestion.text = question.content
        binding.answer1.text = question.answers.getOrNull(0)
        binding.answer2.text = question.answers.getOrNull(1)
        binding.answer3.text = question.answers.getOrNull(2)
        binding.answer4.text = question.answers.getOrNull(3)
    }
}

