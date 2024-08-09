package com.example.nonameapp.adapter

import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.nonameapp.R
import com.example.nonameapp.base.BaseAdapter
import com.example.nonameapp.base.BaseViewHolder
import com.example.nonameapp.databinding.ItemSubjectBinding
import com.example.nonameapp.model.Subject

class SubjectAdapter(
    private val onItemClickListener: () -> Unit,
    private val doQuestion: () -> Unit,
    private val setSubject: (newId: String, newName: String) -> Unit
) : BaseAdapter<Subject, ItemSubjectBinding>(
    bindingInflater = ItemSubjectBinding::inflate,
) {
    override fun bindData(binding: ItemSubjectBinding, item: Subject, position: Int) {
        binding.tvSubject.text = item.name
        binding.btnNext.setImageResource(R.drawable.right)
    }

    override fun onItemClick(binding: ItemSubjectBinding, item: Subject, position: Int) {
        binding.itemLayout.setOnClickListener {
            onItemClickListener.invoke()
            doQuestion.invoke()
            setSubject.invoke(item.id, item.name)
        }
    }

    fun setSubjectList(dataList: List<Subject>) {
        this.dataList = dataList.toMutableList()
        notifyItemChanged(this.dataList.size - 1)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ItemSubjectBinding> {
        return super.onCreateViewHolder(parent, viewType)
    }
}