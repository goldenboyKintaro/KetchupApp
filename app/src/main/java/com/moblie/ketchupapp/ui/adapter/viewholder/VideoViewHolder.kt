package com.moblie.ketchupapp.ui.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.moblie.ketchupapp.databinding.ItemVideoModelBinding
import com.moblie.ketchupapp.model.VideoModel

class VideoViewHolder(private val binding: ItemVideoModelBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: VideoModel?) {
        with(binding){
            video = item
        }
    }
}
