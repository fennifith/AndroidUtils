package me.jfenn.androidutils.prefs

import android.content.Context
import androidx.preference.PreferenceManager
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

interface TypedPreference<T> : ReadWriteProperty<Context, T?> {

    val key: String
    val defaultValue: T?

    fun get(ctx: Context) : T?
    fun set(ctx: Context, value: T?)

    override fun getValue(thisRef: Context, property: KProperty<*>)
        = get(thisRef)

    override fun setValue(thisRef: Context, property: KProperty<*>, value: T?)
        = set(thisRef, value)

}

/**
 * Create a SharedPreferences property delegate
 * with the provided type argument.
 *
 * `val PREF_THEME by pref<String>()`
 *
 * @param key               The preference key. (optional: defaults to property name)
 * @param defaultValue      Default value of the preference.
 */
inline fun <reified T> pref(
        key: String? = null,
        defaultValue: T? = null
) = object : ReadOnlyProperty<Any?, TypedPreference<T>> {

    val pref: TypedPreference<T>? = null

    override fun getValue(thisRef: Any?, property: KProperty<*>) = pref ?: run {
        object : TypedPreference<T> {

            override val key = key ?: property.name
            override val defaultValue: T? = defaultValue

            override fun get(ctx: Context): T? {
                val prefs = PreferenceManager.getDefaultSharedPreferences(ctx)
                return prefs[this.key] ?: this.defaultValue
            }

            override fun set(ctx: Context, value: T?) {
                val prefs = PreferenceManager.getDefaultSharedPreferences(ctx)
                prefs[this.key] = value
            }

        }
    }

}
