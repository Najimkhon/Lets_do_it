package com.hfad.letsdoit.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hfad.letsdoit.data.models.Priority

@Entity(tableName = "todo_table")
data class ToDoData (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val title:String,
    val priority: Priority,
    val description:String
        )
