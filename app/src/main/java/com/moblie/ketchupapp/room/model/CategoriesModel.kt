package com.moblie.ketchupapp.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "categories"
)
data class CategoriesModel(
    @PrimaryKey
    val text: String,
    val image: String,
    val url: String
)