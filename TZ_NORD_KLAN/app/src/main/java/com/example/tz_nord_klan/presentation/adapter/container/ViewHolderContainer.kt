package com.example.tz_nord_klan.presentation.adapter.container

import android.view.View
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tz_nord_klan.R

class ViewHolderContainer(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val name = itemView.findViewById<TextView>(R.id.nameContainer)
    val description =  itemView.findViewById<TextView>(R.id.descriptionContainer)
//    val radio = itemView.findViewById<RadioButton>(R.id.itemContainer)


}