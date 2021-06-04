package com.moblie.ketchupapp

import android.app.Application
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.intent.IntentMonitorImpl
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import co.infinum.retromock.Retromock
import com.moblie.ketchupapp.testutils.ResourceBodyFactory
import com.moblie.ketchupapp.testutils.Retro
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class PagingE2ETest {

    companion object {
        private const val TEST_SUBREDDIT = "test"
    }


    private lateinit var retromock: Retromock

    @Before
    fun init() {
        val app = ApplicationProvider.getApplicationContext<Application>()

        retromock =  Retromock.Builder()
            .retrofit(Retro.retrofit)
            .defaultBodyFactory(ResourceBodyFactory())
            .build()
    }

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun infiniteLoadingTest() {
        var oldItemCount = 0
        runBlocking { delay(2000) }
        onView(withId(R.id.recycler_view)).check { view, noViewFoundException ->
            if (noViewFoundException != null) {
                throw noViewFoundException
            }

            val recyclerView = view as RecyclerView
            oldItemCount =  recyclerView.adapter?.itemCount ?: 0
            assertTrue(true)
        }
        runBlocking { delay(500) }
        onView(withId(R.id.recycler_view))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                mActivityTestRule.activity.
                findViewById<RecyclerView>(R.id.recycler_view).adapter?.itemCount ?: 1 - 1))

        runBlocking { delay(2000) }
        onView(withId(R.id.recycler_view)).check { view, noViewFoundException ->
            if (noViewFoundException != null) {
                throw noViewFoundException
            }

            val recyclerView = view as RecyclerView
            val newItemCount = recyclerView.adapter?.itemCount ?: 0
            assertTrue("More Items should be loaded old ${oldItemCount}, new $newItemCount", oldItemCount < newItemCount)
        }

    }

}
