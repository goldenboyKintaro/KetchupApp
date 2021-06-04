package com.moblie.ketchupapp.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "girls"
)
data class GirlsModel(
    @PrimaryKey
    val text: String,
    val image: String,
    val url: String
)