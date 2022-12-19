package com.hfad.letsdoit.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.hfad.letsdoit.data.ToDoDatabase
import com.hfad.letsdoit.data.models.ToDoData
import com.hfad.letsdoit.data.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModel(application:Application):AndroidViewModel(application) {

    private val toDoDAO = ToDoDatabase.getDatabase(
        application
    ).toDoDao()
    private val repository: ToDoRepository
    val getAllData: LiveData<List<ToDoData>>


    init {
        repository = ToDoRepository(toDoDAO)
        getAllData = repository.getAllData
    }

    fun insertData(toDoData: ToDoData){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(toDoData)
        }
    }
}