package com.moblie.ketchupapp.paging

import androidx.paging.ExperimentalPagingApi
import com.moblie.ketchupapp.api.HQVideoService
import com.moblie.ketchupapp.base.BaseRemoteMediator
import com.moblie.ketchupapp.room.model.PageVideoModel
import com.moblie.ketchupapp.model.VideoModel
import com.moblie.ketchupapp.room.KetchupDatabase
import com.moblie.ketchupapp.utils.PageTag
import com.moblie.ketchupapp.utils.Utils.toVideoModel

@ExperimentalPagingApi
class TopRemoteMediator constructor(
    private val category: Category,
    database: KetchupDatabase,
    private val backend: HQVideoService
) : BaseRemoteMediator<VideoModel>(database) {

    enum class Category {
        ALL_TIME, MONTH, WEEK
    }

    override val tag: String = TopRemoteMediator::class.java.name

    override suspend fun onBackedCall(loadKey: Int?): suspend () -> Boolean {
        val pageNumber = loadKey ?: 1
        val result = when(category){
            Category.MONTH -> backend.topMonth(pageNumber)
            Category.WEEK -> backend.topWeek( pageNumber)
            Category.ALL_TIME -> backend.top( pageNumber)
        }

        val videoModel = toVideoModel(result)

        return suspend {
            videoDao.insertAll(videoModel)
            pageDao.bind(videoModel.map { PageVideoModel(pageNumber, it.id, PageTag.TOP(category)) })
            videoModel.isEmpty()
        }
    }

}