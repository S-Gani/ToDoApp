//package com.example.todoapp
//
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.Query
//import androidx.room.Delete
//
//@Dao
//interface TaskDao {
//
//    @Insert
//    suspend fun insertTask(task: Task)
//
//    @Query("SELECT * FROM tasks")
//    suspend fun getAllTasks(): List<Task>
//
//    @Delete
//    suspend fun deleteTask(task: Task)
//}
