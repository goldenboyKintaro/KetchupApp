package com.moblie.ketchupapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.moblie.ketchupapp.base.BaseVideoListFragment
import com.moblie.ketchupapp.model.VideoModel
import com.moblie.ketchupapp.ui.adapter.HeaderAdapter
import com.moblie.ketchupapp.ui.adapter.TitleAdapter
import com.moblie.ketchupapp.ui.viewmodels.NewVideoFragmentVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewVideoHomeFragment : BaseVideoListFragment() {

    private val mViewModel: NewVideoFragmentVM by viewModels()

    companion object {
        @JvmStatic
        fun newInstance() = NewVideoHomeFragment()
    }

    @ExperimentalPagingApi
    override val listData: LiveData<PagingData<VideoModel>>
        get() = mViewModel.getHomePager()
    override val header: RecyclerView.Adapter<*>
        get() = ConcatAdapter(TitleAdapter("Trending"), HeaderAdapter(), TitleAdapter("New"))
}