package com.moblie.ketchupapp.api

import pl.droidsonroids.jspoon.annotation.Selector

class Video {
    @Selector("section > div:nth-child(3) > h3 > a") lateinit var title: String
    @Selector("section > div:nth-child(3) > h3 > a", attr = "href" ) lateinit var url: String
    @Selector("section > div:nth-child(3) > span:nth-child(2)") lateinit var length: String
}
