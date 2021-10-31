package com.vtpsales.vtpsales.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey
    val id : Int,
    val name : String,
    val email : String,
    val token : String,
    val phoneNumber : String,
    val role : String,
    val status : Int,
    val isActive : Int
)
