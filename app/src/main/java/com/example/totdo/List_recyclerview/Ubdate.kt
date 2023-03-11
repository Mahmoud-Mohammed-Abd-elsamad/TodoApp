package com.example.totdo.List_recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.totdo.DataBase.Modeltodo
import com.example.totdo.DataBase.MyDataBase
import com.example.totdo.R
import com.google.android.material.textfield.TextInputLayout

class Ubdate : AppCompatActivity() {


    lateinit var textInputLayouttitle: TextInputLayout
    lateinit var editTexttitle: EditText
    lateinit var textInputLayoutdiscription: TextInputLayout
    lateinit var editTextdiscription: EditText
    lateinit var choseDate: TextView

    lateinit var ubdateButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubdate)

        editTexttitle = findViewById(R.id.EditText_ubdate_title)
        textInputLayouttitle = findViewById(R.id.TextInputEditText_ubdate_title)
        textInputLayoutdiscription = findViewById(R.id.TextInputEditText_ubdate_discription)
        editTextdiscription = findViewById(R.id.EditText_ubdate_discription)
        ubdateButton = findViewById(R.id.ubdate_task_button)
        choseDate = findViewById(R.id.textview_ubdate_chosedata)



                val todoUbdate = intent.getSerializableExtra("todoo") as? Modeltodo
                if (todoUbdate != null) {
                    editTexttitle.setText(todoUbdate.name)
                    editTextdiscription.setText(todoUbdate.details)
                    choseDate.setText(todoUbdate.date.toString())

                }
        ubdateButton.setOnClickListener(object:View.OnClickListener{
            override fun onClick(p0: View?) {
                todoUbdate?.name = editTexttitle.text.toString()
                todoUbdate?.details = editTextdiscription.text.toString()
             //   todoUbdate?.date = choseDate.text.toString().toLong()
                MyDataBase.getInstance(this@Ubdate).dao_todo().updatetodo(todoUbdate!!)
                MyDataBase.getInstance(this@Ubdate).dao_todo().getallTodosByDate(todoUbdate.date!!)
                finish()
            }
        }
        )


    }
}