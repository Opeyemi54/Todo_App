package com.hfad.todoapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {

    @Insert
    suspend fun insert(task: Task)


    @Delete
    suspend fun delete(task: Task)



    @Query("SELECT * FROM user_table ORDER BY  id DESC")
     fun getAll(): LiveData<List<Task>>
}