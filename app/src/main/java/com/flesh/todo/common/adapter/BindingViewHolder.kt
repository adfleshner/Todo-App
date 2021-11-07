package com.flesh.todo.common.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BindingViewHolder<T>(item: View) : RecyclerView.ViewHolder(item) {

    abstract fun bind(item:T)

}