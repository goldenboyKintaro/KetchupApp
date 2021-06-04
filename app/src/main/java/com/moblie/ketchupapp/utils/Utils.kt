package com.moblie.ketchupapp.utils

import com.moblie.ketchupapp.api.VideoPage
import com.moblie.ketchupapp.model.VideoModel

object Utils {
    fun toVideoModel(result: VideoPage) = result.videos.mapIndexed { i, v ->
        val matcher = Environment.NUMBER_REGEX.matcher(v.url)
        return@mapIndexed VideoModel(
            title = v.title,
            preview = result.previews[i],
            length = v.length,
            id = if (matcher.find()) {
                matcher.group().toLong()
            } else {
                0
            }
        )
    }
}