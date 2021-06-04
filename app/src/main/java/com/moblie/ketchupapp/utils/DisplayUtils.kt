package com.moblie.ketchupapp.utils

import android.content.res.Resources

object DisplayUtils {
    val Float.toPx get() = this * Resources.getSystem().displayMetrics.density
    val Float.toDp get() = this / Resources.getSystem().displayMetrics.density

    val Int.toPx get() = (this * Resources.getSystem().displayMetrics.density).toInt()
    val Int.toDp get() = (this / Resources.getSystem().displayMetrics.density).toInt()
}