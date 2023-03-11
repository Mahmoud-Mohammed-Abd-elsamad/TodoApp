package com.example.totdo

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.totdo.DataBase.Modeltodo
import com.example.totdo.DataBase.MyDataBase
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*

class Add_bottom_sheet:BottomSheetDialogFragment() {

    lateinit var textViewtitle: TextView
    lateinit var textInputLayouttitle: TextInputLayout
    lateinit var editTexttitle: EditText
    lateinit var textInputLayoutdiscription: TextInputLayout
    lateinit var editTextdiscription: EditText
    lateinit var textViewchosedate: TextView
    lateinit var addbutton:Button
    val cal = Calendar.getInstance()




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_add,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initviews()

        textViewchosedate.setOnClickListener(object :View.OnClickListener{
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onClick(p0: View?) {
                startDateBicker()
            }
        })

        addbutton.setOnClickListener(object :View.OnClickListener{
            override fun onClick(p0: View?) {
                if (validateBottomsheet()){

                    val titel:String = textInputLayouttitle.editText?.text.toString()
                    val description:String = textInputLayoutdiscription.editText?.text.toString()
//                validateBottomsheet()

                    insertTodo(titel,description)
                    todoAddlisner?.todoAdd()
                }


            }
        })

    }

    private fun insertTodo(titel:String,decription:String) {
         var newtodo = Modeltodo(
             name = titel ,
             details = decription,
             isdone = false,
             date = cal.clearTime().time )

         MyDataBase.getInstance(requireContext()).dao_todo().addTodo(newtodo)
        todoAddlisner?.todoAdd()
        dismiss()
    }

     var todoAddlisner : TodoAddlisner? = null
    interface TodoAddlisner{
        fun todoAdd()

    }

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.N)
    private fun startDateBicker() {
        textViewchosedate.text = SimpleDateFormat("dd.MM.yyyy").format(System.currentTimeMillis())



        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd.MM.yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            textViewchosedate.text = sdf.format(cal.time)

        }

        textViewchosedate.setOnClickListener {
            DatePickerDialog(requireContext(), dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }
    }



    private fun initviews() {
        textViewtitle = requireView().findViewById(R.id.task_title)
        textInputLayouttitle = requireView().findViewById(R.id.TextInputEditText_title)
        editTexttitle = requireView().findViewById(R.id.EditText_title)
        textInputLayoutdiscription = requireView().findViewById(R.id.TextInputEditText_discription)
        editTextdiscription = requireView().findViewById(R.id.EditText_discription)
        textViewchosedate = requireView().findViewById(R.id.textview_chosedata)
        addbutton = requireView().findViewById(R.id.add_task_button)
    }

    private fun validateBottomsheet():Boolean {

        var isvalidate = true


        if (textInputLayouttitle.editText?.text.isNullOrBlank()) {
            textInputLayouttitle.error = "valid data"
            isvalidate = false
        } else {
            textInputLayouttitle.error = null
        }
        if (textInputLayoutdiscription.editText?.text.isNullOrBlank()) {
            textInputLayoutdiscription.error = "valid data"
            isvalidate = false
        } else {
            textInputLayoutdiscription.error = null
        }
     return isvalidate
    }
}



