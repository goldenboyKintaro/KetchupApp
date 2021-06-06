package com.moblie.ketchupapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moblie.ketchupapp.api.HQVideoService
import com.moblie.ketchupapp.api.MyDaddyService
import com.moblie.ketchupapp.api.MyDaddyStatusService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoActivityViewModel @Inject constructor(private val mdc: MyDaddyService, private val mdss: MyDaddyStatusService, val service: HQVideoService): ViewModel() {
    val url =  MutableLiveData<String>()

    fun getVideo(title: String, id: Long) {
        viewModelScope.launch {
            var y = service.getVideoFrameLink(id, title.replace(" ", "_")).url
            y = y.substring(19,y.length-1)
            val x = mdc.getVideo(y)
            val success = mdss.grantAccess(x.data.first.activationId, x.data.second).isSuccessful
            if(success){
                val pre = x.data.first.urlPrefix
                val mId = x.data.first.mId
                url.postValue("http://$pre.bigcdn.cc/pubs/$mId/1080.mp4")
            }
        }
    }

}