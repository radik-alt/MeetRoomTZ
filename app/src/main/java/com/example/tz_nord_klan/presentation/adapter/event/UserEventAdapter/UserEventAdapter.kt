package com.example.tz_nord_klan.presentation.adapter.event.UserEventAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tz_nord_klan.R
import com.example.tz_nord_klan.data.entity.User

class UserEventAdapter(private val listUser: List<User>) : RecyclerView.Adapter<ViewHolderUserEvent>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderUserEvent {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_userwithevent, parent, false)
        return ViewHolderUserEvent(view)
    }

    override fun onBindViewHolder(holder: ViewHolderUserEvent, position: Int) {

        holder.name.text = listUser[position].nameUser

    }

    override fun getItemCount(): Int = listUser.size

}