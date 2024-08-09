package com.example.nonameapp.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<T, VB : ViewBinding>(
    private val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> VB,
    protected var dataList: MutableList<T> = mutableListOf()
) : Adapter<BaseViewHolder<VB>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VB> {
        val binding = bindingInflater(LayoutInflater.from(parent.context), parent, false)
        return BaseViewHolder(binding = binding)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: BaseViewHolder<VB>, position: Int) {
        bindData(holder.binding, dataList[position], position)
        onItemClick(holder.binding, dataList[position], position)
    }

    abstract fun bindData(binding: VB, item: T, position: Int)

    abstract fun onItemClick(binding: VB, item: T, position: Int)

    fun setData(position: Int, data: T) {
        if (position >= dataList.size) {
            return
        }
        dataList[position] = data
        notifyItemChanged(position)
    }

    fun removeData(position: Int) {
        if (position >= dataList.size) {
            return
        }
        this.dataList.removeAt(position)
        notifyItemRemoved(position)
    }
}