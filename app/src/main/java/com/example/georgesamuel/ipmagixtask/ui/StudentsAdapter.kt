package com.example.georgesamuel.ipmagixtask.ui

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.georgesamuel.ipmagixtask.R
import com.example.georgesamuel.ipmagixtask.model.Data
import com.example.georgesamuel.ipmagixtask.model.StudentResponse
import kotlinx.android.synthetic.main.item_student.view.*

class StudentsAdapter(context: Context): RecyclerView.Adapter<StudentsAdapter.MyViewHolder>() {
    var list : List<Data> = listOf()
    private var context: Context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = list[position].studentName
        holder.id.text = list[position].studentId.toString()
        if(position % 2 != 0){
            holder.container.setBackgroundColor(ContextCompat.getColor(context, R.color.gray))
        }
    }

    fun setItems(list : List<Data>){
        this.list = list
        notifyDataSetChanged()
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val name: TextView = view.stdName
        val id: TextView = view.stdId
        val container: LinearLayout = view.container
    }
}