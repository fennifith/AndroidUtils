package me.jfenn.androidutils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable

/**
 * Converts drawables to bitmaps.
 *
 * @return A bitmap representing the drawable asset.
 */
fun Drawable.toBitmap() : Bitmap {
    (this as? BitmapDrawable)?.let {
        if (it.bitmap != null)
            return it.bitmap
    }

    val bitmap = if (intrinsicWidth <= 0 || intrinsicHeight <= 0)
        Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
    else Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)

    val canvas = Canvas(bitmap)
    setBounds(0, 0, canvas.width, canvas.height)
    draw(canvas)

    return bitmap
}
