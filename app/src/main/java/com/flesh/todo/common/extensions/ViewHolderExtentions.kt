package com.flesh.todo.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

fun <T: ViewDataBinding> ViewGroup.dataBindViewHolder(@LayoutRes layout: Int) : T{
    val inflater = LayoutInflater.from(context)
    return DataBindingUtil.inflate(inflater,layout,this,false)
}