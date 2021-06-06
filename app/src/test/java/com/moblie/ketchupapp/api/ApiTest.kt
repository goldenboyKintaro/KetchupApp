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
    private var service0: MyDaddyService? = null

    @Before
    fun setup(){
        service = createService()
        service0 = createMD()
    }

    @Test
    fun testFetchPage() {
        val k = runBlocking {
            service?.getPage(1)
        }

        assertFalse(k?.videos?.get(20)?.url, true)
        assertNotNull(k?.videos?.get(0)?.url)
        assertNotNull(k?.videos?.get(0)?.title)
        assertNotNull(k?.videos?.get(0)?.length)
    }

    @Test
    fun testGetVideo(){
        val res = runBlocking {
            service0?.getVideo("5e1e1ad0d8c3c391ca")
        }
        val x = res?.data
    }

    @Test
    fun testVideoFetch(){
        val res = runBlocking {
            service?.getVideoFrameLink(101958, "fuck_her_ass_nice_and_hard")
        }

        assertFalse(res?.url, true)

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
        println(k?.items?.get(20)?.title)
        assertNotNull(k?.items?.get(20)?.url)
        assertNotNull(k?.items?.get(20)?.title)
        assertNotNull(k?.items?.get(20)?.thumbnail)
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

    private fun createMD() : MyDaddyService = RetroFit.mydaddy.create(MyDaddyService::class.java)
}