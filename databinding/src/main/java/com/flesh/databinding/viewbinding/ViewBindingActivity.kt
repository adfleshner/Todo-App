package com.flesh.dataandviewbindinglibrary.viewbinding

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

// After extending ViewBindingActivity just call super in the onCreate method and you are ready to use binding
// the inflateBinding method takes the place of all of the boilerplate code needed in view bidngs
// All this class does is remove some boiler plate code from creating a viewBinding.
// link: https://developer.android.com/topic/libraries/view-binding
abstract class ViewBindingActivity<T:ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: T
    abstract fun inflateBinding() : T

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = inflateBinding()
        val view = binding.root
        setContentView(view)
    }




}