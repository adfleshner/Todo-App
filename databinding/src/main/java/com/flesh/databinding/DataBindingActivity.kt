package com.flesh.databinding

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.flesh.dataandviewbindinglibrary.viewbinding.ViewBindingActivity

abstract class DataBindingActivity<T: ViewDataBinding> : ViewBindingActivity<T>() {

    //Use this method to set the data To the binding. (Normally a ViewModel or a Presenter of sort.)
    abstract fun setDataToBinding(dataBinding: T)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDataToBinding(binding)
    }

}