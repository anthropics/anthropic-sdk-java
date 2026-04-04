package com.anthropic.core

import com.anthropic.errors.AnthropicInvalidDataException

sealed class JsonField<out T : Any> {

    fun isMissing(): Boolean = this is JsonMissing
    fun isNull(): Boolean = this is JsonNull

    fun asKnown(): @UnsafeVariance T? = (this as? KnownValue)?.value
    fun asUnknown(): JsonValue? = this as? JsonValue

    fun asBoolean(): Boolean? = when (this) {
        is JsonBoolean -> value; is KnownValue -> value as? Boolean; else -> null
    }

    fun asNumber(): Number? = when (this) {
        is JsonNumber -> value; is KnownValue -> value as? Number; else -> null
    }

    fun asString(): String? = when (this) {
        is JsonString -> value; is KnownValue -> value as? String; else -> null
    }

    fun asStringOrThrow(): String =
        asString() ?: throw AnthropicInvalidDataException("Value is not a string")

    fun asArray(): List<JsonValue>? = when (this) {
        is JsonArray -> values
        is KnownValue -> (value as? List<*>)?.map {
            try { JsonValue.from(it) } catch (e: IllegalArgumentException) { return null }
        }
        else -> null
    }

    fun asObject(): Map<String, JsonValue>? = when (this) {
        is JsonObject -> values
        is KnownValue -> (value as? Map<*, *>)?.map { (key, value) ->
            if (key !is String) return null
            val jv = try { JsonValue.from(value) } catch (e: IllegalArgumentException) { return null }
            key to jv
        }?.toMap()
        else -> null
    }

    internal fun getRequired(name: String): T = when (this) {
        is KnownValue -> value
        is JsonMissing -> throw AnthropicInvalidDataException("`$name` is not set")
        is JsonNull -> throw AnthropicInvalidDataException("`$name` is null")
        else -> throw AnthropicInvalidDataException("`$name` is invalid, received $this")
    }

    internal fun getNullable(name: String): @UnsafeVariance T? = when (this) {
        is KnownValue -> value
        is JsonMissing, is JsonNull -> null
        else -> throw AnthropicInvalidDataException("`$name` is invalid, received $this")
    }

    internal fun <R : Any> map(transform: (T) -> R): JsonField<R> = when (this) {
        is KnownValue -> KnownValue.of(transform(value)); is JsonValue -> this
    }

    internal fun accept(consume: (T) -> Unit) { asKnown()?.let(consume) }

    fun <R> accept(visitor: Visitor<T, R>): R = when (this) {
        is KnownValue -> visitor.visitKnown(value)
        is JsonValue -> accept(visitor as JsonValue.Visitor<R>)
    }

    interface Visitor<in T, out R> : JsonValue.Visitor<R> {
        fun visitKnown(value: T): R = visitDefault()
    }

    class IsMissing {
        override fun equals(other: Any?): Boolean = other is JsonMissing
        override fun hashCode(): Int = 0
    }

    companion object {
        @JvmStatic fun <T : Any> of(value: T): JsonField<T> = KnownValue.of(value)
        @JvmStatic fun <T : Any> ofNullable(value: T?): JsonField<T> =
            if (value == null) JsonNull.of() else KnownValue.of(value)
    }
}

sealed class JsonValue : JsonField<Nothing>() {
    fun <R> accept(visitor: Visitor<R>): R = when (this) {
        is JsonMissing -> visitor.visitMissing(); is JsonNull -> visitor.visitNull()
        is JsonBoolean -> visitor.visitBoolean(value); is JsonNumber -> visitor.visitNumber(value)
        is JsonString -> visitor.visitString(value); is JsonArray -> visitor.visitArray(values)
        is JsonObject -> visitor.visitObject(values)
    }

    interface Visitor<out R> {
        fun visitNull(): R = visitDefault(); fun visitMissing(): R = visitDefault()
        fun visitBoolean(value: Boolean): R = visitDefault()
        fun visitNumber(value: Number): R = visitDefault()
        fun visitString(value: String): R = visitDefault()
        fun visitArray(values: List<JsonValue>): R = visitDefault()
        fun visitObject(values: Map<String, JsonValue>): R = visitDefault()
        fun visitDefault(): R = throw IllegalArgumentException("Unexpected value")
    }

    companion object {
        @JvmStatic fun from(value: Any?): JsonValue = when (value) {
            null -> JsonNull.of(); is JsonValue -> value; is Boolean -> JsonBoolean.of(value)
            is Number -> JsonNumber.of(value); is String -> JsonString.of(value)
            is List<*> -> JsonArray.of(value.map { from(it) })
            is Map<*, *> -> JsonObject.of(value.entries.associate { (k, v) ->
                (k as? String ?: throw IllegalArgumentException("Map keys must be strings")) to from(v)
            })
            is Array<*> -> JsonArray.of(value.map { from(it) })
            else -> throw IllegalArgumentException("Cannot convert ${value::class.simpleName} to JsonValue")
        }
    }
}

@com.fasterxml.jackson.databind.annotation.JsonSerialize(using = com.anthropic.core.KnownValueSerializer::class)
class KnownValue<T : Any> private constructor(@get:com.fasterxml.jackson.annotation.JsonValue val value: T) : JsonField<T>() {
    override fun equals(other: Any?): Boolean =
        this === other || (other is KnownValue<*> && value contentEquals other.value)
    override fun hashCode() = contentHash(value)
    override fun toString() = value.contentToString()
    companion object { @com.fasterxml.jackson.annotation.JsonCreator fun <T : Any> of(value: T) = KnownValue(value) }
}

class JsonMissing : JsonValue() {
    override fun toString() = ""
    companion object { private val INSTANCE = JsonMissing(); fun of() = INSTANCE }
}

class JsonNull : JsonValue() {
    override fun toString() = "null"
    companion object { private val INSTANCE = JsonNull(); @com.fasterxml.jackson.annotation.JsonCreator fun of() = INSTANCE }
}

class JsonBoolean private constructor(@get:com.fasterxml.jackson.annotation.JsonValue val value: Boolean) : JsonValue() {
    override fun equals(other: Any?) = this === other || (other is JsonBoolean && value == other.value)
    override fun hashCode() = value.hashCode(); override fun toString() = value.toString()
    companion object { @com.fasterxml.jackson.annotation.JsonCreator fun of(value: Boolean) = JsonBoolean(value) }
}

class JsonNumber private constructor(@get:com.fasterxml.jackson.annotation.JsonValue val value: Number) : JsonValue() {
    override fun equals(other: Any?) = this === other || (other is JsonNumber && value == other.value)
    override fun hashCode() = value.hashCode(); override fun toString() = value.toString()
    companion object { @com.fasterxml.jackson.annotation.JsonCreator fun of(value: Number) = JsonNumber(value) }
}

class JsonString private constructor(@get:com.fasterxml.jackson.annotation.JsonValue val value: String) : JsonValue() {
    override fun equals(other: Any?) = this === other || (other is JsonString && value == other.value)
    override fun hashCode() = value.hashCode(); override fun toString() = value
    companion object { @com.fasterxml.jackson.annotation.JsonCreator fun of(value: String) = JsonString(value) }
}

@com.fasterxml.jackson.databind.annotation.JsonSerialize(using = com.anthropic.core.JsonArraySerializer::class)
class JsonArray private constructor(val values: List<JsonValue>) : JsonValue() {
    override fun equals(other: Any?) = this === other || (other is JsonArray && values == other.values)
    override fun hashCode() = values.hashCode(); override fun toString() = values.toString()
    companion object { @com.fasterxml.jackson.annotation.JsonCreator fun of(values: List<JsonValue>) = JsonArray(values.toList()) }
}

@com.fasterxml.jackson.databind.annotation.JsonSerialize(using = com.anthropic.core.JsonObjectSerializer::class)
class JsonObject private constructor(val values: Map<String, JsonValue>) : JsonValue() {
    override fun equals(other: Any?) = this === other || (other is JsonObject && values == other.values)
    override fun hashCode() = values.hashCode(); override fun toString() = values.toString()
    companion object { @com.fasterxml.jackson.annotation.JsonCreator fun of(values: Map<String, JsonValue>) = JsonObject(values.toMap()) }
}

annotation class ExcludeMissing

class MultipartField<T : Any> private constructor(
    val value: JsonField<T>, val contentType: String, private val filename: String?,
) {
    fun filename(): String? = filename
    internal fun <R : Any> map(transform: (T) -> R): MultipartField<R> =
        builder<R>().value(value.map(transform)).contentType(contentType).filename(filename).build()

    class Builder<T : Any> internal constructor() {
        private var value: JsonField<T>? = null
        private var contentType: String? = null
        private var filename: String? = null
        fun value(value: JsonField<T>) = apply { this.value = value }
        fun value(value: T?) = value(JsonField.ofNullable(value))
        fun contentType(contentType: String) = apply { this.contentType = contentType }
        fun filename(filename: String?) = apply { this.filename = filename }
        fun build(): MultipartField<T> {
            val value = checkRequired("value", value)
            return MultipartField(value,
                contentType ?: if (value is KnownValue && value.value is ByteArray) "application/octet-stream"
                else "text/plain; charset=utf-8", filename)
        }
    }

    private val hashCode: Int by lazy { contentHash(value, contentType, filename) }
    override fun hashCode(): Int = hashCode
    override fun equals(other: Any?): Boolean = this === other ||
        (other is MultipartField<*> && value == other.value && contentType == other.contentType && filename == other.filename)
    override fun toString() = "MultipartField{value=$value, contentType=$contentType, filename=$filename}"

    companion object {
        @JvmStatic fun <T : Any> of(value: T?) = builder<T>().value(value).build()
        @JvmStatic fun <T : Any> of(value: JsonField<T>) = builder<T>().value(value).build()
        @JvmStatic fun <T : Any> builder() = Builder<T>()
    }
}

// Jackson bridge - fromJsonNode
fun JsonValue.Companion.fromJsonNode(node: com.fasterxml.jackson.databind.JsonNode): JsonValue =
    when (node.nodeType) {
        com.fasterxml.jackson.databind.node.JsonNodeType.MISSING -> JsonMissing.of()
        com.fasterxml.jackson.databind.node.JsonNodeType.NULL -> JsonNull.of()
        com.fasterxml.jackson.databind.node.JsonNodeType.BOOLEAN -> JsonBoolean.of(node.booleanValue())
        com.fasterxml.jackson.databind.node.JsonNodeType.NUMBER -> JsonNumber.of(node.numberValue())
        com.fasterxml.jackson.databind.node.JsonNodeType.STRING -> JsonString.of(node.textValue())
        com.fasterxml.jackson.databind.node.JsonNodeType.ARRAY -> JsonArray.of(node.elements().asSequence().map { fromJsonNode(it) }.toList())
        com.fasterxml.jackson.databind.node.JsonNodeType.OBJECT -> JsonObject.of(node.fields().asSequence().map { it.key to fromJsonNode(it.value) }.toMap())
        com.fasterxml.jackson.databind.node.JsonNodeType.BINARY, com.fasterxml.jackson.databind.node.JsonNodeType.POJO, null -> throw IllegalStateException("Unexpected JsonNode type: ${node.nodeType}")
    }
