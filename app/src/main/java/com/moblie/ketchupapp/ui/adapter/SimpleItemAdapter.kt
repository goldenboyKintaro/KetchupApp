package com.moblie.ketchupapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.moblie.ketchupapp.databinding.ItemSimpleBinding

class SimpleItemAdapter : RecyclerView.Adapter<SimpleItemAdapter.ItemViewHolder>() {

    val data = ArrayList<SimpleItem>()

    fun setData(newItems: List<SimpleItem>) {
        val diffCallback = DiffCallback(data, newItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        data.clear()
        data.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

    class ItemViewHolder(private val bindings: ItemSimpleBinding) : RecyclerView.ViewHolder(bindings.root) {
        fun bind(item: SimpleItem) { bindings.simpleitem = item }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ItemSimpleBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount() = data.size

    private inner class DiffCallback(private val oldList: List<SimpleItem>, private val newList: List<SimpleItem>) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
            val (x, y) = oldList[oldPosition]
            val (x1, y1) = newList[newPosition]
            return y == y1 && x == x1
        }

        @Nullable
        override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
            return super.getChangePayload(oldPosition, newPosition)
        }
    }

    data class SimpleItem( val text: String, val image: String, val url: String)
}