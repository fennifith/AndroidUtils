package me.jfenn.androidutils.prefs

import android.content.SharedPreferences
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.lang.reflect.WildcardType

/**
 * Edits the calling SharedPreferences instance through the provided
 * lambda and applies changes after completion
 */
inline fun SharedPreferences.edit(block: SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    editor.block()
    editor.apply()
}

abstract class TypeReference<T> : Comparable<TypeReference<T>> {
    val type = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.first() as ParameterizedType
    val arg = (type.actualTypeArguments.first() as WildcardType).upperBounds.first() as Class<*>
    override fun compareTo(other: TypeReference<T>) = 0
}

inline operator fun <reified T> SharedPreferences.get(key: String) : T? {
    return when(T::class) {
        List::class, MutableList::class -> {
            val itemType = object : TypeReference<T>() {}.arg
            val list = ArrayList<Any>()
            val size = getInt("${key}-length", -1)

            if (size == -1)
                return null

            for (i in 0 until size) {
                val itemKey = "${key}#${i}"
                when (itemType) {
                    Boolean::class.java -> getPrimitive<Boolean>(itemKey)
                    Float::class.java   -> getPrimitive<Float>(itemKey)
                    Int::class.java     -> getPrimitive<Int>(itemKey)
                    Long::class.java    -> getPrimitive<Long>(itemKey)
                    String::class.java  -> getPrimitive<String>(itemKey)
                    else -> null
                }?.let {
                    list.add(it)
                }
            }

            return list as? T
        }
        else -> getPrimitive(key)
    }
}

inline fun <reified T> SharedPreferences.getPrimitive(key: String) : T? {
    if (!this.contains(key))
        return null

    return when (T::class) {
        Boolean::class  -> this.getBoolean(key, false) as T
        Float::class    -> this.getFloat(key, 0f) as T
        Int::class      -> this.getInt(key, 0) as T
        Long::class     -> this.getLong(key, 0) as T
        String::class   -> this.getString(key, "") as T
        Set::class, HashSet::class -> {
            this.getStringSet(key, HashSet()) as T
        }
        else -> null
    }
}

inline operator fun <reified T> SharedPreferences.set(key: String, value: T?) = edit {
    when (T::class) {
        List::class, MutableList::class -> {
            val itemType = object : TypeReference<T>() {}.arg
            val list = value as List<*>

            putInt("${key}-length", list.size)
            list.forEachIndexed { index, item ->
                val itemKey = "${key}#${index}"
                when (itemType) {
                    Boolean::class.java -> putPrimitive(itemKey, item as? Boolean)
                    Float::class.java   -> putPrimitive(itemKey, item as? Float)
                    Int::class.java     -> putPrimitive(itemKey, item as? Int)
                    Long::class.java    -> putPrimitive(itemKey, item as? Long)
                    String::class.java  -> putPrimitive(itemKey, item as? String)
                    else -> null
                }
            }
        }
        else -> putPrimitive(key, value)
    }
}

inline fun <reified T> SharedPreferences.Editor.putPrimitive(key: String, value: T?) {
    if (value == null)
        remove(key)
    else when(T::class) {
        Boolean::class  -> putBoolean(key, value as Boolean)
        Float::class    -> putFloat(key, value as Float)
        Int::class      -> putInt(key, value as Int)
        Long::class     -> putLong(key, value as Long)
        String::class   -> putString(key, value as String)
        Set::class, HashSet::class -> {
            putStringSet(key, value as Set<String>)
        }
    }
}
