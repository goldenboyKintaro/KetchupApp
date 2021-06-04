package com.moblie.ketchupapp.paging

import androidx.paging.*
import com.moblie.ketchupapp.api.HQVideoService
import com.moblie.ketchupapp.base.BaseRemoteMediator
import com.moblie.ketchupapp.model.*
import com.moblie.ketchupapp.room.KetchupDatabase
import com.moblie.ketchupapp.room.model.PageVideoModel
import com.moblie.ketchupapp.utils.PageTag
import com.moblie.ketchupapp.utils.Utils.toVideoModel

@ExperimentalPagingApi
class HomeRemoteMediator (
    database: KetchupDatabase,
    private val backend: HQVideoService
    ) : BaseRemoteMediator<VideoModel>(database) {

    override val tag: String = HomeRemoteMediator::class.java.name

    override suspend fun onBackedCall(loadKey: Int?): suspend () -> Boolean {
        val pageNumber = loadKey ?: 1
        val result = backend.getPage(pageNumber)

        val videoModel = toVideoModel(result)
        return suspend {
            videoDao.insertAll(videoModel)
            pageDao.bind(videoModel.map { PageVideoModel(pageNumber, it.id, PageTag.HOME) })
            videoModel.isEmpty()
        }
    }

}
