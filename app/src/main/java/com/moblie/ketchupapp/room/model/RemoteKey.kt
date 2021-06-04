package com.moblie.ketchupapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "remote_key"
)
data class RemoteKey(@PrimaryKey val label: String, val nextPageKey: Int?)
