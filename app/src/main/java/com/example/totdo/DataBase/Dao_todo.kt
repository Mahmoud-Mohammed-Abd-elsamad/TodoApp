package com.example.totdo.DataBase

import androidx.room.*
import java.util.*

@Dao
interface Dao_todo {
    @Insert
    fun addTodo(todo:Modeltodo)

    @Update
    fun updatetodo(todo: Modeltodo)

    @Delete
    fun deletetodo(todo: Modeltodo)


    @Query("select * from modeltodo")
    fun getalltodos():List<Modeltodo>

    @Query("select * from modeltodo where date = :date ")
    fun getallTodosByDate( date:Date):MutableList<Modeltodo>
}