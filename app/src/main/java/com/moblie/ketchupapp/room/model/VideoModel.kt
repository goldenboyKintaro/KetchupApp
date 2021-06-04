package com.moblie.ketchupapp.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.moblie.ketchupapp.utils.Environment

@Entity(
    tableName = "video_model",

)
data class VideoModel(
    val title: String,
    val preview: String,
    val length: String,
    @PrimaryKey
    val id: Long
)