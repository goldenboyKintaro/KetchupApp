package com.moblie.ketchupapp.utils

import java.util.regex.Pattern

object Environment {
    const val NETWORK_PAGE_SIZE: Int = 45

    const val BASE_URL = "https://fastporndelivery.hqporner.com"

    @JvmStatic
    val NUMBER_REGEX: Pattern = Pattern.compile("([0-9]+)")

    @JvmStatic
    val URL_REGEX = Pattern.compile("[-a-zA-Z0-9@:%._+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_+.~#?&/=]*)")
}