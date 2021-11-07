package com.flesh.todo.objects.room

import android.content.Context
import com.flesh.todo.objects.TodoItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class TodoRepository(applicationContext: Context, private val dispatcher: CoroutineDispatcher = IO) {

    private val db = TodoDataBase.create(applicationContext)

    suspend fun getAllItems(): List<TodoItem> = withContext(dispatcher) {
        db.userDao().getAll()
    }

    suspend fun addItem(todoItem: TodoItem) = withContext(dispatcher) {
        db.userDao().insertAll(todoItem)
    }

    suspend fun removeItem(todoItem: TodoItem) = withContext(dispatcher) {
        db.userDao().delete(todoItem)
    }

    suspend fun updateItem(item: TodoItem) = withContext(dispatcher) {
        db.userDao().update(item)
    }


}