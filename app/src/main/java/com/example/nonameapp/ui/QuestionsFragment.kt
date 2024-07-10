package com.example.nonameapp.ui

import android.os.Bundle
import android.os.CountDownTimer
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

    private var countDownTimer: CountDownTimer? = null
    private var timeElapsedMillis: Long = 0
    private var isTimerRunning = false
//    private val testViewModel: TestViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startTimer()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        stopTimer()
    }
    override fun initData() {
    }

    override fun bindData() {
        binding.textDone.setOnClickListener {
            navigateToResultFragment()
        }
    }
    private fun startTimer() {
        isTimerRunning = true
        countDownTimer = object : CountDownTimer(MAX_TIME_MILLIS, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeElapsedMillis = MAX_TIME_MILLIS - millisUntilFinished
                updateClockText()
            }

            override fun onFinish() {
                timeElapsedMillis = MAX_TIME_MILLIS
                updateClockText()
                stopTimer()
            }
        }.start()
    }

    private fun stopTimer() {
        isTimerRunning = false
        countDownTimer?.cancel()
    }
    private fun updateClockText() {
        val seconds = (timeElapsedMillis / 1000) % 60
        val minutes = (timeElapsedMillis / (1000 * 60)) % 60
        val hours = (timeElapsedMillis / (1000 * 60 * 60)) % 24
        binding.tvClock.text = String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }

    override fun observeData() {
    }

    override fun setOnClick() {
        binding.textDone.setOnClickListener {
            stopTimer()
            navigateToResultFragment()
        }

        binding.icArrowBack.setOnClickListener {
            stopTimer()
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
    companion object {
        private const val ARG_TEST = "arg_test"
        private const val MAX_TIME_MILLIS = 30 * 60 * 1000L // 30 minutes

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

    private fun navigateToTestFragment() {
        val testFragment = TestFragment()
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentMainfunction, testFragment)
            .commit()
    }
}