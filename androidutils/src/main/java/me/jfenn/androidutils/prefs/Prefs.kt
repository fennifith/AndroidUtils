package me.jfenn.androidutils.prefs

import android.content.SharedPreferences

inline operator fun <reified T> SharedPreferences.get(key: String) : T? {
    if (!this.contains(key))
        return null

    return when(T::class) {
        Boolean::class  -> this.getBoolean(key, false) as T
        Float::class    -> this.getFloat(key, 0f) as T
        Int::class      -> this.getInt(key, 0) as T
        Long::class     -> this.getLong(key, 0) as T
        String::class   -> this.getString(key, "") as T
        Set::class      -> this.getStringSet(key, HashSet()) as T
        else -> null
    }
}

inline operator fun <reified T> SharedPreferences.set(key: String, value: T?) {
    val editor = this.edit()

    if (value == null)
        editor.remove(key)
    else when(T::class) {
        Boolean::class  -> editor.putBoolean(key, value as Boolean)
        Float::class    -> editor.putFloat(key, value as Float)
        Int::class      -> editor.putInt(key, value as Int)
        Long::class     -> editor.putLong(key, value as Long)
        String::class   -> editor.putString(key, value as String)
        Set::class      -> editor.putStringSet(key, value as Set<String>)
    }

    editor.apply()
}
