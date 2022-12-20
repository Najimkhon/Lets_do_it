package com.hfad.letsdoit.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.hfad.letsdoit.data.ToDoDAO
import com.hfad.letsdoit.data.models.ToDoData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoRepository(private val toDoDao: ToDoDAO) {
    val getAllData:LiveData<List<ToDoData>> = toDoDao.getAllData()
    suspend fun insertData(toDoData: ToDoData){
        toDoDao.insertData(toDoData)
    }
    suspend fun updateItem(toDoData: ToDoData){
        toDoDao.updateItem(toDoData)
    }

    suspend fun deleteItem(toDoData: ToDoData){
        toDoDao.deleteItem(toDoData)
    }

    suspend fun deleteAll(){
        toDoDao.deleteAll()
    }

    fun searchThroughDatabase(query: String):LiveData<List<ToDoData>>{
        return toDoDao.searchThroughDatabase(query)
    }
}