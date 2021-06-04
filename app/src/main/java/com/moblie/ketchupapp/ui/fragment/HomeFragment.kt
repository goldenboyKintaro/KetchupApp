package com.moblie.ketchupapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.moblie.ketchupapp.R
import com.moblie.ketchupapp.base.BaseFragment
import com.moblie.ketchupapp.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bindings = FragmentHomeBinding.inflate(inflater)
        return bindings.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}