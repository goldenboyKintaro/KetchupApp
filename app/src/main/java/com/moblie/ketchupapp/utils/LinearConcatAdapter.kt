package com.moblie.ketchupapp.utils

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moblie.ketchupapp.base.BaseConcatHolder

class CustomLayoutConcatAdapter(
    private val adapter: RecyclerView.Adapter<*>,
    private val layoutManager: RecyclerView.LayoutManager,
    private val decoration: RecyclerView.ItemDecoration,
    private val pool: RecyclerView.RecycledViewPool
    ):
    RecyclerView.Adapter<BaseConcatHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val rv = RecyclerView(parent.context)
        rv.addItemDecoration(decoration)
        rv.layoutManager = layoutManager
        rv.setRecycledViewPool(pool)
        return ConcatViewHolder(rv)
    }

    override fun getItemCount(): Int  = 1

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when(holder){
            is ConcatViewHolder -> holder.bind(adapter)
            else -> throw IllegalArgumentException("No viewholder to show this data, did you forgot to add it to the onBindViewHolder?")
        }
    }

    inner class ConcatViewHolder(private val x: RecyclerView): BaseConcatHolder<RecyclerView.Adapter<*>>(x){
        override fun bind(adapter: RecyclerView.Adapter<*>) {
            x.adapter = adapter
        }
    }
}