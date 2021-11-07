package com.flesh.todo.list.adapter

import android.view.ViewGroup
import com.flesh.todo.R
import com.flesh.todo.common.adapter.BindingListAdapter
import com.flesh.todo.common.dataBindViewHolder
import com.flesh.todo.list.adapter.viewHolders.TodoViewHolder
import com.flesh.todo.list.adapter.viewHolders.TodoViewHolderItemClickListener
import com.flesh.todo.objects.TodoItem

class TodoAdapter(private val listener: TodoViewHolderItemClickListener) :
    BindingListAdapter<TodoItem, TodoViewHolder>(TodoItem.todoDiffUtil){

    override fun dataBoundHolder(parent: ViewGroup, viewType: Int): TodoViewHolder =
        TodoViewHolder(parent.dataBindViewHolder(R.layout.item_todo),listener)

}


