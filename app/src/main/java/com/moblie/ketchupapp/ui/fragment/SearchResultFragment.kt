package com.moblie.ketchupapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.moblie.ketchupapp.base.BaseVideoListFragment
import com.moblie.ketchupapp.model.VideoModel
import com.moblie.ketchupapp.ui.viewmodels.SearchResultsVM
import dagger.hilt.android.AndroidEntryPoint

private const val ARG_QUERY = "query"

@ExperimentalPagingApi
@AndroidEntryPoint
class SearchResultFragment : BaseVideoListFragment() {

    private val mViewModel: SearchResultsVM by viewModels()

    private var query: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            query = it.getString(ARG_QUERY)
        }
    }

    companion object {
        @JvmStatic fun newInstance(query: String) =
            SearchResultFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_QUERY, query)
                }
            }
    }

    @ExperimentalPagingApi
    override val listData: LiveData<PagingData<VideoModel>>
        get() = mViewModel.getSearchResults(query ?: "")
    override val header: RecyclerView.Adapter<*>?
        get() = null
}