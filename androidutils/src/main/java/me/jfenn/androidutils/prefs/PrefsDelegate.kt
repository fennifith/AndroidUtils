package me.jfenn.androidutils.prefs

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.view.View
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Create a SharedPreferences property delegate
 * with the provided type argument.
 *
 * `var theme by prefs.get("theme")`
 *
 * @param key               The preference key. (optional: defaults to property name)
 * @param defaultValue      Default value of the preference.
 */
inline fun <reified T> SharedPreferences.get(
        key: String? = null,
        defaultValue: T? = null
) = object : ReadWriteProperty<Any?, T?> {

    override fun getValue(thisRef: Any?, property: KProperty<*>): T? {
        return this@get[key ?: property.name] ?: defaultValue
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        this@get[key ?: property.name] = value
    }

}

inline fun <reified T> SharedPreferences.get(pref: TypedPreference<T>)
    = this.get(pref.key, pref.defaultValue)

inline fun <reified T> Context.prefs(pref: TypedPreference<T>)
    = PreferenceManager.getDefaultSharedPreferences(this).get(pref)

inline fun <reified T> Fragment.prefs(pref: TypedPreference<T>)
        = PreferenceManager.getDefaultSharedPreferences(context).get(pref)

inline fun <reified T> View.prefs(pref: TypedPreference<T>)
        = PreferenceManager.getDefaultSharedPreferences(context).get(pref)

inline fun <reified T> Dialog.prefs(pref: TypedPreference<T>)
        = PreferenceManager.getDefaultSharedPreferences(context).get(pref)
