package com.example.tz_nord_klan.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Container")
data class Container(
    @PrimaryKey(autoGenerate = true)
    val idContainer: Long?,
    val nameRoom: String,
    val isUsed :Boolean,
    val descriptionRoom: String,
)