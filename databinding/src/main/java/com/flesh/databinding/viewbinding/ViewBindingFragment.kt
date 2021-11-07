package com.flesh.dataandviewbindinglibrary.viewbinding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

// After extending ViewBindingFragment use onViewCreated method and you are ready to use bindings
// the inflateBinding method takes the place of all of the boilerplate code needed in view bidngs
// All this class does is remove some boiler plate code from creating removing a viewBinding.
// link: https://developer.android.com/topic/libraries/view-binding
abstract class ViewBindingFragment<T: ViewBinding> : Fragment() {

    private var _binding : T? = null
    protected val binding : T get() = _binding!!
    abstract fun inflateBinding(inflater: LayoutInflater,container:ViewGroup) : T

    abstract fun addLifeCycleObservers(view:View)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (container == null) {
            throw IllegalStateException("Container can not be null")
        }
        _binding = inflateBinding(inflater, container)
        return binding.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addLifeCycleObservers(view)
    }

    //Call onViewCreated to set all of the bindings in the fragment.

    //Clear all the binding
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}