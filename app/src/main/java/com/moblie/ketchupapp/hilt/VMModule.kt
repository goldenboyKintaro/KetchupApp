package com.moblie.ketchupapp.hilt

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.moblie.ketchupapp.model.VideoModel
import com.moblie.ketchupapp.paging.HomeRemoteMediator
import com.moblie.ketchupapp.repository.*
import com.moblie.ketchupapp.room.KetchupDatabase
import com.moblie.ketchupapp.utils.Environment
import com.moblie.ketchupapp.utils.PageTag
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Named


@InstallIn(ViewModelComponent::class)
@Module
abstract class VMModule {

    @Binds
    @ViewModelScoped
    abstract fun getHomeRepository(repository: HomeRepositoryImpl) : HomeRepository


    @Binds
    @ViewModelScoped
    abstract fun getSearchRepository(repository: SearchRepositoryImpl) : SearchRepository

    @Binds
    @ViewModelScoped
    abstract fun getTopRepository(repository: TopRepositoryImpl) : TopRepository

    @Binds
    @ViewModelScoped
    abstract fun getItemsRepository(repository: ItemsRepositoryImpl) : ItemsRepository

}
