package me.jfenn.androidutils.prefs

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
class PrefsTest {

    private val prefs = getSharedPreferences()

    @Test
    fun `should store & return int types`() {
        val key = "testInt"
        val test = 512;
        prefs[key] = test

        assertEquals(test, prefs[key])
    }

    @Test
    fun `should store & return StringSet types`() {
        val key = "testStringSet"
        val test = HashSet<String>().apply {
            add("abc")
            add("xyz")
        }
        prefs[key] = test

        val result: HashSet<String>? = prefs[key]
        assertNotNull(result)
        assertEquals(test.toString(), result.toString())
    }

    @Test
    fun `should store lists with string types`() {
        val key = "testList"
        val test = listOf("a", "b", "c")
        prefs[key] = test

        val result: List<String>? = prefs[key]
        assertNotNull(result)
        assertEquals(test.toString(), result.toString())
    }

}