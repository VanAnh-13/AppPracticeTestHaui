package com.example.nonameapp.base;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T, VB extends ViewBinding> extends RecyclerView.Adapter<BaseViewHolder<VB>> {
    private final BindingInflater<VB> bindingInflater;
    private final List<T> dataList = new ArrayList<>();

    protected BaseAdapter(BindingInflater<VB> bindingInflater) {
        this.bindingInflater = bindingInflater;
    }

    @Override
    public BaseViewHolder<VB> onCreateViewHolder(ViewGroup parent, int viewType) {
        VB binding = bindingInflater.inflate(LayoutInflater.from(parent.getContext()));
        return new BaseViewHolder<>(binding);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<VB> holder, int position) {
        bindData(holder.getBinding(), dataList.get(position), position);
        onItemClick(holder.getBinding(), dataList.get(position), position);
    }

    protected abstract void bindData(VB binding, T item, int position);

    protected abstract void onItemClick(VB binding, T item, int position);

    public void setData(int position, T data) {
        if (position >= dataList.size()) {
            return;
        }
        dataList.set(position, data);
        notifyItemChanged(position);
    }

    public void removeData(int position) {
        if (position >= dataList.size()) {
            return;
        }
        dataList.remove(position);
        notifyItemRemoved(position);
    }

    public interface BindingInflater<VB extends ViewBinding> {
        VB inflate(LayoutInflater inflater);
    }
}
