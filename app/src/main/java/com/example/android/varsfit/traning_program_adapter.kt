package com.example.android.varsfit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.io.Serializable

class traning_program_adapter(
    private val context: Context,
    private val mapData: Map<String, Any>,
    private val whatToShow: String,
) : RecyclerView.Adapter<traning_program_adapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.trainer_image)
        val name = itemView.findViewById<TextView>(R.id.trainer_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.training_program_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if(mapData[mapData.keys.elementAt(position)] is Map<*, *>) {
            val map: Map<String, Any> = mapData[mapData.keys.elementAt(position)] as Map<String, Any>

            val courseimage = map["image"].toString()

            Glide.with(holder.itemView).load(courseimage).fitCenter().into(holder.imageView)
            holder.name.text = map["name"].toString()

            holder.itemView.setOnClickListener {
                val intent = Intent(context, show_exercise::class.java)
                val bundle = Bundle()
                bundle.putSerializable("mapData", map as Serializable)
                bundle.putString("whatToShow",whatToShow)
                intent.putExtra("bundle", bundle)
                context.startActivity(intent)
            }
        }

    }

    override fun getItemCount(): Int {
        return mapData.size
    }
}