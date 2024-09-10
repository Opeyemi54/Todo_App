package com.hfad.todoapp.viewModels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.todoapp.data.Task
import com.hfad.todoapp.repository.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository) :ViewModel() {


    fun addTask(task: Task) =
        viewModelScope.launch {
            repository.insertNote(task)
        }

    fun deleteTasks(tasks: List<Task>) =
        viewModelScope.launch {
            for (task in tasks){
                repository.deleteNote(task)
            }
        }

    val getEvery = repository.getAllTasks()
}