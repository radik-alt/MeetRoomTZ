package com.example.tz_nord_klan.data.db.utils

import androidx.room.TypeConverter
import java.util.*

class ConvertorDate {

    @TypeConverter
    fun dateToTimestamp(date: Date?):Long? {
        return if (date == null) null else date.time
    }

    @TypeConverter
    fun fromTimestamp(value: Long?): Date?{
        return if (value == null) null else Date(value)
    }

}