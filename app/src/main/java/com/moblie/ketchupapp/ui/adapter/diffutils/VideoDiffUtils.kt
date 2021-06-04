package com.moblie.ketchupapp.ui.adapter.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.moblie.ketchupapp.model.VideoModel

object VideoDiffUtils : DiffUtil.ItemCallback<VideoModel>() {
    override fun areItemsTheSame(oldItem: VideoModel, newItem: VideoModel): Boolean {
        // Id is unique.
        return oldItem.preview == newItem.preview
    }

    override fun areContentsTheSame(oldItem: VideoModel, newItem: VideoModel): Boolean {
        return oldItem == newItem
    }
}
