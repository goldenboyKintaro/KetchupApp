package com.moblie.ketchupapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.moblie.ketchupapp.base.BaseVideoListFragment
import com.moblie.ketchupapp.model.VideoModel
import com.moblie.ketchupapp.paging.TopRemoteMediator.*
import com.moblie.ketchupapp.ui.viewmodels.NewVideoFragmentVM
import com.moblie.ketchupapp.ui.viewmodels.TopVideoFragmentVM
import dagger.hilt.android.AndroidEntryPoint

private const val ARG_CATEGORY = "category"

@ExperimentalPagingApi
@AndroidEntryPoint
class TopVideoHomeFragment : BaseVideoListFragment() {

    private val mViewModel: TopVideoFragmentVM by viewModels()

    private var category: Category? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            category = it.getString(ARG_CATEGORY)?.let { it1 -> Category.valueOf(it1) }
        }
    }

    companion object {
        @JvmStatic fun newInstance(category: Category) =
            TopVideoHomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_CATEGORY, category.toString())
                }
            }
    }

    @ExperimentalPagingApi
    override val listData: LiveData<PagingData<VideoModel>>
        get() = mViewModel.getTopPager(category ?: Category.ALL_TIME)
    override val header: RecyclerView.Adapter<*>?
        get() = null
}