package com.example.nonameapp.ui

import android.graphics.Color
import android.view.View
import androidx.appcompat.widget.AppCompatButton
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
//        viewModel.testName.observe(viewLifecycleOwner) { testName ->
//            binding.txtTestName.text = testName
//            println(testName)
//        }
//
//        viewModel.questions.observe(viewLifecycleOwner) { questions ->
//            listQuestionsT = questions
//            if (listQuestionsT.isNotEmpty()) {
//                displayQuestion(listQuestionsT[currentQuestionTIndex])
//            }
//        }
//
//        viewModel.error.observe(viewLifecycleOwner) { error ->
//            // Xử lý lỗi
//        }
    }

    override fun setOnClick() {

        var currentAnswer = ""
        var currentButton: AppCompatButton? = null

        binding.icArrowBack.setOnClickListener {
        }
        binding.textDone.setOnClickListener {
        }


        binding.answer1.setOnClickListener {
            chooseAnswer(binding.answer1)
        }
        binding.answer2.setOnClickListener {
            chooseAnswer(binding.answer2)
        }
        binding.answer3.setOnClickListener {
            chooseAnswer(binding.answer3)
        }
        binding.answer4.setOnClickListener {
            chooseAnswer(binding.answer4)
        }

        binding.btnNext.setOnClickListener {
            // next question
            if (currentQuestionTIndex < listQuestionsT.size - 1) {
                currentQuestionTIndex++
                resetAnswer()
                displayQuestion(listQuestionsT[currentQuestionTIndex])
            }
        }

        binding.btnReset.setOnClickListener {
            resetAnswer()
        }

        binding.btnPrevious.setOnClickListener {
            // previous question
            if (currentQuestionTIndex > 0) {
                currentQuestionTIndex--
                resetAnswer()
                displayQuestion(listQuestionsT[currentQuestionTIndex])
            }
        }
    }

    private fun resetAnswer() {
        binding.answer1.isSelected = false
        binding.answer2.isSelected = false
        binding.answer3.isSelected = false
        binding.answer4.isSelected = false

    }

    private fun displayQuestion(question: QuestionsT) {
        binding.apply {
            binding.tvQuestion.text = question.content
            binding.answer1.text = question.answers.getOrNull(0)
            binding.answer2.text = question.answers.getOrNull(1)
            binding.answer3.text = question.answers.getOrNull(2)
            binding.answer4.text = question.answers.getOrNull(3)
            tvProgress.text = "${currentQuestionTIndex + 1}/${listQuestionsT.size}"
        }
    }

    private fun chooseAnswer(button: AppCompatButton) {
        binding.answer1.isSelected = false
        binding.answer2.isSelected = false
        binding.answer3.isSelected = false
        binding.answer4.isSelected = false
        button.setBackgroundColor(R.drawable.choice_button_background)
        button.isSelected = true
    }
}

