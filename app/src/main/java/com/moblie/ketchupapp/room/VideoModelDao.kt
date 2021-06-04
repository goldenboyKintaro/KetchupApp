package com.moblie.ketchupapp.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moblie.ketchupapp.model.VideoModel
import com.moblie.ketchupapp.utils.PageTag

@Dao
interface VideoModelDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(videos: List<VideoModel>)

    @Query("SELECT v.* FROM video_model v join  page_video_model vp on (vp.videoId = v.id and  vp.tag = :pageTag) ORDER BY id DESC")
    fun pagingSource(pageTag: String): PagingSource<Int, VideoModel>

    @Query("DELETE FROM video_model")
    suspend fun clearAll()


}
