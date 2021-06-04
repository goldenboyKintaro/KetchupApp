package com.moblie.ketchupapp.model

import android.content.ContentUris
import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "SEARCH_HISTORY"
)
data class SearchHistory(
    val query: String,
    val  is_history: Boolean
){
    var insert_date = System.currentTimeMillis()

    @PrimaryKey(autoGenerate = true)
    var _id: Int = 0
}