package com.moblie.ketchupapp.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.cachedIn
import com.moblie.ketchupapp.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewVideoFragmentVM @Inject constructor(handle: SavedStateHandle, private val repository: HomeRepository): ViewModel() {

    @ExperimentalPagingApi
    fun getHomePager() = repository.getVideos().cachedIn(viewModelScope)
}