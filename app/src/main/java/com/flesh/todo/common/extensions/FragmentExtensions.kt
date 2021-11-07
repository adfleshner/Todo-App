package com.flesh.todo.common.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

fun Fragment.setFragmentToolbar(toolbar: Toolbar,
                                 withUpNavigation: Boolean = false,
                                 upNavigation : (()->Unit)? = null){
    (requireActivity() as AppCompatActivity).apply {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(withUpNavigation)
        if(withUpNavigation){
            toolbar.setNavigationOnClickListener {
                upNavigation?.invoke()
            }
        }
    }
}