package com.moblie.ketchupapp.ui.viewmodels

import androidx.lifecycle.*
import androidx.paging.ExperimentalPagingApi
import androidx.paging.cachedIn
import com.moblie.ketchupapp.repository.ItemsRepository
import com.moblie.ketchupapp.repository.SearchRepository
import com.moblie.ketchupapp.room.model.CategoriesModel
import com.moblie.ketchupapp.ui.adapter.SimpleItemAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SearchFragmentVM @Inject constructor(val repository: ItemsRepository): ViewModel() {

    fun getCategories() = repository.getCategories().asLiveData(viewModelScope.coroutineContext)

    fun getGirls() = repository.getGirls().asLiveData(viewModelScope.coroutineContext)

}