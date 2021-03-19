package me.jfenn.androidutils

import android.app.Activity
import android.app.Dialog
import android.os.Build
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.IdRes
import androidx.annotation.RequiresApi
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment

inline fun <reified T> ignore(what: () -> T?) =
        try {
            what()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }

inline fun <reified T : View> Activity.bind(@IdRes res: Int): Lazy<T?> = lazy { findView<T>(res) }

inline fun <reified T : View> Fragment.bind(@IdRes res: Int): Lazy<T?> = lazy { findView<T>(res) }

inline fun <reified T : View> View.bind(@IdRes res: Int): Lazy<T?> = lazy { findView<T>(res) }

inline fun <reified T : View> Dialog.bind(@IdRes res: Int): Lazy<T?> = lazy { findView<T>(res) }

inline fun <reified T : View> Activity.findView(@IdRes res: Int): T? = ignore { findViewById(res) }

inline fun <reified T : View> Fragment.findView(@IdRes res: Int): T? = ignore { view?.findView(res) }

inline fun <reified T : View> View.findView(@IdRes res: Int): T? = ignore { findViewById(res) }

inline fun <reified T : View> Dialog.findView(@IdRes res: Int): T? = ignore { findViewById(res) }

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
fun View.setBackgroundTint(@ColorInt color: Int) {
    background = DrawableCompat.wrap(background).apply {
        DrawableCompat.setTint(this, color)
    }
}
