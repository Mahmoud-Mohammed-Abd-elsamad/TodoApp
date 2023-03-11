package com.example.totdo.DataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity
data class Modeltodo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val id:Int? = null ,
    @ColumnInfo
    var name:String? = null ,
    @ColumnInfo
    var details:String? = null ,
    @ColumnInfo
    var date: Date? = null ,
    @ColumnInfo
    val isdone:Boolean? = null
):Serializable
