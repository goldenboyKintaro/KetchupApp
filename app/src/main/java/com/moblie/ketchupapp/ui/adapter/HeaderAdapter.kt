package com.moblie.ketchupapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.moblie.ketchupapp.R
import com.moblie.ketchupapp.databinding.ItemHeaderViewBinding
import com.moblie.ketchupapp.ui.adapter.HeaderAdapter.HeaderViewHolder

class HeaderAdapter : RecyclerView.Adapter<HeaderViewHolder>() {
    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val bindings = ItemHeaderViewBinding.inflate(layoutInflater)
        return HeaderViewHolder(bindings.root)
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {}

    override fun getItemCount() = 1

}