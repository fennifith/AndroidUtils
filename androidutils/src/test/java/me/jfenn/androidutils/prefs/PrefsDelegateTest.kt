package me.jfenn.androidutils.prefs

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import androidx.test.core.app.ApplicationProvider
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class PrefsDelegateTest {

    private val prefs = getSharedPreferences()

    @Test
    fun `should store & return int types`() {
        var testInt by prefs.get<Int>()

        val test = 512;
        testInt = test

        assertEquals(test, testInt)
    }

    @Test
    fun `should store & return StringSet types`() {
        var testStringSet by prefs.get<Set<String>>()

        val test = HashSet<String>().apply {
            add("abc")
            add("xyz")
        }
        testStringSet = test

        val result = testStringSet
        assertNotNull(result)
        assertEquals(test.toString(), result.toString())
    }

    @Test
    fun `should store lists with string types`() {
        var testList by prefs.get<List<String>>()

        val test = listOf("a", "b", "c")
        testList = test

        val result = testList
        assertNotNull(result)
        assertEquals(test.toString(), result.toString())
    }

}