package com.moblie.ketchupapp.ui

import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentManager
import androidx.paging.ExperimentalPagingApi
import com.moblie.ketchupapp.R
import com.moblie.ketchupapp.ui.fragment.SearchResultFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchableActivity : AppCompatActivity() {

    @ExperimentalPagingApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searchable)
        var _query = "milf"
        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                _query = query
            }
        }
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragmentContainerView, SearchResultFragment.newInstance(_query))
        ft.commit()
    }
}