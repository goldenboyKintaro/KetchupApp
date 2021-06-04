package com.moblie.ketchupapp.api

import com.moblie.ketchupapp.utils.Environment
import org.jsoup.nodes.Element
import pl.droidsonroids.jspoon.annotation.Selector
import pl.droidsonroids.jspoon.ElementConverter

class VideoPage {
    @Selector(".6u") lateinit var videos: List<Video>
    @Selector("section.box:nth-child(2) > div > div", converter = ScriptChildrenClassConverter::class) lateinit var previews: List<String>

    class ScriptChildrenClassConverter : ElementConverter<List<String>> {
        override fun convert(node: Element, selector: Selector): List<String> {
            return node.getElementsByTag("script").map(Element::parseScript).toCollection(ArrayList())
        }
    }
}

private fun Element.parseScript(): String {
    val matcher = Environment.URL_REGEX.matcher(this.data().substring(0, 132))
    return if(matcher.find()){
        matcher.group(1) ?: ""
    }else{
        ""
    }
}
