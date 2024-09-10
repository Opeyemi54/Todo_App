package com.hfad.todoapp.repository

import androidx.lifecycle.LiveData
import com.hfad.todoapp.data.Task
import com.hfad.todoapp.data.TaskDao
import com.hfad.todoapp.data.TaskDataBase

class TaskRepository(private val db: TaskDataBase) {

   suspend fun insertNote(task:Task) = db.dao().insert(task)
    suspend fun deleteNote(task:Task) = db.dao().delete(task)
     fun getAllTasks() = db.dao().getAll()
}