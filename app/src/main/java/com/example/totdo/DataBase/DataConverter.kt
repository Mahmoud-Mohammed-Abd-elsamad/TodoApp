package com.example.totdo.DataBase

import androidx.room.TypeConverter
import java.util.*

class DataConverter {
    @TypeConverter
    fun toDate(longdate:Long):Date{
        return Date(longdate)
    }
    @TypeConverter
    fun toLongDate(date:Date):Long{
        return date.time
    }
}