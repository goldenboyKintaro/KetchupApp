package com.moblie.ketchupapp.room.model

import androidx.room.Entity

@Entity(tableName = "cache_table", primaryKeys = ["id"])
data class CacheModel(val id: String, val lastUpdate: Long)