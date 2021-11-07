package com.flesh.todo.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.flesh.databinding.DataBindingFragment
import com.flesh.todo.R
import com.flesh.todo.common.checkIfListOfType
import com.flesh.todo.common.sealed.UIState
import com.flesh.todo.common.extensions.setFragmentToolbar
import com.flesh.todo.databinding.FragmentTodoBinding
import com.flesh.todo.list.adapter.TodoAdapter
import com.flesh.todo.list.adapter.viewHolders.TodoViewHolderItemClickListener
import com.flesh.todo.objects.TodoItem
import com.flesh.todo.objects.room.TodoRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TodoFragment : DataBindingFragment<FragmentTodoBinding>(), TodoViewHolderItemClickListener {

    private val _repo : TodoRepository by lazy { TodoRepository(requireActivity().applicationContext) }
    private val _viewModel: TodoViewModel by viewModels { TodoViewModel.Factory(_repo) }
    private val _adapter: TodoAdapter by lazy { TodoAdapter(this) }

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup):
            FragmentTodoBinding =
        FragmentTodoBinding.inflate(inflater, container, false)

    //Bind ViewModels
    override fun setDataToBinding(binding: FragmentTodoBinding) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = _viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //list setup
        binding.listTodo.adapter = _adapter
        binding.listTodo.layoutManager = LinearLayoutManager(binding.listTodo.context)
        binding.listTodo.itemAnimator = DefaultItemAnimator()
        //Set up toolbar
        setFragmentToolbar(binding.tbTodo, withUpNavigation = true) {
            requireActivity().finish()
        }
        //Menu
        setHasOptionsMenu(true)
    }

    //Add observers
    override fun addLifeCycleObservers(view: View) {
        viewLifecycleOwner.lifecycle.addObserver(_viewModel.todoObserver)
        viewLifecycleOwner.lifecycleScope.launch {
            _viewModel.uiState.collect { state ->
                when (state) {
                    is UIState.Empty<*>->{
                        //Gotta tell the adapter its empty
                        _adapter.submitList(listOf())
                    }
                    is UIState.Success<*> -> {
                        state.result?.checkIfListOfType<TodoItem> { list->
                            _adapter.submitList(list)
                        }
                    }
                    else -> { }
                }
            }
        }
        setFragmentResultListener(AddItemAlertDialogFragment.RESULT_KEY) { key, data ->
            if (key == AddItemAlertDialogFragment.RESULT_KEY) {
                data.getParcelable<TodoItem>(AddItemAlertDialogFragment.DATA_KEY)?.let { result ->
                    if(result.id == 0){
                        _viewModel.addItem(result)
                    }else{
                        _viewModel.updateItem(result)
                    }
                }
            }
        }
    }

    //Menu inflation
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_todo, menu)
    }

    //Menu Interaction
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_add -> {
                val openNewItemDialog = TodoFragmentDirections
                    .actionDataBindingFragmentToAddItemAlertDialogFragment(null)
                findNavController().navigate(openNewItemDialog)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    //List Interactions
    override fun onDeleteClicked(item: TodoItem) {
        _viewModel.removeItem(item)
    }

    override fun onItemClick(item: TodoItem) {
        _viewModel.toggleCompletedState(item)
    }

    override fun onItemLongClick(item: TodoItem) {
        val openUpdateItemDialog = TodoFragmentDirections
            .actionDataBindingFragmentToAddItemAlertDialogFragment(item)
        findNavController().navigate(openUpdateItemDialog)
    }
}