package com.moblie.ketchupapp.repository

import com.moblie.ketchupapp.room.model.CategoriesModel
import com.moblie.ketchupapp.room.model.GirlsModel
import kotlinx.coroutines.flow.Flow

interface ItemsRepository {
    fun getGirls(): Flow<List<GirlsModel>>
    fun getCategories(): Flow<List<CategoriesModel>>
}