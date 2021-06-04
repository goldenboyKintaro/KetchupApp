package com.moblie.ketchupapp.repository

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.room.withTransaction
import com.moblie.ketchupapp.api.HQVideoService
import com.moblie.ketchupapp.api.PageItem
import com.moblie.ketchupapp.room.KetchupDatabase
import com.moblie.ketchupapp.room.model.CategoriesModel
import com.moblie.ketchupapp.room.model.GirlsModel
import com.moblie.ketchupapp.ui.adapter.SimpleItemAdapter
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import java.lang.Exception
import javax.inject.Inject

class ItemsRepositoryImpl @Inject constructor(
    val database: KetchupDatabase,
    private val backend: HQVideoService
) : ItemsRepository {

    private val itemDao = database.itemDao()


    override fun getCategories() = itemDao
        .getCategories().map { list ->
            if(list.isEmpty()){
                val categories = backend.getCategories().items
                    .filter { it.isInitialized() }
                    .map cat@{
                        return@cat CategoriesModel(it.title, "https://${it.thumbnail}", it.url)
                    }
                database.withTransaction {
                    itemDao
                        .insertAllCategories(categories)
                }
                return@map categories
            }
            return@map list
        }

    override fun getGirls(): Flow<List<GirlsModel>> {
        return itemDao
            .getGirls().map { list ->
                if(list.isEmpty()){
                    val girls = backend.getGirls().items
                        .filter { it.isInitialized() }
                        .map girls@{
                        return@girls GirlsModel(it.title, "https://${it.thumbnail}", it.url)
                    }
                    database.withTransaction {
                        itemDao.insertAllGirls(girls)
                    }
                    return@map girls
                }
                return@map list
            }
    }

}