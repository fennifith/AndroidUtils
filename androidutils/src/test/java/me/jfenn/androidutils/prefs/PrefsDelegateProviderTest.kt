package me.jfenn.androidutils.prefs

import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class PrefsDelegateProviderTest {

    private val prefs = getSharedPreferences()

    @Test
    fun `should store & return int types`() {
        val testInt by pref<Int>()
        var testIntDelegate by prefs.get(testInt)

        val test = 512;
        testIntDelegate = test

        assertEquals(test, testIntDelegate)
    }

    @Test
    fun `should use default values`() {
        val test = listOf("a", "b", "c")
        val testList by pref(defaultValue = test)
        val result by prefs.get(testList)

        assertNotNull(result)
        assertEquals(test.toString(), result!!.toString())
    }

}