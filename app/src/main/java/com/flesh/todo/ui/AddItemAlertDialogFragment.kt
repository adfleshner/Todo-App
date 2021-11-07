package com.flesh.todo.ui

import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.flesh.todo.R
import com.flesh.todo.common.suspend.closeKeyboardFirst
import com.flesh.todo.objects.TodoItem
import kotlinx.coroutines.launch

class AddItemAlertDialogFragment : DialogFragment() {

    private val _navArgs : AddItemAlertDialogFragmentArgs by navArgs()

    companion object {
        const val RESULT_KEY = "AddItemAlertDialogFragment_RESULT_KEY"
        const val DATA_KEY = "AddItemAlertDialogFragment_DATA_KEY"
    }

    init {
        isCancelable = false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_add_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Get EditText
        val et = view.findViewById<EditText>(R.id.etAddItem)
        val btnAdd = view.findViewById<Button>(R.id.btnAddItem)
        val item = _navArgs.item
        item?.let {
            et.setText(it.title)
            et.setSelection(it.title.length)
            btnAdd.text = view.context.getString(R.string.update)
        }

        view.findViewById<Button>(R.id.btnCancel).setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                closeKeyboardFirst(view)
                dismiss()
            }
        }
        btnAdd.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                closeKeyboardFirst(view)
                setFragmentResult(RESULT_KEY, Bundle().apply {
                    val title = et.text.toString()
                    val data = if(item==null){
                        TodoItem(title = title)
                    }else{
                        TodoItem(item.id,title,false)
                    }
                    putParcelable(DATA_KEY, data)
                })
                dismiss()
            }
        }
    }

}