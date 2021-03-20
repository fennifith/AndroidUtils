package me.jfenn.androidutils.prefs

import android.content.Context
import android.content.SharedPreferences
import androidx.test.core.app.ApplicationProvider

fun Any.getSharedPreferences() : SharedPreferences {
    return ApplicationProvider.getApplicationContext<Context>()
            .getSharedPreferences(javaClass.name, Context.MODE_PRIVATE)
}
