package com.example.todoappwithroomdb.DB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.todoappwithroomdb.tododata

@Dao
interface TodoDAO {
        //tododata is entity now

    @Query("SELECT * FROM TODODATA")
    fun getAll(): LiveData<List<tododata>>

    // we could also use the query of sql lite
    @Insert
    fun addTodo(todo : tododata)

    // ':id' is for inserted id in the function
    @Query("DELETE FROM TODODATA WHERE id= :id")
    fun deleteTodo(id : Int)

}