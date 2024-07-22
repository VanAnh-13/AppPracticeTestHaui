//package com.example.nonameapp.ui
//
//import android.content.res.Resources
//import android.widget.GridLayout
//import android.widget.TextView
//import androidx.core.content.ContextCompat
//import androidx.lifecycle.ViewModelProvider
//import com.example.nonameapp.R
//import com.example.nonameapp.base.BaseFragment
//import com.example.nonameapp.databinding.FragmentResultTestBinding
//
//class ResultTestFragment : BaseFragment<FragmentResultTestBinding>(FragmentResultTestBinding::inflate) {
//    override val viewModel: QuestionsViewModel
//        get() = ViewModelProvider(this)[QuestionsViewModel::class.java]
//
//
//    private val totalQuestions = 20
//    private val answers = mutableListOf<Boolean>() // giả sử câu trả lời đúng/sai
//
//    override fun initData() {
//        // Giả lập kết quả câu trả lời, bạn có thể thay thế bằng dữ liệu thật
//        for (i in 1..totalQuestions) {
//            answers.add((0..1).random() == 1) // Random đúng/sai
//        }
//    }
//
//    override fun bindData() {
//        // Thêm các TextView hình tròn vào GridLayout
//        for (i in 1..totalQuestions) {
//            val textView = TextView(context).apply {
//                layoutParams = GridLayout.LayoutParams().apply {
//                    width = 50 // Kích thước của hình tròn
//                    height = 100
//                    columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
//                    setMargins(3.dpToPx(), 3.dpToPx(), 3.dpToPx(), 3.dpToPx()) // Cách nhau 5dp, sử dụng extension function để chuyển đổi dp sang px
//                }
//                text = i.toString()
//                textAlignment = TextView.TEXT_ALIGNMENT_CENTER
//                setTextColor(ContextCompat.getColor(requireContext(), android.R.color.black))
//                setBackgroundResource(R.drawable.circle_default)
//                gravity = android.view.Gravity.CENTER
//                setOnClickListener {
//                    updateCircleColor(this, answers[i - 1])
//                }
//            }
//            binding.gridLayout.addView(textView)
//        }
//    }
//
//    private fun updateCircleColor(textView: TextView, isCorrect: Boolean) {
//        val drawableId = if (isCorrect) {
//            R.drawable.circle_correct
//        } else {
//            R.drawable.circle_incorrect
//        }
//        textView.setBackgroundResource(drawableId)
//    }
//
//    override fun observeData() {
//    }
//
//    override fun setOnClick() {
//        binding.icArrowBack.setOnClickListener {
//            requireActivity().onBackPressed()
//        }
//    }
//
////    companion object {
////        private const val ARG_TEST = "arg_test"
////
////        @JvmStatic
////        fun newInstance(test: Test): ResultTestFragment {
////            val fragment = ResultTestFragment()
////            val args = Bundle()
////            args.putParcelable(ARG_TEST, test)
////            fragment.arguments = args
////            return fragment
////        }
////    }
//
//}
//fun Int.dpToPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()
//
