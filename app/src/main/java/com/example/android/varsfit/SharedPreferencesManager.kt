package com.example.android.varsfit
import android.content.Context
import android.content.SharedPreferences
import com.google.common.reflect.TypeToken
import com.google.gson.Gson

class SharedPreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("VarsFit", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    fun getString(key: String, defaultValue: String = ""): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    fun putString(key: String, value: String) {
        editor.putString(key, value)
        editor.apply()
    }

    fun getInt(key: String, defaultValue: Int = 0): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }

    fun putInt(key: String, value: Int) {
        editor.putInt(key, value)
        editor.apply()
    }

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    fun putBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getMutableMap(key: String, defaultValue: MutableMap<String, Int> = mutableMapOf()): MutableMap<String, Int> {
        val serializedMap = sharedPreferences.getString(key, null)
        return if (serializedMap != null) {
            deserializeMutableMap(serializedMap)
        } else {
            defaultValue
        }
    }

    fun putMutableMap(key: String, value: MutableMap<String, Int>) {
        val serializedMap = serializeMutableMap(value)
        editor.putString(key, serializedMap)
        editor.apply()
    }

    private fun serializeMutableMap(map: MutableMap<String, Int>): String {
        val json = Gson().toJson(map)
        return json
    }

    private fun deserializeMutableMap(serializedMap: String): MutableMap<String, Int> {
        val type = object : TypeToken<MutableMap<String, Int>>() {}.type
        return Gson().fromJson(serializedMap, type)
    }

    fun remove(key: String) {
        editor.remove(key)
        editor.apply()
    }
}
