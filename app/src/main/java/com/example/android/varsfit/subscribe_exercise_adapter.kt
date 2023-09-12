package com.example.android.varsfit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class subscribe_exercise_adapter (
    private val context: Context,
    private val mapData: Map<String, Any>,
) : RecyclerView.Adapter<subscribe_exercise_adapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.name_text)
        val reps = itemView.findViewById<TextView>(R.id.reps_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.subscribe_exercise_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

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