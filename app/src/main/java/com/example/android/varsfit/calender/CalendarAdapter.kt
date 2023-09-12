package com.example.android.varsfit.calender

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.android.varsfit.R

class CalendarAdapter(
    private val daysOfMonth: ArrayList<Pair<String, String>>,
    private val onItemListener: OnItemListener
) :
    RecyclerView.Adapter<CalendarViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.calendar_cell, parent, false)

        return CalendarViewHolder(view, onItemListener)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {

        if(daysOfMonth[position].first!="") {
            holder.cell_bg.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.calender_bg)
            holder.cell.setBackgroundColor(Color.parseColor(daysOfMonth[position].second))
        }
        holder.dayOfMonth.text = daysOfMonth[position].first
    }

    override fun getItemCount(): Int {
        return daysOfMonth.size
    }

    interface OnItemListener {
        fun onItemClick(position: Int, dayText: String?)
    }
}
