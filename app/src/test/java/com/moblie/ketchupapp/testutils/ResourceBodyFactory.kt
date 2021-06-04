package com.moblie.ketchupapp.testutils

import co.infinum.retromock.BodyFactory
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream


class ResourceBodyFactoryUnit : BodyFactory {
    @Throws(IOException::class)
    override fun create(input: String): InputStream {
        val file = File("/home/snehil/AndroidStudioProjects/KetchupApp/app/src/test/java/com/moblie/ketchupapp/mockkedpages/$input")
        return FileInputStream(file)
    }
}