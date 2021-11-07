package com.flesh.todo.objects

import android.os.Parcelable
import android.util.Log
import androidx.annotation.Keep
import androidx.recyclerview.widget.DiffUtil
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
@Entity
data class TodoItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val title: String,
    @ColumnInfo var completed: Boolean = false
) : Parcelable {

    fun toggleState(){
        this.completed = !completed
    }

    companion object{
        //For udpates the diff util needs a copy or a new item. Never use a manipulated version for the same item.
        //Diff Util to tell if the objects are the same
        val todoDiffUtil = object : DiffUtil.ItemCallback<TodoItem>() {
            override fun areItemsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
                val itemSame = oldItem.id == newItem.id
                return itemSame // replace with id
            }

            override fun areContentsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
                val contentsSame = oldItem.title == newItem.title && oldItem.completed == newItem.completed
                return contentsSame
            }

            override fun getChangePayload(oldItem: TodoItem, newItem: TodoItem): Any? {
                val list = mutableListOf<String>()
                if(oldItem.title != newItem.title){
                    list.add("t")
                }
                if(oldItem.completed != newItem.completed){
                    list.add("c")
                }
                Log.d("WTF2","Diff What Changed ${list.joinToString { it }}")
                return list
            }
        }
    }
}