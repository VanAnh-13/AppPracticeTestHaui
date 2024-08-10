package com.example.nonameapp.ui

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nonameapp.R
import com.example.nonameapp.activity.HomeActivity
import com.example.nonameapp.adapter.SubjectAdapter
import com.example.nonameapp.base.BaseFragment
import com.example.nonameapp.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private lateinit var homeActivity: HomeActivity
    private lateinit var adapter: SubjectAdapter

    companion object {
        var idSubject = ""
        var nameSubject = ""
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        homeActivity = activity as HomeActivity
        adapter = SubjectAdapter(
            { homeActivity.onItemClick(false) },
            { findNavController().navigate(R.id.home_to_question) },
            { id, name -> setSubjectIdAndName(newId = id, newName = name) },
        )
    }

    private fun setSubjectIdAndName(newId: String = "", newName: String = "") {
        idSubject = newId
        nameSubject = newName
    }

    override val viewModel: HomeFragmentModel
        get() = ViewModelProvider(this)[HomeFragmentModel::class.java]

    override fun initData() {
        try {
            viewModel.getSubject(accessToken = getToken(context = requireContext()))
        } catch (e: Exception) {
            Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show();
        }
    }

    override fun bindData() {
        binding.recyclerviewSubject.adapter = adapter
        binding.recyclerviewSubject.layoutManager = LinearLayoutManager(requireContext())
        viewModel.listSubject.value?.subjects?.let {
            adapter.setSubjectList(it)
        }
    }

    override fun observeData() {
        viewModel.listSubject.observe(this) {
            bindData()
        }
    }

    override fun setOnClick() {
    }

    private fun getToken(
        context: Context,
        key: String = "access_token",
        defaultValue: String = "",
    ) = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        .getString(key, defaultValue) ?: defaultValue

}