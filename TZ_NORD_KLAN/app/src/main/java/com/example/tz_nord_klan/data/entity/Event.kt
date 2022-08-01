package com.example.tz_nord_klan.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.tz_nord_klan.data.db.utils.ConvertorDate
import java.util.*

@Entity(tableName = "Event")
data class Event (
    @PrimaryKey(autoGenerate = true)
    val idEvent: Long?,
    val nameEvent: String,
    @TypeConverters(ConvertorDate::class)
    val dateEvent: Date,
    val timeEventStart: Long,
    val timeEventEnd: Long,
    val listEventId: Long,
)