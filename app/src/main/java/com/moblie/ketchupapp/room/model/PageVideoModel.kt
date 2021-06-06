package com.moblie.ketchupapp.room.model

import androidx.room.Entity
import androidx.room.ForeignKey
import com.moblie.ketchupapp.utils.PageTag


@Entity(
    tableName = "page_video_model",
    primaryKeys = ["pageNo", "tag", "pageIndex"])
data class PageVideoModel(
    val pageNo: Int,
    val pageIndex: Int,
    val videoId: Long,
    val tag: String
)