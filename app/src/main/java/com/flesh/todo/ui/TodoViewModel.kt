package com.flesh.todo.ui

import androidx.lifecycle.*
import com.flesh.todo.common.sealed.UIEmpty
import com.flesh.todo.common.sealed.UIState
import com.flesh.todo.objects.TodoItem
import com.flesh.todo.objects.room.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TodoViewModel(private val repository: TodoRepository) : ViewModel() {

    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: TodoRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            TodoViewModel(repository) as T
    }

    private val _uiState = MutableStateFlow<UIState>(UIState.NotStarted)
    val uiState : StateFlow<UIState> = _uiState

    val todoObserver = object : DefaultLifecycleObserver {
        override fun onResume(owner: LifecycleOwner) {
            super.onResume(owner)
            setData()
        }
    }

    fun setData() = viewModelScope.launch {
        _uiState.emit(UIState.Loading)
        updateData()
    }

    fun addItem(data:TodoItem) = viewModelScope.launch {
        repository.addItem(data)
        updateData()
    }

    fun updateItem(result: TodoItem)  = viewModelScope.launch{
        repository.updateItem(result.copy())
        updateData()
    }

    fun toggleCompletedState(item: TodoItem) = viewModelScope.launch {
        //Always make a copy when working this diffutils
        repository.updateItem(item.copy().apply {
            toggleState()
        })
        updateData()
    }

    fun removeItem(item: TodoItem)  = viewModelScope.launch {
        repository.removeItem(item)
        updateData()
    }

    private fun updateData() = viewModelScope.launch {
        val data = repository.getAllItems().toList()
        if(data.isEmpty()){
            val message = "Click the '+' to add a task"
            val uiEmpty = UIEmpty.StringEmpty(message)
            val emptyState = UIState.Empty(uiEmpty)
            _uiState.emit(emptyState)
        }else{
            _uiState.emit(UIState.Success(data))
        }
    }

}