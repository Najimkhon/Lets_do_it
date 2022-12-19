package com.hfad.letsdoit.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hfad.letsdoit.data.models.Priority
import kotlinx.parcelize.Parcelize

@Entity(tableName = "todo_table")
@Parcelize
data class ToDoData (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val title:String,
    val priority: Priority,
    val description:String
        ):Parcelable

