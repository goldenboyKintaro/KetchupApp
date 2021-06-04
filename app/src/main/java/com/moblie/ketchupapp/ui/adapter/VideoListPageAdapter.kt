package com.moblie.ketchupapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.moblie.ketchupapp.ui.adapter.viewholder.VideoViewHolder
import com.moblie.ketchupapp.databinding.ItemVideoModelBinding
import com.moblie.ketchupapp.model.VideoModel

class VideoListPageAdapter(diffCallback: DiffUtil.ItemCallback<VideoModel>) : PagingDataAdapter<VideoModel, VideoViewHolder>(diffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VideoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemVideoModelBinding.inflate(inflater)
        return VideoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val item = getItem(position)
        // Note that item may be null. ViewHolder must support binding a
        // null item as a placeholder.
        holder.bind(item)
    }

}