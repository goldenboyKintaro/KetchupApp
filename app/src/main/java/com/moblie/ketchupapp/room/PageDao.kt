package com.moblie.ketchupapp.room

import androidx.room.*
import com.moblie.ketchupapp.room.model.PageVideoModel

@Dao
interface PageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun bind(page: List<PageVideoModel>)

    @Query("DELETE FROM page_video_model WHERE tag = :tag")
    suspend fun clearPage(tag: String)
}