package com.moblie.ketchupapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment(){
    fun setupToolBar(toolbar: Toolbar) {
        (this.activity as AppCompatActivity).setSupportActionBar(toolbar)
    }
}
