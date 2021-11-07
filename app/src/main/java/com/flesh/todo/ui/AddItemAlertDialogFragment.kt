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
        val item = _navArgs.item
        item?.let {
            view.findViewById<EditText>(R.id.etAddItem).setText(it.title)
            view.findViewById<Button>(R.id.btnAddItem).text = view.context.getString(R.string.update)
        }

        view.findViewById<Button>(R.id.btnCancel).setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                closeKeyboardFirst(view)
                dismiss()
            }
        }
        view.findViewById<Button>(R.id.btnAddItem).setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                closeKeyboardFirst(view)
                setFragmentResult(RESULT_KEY, Bundle().apply {
                    val title = view.findViewById<EditText>(R.id.etAddItem).text.toString()
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