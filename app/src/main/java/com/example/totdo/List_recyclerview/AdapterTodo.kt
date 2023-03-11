package com.example.totdo.List_recyclerview

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.totdo.DataBase.Modeltodo
import com.example.totdo.R

class AdapterTodo(var list: MutableList<Modeltodo>?):RecyclerView.Adapter<AdapterTodo.viewholder>(){




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        var view:View = LayoutInflater.from(parent.context).inflate(R.layout.todo_item,parent,false)
    return viewholder(view)
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val todooo = list!!.get(position)
       holder.title_textview_item.setText(list!!.get(position).name)
        holder.descriotion_textview_item.setText(list!!.get(position).details)
        if(setOnActionListener!=null){
        holder.right_view.setOnClickListener {
            setOnActionListener?.onAcionDelete(position,todooo)
        }
            }
    holder.image_textview_item.setOnClickListener {
        setOnActionListener?.onAcionUbdate(position,todooo )
    }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun changedate(newlist:MutableList<Modeltodo>){
        list = newlist
        notifyDataSetChanged() // notify  adater that day is canged
    }

    override fun getItemCount(): Int {
    return list?.size ?: 0
    }

    var setOnActionListener : SetOnActionListener? = null
    interface SetOnActionListener{
        fun onAcionDelete(position: Int,todo:Modeltodo)
        fun onAcionUbdate(position: Int,todo:Modeltodo)
    }


    class viewholder(itemView:View): RecyclerView.ViewHolder(itemView){

        var title_textview_item = itemView.findViewById<TextView>(R.id.title_textview_item_todo)
        var descriotion_textview_item = itemView.findViewById<TextView>(R.id.description_textview_item_todo)
        var image_textview_item = itemView.findViewById<ImageView>(R.id.imageView_item_todo)
        var right_view = itemView.findViewById<ImageView>(R.id.right_view)
    }
}