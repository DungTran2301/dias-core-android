package com.dungtran.dias.core.utils

import com.google.gson.GsonBuilder
import java.lang.reflect.Type

object StringUtils {
    private val gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()

    @JvmStatic
    fun toJson(source: Any): String {
        return gson.toJson(source)
    }

    @JvmStatic
    fun <T> parseJson(source: String, clazz: Class<T>): T {
        return gson.fromJson(source, clazz)
    }

    @JvmStatic
    fun <T> parseJson(source: String, type: Type): T {
        return gson.fromJson(source, type)
    }

}