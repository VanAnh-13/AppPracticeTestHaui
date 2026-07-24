package com.example.nonameapp.base;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.viewbinding.ViewBinding;

public abstract class BaseFragment<VB extends ViewBinding> extends Fragment {
    private VB binding;
    private final BindingInflater<VB> bindingInflater;
    private Dialog loadingDialog;

    protected BaseFragment(BindingInflater<VB> bindingInflater) {
        this.bindingInflater = bindingInflater;
    }

    protected VB getBinding() {
        return binding;
    }

    protected abstract BaseViewModel getViewModel();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = bindingInflater.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadingDialog = new Dialog(requireContext());
        getViewModel().getLoading().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if (isLoading) {
                    if (!loadingDialog.isShowing()) {
                        loadingDialog.show();
                    }
                } else {
                    if (loadingDialog.isShowing()) {
                        loadingDialog.dismiss();
                    }
                }
            }
        });
        bindData();
        observeData();
        setOnClick();
    }

    protected abstract void initData();

    protected abstract void bindData();

    protected abstract void observeData();

    protected abstract void setOnClick();

    public interface BindingInflater<VB extends ViewBinding> {
        VB inflate(LayoutInflater inflater);
    }
}
