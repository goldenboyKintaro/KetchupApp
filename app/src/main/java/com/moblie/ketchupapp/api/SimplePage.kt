package com.moblie.ketchupapp.api

import pl.droidsonroids.jspoon.annotation.Selector

class SimplePage {
    @Selector("section.box:nth-child(2) > div:nth-child(1) > div:nth-child(1)  > div ") lateinit var items : List<PageItem>
}
