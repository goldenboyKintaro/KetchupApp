package com.moblie.ketchupapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moblie.ketchupapp.room.model.CacheModel

@Dao
interface CacheModelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(caches: List<CacheModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cache: CacheModel)


    @Query("SELECT * FROM cache_table WHERE id = :query")
    fun get(query: String): CacheModel?

    @Query("DELETE FROM cache_table")
    suspend fun clearAll()
}