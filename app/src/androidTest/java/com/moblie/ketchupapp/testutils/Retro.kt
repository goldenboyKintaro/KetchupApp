package com.moblie.ketchupapp.testutils

import com.moblie.ketchupapp.utils.Environment
import pl.droidsonroids.retrofit2.JspoonConverterFactory
import retrofit2.Retrofit

object Retro {

    @JvmStatic
    val retrofit = Retrofit.Builder()
        .baseUrl(Environment.BASE_URL)
        .addConverterFactory(JspoonConverterFactory.create())
        .build()
}