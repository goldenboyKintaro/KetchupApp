package com.moblie.ketchupapp.api.data

import androidx.annotation.Keep
import com.moblie.ketchupapp.room.model.MyDaddyModel
import com.moblie.ketchupapp.utils.Environment
import org.jsoup.nodes.Element
import pl.droidsonroids.jspoon.ElementConverter
import pl.droidsonroids.jspoon.annotation.Selector
import pl.droidsonroids.jspoon.Jspoon


class MyDaddyVideo {

    @Keep @Selector("body", converter = ScriptChildrenClassConverter::class)
    lateinit var data: Pair<MyDaddyModel, String>

    @Keep private class ScriptChildrenClassConverter() : ElementConverter<Pair<MyDaddyModel, String>> {
        override fun convert(node: Element, selector: Selector): Pair<MyDaddyModel, String> {
            val scripts = node.getElementsByTag("script")
            var ip: String? = null
            var activationId: String? = null
            var mId: String? = null
            var urlPrefix: String? = null
            for(script in scripts){
                val text = script.data()
                if(ip == null){
                    val matcher = Environment.VIDEO_PING_REGEX.matcher(text)
                    if(matcher.find()){
                        ip = matcher.group(2)
                        activationId = matcher.group(1)
                    }
                }
                if(mId == null){
                    val matcher = Environment.VIDEO_URL_REGEX.matcher(text)
                    if(matcher.find()){
                        urlPrefix = matcher.group(2)
                        mId = matcher.group(3)
                    }
                }
            }
            return if(ip != null && mId != null && urlPrefix != null && activationId!= null){
                Pair(MyDaddyModel(
                    activationId, urlPrefix, mId
                ), ip)
            }else
                Pair(MyDaddyModel("", "", ""), "")
        }
    }
}
