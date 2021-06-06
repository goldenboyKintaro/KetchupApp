package com.moblie.ketchupapp.utils

import java.util.regex.Pattern

object Environment {
    const val NETWORK_PAGE_SIZE: Int = 45

    const val BASE_URL = "https://fastporndelivery.hqporner.com"

    const val MY_DADDY_URL = "http://mydaddy.cc"

    const val STAT_URL = "http://stat.mydaddy.cc"

    @JvmStatic
    val NUMBER_REGEX: Pattern = Pattern.compile("([0-9]+)")

    @JvmStatic
    val VIDEO_PING_REGEX: Pattern = Pattern.compile("https://stat.mydaddy.cc/e.php\", \\{ v:\"(\\w+)\" , r:\"(([0-9][0-9]\\.?)+)")

    @JvmStatic
    val VIDEO_URL_REGEX: Pattern = Pattern.compile("(\"|')[^(\"|')]*\\/(\\w+)\\.bigcdn\\.cc/pubs/(\\w+\\.\\w+)")

    @JvmStatic
    val URL_REGEX: Pattern = Pattern.compile("[-a-zA-Z0-9@:%._+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_+.~#?&/=]*)")
}