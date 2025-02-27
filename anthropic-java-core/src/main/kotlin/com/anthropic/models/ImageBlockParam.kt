// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.NoAutoDetect
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
import com.anthropic.core.immutableEmptyMap
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

@NoAutoDetect
class ImageBlockParam
@JsonCreator
private constructor(
    @JsonProperty("source")
    @ExcludeMissing
    private val source: JsonField<Source> = JsonMissing.of(),
    @JsonProperty("type") @ExcludeMissing private val type: JsonValue = JsonMissing.of(),
    @JsonProperty("cache_control")
    @ExcludeMissing
    private val cacheControl: JsonField<CacheControlEphemeral> = JsonMissing.of(),
    @JsonAnySetter private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
) {

    fun source(): Source = source.getRequired("source")

    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    fun cacheControl(): Optional<CacheControlEphemeral> =
        Optional.ofNullable(cacheControl.getNullable("cache_control"))

    @JsonProperty("source") @ExcludeMissing fun _source(): JsonField<Source> = source

    @JsonProperty("cache_control")
    @ExcludeMissing
    fun _cacheControl(): JsonField<CacheControlEphemeral> = cacheControl

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    private var validated: Boolean = false

    fun validate(): ImageBlockParam = apply {
        if (validated) {
            return@apply
        }

        source().validate()
        _type().let {
            if (it != JsonValue.from("image")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        cacheControl().ifPresent { it.validate() }
        validated = true
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ImageBlockParam]. */
    class Builder internal constructor() {

        private var source: JsonField<Source>? = null
        private var type: JsonValue = JsonValue.from("image")
        private var cacheControl: JsonField<CacheControlEphemeral> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(imageBlockParam: ImageBlockParam) = apply {
            source = imageBlockParam.source
            type = imageBlockParam.type
            cacheControl = imageBlockParam.cacheControl
            additionalProperties = imageBlockParam.additionalProperties.toMutableMap()
        }

        fun source(source: Source) = source(JsonField.of(source))

        fun source(source: JsonField<Source>) = apply { this.source = source }

        fun source(base64Image: Base64ImageSource) = source(Source.ofBase64Image(base64Image))

        fun source(urlImage: UrlImageSource) = source(Source.ofUrlImage(urlImage))

        fun urlImageSource(url: String) = source(UrlImageSource.builder().url(url).build())

        fun type(type: JsonValue) = apply { this.type = type }

        fun cacheControl(cacheControl: CacheControlEphemeral?) =
            cacheControl(JsonField.ofNullable(cacheControl))

        fun cacheControl(cacheControl: Optional<CacheControlEphemeral>) =
            cacheControl(cacheControl.orElse(null))

        fun cacheControl(cacheControl: JsonField<CacheControlEphemeral>) = apply {
            this.cacheControl = cacheControl
        }

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

        fun build(): ImageBlockParam =
            ImageBlockParam(
                checkRequired("source", source),
                type,
                cacheControl,
                additionalProperties.toImmutable(),
            )
    }

    @JsonDeserialize(using = Source.Deserializer::class)
    @JsonSerialize(using = Source.Serializer::class)
    class Source
    private constructor(
        private val base64Image: Base64ImageSource? = null,
        private val urlImage: UrlImageSource? = null,
        private val _json: JsonValue? = null,
    ) {

        fun base64Image(): Optional<Base64ImageSource> = Optional.ofNullable(base64Image)

        fun urlImage(): Optional<UrlImageSource> = Optional.ofNullable(urlImage)

        fun isBase64Image(): Boolean = base64Image != null

        fun isUrlImage(): Boolean = urlImage != null

        fun asBase64Image(): Base64ImageSource = base64Image.getOrThrow("base64Image")

        fun asUrlImage(): UrlImageSource = urlImage.getOrThrow("urlImage")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T {
            return when {
                base64Image != null -> visitor.visitBase64Image(base64Image)
                urlImage != null -> visitor.visitUrlImage(urlImage)
                else -> visitor.unknown(_json)
            }
        }

        private var validated: Boolean = false

        fun validate(): Source = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitBase64Image(base64Image: Base64ImageSource) {
                        base64Image.validate()
                    }

                    override fun visitUrlImage(urlImage: UrlImageSource) {
                        urlImage.validate()
                    }
                }
            )
            validated = true
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Source && base64Image == other.base64Image && urlImage == other.urlImage /* spotless:on */
        }

        override fun hashCode(): Int = /* spotless:off */ Objects.hash(base64Image, urlImage) /* spotless:on */

        override fun toString(): String =
            when {
                base64Image != null -> "Source{base64Image=$base64Image}"
                urlImage != null -> "Source{urlImage=$urlImage}"
                _json != null -> "Source{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Source")
            }

        companion object {

            @JvmStatic
            fun ofBase64Image(base64Image: Base64ImageSource) = Source(base64Image = base64Image)

            @JvmStatic fun ofUrlImage(urlImage: UrlImageSource) = Source(urlImage = urlImage)
        }

        /** An interface that defines how to map each variant of [Source] to a value of type [T]. */
        interface Visitor<out T> {

            fun visitBase64Image(base64Image: Base64ImageSource): T

            fun visitUrlImage(urlImage: UrlImageSource): T

            /**
             * Maps an unknown variant of [Source] to a value of type [T].
             *
             * An instance of [Source] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Source: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Source>(Source::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Source {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "base64" -> {
                        tryDeserialize(node, jacksonTypeRef<Base64ImageSource>()) { it.validate() }
                            ?.let {
                                return Source(base64Image = it, _json = json)
                            }
                    }
                    "url" -> {
                        tryDeserialize(node, jacksonTypeRef<UrlImageSource>()) { it.validate() }
                            ?.let {
                                return Source(urlImage = it, _json = json)
                            }
                    }
                }

                return Source(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<Source>(Source::class) {

            override fun serialize(
                value: Source,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.base64Image != null -> generator.writeObject(value.base64Image)
                    value.urlImage != null -> generator.writeObject(value.urlImage)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Source")
                }
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is ImageBlockParam && source == other.source && type == other.type && cacheControl == other.cacheControl && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(source, type, cacheControl, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "ImageBlockParam{source=$source, type=$type, cacheControl=$cacheControl, additionalProperties=$additionalProperties}"
}
