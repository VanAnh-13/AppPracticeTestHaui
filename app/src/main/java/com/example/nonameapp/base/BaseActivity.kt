package com.example.nonameapp.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding, LoginFragment>(
    private val bindingInflater: (LayoutInflater) -> VB
) : AppCompatActivity() {

    private var _binding: VB? = null
    protected val binding: VB
        get() = _binding as VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater(layoutInflater)
        setContentView(binding.root)

        initData()
        bindData()
        setOnClick()
    }

    abstract fun initData()

    abstract fun bindData()

    abstract fun setOnClick()
}
