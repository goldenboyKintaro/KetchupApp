package com.moblie.ketchupapp.api.data

import androidx.annotation.Keep
import pl.droidsonroids.jspoon.annotation.Selector


class VideoItemPage @Keep constructor(){

     @Keep @Selector("#playerWrapper > iframe:nth-child(2)", attr = "src") lateinit var url : String

     @Keep @Selector(".fa-calendar") lateinit var uploadDate : String

     @Keep @Selector(".fa-star-o > a") lateinit var actressName : List<String>

     @Keep @Selector(".content-left > div:nth-child(1) > section:nth-child(2) > p:nth-child(2) > a") lateinit var tags: List<String>

     @Keep @Selector("div > .4u") lateinit var similar : List<Video>
}
