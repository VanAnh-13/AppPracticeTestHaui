package com.example.nonameapp.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.nonameapp.R
import com.example.nonameapp.activity.HomeActivity
import com.example.nonameapp.adapter.SubjectAdapter
import com.example.nonameapp.base.BaseFragment
import com.example.nonameapp.databinding.FragmentTestBinding
import com.example.nonameapp.model.QuestionsT
import com.example.nonameapp.ui.HomeFragment.Companion.setSubjectIdAndName
import java.util.concurrent.TimeUnit

class TestFragment :
    BaseFragment<FragmentTestBinding>(FragmentTestBinding::inflate) {
    private lateinit var adapterSubject: SubjectAdapter
    private lateinit var homeActivity: HomeActivity

    override val viewModel: TestViewModel
        get() = ViewModelProvider(this)[TestViewModel::class.java]

    private var selectedAnswerId: Int? = null
    private var totalQuestionsT: List<QuestionsT> = emptyList()
    private var currentQuestion = 0

    private var startTime: Long = 0
    private var elapsedTime: Long = 0
    private val handler = Handler(Looper.getMainLooper())
    private val updateTimeRunnable = object : Runnable {
        override fun run() {
            elapsedTime = System.currentTimeMillis() - startTime
            updateClock()
            handler.postDelayed(this, 1000)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        homeActivity = activity as HomeActivity

        adapterSubject = SubjectAdapter(
            { homeActivity.onItemClick(false) },
            { findNavController().navigate(R.id.home_to_exerciseTest) },
            { id, name -> setSubjectIdAndName(newId = id, newName = name) },
        )

    }

    override fun onDetach() {
        super.onDetach()

        homeActivity.onItemClick(true)
    }

    override fun initData() {
        viewModel.getQuestionsT(ExerciseTestFragment.idSubject)
        startTime = System.currentTimeMillis()
        handler.post(updateTimeRunnable)
    }

    override fun bindData() {
    }

    override fun observeData() {
        viewModel.questionsT.observe(viewLifecycleOwner) { questionsT ->
            Log.d("TestFragment", "QuestionsT: $questionsT")
            if (questionsT != null) {
                totalQuestionsT = questionsT.questions
                if (totalQuestionsT.isNotEmpty()) {
                    Log.d("TestFragment", "Displaying question at index: $currentQuestion")
                    displayQuestion(totalQuestionsT[currentQuestion])
                    updateProgress()
                } else {
                    Log.d("TestFragment", "No questions found")
                    Toast.makeText(requireContext(), "No questions found", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Log.d("TestFragment", "Questions list is null")
                Toast.makeText(requireContext(), "Questions list is null", Toast.LENGTH_SHORT)
                    .show()
            }
            viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun setOnClick() {
        binding.apply {
            textDone.setOnClickListener {
                // Stop the timer and pass elapsed time to ResultTestFragment
                handler.removeCallbacks(updateTimeRunnable)
                val resultTestFragment = ResultTestFragment()
                val bundle = Bundle()
                bundle.putString("elapsedTime", formatElapsedTime(elapsedTime))
                bundle.putIntegerArrayList(KEY_ANSWER, viewModel.answer.value)
                resultTestFragment.arguments = bundle

                findNavController().navigate(R.id.test_to_result, bundle)
            }
            btnNext.setOnClickListener {
                if (currentQuestion < totalQuestionsT.size - 1) {
                    currentQuestion++
                    displayQuestion(totalQuestionsT[currentQuestion])
                    updateProgress()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "You are on the last question",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            btnPrevious.setOnClickListener {
                if (currentQuestion > 0) {
                    currentQuestion--
                    displayQuestion(totalQuestionsT[currentQuestion])
                    updateProgress()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "You are on the first question",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            // choose answer
            setupAnswerButtons()

            binding.btnBackToExercise.setOnClickListener {
                findNavController().navigate(R.id.test_to_exercise)

                homeActivity.onItemClick(true)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateProgress() {
        binding.tvProgress.text = "${currentQuestion + 1}/${totalQuestionsT.size}"
    }

    private fun setupAnswerButtons() {
        val buttons = listOf(
            binding.answer1,
            binding.answer2,
            binding.answer3,
            binding.answer4
        )

        buttons.forEach { button ->
            button.setOnClickListener {
                onAnswerClicked(button)
            }
        }
    }

    private fun displayQuestion(question: QuestionsT) {
        // Clear the selected answer state
        selectedAnswerId = null
        clearAnswerSelection()

        binding.apply {
            tvQuestion.text = question.content
            answer1.text = question.answers.getOrNull(0)
            answer2.text = question.answers.getOrNull(1)
            answer3.text = question.answers.getOrNull(2)
            answer4.text = question.answers.getOrNull(3)
        }
    }

    private fun clearAnswerSelection() {
        val buttons = listOf(
            binding.answer1,
            binding.answer2,
            binding.answer3,
            binding.answer4
        )
        buttons.forEach { button ->
            button.setBackgroundResource(R.drawable.button_background_subjects)
        }
    }

    private fun onAnswerClicked(button: Button) {
        selectedAnswerId?.let { prevSelectedId ->
            val previousButton = view?.findViewById<Button>(prevSelectedId)
            previousButton?.setBackgroundResource(R.drawable.button_background_subjects) // Màu nền không chọn
        }
        button.setBackgroundResource(R.drawable.button_background)
        selectedAnswerId = button.id

        viewModel.onAnswerItemClick(currentQuestion, button.text.toString())
    }

    private fun updateClock() {
        val timeString = formatElapsedTime(elapsedTime)
        binding.tvClock.text = timeString
    }

    @SuppressLint("DefaultLocale")
    private fun formatElapsedTime(elapsedTime: Long): String {
        val seconds = TimeUnit.MILLISECONDS.toSeconds(elapsedTime) % 60
        val minutes = TimeUnit.MILLISECONDS.toMinutes(elapsedTime) % 60
        val hours = TimeUnit.MILLISECONDS.toHours(elapsedTime)
        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }

    companion object {
        const val KEY_ANSWER = "answers"
    }
}

