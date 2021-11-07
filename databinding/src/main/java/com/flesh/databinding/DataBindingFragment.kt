package com.flesh.databinding

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.databinding.ViewDataBinding
import com.flesh.dataandviewbindinglibrary.viewbinding.ViewBindingFragment

abstract class DataBindingFragment <T : ViewDataBinding> : ViewBindingFragment<T>() {

    //Add the Data from the data (normally a view model or presenter) to the ViewDataBinding
    abstract fun setDataToBinding(binding: T)

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDataToBinding(binding)
    }

}