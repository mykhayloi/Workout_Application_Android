package com.example.workout_application_android

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun<reified T> LocalStorage.retrieveObject(key: String): T? {
    this.retrieve<String>(key)?.let {
        return Gson().fromJson(it, object: TypeToken<T>() {}.type)
    }

    return null
}

class LocalStorage(context: Context) {
    private val preferencesName = context.applicationInfo.name
    private val sharedPreferences = context.getSharedPreferences(
        preferencesName,
        Context.MODE_PRIVATE
    )

    fun<T> save(key: String, value: T) {
        val editor = sharedPreferences.edit()

        when(value) {
            is String -> editor.putString(key, value)
            is Int -> editor.putInt(key, value)
            is Float -> editor.putFloat(key, value)
            is Long -> editor.putLong(key, value)
            is Boolean -> editor.putBoolean(key, value)
            else -> {
                val json = Gson().toJson(value)
                editor.putString(key, json)
            }
        }

        editor.apply()
    }

    fun<T> retrieve(key: String): T? {
        return sharedPreferences.all[key] as? T
    }

    fun clear() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    fun remove(key: String) {
        val editor = sharedPreferences.edit()
        editor.remove(key)
        editor.apply()
    }
}