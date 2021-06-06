package com.moblie.ketchupapp.room.model

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "my_daddy")
data class MyDaddyModel(
    val activationId: String,
    val urlPrefix: String,
    @PrimaryKey
    val mId: String
)