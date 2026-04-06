// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages
import kotlinx.kmp.util.core.fromJsonNode
import kotlinx.kmp.util.core.getOptional

import kotlinx.kmp.util.core.BaseDeserializer
import kotlinx.kmp.util.core.BaseSerializer
import kotlinx.kmp.util.core.ExcludeMissing
import kotlinx.kmp.util.core.JsonField
import kotlinx.kmp.util.core.JsonMissing
import kotlinx.kmp.util.core.JsonValue
import kotlinx.kmp.util.core.checkRequired
import kotlinx.kmp.util.core.getOrThrow
import kotlinx.kmp.util.core.errors.ApiInvalidDataException
import kotlinx.kmp.util.core.json.JsonAnyGetter
import kotlinx.kmp.util.core.json.JsonAnySetter
import kotlinx.kmp.util.core.json.JsonCreator
import kotlinx.kmp.util.core.json.JsonCreatorMode
import kotlinx.kmp.util.core.json.JsonProperty
import kotlinx.kmp.util.core.json.JsonGenerator
import kotlinx.kmp.util.core.json.ObjectCodec
import kotlinx.kmp.util.core.json.JsonNode
import kotlinx.kmp.util.core.json.SerializerProvider
import kotlinx.kmp.util.core.json.JsonDeserialize
import kotlinx.kmp.util.core.json.JsonSerialize
import kotlinx.kmp.util.core.json.jacksonTypeRef
import kotlinx.kmp.util.core.contentHash
import java.util.Optional

import kotlin.jvm.optionals.getOrNull

class ImageBlockParam
@JsonCreator(mode = JsonCreatorMode.DISABLED)
private constructor(
    private val source: JsonField<Source>,
    private val type: JsonValue,
    private val cacheControl: JsonField<CacheControlEphemeral>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("source") @ExcludeMissing source: JsonField<Source> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("cache_control")
        @ExcludeMissing
        cacheControl: JsonField<CacheControlEphemeral> = JsonMissing.of(),
    ) : this(source, type, cacheControl, mutableMapOf())

    /**
     * @throws ApiInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun source(): Source = source.getRequired("source")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("image")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Create a cache control breakpoint at this content block.
     *
     * @throws ApiInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun cacheControl(): Optional<CacheControlEphemeral> = cacheControl.getOptional("cache_control")

    /**
     * Returns the raw JSON value of [source].
     *
     * Unlike [source], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("source") @ExcludeMissing fun _source(): JsonField<Source> = source

    /**
     * Returns the raw JSON value of [cacheControl].
     *
     * Unlike [cacheControl], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("cache_control")
    @ExcludeMissing
    fun _cacheControl(): JsonField<CacheControlEphemeral> = cacheControl

    @JsonAnySetter
    private fun putAdditionalProperty(key: String, value: JsonValue) {
        additionalProperties.put(key, value)
    }

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> =
        additionalProperties.toMap()

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [ImageBlockParam].
         *
         * The following fields are required:
         * ```java
         * .source()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ImageBlockParam]. */
    class Builder internal constructor() {

        private var source: JsonField<Source>? = null
        private var type: JsonValue = JsonValue.from("image")
        private var cacheControl: JsonField<CacheControlEphemeral> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic internal fun from(imageBlockParam: ImageBlockParam) = apply {
            source = imageBlockParam.source
            type = imageBlockParam.type
            cacheControl = imageBlockParam.cacheControl
            additionalProperties = imageBlockParam.additionalProperties.toMutableMap()
        }

        fun source(source: Source) = source(JsonField.of(source))

        /**
         * Sets [Builder.source] to an arbitrary JSON value.
         *
         * You should usually call [Builder.source] with a well-typed [Source] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun source(source: JsonField<Source>) = apply { this.source = source }

        /** Alias for calling [source] with `Source.ofBase64(base64)`. */
        fun source(base64: Base64ImageSource) = source(Source.ofBase64(base64))

        /** Alias for calling [source] with `Source.ofUrl(url)`. */
        fun source(url: UrlImageSource) = source(Source.ofUrl(url))

        /**
         * Alias for calling [source] with the following:
         * ```java
         * UrlImageSource.builder()
         *     .url(url)
         *     .build()
         * ```
         */
        fun urlSource(url: String) = source(UrlImageSource.builder().url(url).build())

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("image")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** Create a cache control breakpoint at this content block. */
        fun cacheControl(cacheControl: CacheControlEphemeral?) =
            cacheControl(JsonField.ofNullable(cacheControl))

        /** Alias for calling [Builder.cacheControl] with `cacheControl.orElse(null)`. */
        fun cacheControl(cacheControl: Optional<CacheControlEphemeral>) =
            cacheControl(cacheControl.getOrNull())

        /**
         * Sets [Builder.cacheControl] to an arbitrary JSON value.
         *
         * You should usually call [Builder.cacheControl] with a well-typed [CacheControlEphemeral]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
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

        /**
         * Returns an immutable instance of [ImageBlockParam].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .source()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): ImageBlockParam =
            ImageBlockParam(
                checkRequired("source", source),
                type,
                cacheControl,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): ImageBlockParam = apply {
        if (validated) {
            return@apply
        }

        source().validate()
        _type().let {
            if (it != JsonValue.from("image")) {
                throw ApiInvalidDataException("'type' is invalid, received $it")
            }
        }
        cacheControl().ifPresent { it.validate() }
        validated = true
    }

    fun isValid(): Boolean =
        try {
            validate()
            true
        } catch (e: ApiInvalidDataException) {
            false
        }

    /**
     * Returns a score indicating how many valid values are contained in this object recursively.
     *
     * Used for best match union deserialization.
     */
    internal fun validity(): Int =
        (source.asKnown()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("image")) 1 else 0 } +
            (cacheControl.asKnown()?.validity() ?: 0)

    @JsonDeserialize(using = Source.Deserializer::class)
    @JsonSerialize(using = Source.Serializer::class)
    class Source
    private constructor(
        private val base64: Base64ImageSource? = null,
        private val url: UrlImageSource? = null,
        private val _json: JsonValue? = null,
    ) {

        fun base64(): Optional<Base64ImageSource> = Optional.ofNullable(base64)

        fun url(): Optional<UrlImageSource> = Optional.ofNullable(url)

        fun isBase64(): Boolean = base64 != null

        fun isUrl(): Boolean = url != null

        fun asBase64(): Base64ImageSource = base64.getOrThrow("base64")

        fun asUrl(): UrlImageSource = url.getOrThrow("url")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T =
            when {
                base64 != null -> visitor.visitBase64(base64)
                url != null -> visitor.visitUrl(url)
                else -> visitor.unknown(_json)
            }

        private var validated: Boolean = false

        fun validate(): Source = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitBase64(base64: Base64ImageSource) {
                        base64.validate()
                    }

                    override fun visitUrl(url: UrlImageSource) {
                        url.validate()
                    }
                }
            )
            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: ApiInvalidDataException) {
                false
            }

        /**
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        internal fun validity(): Int =
            accept(
                object : Visitor<Int> {
                    override fun visitBase64(base64: Base64ImageSource) = base64.validity()

                    override fun visitUrl(url: UrlImageSource) = url.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Source && base64 == other.base64 && url == other.url
        }

        override fun hashCode(): Int = contentHash(base64, url)

        override fun toString(): String =
            when {
                base64 != null -> "Source{base64=$base64}"
                url != null -> "Source{url=$url}"
                _json != null -> "Source{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Source")
            }

        companion object {

            @JvmStatic fun ofBase64(base64: Base64ImageSource) = Source(base64 = base64)

            @JvmStatic fun ofUrl(url: UrlImageSource) = Source(url = url)
        }

        /** An interface that defines how to map each variant of [Source] to a value of type [T]. */
        interface Visitor<out T> {

            fun visitBase64(base64: Base64ImageSource): T

            fun visitUrl(url: UrlImageSource): T

            /**
             * Maps an unknown variant of [Source] to a value of type [T].
             *
             * An instance of [Source] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws ApiInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw ApiInvalidDataException("Unknown Source: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Source>(Source::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Source {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject()?.get("type")?.asString()

                when (type) {
                    "base64" -> {
                        return tryDeserialize(node, jacksonTypeRef<Base64ImageSource>())?.let {
                            Source(base64 = it, _json = json)
                        } ?: Source(_json = json)
                    }
                    "url" -> {
                        return tryDeserialize(node, jacksonTypeRef<UrlImageSource>())?.let {
                            Source(url = it, _json = json)
                        } ?: Source(_json = json)
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
                    value.base64 != null -> generator.writeObject(value.base64)
                    value.url != null -> generator.writeObject(value.url)
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

        return other is ImageBlockParam &&
            source == other.source &&
            type == other.type &&
            cacheControl == other.cacheControl &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        contentHash(source, type, cacheControl, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "ImageBlockParam{source=$source, type=$type, cacheControl=$cacheControl, additionalProperties=$additionalProperties}"
}
