package com.example.android.varsfit.calender

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.android.varsfit.R

public class CalendarViewHolder(itemView: View, onItemListener: CalendarAdapter.OnItemListener) :
    RecyclerView.ViewHolder(itemView), View.OnClickListener {
    val dayOfMonth: TextView
    val cell:ConstraintLayout
    val cell_bg:ConstraintLayout

    private val onItemListener: CalendarAdapter.OnItemListener

    init {
        dayOfMonth = itemView.findViewById(R.id.cellDayText)
        cell = itemView.findViewById(R.id.cell)
        cell_bg = itemView.findViewById(R.id.cell_bg)
        this.onItemListener = onItemListener
        itemView.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        onItemListener.onItemClick(adapterPosition, dayOfMonth.text as String)
    }
}
