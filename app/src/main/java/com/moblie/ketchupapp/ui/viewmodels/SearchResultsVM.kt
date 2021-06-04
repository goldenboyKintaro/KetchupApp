package com.moblie.ketchupapp.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.cachedIn
import com.moblie.ketchupapp.paging.TopRemoteMediator
import com.moblie.ketchupapp.repository.SearchRepository
import com.moblie.ketchupapp.repository.TopRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchResultsVM @Inject constructor(handle: SavedStateHandle, private val repository: SearchRepository): ViewModel() {

    @ExperimentalPagingApi
    fun getSearchResults(query: String) = repository.getVideos(query).cachedIn(viewModelScope)
}