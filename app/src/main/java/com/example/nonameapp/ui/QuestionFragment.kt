package com.example.nonameapp.ui

import android.content.Context
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.ViewModelProvider
import com.example.nonameapp.R
import com.example.nonameapp.base.BaseFragment
import com.example.nonameapp.databinding.FragmentQuestionBinding
import com.example.nonameapp.model.Question

class QuestionFragment : BaseFragment<FragmentQuestionBinding>(FragmentQuestionBinding::inflate) {

    override val viewModel: QuestionsViewModel
        get() = ViewModelProvider(this)[QuestionsViewModel::class.java]

    private lateinit var listQuestions: List<Question>
    private var currentQuestionIndex = 0

    override fun initData() {
        val accessToken = getAccessToken()
        if (accessToken != null) {
            viewModel.getQuestions(accessToken, "667f8c8a143b9e33691dc669")
        } else {
            Toast.makeText(requireContext(), "Access token is missing", Toast.LENGTH_SHORT).show()
        }
    }

    override fun bindData() {}

    override fun observeData() {
        viewModel.questions.observe(viewLifecycleOwner) { questions ->
            this.listQuestions = questions
            if (listQuestions.isNotEmpty()) {
                displayQuestion(listQuestions[currentQuestionIndex])
            } else {
                Toast.makeText(requireContext(), "No questions found", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    override fun setOnClick() {
        var currentAnswer = ""
        var currentButton: AppCompatButton? = null

        binding.buttonOption1.setOnClickListener {
            setSelectedButton(binding.buttonOption1)
            currentButton = binding.buttonOption1
            currentAnswer = binding.buttonOption1.text.toString()
        }
        binding.buttonOption2.setOnClickListener {
            setSelectedButton(binding.buttonOption2)
            currentButton = binding.buttonOption2
            currentAnswer = binding.buttonOption2.text.toString()
        }
        binding.buttonOption3.setOnClickListener {
            setSelectedButton(binding.buttonOption3)
            currentButton = binding.buttonOption3
            currentAnswer = binding.buttonOption3.text.toString()
        }
        binding.buttonOption4.setOnClickListener {
            setSelectedButton(binding.buttonOption4)
            currentButton = binding.buttonOption4
            currentAnswer = binding.buttonOption4.text.toString()
        }
        binding.btnPrevious.setOnClickListener {
            if (currentQuestionIndex > 0) {
                currentQuestionIndex--
                resetSelection()
                displayQuestion(listQuestions[currentQuestionIndex])
            }
        }

        binding.btnNext.setOnClickListener {
            if (currentQuestionIndex < listQuestions.size - 1) {
                currentQuestionIndex++
                resetSelection()
                displayQuestion(listQuestions[currentQuestionIndex])
            }
        }

        binding.btnReset.setOnClickListener {
            resetSelection()
        }

        binding.btnCheck.setOnClickListener {
            checkAnswer(currentAnswer, currentButton)
        }

        binding.btnKey.setOnClickListener {
            showKey()
        }
    }

    private fun getAccessToken(): String? {
        val sharedPreferences = requireContext().getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
        return sharedPreferences.getString("access_token", null)
    }

    private fun displayQuestion(question: Question) {
        binding.apply {
            tvQuestion.text = question.content
            buttonOption1.text = question.answers[0]
            buttonOption2.text = question.answers[1]
            buttonOption3.text = question.answers[2]
            buttonOption4.text = question.answers[3]
            tvProgress.text = "${currentQuestionIndex + 1}/${listQuestions.size}"
        }
    }

    private fun checkAnswer(answer: String, selectedButton: AppCompatButton?) {
        if (answer.isEmpty()) {
            Toast.makeText(requireContext(), "Please select an answer", Toast.LENGTH_SHORT).show()
            return
        }
        val correctAnswer = listQuestions[currentQuestionIndex].correctAnswer
        if (answer == correctAnswer) {
            selectedButton?.setBackgroundResource(R.drawable.button_border_correct)
        } else {
            selectedButton?.setBackgroundResource(R.drawable.button_border_incorrect)
        }
    }

    private fun resetButtonBorders() {
        binding.buttonOption1.setBackgroundResource(R.drawable.choice_button_background)
        binding.buttonOption2.setBackgroundResource(R.drawable.choice_button_background)
        binding.buttonOption3.setBackgroundResource(R.drawable.choice_button_background)
        binding.buttonOption4.setBackgroundResource(R.drawable.choice_button_background)
    }

    private fun resetSelection() {
        binding.buttonOption1.isSelected = false
        binding.buttonOption2.isSelected = false
        binding.buttonOption3.isSelected = false
        binding.buttonOption4.isSelected = false
        resetButtonBorders()
    }

    private fun setSelectedButton(button: AppCompatButton) {
        binding.buttonOption1.isSelected = false
        binding.buttonOption2.isSelected = false
        binding.buttonOption3.isSelected = false
        binding.buttonOption4.isSelected = false
        button.isSelected = true
    }

    private fun showKey() {
        val correctAnswer = listQuestions[currentQuestionIndex].correctAnswer
        when (correctAnswer) {
            binding.buttonOption1.text.toString() -> binding.buttonOption1.setBackgroundResource(R.drawable.button_border_correct)
            binding.buttonOption2.text.toString() -> binding.buttonOption2.setBackgroundResource(R.drawable.button_border_correct)
            binding.buttonOption3.text.toString() -> binding.buttonOption3.setBackgroundResource(R.drawable.button_border_correct)
            binding.buttonOption4.text.toString() -> binding.buttonOption4.setBackgroundResource(R.drawable.button_border_correct)
        }
    }
}
