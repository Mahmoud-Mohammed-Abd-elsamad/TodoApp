package com.example.totdo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.example.totdo.DataBase.MyDataBase
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    lateinit var bottomNavigation:BottomNavigationView
    lateinit var addButton:FloatingActionButton
    var todoListFragment  = TodoListFragment()
    var todoSettingsFragment  = TodoSettingsFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //  MyDataBase.getInstance(this)

        bottomNavigation  = findViewById(R.id.todo_bottomNanigation)
        addButton = findViewById(R.id.add_floating_button)
        startFragment(TodoListFragment())
        addButton.setOnClickListener(object :View.OnClickListener{

            override fun onClick(p0: View?) {
                startAddButtomsheet()


            }

        })
        Add_bottom_sheet().todoAddlisner = object :Add_bottom_sheet.TodoAddlisner{
            override fun todoAdd() {

            }
        }

        bottomNavigation.setOnItemSelectedListener {
            if (it.itemId == R.id.todo_ic_list){
                startFragment(todoListFragment)
            }else{
                startFragment(todoSettingsFragment)
        }
        return@setOnItemSelectedListener true
        }
    }

    public fun startAddButtomsheet(){
        val bottomsheet = Add_bottom_sheet()

        bottomsheet.show(supportFragmentManager,"")
        bottomsheet.todoAddlisner = object :Add_bottom_sheet.TodoAddlisner{
            override fun todoAdd() {
                todoListFragment.getTodlistfromDB()
            }}


    }


    public fun startFragment(fragment:Fragment):Fragment{
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit()

        return fragment
    }

}
