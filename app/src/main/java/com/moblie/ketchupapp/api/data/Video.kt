package com.moblie.ketchupapp.api.data

import androidx.annotation.Keep
import pl.droidsonroids.jspoon.annotation.Selector


class Video @Keep constructor(){
     @Keep @Selector("section > div:nth-child(3) > h3 > a") lateinit var title: String
     @Keep @Selector("section > div:nth-child(3) > h3 > a", attr = "href" ) lateinit var url: String
     @Keep @Selector("section > div:nth-child(3) > span:nth-child(2)") lateinit var length: String
}
