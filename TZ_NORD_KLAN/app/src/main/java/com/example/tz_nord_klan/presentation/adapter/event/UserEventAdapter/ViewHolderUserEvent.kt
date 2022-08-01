package com.example.tz_nord_klan.presentation.adapter.event.UserEventAdapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tz_nord_klan.R

class ViewHolderUserEvent(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val name = itemView.findViewById<TextView>(R.id.nameUserEvent)

}