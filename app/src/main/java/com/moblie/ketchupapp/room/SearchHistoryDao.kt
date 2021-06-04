package com.moblie.ketchupapp.room

import android.view.inputmethod.InlineSuggestion
import androidx.room.*
import com.moblie.ketchupapp.model.SearchHistory
import com.moblie.ketchupapp.model.VideoModel

@Dao
interface SearchHistoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(searches: List<SearchHistory>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(search: SearchHistory)

    @Query("DELETE FROM search_history")
    suspend fun clearAll()

    @Query("DELETE FROM search_history where `query` = :suggestion")
    suspend fun remove(suggestion: String)

    @Query("DELETE FROM search_history where is_history = ${false}")
    suspend fun clearAllSuggestions()

    @Query("DELETE FROM search_history where is_history = ${true}")
    suspend fun clearAllHistory()

}