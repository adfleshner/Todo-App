package com.flesh.todo.common.adapter

import android.view.View
import androidx.annotation.CallSuper

interface ItemClickListener<T> {
    fun onItemClick(item:T)
    fun onItemLongClick(item:T)
}

open class ItemClickViewHolder<T, CL : ItemClickListener<T>>
    (view : View, internal val clickListener: CL) : BindingViewHolder<T>(view){

    @CallSuper
    override fun bind(item:T){
        itemView.setOnClickListener {
            clickListener.onItemClick(item)
        }
        itemView.setOnLongClickListener {
            clickListener.onItemLongClick(item)
            true
        }
    }

}
