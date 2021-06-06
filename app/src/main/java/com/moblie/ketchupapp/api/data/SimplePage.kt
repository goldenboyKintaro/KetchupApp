package com.moblie.ketchupapp.api.data

import androidx.annotation.Keep
import pl.droidsonroids.jspoon.annotation.Selector


class SimplePage @Keep constructor(){
     @Keep @Selector("section.box:nth-child(2) > div:nth-child(1) > div:nth-child(1)  > div ") lateinit var items : List<PageItem>
}
