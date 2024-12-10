package com.example.todoappwithroomdb.DB

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.todoappwithroomdb.tododata

@Database(entities = [tododata::class], version = 1)
@TypeConverters(Converters::class)
abstract class TodoDatabase:RoomDatabase() {

    companion object{
        const val NAME = "todo_DB"
    }
    abstract fun getTodoDao(): TodoDAO
}