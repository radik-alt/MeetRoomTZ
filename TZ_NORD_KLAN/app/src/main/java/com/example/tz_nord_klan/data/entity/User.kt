package com.example.tz_nord_klan.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User(
    @PrimaryKey(autoGenerate = true)
    val idUser: Long?,
    val nameUser: String,
    val hashPassword: String
)
