package com.hfad.todoapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class Task (
    @PrimaryKey(autoGenerate = true)
    var id:Int  = 0,

    @ColumnInfo(name = "taskName")
    var userText:String = "",

    @ColumnInfo(name = "task_done")
    var isChecked:Boolean = false)
{
    fun getUserTxt():String{
        return userText
    }
    fun checkDone():Boolean{
        return isChecked
    }
}