package com.flesh.todo.objects.room

import androidx.room.*
import com.flesh.todo.objects.TodoItem

@Dao
interface TodoDao {

    @Query("SELECT * FROM todoitem")
    fun getAll(): List<TodoItem>

    @Insert
    fun insertAll(vararg items: TodoItem)

    @Delete
    fun delete(item: TodoItem)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(item: TodoItem)

}
