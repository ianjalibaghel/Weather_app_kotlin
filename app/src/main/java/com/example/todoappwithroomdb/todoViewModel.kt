package com.example.todoappwithroomdb

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class todoViewModel: ViewModel() {
    val todoDao= MainApplication.todoDatabase.getTodoDao()
    val todo : LiveData<List<tododata>> = todoDao.getAll()

    fun addtodo(title: String){
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.addTodo(tododata(title = title,createdAt = Date.from(Instant.now())))
        }
    }

    fun deletetodo(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.deleteTodo(id)
        }
    }


}