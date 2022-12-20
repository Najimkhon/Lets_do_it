package com.hfad.letsdoit.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hfad.letsdoit.data.models.ToDoData

@Dao
interface ToDoDAO {
    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllData():LiveData<List<ToDoData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(toDoData: ToDoData)

    @Update
    suspend fun updateItem(toDoData: ToDoData)

    @Delete
    suspend fun deleteItem(toDoData: ToDoData)

    @Query("DELETE FROM todo_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM todo_table WHERE title LIKE :query")
    fun searchThroughDatabase(query: String): LiveData<List<ToDoData>>
}