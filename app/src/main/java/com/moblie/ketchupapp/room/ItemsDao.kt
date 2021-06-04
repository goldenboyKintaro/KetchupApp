package com.moblie.ketchupapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moblie.ketchupapp.api.PageItem
import com.moblie.ketchupapp.model.VideoModel
import com.moblie.ketchupapp.room.model.CategoriesModel
import com.moblie.ketchupapp.room.model.GirlsModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCategories(items: List<CategoriesModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllGirls(items: List<GirlsModel>)

    @Query("select * from categories")
    fun getCategories(): Flow<List<CategoriesModel>>

    @Query("select * from girls")
    fun getGirls(): Flow<List<GirlsModel>>
}