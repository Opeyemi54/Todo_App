package com.hfad.todoapp.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hfad.todoapp.data.Task

class DiffUtill : DiffUtil.ItemCallback<Task>() {
    override fun areContentsTheSame(oldItem: Task, newItem: Task)
            = (oldItem.id == newItem.id)

    override fun areItemsTheSame(oldItem: Task, newItem: Task) = (oldItem == newItem)


}