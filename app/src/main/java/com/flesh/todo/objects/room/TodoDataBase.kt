package com.flesh.todo.objects.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.flesh.todo.objects.TodoItem

@Database(entities = [TodoItem::class], version = 1)
abstract class TodoDataBase : RoomDatabase() {
    abstract fun userDao(): TodoDao

    companion object {
        fun create(applicationContext : Context) : TodoDataBase{
            return Room.databaseBuilder(
                applicationContext,
                TodoDataBase::class.java, "todoitems"
            ).build()
        }
     }
}