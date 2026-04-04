package com.fasterxml.jackson.databind.module
import com.fasterxml.jackson.databind.*
open class SimpleModule : Module() { fun <T : Any> addSerializer(serializer: JsonSerializer<T>): SimpleModule = this; fun <T : Any> addDeserializer(type: Any, deserializer: JsonDeserializer<*>): SimpleModule = this }
