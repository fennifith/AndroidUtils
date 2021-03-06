package me.jfenn.androidutils

import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.Window
import androidx.annotation.ColorInt

private const val ALPHA_CHANNEL = 24
private const val RED_CHANNEL = 16
private const val GREEN_CHANNEL = 8
private const val BLUE_CHANNEL = 0

val Int.red     get() = this.shr(RED_CHANNEL).and(0xFF)
val Int.green   get() = this.shr(GREEN_CHANNEL).and(0xFF)
val Int.blue    get() = this.shr(BLUE_CHANNEL).and(0xFF)
val Int.alpha   get() = this.shr(ALPHA_CHANNEL).and(0xFF).div(255f)

fun Int.setColor(
        red: Int = this.red,
        green: Int = this.green,
        blue: Int = this.blue,
        alpha: Float = this.alpha
): Int {
    return Color.argb((alpha * 255).toInt(), red, green, blue)
}

fun Int.mixColor(vararg colors: Int): Int {
    var r: Int = red
    var g: Int = green
    var b: Int = blue
    var a: Float = alpha

    for (color in colors) {
        r += (color.red * color.alpha).toInt()
        g += (color.green * color.alpha).toInt()
        b += (color.blue * color.alpha).toInt()
        a += color.alpha
    }

    return Color.argb(
            ((a / (colors.size + 1)) * 255).toInt(),
            (r / a).toInt().coerceIn(0, 255),
            (g / a).toInt().coerceIn(0, 255),
            (b / a).toInt().coerceIn(0, 255)
    )
}

fun Int.multiplyColor(vararg colors: Int, base: Int = 0): Int {
    val mixer = base.mixColor(*colors)

    return Color.rgb(
            (red * mixer.red / 255).coerceIn(0, 255),
            (green * mixer.green / 255).coerceIn(0, 255),
            (blue * mixer.blue / 255).coerceIn(0, 255)
    )
}

/**
 * Determine if a color is readable on a light background, using some magic numbers.
 *
 * @return True if the color should be considered "dark".
 */
fun Int.isColorDark() : Boolean {
    return getColorDarkness(this) >= 0.5
}

/**
 * Negation of Int.isColorDark()
 *
 * @return True if the color should be considered "light".
 */
fun Int.isColorLight() : Boolean {
    return !isColorDark()
}

/**
 * Determine the darkness of a color, using some magic numbers.
 *
 * @param color         A color int to determine the luminance of.
 * @return              The darkness of the color; a double between 0 and 1.
 */
private fun getColorDarkness(@ColorInt color: Int): Double {
    if (color == Color.BLACK) return 1.0 else if (color == Color.WHITE || color == Color.TRANSPARENT) return 0.0
    return 1 - (0.259 * Color.red(color) + 0.667 * Color.green(color) + 0.074 * Color.blue(color)) / 255
}

fun createColorWheelArray(saturation: Float, brightness: Float): IntArray {
    val arr = IntArray(13)
    for (i in 0..12) arr[i] = Color.HSVToColor(floatArrayOf(i * 30.toFloat(), saturation, brightness))
    return arr
}

/**
 * Set light status/navigation bar window flags automatically.
 * Falls back to Color.BLACK on lower SDK versions.
 */
fun Window.autoSystemUiColors() {
    if (Build.VERSION.SDK_INT < 21)
        return

    // handle light status bar colors
    if (statusBarColor.isColorLight()) {
        if (Build.VERSION.SDK_INT >= 23)
            decorView.systemUiVisibility = decorView.systemUiVisibility.or(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        else if (Build.VERSION.SDK_INT >= 21)
            statusBarColor = Color.BLACK
    }

    // handle light nav bar colors
    if (navigationBarColor.isColorLight()) {
        if (Build.VERSION.SDK_INT >= 26)
            decorView.systemUiVisibility = decorView.systemUiVisibility.or(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR)
        else if (Build.VERSION.SDK_INT >= 21)
            navigationBarColor = Color.BLACK
    }
}
