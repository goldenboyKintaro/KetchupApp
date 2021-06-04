package com.moblie.ketchupapp.ui.adapter

import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import com.moblie.ketchupapp.R
import com.moblie.ketchupapp.utils.DisplayUtils.toPx

class TitleAdapter(val title: String) : RecyclerView.Adapter<TitleAdapter.TitleViewHolder>() {

    class TitleViewHolder(val view: TextView) : RecyclerView.ViewHolder(view){
        fun bindTitle(title: String){
            view.text = title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleViewHolder {
        val textView = TextView(parent.context)
        textView.setTextAppearance(R.style.TextAppearance_KetchupApp_TitleText)
        textView.setPadding(16.toPx)
        return TitleViewHolder(textView)
    }

    override fun onBindViewHolder(holder: TitleViewHolder, position: Int) {
        holder.bindTitle(title)
    }

    override fun getItemCount() = 1

}