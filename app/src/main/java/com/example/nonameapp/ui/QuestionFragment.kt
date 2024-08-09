package com.example.nonameapp.ui

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.nonameapp.R
import com.example.nonameapp.activity.HomeActivity
import com.example.nonameapp.base.BaseFragment
import com.example.nonameapp.databinding.FragmentQuestionBinding
import com.example.nonameapp.model.Question

class QuestionFragment : BaseFragment<FragmentQuestionBinding>(FragmentQuestionBinding::inflate) {
    private lateinit var homeActivity: HomeActivity

    override val viewModel: QuestionsViewModel
        get() = ViewModelProvider(this)[QuestionsViewModel::class.java]

    private lateinit var listQuestions: List<Question>
    private var currentQuestionIndex = 0

    override fun initData() {
        homeActivity = activity as HomeActivity

        val accessToken = getAccessToken()
        if (accessToken != null) {
            viewModel.getQuestions(
                accessToken = accessToken,
                subjectId = HomeFragment.idSubject
            )
        } else {
            Toast.makeText(requireContext(), "Access token is missing", Toast.LENGTH_SHORT).show()
        }
    }

    override fun bindData() {}

    override fun observeData() {
        viewModel.questions.observe(viewLifecycleOwner) { questions ->
            this.listQuestions = questions.shuffled()
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
            displayQuestion(listQuestions[currentQuestionIndex])
        }

        binding.btnCheck.setOnClickListener {
            checkAnswer(currentAnswer, currentButton)
        }

        binding.btnKey.setOnClickListener {
            showKey()
        }
        binding.btnExit.setOnClickListener {
            findNavController().navigate(R.id.question_to_home)
            homeActivity.onItemClick(true)
        }
    }

    private fun getAccessToken(): String? {
        val sharedPreferences =
            requireContext().getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
        return sharedPreferences.getString("access_token", null)
    }

    @SuppressLint("SetTextI18n")
    private fun displayQuestion(question: Question) {
        val shuffledAnswers = question.answers.shuffled()
        binding.apply {
            tvTitle.text = HomeFragment.nameSubject
            tvQuestion.text = question.content
            buttonOption1.text = shuffledAnswers[0]
            buttonOption2.text = shuffledAnswers[1]
            buttonOption3.text = shuffledAnswers[2]
            buttonOption4.text = shuffledAnswers[3]
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

    private fun setSelectedButton(button: AppCompatButton) {
        resetSelection()
        button.isSelected = true
    }

    private fun resetSelection() {
        binding.buttonOption1.isSelected = false
        binding.buttonOption2.isSelected = false
        binding.buttonOption3.isSelected = false
        binding.buttonOption4.isSelected = false
        resetButtonBorders()
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
