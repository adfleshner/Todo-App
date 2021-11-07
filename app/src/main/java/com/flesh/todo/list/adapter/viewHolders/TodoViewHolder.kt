package com.flesh.todo.list.adapter.viewHolders

import com.flesh.todo.databinding.ItemTodoBinding
import com.flesh.todo.common.adapter.ItemClickListener
import com.flesh.todo.common.adapter.ItemClickViewHolder
import com.flesh.todo.objects.TodoItem

interface TodoViewHolderItemClickListener : ItemClickListener<TodoItem> {
    fun onDeleteClicked(item: TodoItem)
}

//build the cell
class TodoViewHolder(private val binding : ItemTodoBinding, itemClickListener: TodoViewHolderItemClickListener)
    : ItemClickViewHolder<TodoItem, TodoViewHolderItemClickListener>(binding.root,itemClickListener){

    override fun bind(item: TodoItem){
        super.bind(item)
        binding.item = item
        binding.executePendingBindings()
        binding.btnDelete.setOnClickListener {
            clickListener.onDeleteClicked(item)
        }
    }

}