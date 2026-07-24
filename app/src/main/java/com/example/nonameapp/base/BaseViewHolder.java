package com.example.nonameapp.base;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

public class BaseViewHolder<VB extends ViewBinding> extends RecyclerView.ViewHolder {
    private final VB binding;

    public BaseViewHolder(VB binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public VB getBinding() {
        return binding;
    }
}
