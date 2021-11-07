package com.flesh.todo.common.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

abstract class BindingListAdapter<T, BVH : BindingViewHolder<T>>(diffUtil: DiffUtil.ItemCallback<T>) : ListAdapter<T, BVH>(diffUtil){

    abstract fun dataBoundHolder(parent: ViewGroup, viewType: Int) : BVH

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BVH {
        return dataBoundHolder(parent,viewType)
    }

    override fun onBindViewHolder(holder: BVH, position: Int) {
        holder.bind(getItem(position))
    }

}
