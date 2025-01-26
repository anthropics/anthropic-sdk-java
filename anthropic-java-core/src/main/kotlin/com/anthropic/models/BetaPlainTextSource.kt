// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.NoAutoDetect
import com.anthropic.core.checkRequired
import com.anthropic.core.immutableEmptyMap
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Objects

@NoAutoDetect
class BetaPlainTextSource
@JsonCreator
private constructor(
    @JsonProperty("data") @ExcludeMissing private val data: JsonField<String> = JsonMissing.of(),
    @JsonProperty("media_type")
    @ExcludeMissing
    private val mediaType: JsonField<MediaType> = JsonMissing.of(),
    @JsonProperty("type") @ExcludeMissing private val type: JsonField<Type> = JsonMissing.of(),
    @JsonAnySetter private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
) {

    fun data(): String = data.getRequired("data")

    fun mediaType(): MediaType = mediaType.getRequired("media_type")

    fun type(): Type = type.getRequired("type")

    @JsonProperty("data") @ExcludeMissing fun _data(): JsonField<String> = data

    @JsonProperty("media_type") @ExcludeMissing fun _mediaType(): JsonField<MediaType> = mediaType

    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    private var validated: Boolean = false

    fun validate(): BetaPlainTextSource = apply {
        if (validated) {
            return@apply
        }

        data()
        mediaType()
        type()
        validated = true
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    class Builder internal constructor() {

        private var data: JsonField<String>? = null
        private var mediaType: JsonField<MediaType>? = null
        private var type: JsonField<Type>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaPlainTextSource: BetaPlainTextSource) = apply {
            data = betaPlainTextSource.data
            mediaType = betaPlainTextSource.mediaType
            type = betaPlainTextSource.type
            additionalProperties = betaPlainTextSource.additionalProperties.toMutableMap()
        }

        fun data(data: String) = data(JsonField.of(data))

        fun data(data: JsonField<String>) = apply { this.data = data }

        fun mediaType(mediaType: MediaType) = mediaType(JsonField.of(mediaType))

        fun mediaType(mediaType: JsonField<MediaType>) = apply { this.mediaType = mediaType }

        fun type(type: Type) = type(JsonField.of(type))

        fun type(type: JsonField<Type>) = apply { this.type = type }

        fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.clear()
            putAllAdditionalProperties(additionalProperties)
        }

        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
            additionalProperties.put(key, value)
        }

        fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.putAll(additionalProperties)
        }

        fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
            keys.forEach(::removeAdditionalProperty)
        }

        fun build(): BetaPlainTextSource =
            BetaPlainTextSource(
                checkRequired("data", data),
                checkRequired("mediaType", mediaType),
                checkRequired("type", type),
                additionalProperties.toImmutable(),
            )
    }

    class MediaType
    @JsonCreator
    private constructor(
        private val value: JsonField<String>,
    ) : Enum {

        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val TEXT_PLAIN = of("text/plain")

            @JvmStatic fun of(value: String) = MediaType(JsonField.of(value))
        }

        enum class Known {
            TEXT_PLAIN,
        }

        enum class Value {
            TEXT_PLAIN,
            _UNKNOWN,
        }

        fun value(): Value =
            when (this) {
                TEXT_PLAIN -> Value.TEXT_PLAIN
                else -> Value._UNKNOWN
            }

        fun known(): Known =
            when (this) {
                TEXT_PLAIN -> Known.TEXT_PLAIN
                else -> throw AnthropicInvalidDataException("Unknown MediaType: $value")
            }

        fun asString(): String = _value().asStringOrThrow()

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is MediaType && value == other.value /* spotless:on */
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    class Type
    @JsonCreator
    private constructor(
        private val value: JsonField<String>,
    ) : Enum {

        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val TEXT = of("text")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        enum class Known {
            TEXT,
        }

        enum class Value {
            TEXT,
            _UNKNOWN,
        }

        fun value(): Value =
            when (this) {
                TEXT -> Value.TEXT
                else -> Value._UNKNOWN
            }

        fun known(): Known =
            when (this) {
                TEXT -> Known.TEXT
                else -> throw AnthropicInvalidDataException("Unknown Type: $value")
            }

        fun asString(): String = _value().asStringOrThrow()

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Type && value == other.value /* spotless:on */
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaPlainTextSource && data == other.data && mediaType == other.mediaType && type == other.type && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(data, mediaType, type, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaPlainTextSource{data=$data, mediaType=$mediaType, type=$type, additionalProperties=$additionalProperties}"
}
