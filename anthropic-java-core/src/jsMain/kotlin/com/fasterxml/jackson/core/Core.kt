// JS compile-only stubs for com.fasterxml.jackson.core
package com.fasterxml.jackson.core

abstract class JsonGenerator {
    abstract fun writeStartObject()
    abstract fun writeEndObject()
    abstract fun writeStartArray()
    abstract fun writeEndArray()
    abstract fun writeFieldName(name: String)
    abstract fun writeString(text: String)
    abstract fun writeNumber(text: String)
    abstract fun writeBoolean(state: Boolean)
    abstract fun writeNull()
    abstract fun writeBinary(data: ByteArray)
    abstract fun writeObject(pojo: Any?)
}

abstract class JsonParser {
    abstract val text: String
    abstract val codec: ObjectCodec
    fun <T> readValueAsTree(): T = TODO("JS stub")
}

abstract class ObjectCodec {
    abstract fun <T> readValue(parser: JsonParser, type: com.fasterxml.jackson.core.type.TypeReference<T>): T
    abstract fun <T> readValue(parser: JsonParser, type: com.fasterxml.jackson.databind.JavaType): T
    abstract fun treeAsTokens(node: com.fasterxml.jackson.databind.JsonNode): JsonParser
}

open class JsonParseException(parser: JsonParser?, message: String?) : java.io.IOException(message) {
    constructor(parser: JsonParser?, message: String?, cause: Throwable?) : this(parser, message)
}
