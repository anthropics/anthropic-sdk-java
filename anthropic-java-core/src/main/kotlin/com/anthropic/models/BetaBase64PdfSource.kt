// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.NoAutoDetect
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import java.util.Objects

@JsonDeserialize(builder = BetaBase64PdfSource.Builder::class)
@NoAutoDetect
class BetaBase64PdfSource
private constructor(
    private val type: JsonField<Type>,
    private val mediaType: JsonField<MediaType>,
    private val data: JsonField<String>,
    private val additionalProperties: Map<String, JsonValue>,
) {

    private var validated: Boolean = false

    fun type(): Type = type.getRequired("type")

    fun mediaType(): MediaType = mediaType.getRequired("media_type")

    fun data(): String = data.getRequired("data")

    @JsonProperty("type") @ExcludeMissing fun _type() = type

    @JsonProperty("media_type") @ExcludeMissing fun _mediaType() = mediaType

    @JsonProperty("data") @ExcludeMissing fun _data() = data

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    fun validate(): BetaBase64PdfSource = apply {
        if (!validated) {
            type()
            mediaType()
            data()
            validated = true
        }
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    class Builder {

        private var type: JsonField<Type> = JsonMissing.of()
        private var mediaType: JsonField<MediaType> = JsonMissing.of()
        private var data: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaBase64PdfSource: BetaBase64PdfSource) = apply {
            this.type = betaBase64PdfSource.type
            this.mediaType = betaBase64PdfSource.mediaType
            this.data = betaBase64PdfSource.data
            additionalProperties(betaBase64PdfSource.additionalProperties)
        }

        fun type(type: Type) = type(JsonField.of(type))

        @JsonProperty("type")
        @ExcludeMissing
        fun type(type: JsonField<Type>) = apply { this.type = type }

        fun mediaType(mediaType: MediaType) = mediaType(JsonField.of(mediaType))

        @JsonProperty("media_type")
        @ExcludeMissing
        fun mediaType(mediaType: JsonField<MediaType>) = apply { this.mediaType = mediaType }

        fun data(data: String) = data(JsonField.of(data))

        @JsonProperty("data")
        @ExcludeMissing
        fun data(data: JsonField<String>) = apply { this.data = data }

        fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.clear()
            this.additionalProperties.putAll(additionalProperties)
        }

        @JsonAnySetter
        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
            this.additionalProperties.put(key, value)
        }

        fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.putAll(additionalProperties)
        }

        fun build(): BetaBase64PdfSource =
            BetaBase64PdfSource(
                type,
                mediaType,
                data,
                additionalProperties.toImmutable(),
            )
    }

    class MediaType
    @JsonCreator
    private constructor(
        private val value: JsonField<String>,
    ) : Enum {

        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is MediaType && value == other.value /* spotless:on */
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()

        companion object {

            @JvmField val APPLICATION_PDF = MediaType(JsonField.of("application/pdf"))

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
    }

    class Type
    @JsonCreator
    private constructor(
        private val value: JsonField<String>,
    ) : Enum {

        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Type && value == other.value /* spotless:on */
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()

        companion object {

            @JvmField val BASE64 = Type(JsonField.of("base64"))

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
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaBase64PdfSource && type == other.type && mediaType == other.mediaType && data == other.data && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(type, mediaType, data, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaBase64PdfSource{type=$type, mediaType=$mediaType, data=$data, additionalProperties=$additionalProperties}"
}
