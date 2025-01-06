// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.NoAutoDetect
import com.anthropic.core.immutableEmptyMap
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Objects

@NoAutoDetect
class Base64PdfSource
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

    @JsonProperty("data") @ExcludeMissing fun _data() = data

    @JsonProperty("media_type") @ExcludeMissing fun _mediaType() = mediaType

    @JsonProperty("type") @ExcludeMissing fun _type() = type

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    private var validated: Boolean = false

    fun validate(): Base64PdfSource = apply {
        if (!validated) {
            data()
            mediaType()
            type()
            validated = true
        }
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    class Builder {

        private var data: JsonField<String> = JsonMissing.of()
        private var mediaType: JsonField<MediaType> = JsonMissing.of()
        private var type: JsonField<Type> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(base64PdfSource: Base64PdfSource) = apply {
            data = base64PdfSource.data
            mediaType = base64PdfSource.mediaType
            type = base64PdfSource.type
            additionalProperties = base64PdfSource.additionalProperties.toMutableMap()
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

        fun build(): Base64PdfSource =
            Base64PdfSource(
                data,
                mediaType,
                type,
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

            @JvmField val APPLICATION_PDF = of("application/pdf")

            @JvmStatic fun of(value: String) = MediaType(JsonField.of(value))
        }

        enum class Known {
            APPLICATION_PDF,
        }

        enum class Value {
            APPLICATION_PDF,
            _UNKNOWN,
        }

        fun value(): Value =
            when (this) {
                APPLICATION_PDF -> Value.APPLICATION_PDF
                else -> Value._UNKNOWN
            }

        fun known(): Known =
            when (this) {
                APPLICATION_PDF -> Known.APPLICATION_PDF
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

            @JvmField val BASE64 = of("base64")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        enum class Known {
            BASE64,
        }

        enum class Value {
            BASE64,
            _UNKNOWN,
        }

        fun value(): Value =
            when (this) {
                BASE64 -> Value.BASE64
                else -> Value._UNKNOWN
            }

        fun known(): Known =
            when (this) {
                BASE64 -> Known.BASE64
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

        return /* spotless:off */ other is Base64PdfSource && data == other.data && mediaType == other.mediaType && type == other.type && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(data, mediaType, type, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "Base64PdfSource{data=$data, mediaType=$mediaType, type=$type, additionalProperties=$additionalProperties}"
}
