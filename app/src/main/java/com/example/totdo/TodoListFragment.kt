package com.example.totdo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.totdo.DataBase.Modeltodo
import com.example.totdo.DataBase.MyDataBase
import com.example.totdo.List_recyclerview.AdapterTodo
import com.example.totdo.List_recyclerview.Ubdate
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import java.util.*
import java.util.Calendar.*

class TodoListFragment : Fragment() {

    lateinit var list: MutableList<Modeltodo>
    lateinit var recyclerView: RecyclerView
    lateinit var adapterTodo: AdapterTodo
    lateinit var TodoSList:MutableList<Modeltodo>
    lateinit var calendarview: MaterialCalendarView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calendarview = view.findViewById(R.id.calendarView)

        recyclerView = view.findViewById(R.id.recyclerview)
        adapterTodo = AdapterTodo(null)
        recyclerView.adapter = adapterTodo


      


        calendarview.selectedDate = CalendarDay.today()
        //getTodlistfromDB()
        calendarview.setOnDateChangedListener { widget, date, selected ->
            calendar.set(DAY_OF_MONTH,date.day)
            calendar.set(MONTH,date.month-1)
            calendar.set(YEAR,date.year)
            getTodlistfromDB()
        }

        adapterTodo.setOnActionListener = object:AdapterTodo.SetOnActionListener{
            override fun onAcionDelete(position: Int, todo: Modeltodo) {
                TodoSList.remove(todo)
                adapterTodo.notifyItemRemoved(position)
                adapterTodo.notifyDataSetChanged()
                 MyDataBase.getInstance(requireContext()).dao_todo().deletetodo(todo)
                getTodlistfromDB()
                Toast.makeText(requireContext(),"done",Toast.LENGTH_LONG)
            }

            override fun onAcionUbdate(position: Int, todo: Modeltodo) {
                val intent = Intent(requireContext(),Ubdate::class.java)

                intent.putExtra("position",position)
                intent.putExtra("todoo",todo)
                startActivity(intent)

            }
        }






    }

    override fun onResume() {
        super.onResume()
       // getTodlistfromDB()

    }
    var calendar = Calendar.getInstance()

    fun getTodlistfromDB(){
    if(context == null)return
        TodoSList = MyDataBase.getInstance(requireContext()).
        dao_todo().
        getallTodosByDate(calendar.clearTime().time)
        Log.e("getTodlistfromDB", "length: ${TodoSList.size}")
        adapterTodo.changedate(TodoSList.toMutableList())

    }

    }

