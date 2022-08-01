package com.example.tz_nord_klan.presentation.adapter.event

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tz_nord_klan.R
import com.example.tz_nord_klan.data.entity.EventWithUser
import com.example.tz_nord_klan.presentation.adapter.Interface.InterfaceEvent
import java.lang.RuntimeException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.time.Duration.Companion.days

class EventAdapter(private val list: List<EventWithUser>, private val interfaceEvent: InterfaceEvent) : RecyclerView.Adapter<EventViewHolder>() {

    private val nowDate = Calendar.getInstance()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val layout = when(viewType){
            ACTIVITY -> R.layout.event_item_used
            NOT_ACTIVITY -> R.layout.event_item
            else -> throw RuntimeException("EventAdapter $viewType")
        }
        return EventViewHolder(LayoutInflater.from(parent.context).inflate(layout, parent, false))
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {

        holder.name.text = list[position].event.nameEvent
        holder.startTime.text = convertLongToTime(list[position].event.timeEventStart)
        holder.endTine.text = convertLongToTime(list[position].event.timeEventEnd)


        holder.itemView.setOnClickListener {
            interfaceEvent.onClickEvent(list[position])
        }
    }

    private fun convertLongToTime(time: Long): String = String.format("%02d:%02d", time / 60, time % 60)

    override fun getItemViewType(position: Int): Int {
        return if (list[position].event.dateEvent.date.days < nowDate.time.date.days
            || list[position].event.dateEvent.month < nowDate.time.month
            || list[position].event.dateEvent.year < nowDate.time.year){
            NOT_ACTIVITY
        } else{
            ACTIVITY
        }
    }

    override fun getItemCount(): Int = list.size

    companion object{
        private const val ACTIVITY = 1
        private const val NOT_ACTIVITY = 0
    }
}