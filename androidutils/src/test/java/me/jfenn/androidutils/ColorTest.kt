package me.jfenn.androidutils

import android.graphics.Color
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class ColorTest {

    @Test
    fun `color channels should equal the provided values`() {
        val red = 210
        val green = 150
        val blue = 100
        val color = 0.setColor(red, green, blue)

        println(color.toString(16))

        assertEquals(red, color.red)
        assertEquals(green, color.green)
        assertEquals(blue, color.blue)
    }

    @Test
    fun `colors should stay the same when mixed with nothing`() {
        val color = Color.argb(255, 255, 255, 255)
        val mixedColor = color.mixColor()

        assertEquals(color.red, mixedColor.red)
        assertEquals(color.green, mixedColor.green)
        assertEquals(color.blue, mixedColor.blue)
    }

    @Test
    fun `colors should stay the same when mixed with a transparent color`() {
        val color = Color.argb(255, 255, 255, 255)
        val mixedColor = color.mixColor(
                Color.argb(0, 148, 0, 211)
        )

        assertEquals(color.red, mixedColor.red)
        assertEquals(color.green, mixedColor.green)
        assertEquals(color.blue, mixedColor.blue)
    }

}