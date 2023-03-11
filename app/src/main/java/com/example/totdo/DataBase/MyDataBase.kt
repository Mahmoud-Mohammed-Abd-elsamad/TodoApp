package com.example.totdo.DataBase

import android.content.Context
import android.view.View
import androidx.room.*
import java.util.*

@Database(entities = [Modeltodo::class], version = 1)
@TypeConverters(DataConverter::class)
abstract  class MyDataBase:RoomDatabase(){

    abstract fun dao_todo(): Dao_todo ///>>>>>>>>>>>>>>>>>>>>>> polimarfism>> 3shan anady 3la el Dao men class MyDataBase


    companion object {

        var myDataBase: MyDataBase? = null


        fun getInstance(context: Context): MyDataBase {
            /// Singleton pattern
            if (myDataBase == null) {
                myDataBase = Room.databaseBuilder(
                    context,
                    MyDataBase::class.java, "database-name"
                ).fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return myDataBase!!
        }
    }
}