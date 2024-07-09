package com.example.nonameapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.nonameapp.R
import com.example.nonameapp.base.BaseFragment
import com.example.nonameapp.databinding.FragmentResultTestBinding

class ResultTestFragment : BaseFragment<FragmentResultTestBinding>(FragmentResultTestBinding::inflate) {
    private val totalQuestions = 20
    private val answers = mutableListOf<Boolean>() // giả sử câu trả lời đúng/sai

    override fun initData() {
        // Giả lập kết quả câu trả lời, bạn có thể thay thế bằng dữ liệu thật
        for (i in 1..totalQuestions) {
            answers.add((0..1).random() == 1) // Random đúng/sai
        }
    }

    override fun bindData() {
        // Thêm các TextView hình tròn vào GridLayout
        for (i in 1..totalQuestions) {
            val textView = TextView(context).apply {
                layoutParams = ViewGroup.LayoutParams(100, 100)
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
        // Bạn có thể thêm logic quan sát dữ liệu ở đây nếu cần
    }

    override fun setOnClick() {
        // Thêm sự kiện click cho nút back
        binding.icArrowBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

}