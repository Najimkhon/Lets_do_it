package com.hfad.letsdoit.data.repository

import androidx.lifecycle.LiveData
import com.hfad.letsdoit.data.ToDoDAO
import com.hfad.letsdoit.data.models.ToDoData

class ToDoRepository(private val toDoDao: ToDoDAO) {
    val getAllData:LiveData<List<ToDoData>> = toDoDao.getAllData()
    suspend fun insertData(toDoData: ToDoData){
        toDoDao.insertData(toDoData)
    }
}