package com.example.tz_nord_klan.data.entity

import androidx.room.Entity

@Entity(
    primaryKeys = [
        "idEvent", "idUser"
    ]
)
data class EventUserRef (
    val idEvent: Long,
    val idUser: Long
)