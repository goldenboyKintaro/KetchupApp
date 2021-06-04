package com.moblie.ketchupapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.moblie.ketchupapp.model.*
import com.moblie.ketchupapp.room.model.CacheModel
import com.moblie.ketchupapp.room.model.CategoriesModel
import com.moblie.ketchupapp.room.model.GirlsModel
import com.moblie.ketchupapp.room.model.PageVideoModel

@Database(entities = [
    VideoModel::class,
    CacheModel::class,
    RemoteKey::class,
    PageVideoModel::class,
    SearchHistory::class,
    CategoriesModel::class,
    GirlsModel::class], version = 1, exportSchema = false)
abstract class KetchupDatabase : RoomDatabase() {
    abstract fun videoDao(): VideoModelDao
    abstract fun cacheDao(): CacheModelDao
    abstract fun remoteKeyDao(): RemoteKeyDao
    abstract fun pageDao(): PageDao
    abstract fun historyDao(): SearchHistoryDao
    abstract fun itemDao() : ItemsDao
}

