package com.hfad.todoapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.hfad.todoapp.adapter.TaskAdapter
import com.hfad.todoapp.data.Task
import com.hfad.todoapp.data.TaskDataBase
import com.hfad.todoapp.databinding.ActivityMainBinding
import com.hfad.todoapp.repository.TaskRepository
import com.hfad.todoapp.viewModels.TaskViewModel
import com.hfad.todoapp.viewModels.TaskViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: TaskAdapter
    private lateinit var viewModel: TaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = TaskRepository(TaskDataBase.getInstance(this))
        viewModel = ViewModelProvider(this, TaskViewModelFactory(repository))[TaskViewModel::class.java]

        adapter = TaskAdapter(onClickListener = {
                tasks ->
                if ( tasks.isNotEmpty()){
                    binding.buttonDelete.apply {
                        alpha = 1.0f
                        setOnClickListener{
                                showDialog(tasks)
                            alpha = 0.1f
                        }
                    }
                } else{
                    binding.buttonDelete.apply {
                        alpha = 0.1f
                        setOnClickListener(null)
                    }
                }

        })

        binding.recycler.adapter = adapter

        viewModel.getEvery.observe(this) { todoId ->
            adapter.submitList(todoId)
        }
        binding.buttonAdd.setOnClickListener {
            val taskContent = binding.editTextId.text.toString().trim()
            if (taskContent.isNotEmpty()) {
                val note = Task(0, taskContent)
                viewModel.addTask(note)
                binding.editTextId.text.clear()
                Toast.makeText(this, "Task Added", Toast.LENGTH_SHORT).show()
            } else {
               showDialogAdd()
            }
        }

    }



    private fun showDialog(tasks: List<Task>){
        val builder: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle("Alert!")

        if (tasks.size > 1) {
            builder.setMessage(" Are you sure you want to delete these tasks? ")
        } else {
            builder.setMessage("Are you sure you want to delete this task?")
        }

        builder.setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, _ ->
                viewModel.deleteTasks(tasks)
                dialog.dismiss()
                Toast.makeText(this, "Task Deleted", Toast.LENGTH_SHORT).show()

            })
            builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, _ ->
                dialog.dismiss()
            })
            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()

    }

    private fun showDialogAdd(){
        val builder: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle("Alert!")
        builder.setMessage(" Field empty!  Add a task to keep you going ")

        builder.setPositiveButton("Ok", DialogInterface.OnClickListener {
                dialog, _ ->
            dialog.dismiss()
        })
        val alertDialog:AlertDialog = builder.create()
        alertDialog.show()
        Toast.makeText(this, "Fill Task",Toast.LENGTH_SHORT).show()
    }
}