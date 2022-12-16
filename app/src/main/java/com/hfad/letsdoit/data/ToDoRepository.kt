package com.hfad.letsdoit.data

import androidx.lifecycle.LiveData

class ToDoRepository(private val toDoDao: ToDoDAO) {
    val getAllData:LiveData<List<ToDoData>> = toDoDao.getAllData()
    suspend fun insertData(toDoData: ToDoData){
        toDoDao.insertData(toDoData)
    }
}