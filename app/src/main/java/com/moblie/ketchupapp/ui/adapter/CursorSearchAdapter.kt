package com.moblie.ketchupapp.ui.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.FilterQueryProvider
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import com.moblie.ketchupapp.R

/**
 * Created by mauker on 19/04/2016.
 *
 * Default adapter used for the suggestion/history ListView.
 */
class CursorSearchAdapter @JvmOverloads constructor(
        context: Context,
        private val searches: ArrayList<SearchHistoryItem>,
        flags: Int = 0
) : RecyclerView.Adapter<CursorSearchAdapter.SearchHistoryItemHolder>() {

    inner class SearchHistoryItemHolder(val root: View) : RecyclerView.ViewHolder(root){
        private val ivIcon: ImageView = root.findViewById(R.id.iv_icon)
        private val tvContent: TextView = root.findViewById(R.id.tv_str)

        fun bindItem(item: SearchHistoryItem) {

            tvContent.text = item.text
            tvContent.setTextColor(textColor)

            val iconRes = if (item.isHistory) historyIcon else suggestionIcon
            ivIcon.setImageResource(iconRes)
        }
    }

    fun setData(newSearches: List<SearchHistoryItem>) {
        val diffCallback = DiffCallback(searches, newSearches)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        searches.clear()
        searches.addAll(newSearches)
        diffResult.dispatchUpdatesTo(this)
    }

    var onItemClick: ((position: Int) -> Unit)? = null
    var textColor = Color.WHITE
    var historyIcon = R.drawable.clock_rotate_left
    var suggestionIcon = R.drawable.magnifying_glass_solid

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHistoryItemHolder {
        return SearchHistoryItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: SearchHistoryItemHolder, position: Int) {
        holder.root.setOnClickListener {
            onItemClick?.let { it(position) }
        }
        holder.bindItem(searches[position])
    }

    fun getItemAt(position: Int) = searches[position]

    override fun getItemCount() =
        searches.size

    private inner class DiffCallback(private val oldList: List<SearchHistoryItem>, private val newList: List<SearchHistoryItem>) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
            val (value, name) = oldList[oldPosition]
            val (value1, name1) = newList[newPosition]

            return name == name1 && value == value1
        }

        @Nullable
        override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
            return super.getChangePayload(oldPosition, newPosition)
        }
    }
}

data class SearchHistoryItem(val text: String, val isHistory: Boolean)
