package com.example.android.varsfit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.varsfit.SharedData.todaysScore

class exercise_adapter(
    private val context: Context,
    private val mapData: Map<String, Any>,
) : RecyclerView.Adapter<exercise_adapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.name_text)
        val reps = itemView.findViewById<TextView>(R.id.reps_text)
        val text1 = itemView.findViewById<TextView>(R.id.settext1)
        val text2 = itemView.findViewById<TextView>(R.id.settext2)
        val text3 = itemView.findViewById<TextView>(R.id.settext3)
        val checkbox1 = itemView.findViewById<CheckBox>(R.id.customCheckBox)
        val checkbox2 = itemView.findViewById<CheckBox>(R.id.customCheckBox2)
        val checkbox3 = itemView.findViewById<CheckBox>(R.id.customCheckBox3)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.exercise_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.text1.setOnClickListener {
            holder.checkbox1.isChecked = !holder.checkbox1.isChecked
            if(holder.checkbox1.isChecked) todaysScore++
            else todaysScore--
        }
        holder.text2.setOnClickListener {
            holder.checkbox2.isChecked = !holder.checkbox2.isChecked
            if(holder.checkbox2.isChecked) todaysScore++
            else todaysScore--
        }
        holder.text3.setOnClickListener {
            holder.checkbox3.isChecked = !holder.checkbox3.isChecked
            if(holder.checkbox3.isChecked) todaysScore++
            else todaysScore--
        }

        holder.checkbox1.setOnClickListener {
            if(holder.checkbox1.isChecked) todaysScore++
            else todaysScore--
        }
        holder.checkbox2.setOnClickListener {
            if(holder.checkbox2.isChecked) todaysScore++
            else todaysScore--
        }
        holder.checkbox3.setOnClickListener {
            if(holder.checkbox3.isChecked) todaysScore++
            else todaysScore--
        }

        if(mapData[mapData.keys.elementAt(position)] is Map<*, *>) {
            val map: Map<String, Any> = mapData[mapData.keys.elementAt(position)] as Map<String, Any>

            val image = map["image"].toString()

            holder.name.text = map["name"].toString()
            holder.reps.append(map["reps"].toString())

            holder.itemView.setOnClickListener {
                
            }
        }

    }

    override fun getItemCount(): Int {
        return mapData.size
    }
}