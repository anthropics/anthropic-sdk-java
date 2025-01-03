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
import java.util.Optional

@JsonDeserialize(builder = BetaImageBlockParam.Builder::class)
@NoAutoDetect
class BetaImageBlockParam
private constructor(
    private val cacheControl: JsonField<BetaCacheControlEphemeral>,
    private val type: JsonField<Type>,
    private val source: JsonField<Source>,
    private val additionalProperties: Map<String, JsonValue>,
) {

    private var validated: Boolean = false

    fun cacheControl(): Optional<BetaCacheControlEphemeral> =
        Optional.ofNullable(cacheControl.getNullable("cache_control"))

    fun type(): Type = type.getRequired("type")

    fun source(): Source = source.getRequired("source")

    @JsonProperty("cache_control") @ExcludeMissing fun _cacheControl() = cacheControl

    @JsonProperty("type") @ExcludeMissing fun _type() = type

    @JsonProperty("source") @ExcludeMissing fun _source() = source

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    fun validate(): BetaImageBlockParam = apply {
        if (!validated) {
            cacheControl().map { it.validate() }
            type()
            source().validate()
            validated = true
        }
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    class Builder {

        private var cacheControl: JsonField<BetaCacheControlEphemeral> = JsonMissing.of()
        private var type: JsonField<Type> = JsonMissing.of()
        private var source: JsonField<Source> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaImageBlockParam: BetaImageBlockParam) = apply {
            this.cacheControl = betaImageBlockParam.cacheControl
            this.type = betaImageBlockParam.type
            this.source = betaImageBlockParam.source
            additionalProperties(betaImageBlockParam.additionalProperties)
        }

        fun cacheControl(cacheControl: BetaCacheControlEphemeral) =
            cacheControl(JsonField.of(cacheControl))

        @JsonProperty("cache_control")
        @ExcludeMissing
        fun cacheControl(cacheControl: JsonField<BetaCacheControlEphemeral>) = apply {
            this.cacheControl = cacheControl
        }

        fun type(type: Type) = type(JsonField.of(type))

        @JsonProperty("type")
        @ExcludeMissing
        fun type(type: JsonField<Type>) = apply { this.type = type }

        fun source(source: Source) = source(JsonField.of(source))

        @JsonProperty("source")
        @ExcludeMissing
        fun source(source: JsonField<Source>) = apply { this.source = source }

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

        fun build(): BetaImageBlockParam =
            BetaImageBlockParam(
                cacheControl,
                type,
                source,
                additionalProperties.toImmutable(),
            )
    }

    @JsonDeserialize(builder = Source.Builder::class)
    @NoAutoDetect
    class Source
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

        fun validate(): Source = apply {
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
            internal fun from(source: Source) = apply {
                this.type = source.type
                this.mediaType = source.mediaType
                this.data = source.data
                additionalProperties(source.additionalProperties)
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

            fun build(): Source =
                Source(
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

            companion object {

                @JvmField val IMAGE_JPEG = of("image/jpeg")

                @JvmField val IMAGE_PNG = of("image/png")

                @JvmField val IMAGE_GIF = of("image/gif")

                @JvmField val IMAGE_WEBP = of("image/webp")

                @JvmStatic fun of(value: String) = MediaType(JsonField.of(value))
            }

            enum class Known {
                IMAGE_JPEG,
                IMAGE_PNG,
                IMAGE_GIF,
                IMAGE_WEBP,
            }

            enum class Value {
                IMAGE_JPEG,
                IMAGE_PNG,
                IMAGE_GIF,
                IMAGE_WEBP,
                _UNKNOWN,
            }

            fun value(): Value =
                when (this) {
                    IMAGE_JPEG -> Value.IMAGE_JPEG
                    IMAGE_PNG -> Value.IMAGE_PNG
                    IMAGE_GIF -> Value.IMAGE_GIF
                    IMAGE_WEBP -> Value.IMAGE_WEBP
                    else -> Value._UNKNOWN
                }

            fun known(): Known =
                when (this) {
                    IMAGE_JPEG -> Known.IMAGE_JPEG
                    IMAGE_PNG -> Known.IMAGE_PNG
                    IMAGE_GIF -> Known.IMAGE_GIF
                    IMAGE_WEBP -> Known.IMAGE_WEBP
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

            return /* spotless:off */ other is Source && type == other.type && mediaType == other.mediaType && data == other.data && additionalProperties == other.additionalProperties /* spotless:on */
        }

        /* spotless:off */
        private val hashCode: Int by lazy { Objects.hash(type, mediaType, data, additionalProperties) }
        /* spotless:on */

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Source{type=$type, mediaType=$mediaType, data=$data, additionalProperties=$additionalProperties}"
    }

    class Type
    @JsonCreator
    private constructor(
        private val value: JsonField<String>,
    ) : Enum {

        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val IMAGE = of("image")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        enum class Known {
            IMAGE,
        }

        enum class Value {
            IMAGE,
            _UNKNOWN,
        }

        fun value(): Value =
            when (this) {
                IMAGE -> Value.IMAGE
                else -> Value._UNKNOWN
            }

        fun known(): Known =
            when (this) {
                IMAGE -> Known.IMAGE
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

        return /* spotless:off */ other is BetaImageBlockParam && cacheControl == other.cacheControl && type == other.type && source == other.source && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(cacheControl, type, source, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaImageBlockParam{cacheControl=$cacheControl, type=$type, source=$source, additionalProperties=$additionalProperties}"
}
