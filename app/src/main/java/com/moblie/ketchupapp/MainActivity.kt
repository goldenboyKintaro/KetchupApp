package com.moblie.ketchupapp

import android.app.SearchManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.ActivityNavigator
import dagger.hilt.android.AndroidEntryPoint

import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.moblie.ketchupapp.ui.views.MaterialSearchView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity  : AppCompatActivity() {

    val searchView: MaterialSearchView by lazy { findViewById(R.id.search_view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val controller = Navigation.findNavController(findViewById(R.id.mainAppContainer))
        findViewById<BottomNavigationView>(R.id.bottomNavigationView).setupWithNavController(controller)

        findViewById<MaterialSearchView>(R.id.search_view).setOnQueryTextListener(object: MaterialSearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                triggerSearch(query, Bundle().apply {
                    putString(SearchManager.QUERY, query)
                })
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

        })
    }
}
