package com.hfad.todoapp.adapter

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hfad.todoapp.R
import com.hfad.todoapp.data.Task

class TaskAdapter(private val onClickListener: (tasks: List<Task>) -> Unit)
    : ListAdapter<Task, TaskAdapter.MyViewHolder>(DiffUtill()) {

    private val finishedTasks = mutableListOf<Task>()

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var textShow:TextView = itemView.findViewById(R.id.todo_text)
        var check:CheckBox = itemView.findViewById(R.id.check_done)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_list,parent,false)
        return MyViewHolder(view)
    }

    private fun toggleStrikeThrough(textView: TextView, isChecked:Boolean ){
        if (isChecked) {
            textView.paintFlags = textView.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            textView.paintFlags = textView.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    /*fun addTodo(todo:Task) {
        list.add(todo)
        notifyItemInserted(list.size -1)
    }

    fun deleteDoneTodo(){
        list.removeAll{todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }*/

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val task = getItem(position)
        val text = holder.textShow
        text.text = task.getUserTxt()
        holder.check.isChecked = task.checkDone()
        toggleStrikeThrough(text, task.isChecked)
        holder.check.setOnCheckedChangeListener{
            _, isChecked ->
            toggleStrikeThrough(text,isChecked)
                task.isChecked = !task.isChecked
            if (task.isChecked){
                finishedTasks.add(task)
            }
            if(!task.isChecked && finishedTasks.contains(task)){
                finishedTasks.remove(task)
            }
            onClickListener(finishedTasks)
        }
    }
}