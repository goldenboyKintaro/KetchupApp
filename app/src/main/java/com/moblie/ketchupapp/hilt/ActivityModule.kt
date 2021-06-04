package com.moblie.ketchupapp.hilt

import com.moblie.ketchupapp.ui.adapter.VideoListPageAdapter
import com.moblie.ketchupapp.ui.adapter.diffutils.VideoDiffUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
class ActivityModule {

    @Provides
    fun homeAdapter() = VideoListPageAdapter(VideoDiffUtils)
}