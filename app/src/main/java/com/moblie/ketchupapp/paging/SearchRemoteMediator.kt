package com.moblie.ketchupapp.paging

import androidx.paging.ExperimentalPagingApi
import com.moblie.ketchupapp.api.HQVideoService
import com.moblie.ketchupapp.base.BaseRemoteMediator
import com.moblie.ketchupapp.room.model.PageVideoModel
import com.moblie.ketchupapp.model.VideoModel
import com.moblie.ketchupapp.room.KetchupDatabase
import com.moblie.ketchupapp.utils.PageTag
import com.moblie.ketchupapp.utils.Utils

@ExperimentalPagingApi
class SearchRemoteMediator (
    private var query: String,
    database: KetchupDatabase,
    private val backend: HQVideoService
) : BaseRemoteMediator<VideoModel>(database) {


    override val tag: String = "${SearchRemoteMediator::class.java.name}/${query}"

    override suspend fun onBackedCall(loadKey: Int?): suspend () -> Boolean {
        val pageNumber = loadKey ?: 1
        val result = backend.search(query, pageNumber)

        val videoModel = Utils.toVideoModel(result)

        return suspend {
            videoDao.insertAll(videoModel)
            pageDao.bind(videoModel.map { PageVideoModel(pageNumber, it.id, PageTag.SEARCH(query)) })
            videoModel.isEmpty()
        }
    }

}