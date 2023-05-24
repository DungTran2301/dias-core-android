package com.dungtran.dias.core.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceUtils private constructor(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("PRANK_SOUNDS", Context.MODE_PRIVATE)

    companion object : SingletonHolder<SharedPreferenceUtils, Context>(::SharedPreferenceUtils)

    fun putAcceptPolicy(value: Boolean) {
        putBooleanValue("isAcceptPolicy", value)
    }

    fun getAcceptPolicy(): Boolean {
        return getBooleanValue("isAcceptPolicy")
    }

    fun putVibration(value: Boolean) {
        putBooleanValue("isVibration", value)
    }

    fun getVibration(): Boolean {
        return getBooleanValue("isVibration")
    }

    fun putStringValue(key: String?, value: String?) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value).apply()
    }

    fun getStringValue(key: String?): String? {
        return sharedPreferences.getString(key, "")
    }

    fun putBooleanValue(key: String, value: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value).apply()
    }

    fun getBooleanValue(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    fun getBooleanValueWithTrueDefault(key: String): Boolean {
        return sharedPreferences.getBoolean(key, true)
    }

    fun putIntValue(key: String?, value: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(key, value).apply()
    }

    fun getIntValue(key: String?): Int {
        return sharedPreferences.getInt(key, 0)
    }

    fun putLongValue(key: String?, value: Long) {
        val editor = sharedPreferences.edit()
        editor.putLong(key, value).apply()
    }

    fun getLongValue(key: String?): Long {
        return sharedPreferences.getLong(key, 0L)
    }


}