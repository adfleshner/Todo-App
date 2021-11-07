package com.flesh.todo.ui

import com.flesh.dataandviewbindinglibrary.viewbinding.ViewBindingActivity
import com.flesh.todo.databinding.ActivityMainBinding

class MainActivity : ViewBindingActivity<ActivityMainBinding>() {

    override fun inflateBinding() = ActivityMainBinding.inflate(layoutInflater)

}