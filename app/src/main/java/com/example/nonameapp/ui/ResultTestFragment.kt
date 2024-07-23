package com.example.nonameapp.ui

import android.content.res.Resources
import android.os.Bundle
import android.widget.GridLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.nonameapp.R
import com.example.nonameapp.base.BaseFragment
import com.example.nonameapp.databinding.FragmentResultTestBinding

class ResultTestFragment :
    BaseFragment<FragmentResultTestBinding>(FragmentResultTestBinding::inflate) {
    override val viewModel: TestViewModel
        get() = ViewModelProvider(this)[TestViewModel::class.java]


    private val totalQuestions = 20
    private val answers = mutableListOf<Boolean>() // giả sử câu trả lời đúng/sai

    override fun initData() {
        // Giả lập kết quả câu trả lời, bạn có thể thay thế bằng dữ liệu thật
        for (i in 1..totalQuestions) {
            answers.add((0..1).random() == 1) // Random đúng/sai
        }
    }

    override fun bindData() {
        // Show test time
        val elapsedTime = arguments?.getString("elapsedTime") ?: "00:00:00"
        binding.txtTimeTest.text = "$elapsedTime"

        // Thêm các TextView hình tròn vào GridLayout
        for (i in 1..totalQuestions) {
            val textView = TextView(context).apply {
                layoutParams = GridLayout.LayoutParams().apply {
                    width = 50 // Kích thước của hình tròn
                    height = 100
                    columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                    setMargins(
                        3.dpToPx(),
                        3.dpToPx(),
                        3.dpToPx(),
                        3.dpToPx()
                    )
                }
                text = i.toString()
                textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                setTextColor(ContextCompat.getColor(requireContext(), android.R.color.black))
                setBackgroundResource(R.drawable.circle_default)
                gravity = android.view.Gravity.CENTER
                setOnClickListener {
                    updateCircleColor(this, answers[i - 1])
                }
            }
            binding.gridLayout.addView(textView)
        }
    }

    private fun updateCircleColor(textView: TextView, isCorrect: Boolean) {
        val drawableId = if (isCorrect) {
            R.drawable.circle_correct
        } else {
            R.drawable.circle_incorrect
        }
        textView.setBackgroundResource(drawableId)
    }

    override fun observeData() {
    }

    override fun setOnClick() {
        binding.icArrowBack.setOnClickListener {
            // back home
        }
    }
}

fun Int.dpToPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

