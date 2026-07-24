package com.example.nonameapp.base;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

public abstract class BaseActivity<VB extends ViewBinding> extends AppCompatActivity {
    private final BindingInflater<VB> bindingInflater;
    private VB binding;

    protected BaseActivity(BindingInflater<VB> bindingInflater) {
        this.bindingInflater = bindingInflater;
    }

    protected VB getBinding() {
        return binding;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = bindingInflater.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initData();
        bindData();
        setOnClick();
    }

    protected abstract void initData();

    protected abstract void bindData();

    protected abstract void setOnClick();

    public interface BindingInflater<VB extends ViewBinding> {
        VB inflate(LayoutInflater inflater);
    }
}
