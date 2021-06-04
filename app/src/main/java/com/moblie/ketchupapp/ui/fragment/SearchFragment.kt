package com.moblie.ketchupapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.moblie.ketchupapp.MainActivity
import com.moblie.ketchupapp.base.BaseFragment
import com.moblie.ketchupapp.databinding.FragmentSearchBinding
import com.moblie.ketchupapp.ui.adapter.SimpleItemAdapter
import com.moblie.ketchupapp.ui.adapter.TitleAdapter
import com.moblie.ketchupapp.ui.decorations.EqualSpacingItemDecoration
import com.moblie.ketchupapp.ui.viewmodels.SearchFragmentVM
import com.moblie.ketchupapp.utils.DisplayUtils.toPx
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment() {

    private val mViewModel: SearchFragmentVM by viewModels()

    private lateinit var bindings: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindings = FragmentSearchBinding.inflate(inflater)
        bindings.searchBar.setOnClickListener {
            (this.activity as MainActivity).searchView.openSearch()
        }
        initRecyclerView()
        return bindings.root
    }

    private fun initRecyclerView() {
        val mCategoriesAdapter = SimpleItemAdapter()
        val mGirlsAdapter = SimpleItemAdapter()
        mViewModel.getCategories().observe(this.viewLifecycleOwner){list->
            list?.let { listItems ->
                mCategoriesAdapter.setData(listItems.map { SimpleItemAdapter.SimpleItem(it.text, it.image, it.url) })
            }
        }

        mViewModel.getGirls().observe(this.viewLifecycleOwner){ list->
            list?.let { listItems ->
                mGirlsAdapter.setData(listItems.map { SimpleItemAdapter.SimpleItem(it.text, it.image, it.url) })
            }
        }

        val container = bindings.container
        val layoutManager = GridLayoutManager(context,2)
        val concatAdapter = ConcatAdapter(
            TitleAdapter("Categories"),
            mCategoriesAdapter,
            TitleAdapter("Girls"),
            mGirlsAdapter
        )
        val tb = 16.toPx
        val lr = 8.toPx
        container.addItemDecoration(EqualSpacingItemDecoration(tb, lr, lr, tb))
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val secondTitlePos = mCategoriesAdapter.itemCount+1
                return when (position) {
                    0, secondTitlePos -> 2
                    else -> 1
                }
            }
        }
        container.layoutManager = layoutManager
        container.adapter = concatAdapter
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = SearchFragment()
    }
}