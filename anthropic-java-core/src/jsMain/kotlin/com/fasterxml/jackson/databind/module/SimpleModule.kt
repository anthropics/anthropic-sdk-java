// JS compile-only stub for com.fasterxml.jackson.databind.module.SimpleModule
package com.fasterxml.jackson.databind.module

import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.Module as JacksonModule
import kotlin.reflect.KClass

open class SimpleModule : JacksonModule() {
    fun <T : Any> addSerializer(serializer: JsonSerializer<T>): SimpleModule = this
    fun <T : Any> addDeserializer(type: KClass<T>, deserializer: JsonDeserializer<out T>): SimpleModule = this
    // Accept Any for type.java patterns
    fun <T : Any> addDeserializer(type: Any, deserializer: JsonDeserializer<*>): SimpleModule = this
}
