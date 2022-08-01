package com.example.tz_nord_klan.presentation.adapter.event

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tz_nord_klan.R

class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val name = itemView.findViewById<TextView>(R.id.nameEvent)
    val startTime = itemView.findViewById<TextView>(R.id.dateEventStart)
    val endTine = itemView.findViewById<TextView>(R.id.dateEventEnd)

}