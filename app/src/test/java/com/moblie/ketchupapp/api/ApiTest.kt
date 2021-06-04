package com.moblie.ketchupapp.api

import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import co.infinum.retromock.Retromock
import com.moblie.ketchupapp.testutils.RetroFit


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ApiTest {

    private var service: HQVideoService? = null

    @Before
    fun setup(){
        service = createService()
    }

    @Test
    fun testFetchPage() {
        val k = runBlocking {
            service?.getPage(1)
        }
        assertNotNull(k?.videos?.get(0)?.url)
        assertNotNull(k?.videos?.get(0)?.title)
        assertNotNull(k?.videos?.get(0)?.length)
    }

    @Test
    fun testQueryPage() {
        val k = runBlocking {
            service?.search("boo")
        }
        assertNotNull(k?.videos?.get(0)?.url)
        assertNotNull(k?.videos?.get(0)?.title)
        assertNotNull(k?.videos?.get(0)?.length)
    }

    @Test
    fun testCategories() {
        val k = runBlocking {
            service?.getCategories()
        }
        println(k?.items?.get(0)?.title)
        assertNotNull(k?.items?.get(0)?.url)
        assertNotNull(k?.items?.get(0)?.title)
        assertNotNull(k?.items?.get(0)?.thumbnail)
    }

    @Test
    fun testGirls() {
        val k = runBlocking {
            service?.getGirls()
        }
        println(k?.items?.get(0)?.title)
        assertNotNull(k?.items?.get(0)?.url)
        assertNotNull(k?.items?.get(0)?.title)
        assertNotNull(k?.items?.get(0)?.thumbnail)
    }
    private fun createService(): HQVideoService = RetroFit.retrofit.create(HQVideoService::class.java)

}