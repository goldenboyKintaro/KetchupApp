package com.moblie.ketchupapp.base

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.moblie.ketchupapp.model.*
import com.moblie.ketchupapp.room.KetchupDatabase
import com.moblie.ketchupapp.room.model.CacheModel
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeUnit

@ExperimentalPagingApi
abstract class BaseRemoteMediator<T: Any>(val database: KetchupDatabase) : RemoteMediator<Int, T>() {

    protected val videoDao = database.videoDao()
    private val cacheModelDao = database.cacheDao()
    private val remoteKeyDao = database.remoteKeyDao()
    protected val pageDao = database.pageDao()

    abstract val tag : String

    @ExperimentalPagingApi
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, T>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                // In this example, you never need to prepend, since REFRESH
                // will always load the first page in the list. Immediately
                // return, reporting end of pagination.
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val remoteKey = database.withTransaction {
                        remoteKeyDao.remoteKeyByQuery(tag)
                    }
                    remoteKey?.nextPageKey
                }
            }

            val pageNo = loadKey ?: 1

            var transactions = true

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    pageDao.clearPage(tag, pageNo)
                    remoteKeyDao.deleteByQuery(tag)
                }

                transactions = onBackedCall(loadKey)()
                if(!transactions){
                    remoteKeyDao.insertOrReplace(RemoteKey(tag, (loadKey ?: 1) + 1))
                }
            }



            Log.i("Log","page is $loadKey")
            MediatorResult.Success(
                endOfPaginationReached = transactions
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    /**
    *  @return Transaction to be executed after page creation which also returns stop signal
    * */
    abstract suspend fun onBackedCall(loadKey: Int?): suspend () -> Boolean


    @ExperimentalPagingApi
    override suspend fun initialize(): InitializeAction {
        val cacheTimeout = TimeUnit.HOURS.convert(1, TimeUnit.MILLISECONDS)

        val cache = database.withTransaction {
            return@withTransaction cacheModelDao.get(tag)
        }

        val lastUpdate = cache?.lastUpdate ?: System.currentTimeMillis() + cacheTimeout + 1
        return if (System.currentTimeMillis() -  lastUpdate>= cacheTimeout)
        {
            // Cached data is up-to-date, so there is no need to re-fetch
            // from the network.
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            // Need to refresh cached data from network; returning
            // LAUNCH_INITIAL_REFRESH here will also block RemoteMediator's
            // APPEND and PREPEND from running until REFRESH succeeds.
            cacheModelDao.insert(CacheModel(tag, System.currentTimeMillis()))
            remoteKeyDao.insertOrReplace(RemoteKey(tag, 1))
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }
}