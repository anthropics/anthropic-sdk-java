// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
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
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Image content specified directly as base64 data or as a reference via a URL. */
class BetaManagedAgentsImageBlock
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val source: JsonField<Source>,
    private val type: JsonField<Type>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("source") @ExcludeMissing source: JsonField<Source> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonField<Type> = JsonMissing.of(),
    ) : this(source, type, mutableMapOf())

    /**
     * Union type for image source variants.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun source(): Source = source.getRequired("source")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun type(): Type = type.getRequired("type")

    /**
     * Returns the raw JSON value of [source].
     *
     * Unlike [source], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("source") @ExcludeMissing fun _source(): JsonField<Source> = source

    /**
     * Returns the raw JSON value of [type].
     *
     * Unlike [type], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

    @JsonAnySetter
    private fun putAdditionalProperty(key: String, value: JsonValue) {
        additionalProperties.put(key, value)
    }

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> =
        Collections.unmodifiableMap(additionalProperties)

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [BetaManagedAgentsImageBlock].
         *
         * The following fields are required:
         * ```java
         * .source()
         * .type()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsImageBlock]. */
    class Builder internal constructor() {

        private var source: JsonField<Source>? = null
        private var type: JsonField<Type>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaManagedAgentsImageBlock: BetaManagedAgentsImageBlock) = apply {
            source = betaManagedAgentsImageBlock.source
            type = betaManagedAgentsImageBlock.type
            additionalProperties = betaManagedAgentsImageBlock.additionalProperties.toMutableMap()
        }

        /** Union type for image source variants. */
        fun source(source: Source) = source(JsonField.of(source))

        /**
         * Sets [Builder.source] to an arbitrary JSON value.
         *
         * You should usually call [Builder.source] with a well-typed [Source] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun source(source: JsonField<Source>) = apply { this.source = source }

        /** Alias for calling [source] with `Source.ofBase64(base64)`. */
        fun source(base64: BetaManagedAgentsBase64ImageSource) = source(Source.ofBase64(base64))

        /** Alias for calling [source] with `Source.ofUrl(url)`. */
        fun source(url: BetaManagedAgentsUrlImageSource) = source(Source.ofUrl(url))

        /**
         * Alias for calling [source] with the following:
         * ```java
         * BetaManagedAgentsUrlImageSource.builder()
         *     .type(BetaManagedAgentsUrlImageSource.Type.URL)
         *     .url(url)
         *     .build()
         * ```
         */
        fun urlSource(url: String) =
            source(
                BetaManagedAgentsUrlImageSource.builder()
                    .type(BetaManagedAgentsUrlImageSource.Type.URL)
                    .url(url)
                    .build()
            )

        /** Alias for calling [source] with `Source.ofFile(file)`. */
        fun source(file: BetaManagedAgentsFileImageSource) = source(Source.ofFile(file))

        /**
         * Alias for calling [source] with the following:
         * ```java
         * BetaManagedAgentsFileImageSource.builder()
         *     .type(BetaManagedAgentsFileImageSource.Type.FILE)
         *     .fileId(fileId)
         *     .build()
         * ```
         */
        fun fileSource(fileId: String) =
            source(
                BetaManagedAgentsFileImageSource.builder()
                    .type(BetaManagedAgentsFileImageSource.Type.FILE)
                    .fileId(fileId)
                    .build()
            )

        fun type(type: Type) = type(JsonField.of(type))

        /**
         * Sets [Builder.type] to an arbitrary JSON value.
         *
         * You should usually call [Builder.type] with a well-typed [Type] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
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

        /**
         * Returns an immutable instance of [BetaManagedAgentsImageBlock].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .source()
         * .type()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsImageBlock =
            BetaManagedAgentsImageBlock(
                checkRequired("source", source),
                checkRequired("type", type),
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaManagedAgentsImageBlock = apply {
        if (validated) {
            return@apply
        }

        source().validate()
        type().validate()
        validated = true
    }

    fun isValid(): Boolean =
        try {
            validate()
            true
        } catch (e: AnthropicInvalidDataException) {
            false
        }

    /**
     * Returns a score indicating how many valid values are contained in this object recursively.
     *
     * Used for best match union deserialization.
     */
    @JvmSynthetic
    internal fun validity(): Int =
        (source.asKnown().getOrNull()?.validity() ?: 0) +
            (type.asKnown().getOrNull()?.validity() ?: 0)

    /** Union type for image source variants. */
    @JsonDeserialize(using = Source.Deserializer::class)
    @JsonSerialize(using = Source.Serializer::class)
    class Source
    private constructor(
        private val base64: BetaManagedAgentsBase64ImageSource? = null,
        private val url: BetaManagedAgentsUrlImageSource? = null,
        private val file: BetaManagedAgentsFileImageSource? = null,
        private val _json: JsonValue? = null,
    ) {

        /** Base64-encoded image data. */
        fun base64(): Optional<BetaManagedAgentsBase64ImageSource> = Optional.ofNullable(base64)

        /** Image referenced by URL. */
        fun url(): Optional<BetaManagedAgentsUrlImageSource> = Optional.ofNullable(url)

        /** Image referenced by file ID. */
        fun file(): Optional<BetaManagedAgentsFileImageSource> = Optional.ofNullable(file)

        fun isBase64(): Boolean = base64 != null

        fun isUrl(): Boolean = url != null

        fun isFile(): Boolean = file != null

        /** Base64-encoded image data. */
        fun asBase64(): BetaManagedAgentsBase64ImageSource = base64.getOrThrow("base64")

        /** Image referenced by URL. */
        fun asUrl(): BetaManagedAgentsUrlImageSource = url.getOrThrow("url")

        /** Image referenced by file ID. */
        fun asFile(): BetaManagedAgentsFileImageSource = file.getOrThrow("file")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T =
            when {
                base64 != null -> visitor.visitBase64(base64)
                url != null -> visitor.visitUrl(url)
                file != null -> visitor.visitFile(file)
                else -> visitor.unknown(_json)
            }

        private var validated: Boolean = false

        fun validate(): Source = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitBase64(base64: BetaManagedAgentsBase64ImageSource) {
                        base64.validate()
                    }

                    override fun visitUrl(url: BetaManagedAgentsUrlImageSource) {
                        url.validate()
                    }

                    override fun visitFile(file: BetaManagedAgentsFileImageSource) {
                        file.validate()
                    }
                }
            )
            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: AnthropicInvalidDataException) {
                false
            }

        /**
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic
        internal fun validity(): Int =
            accept(
                object : Visitor<Int> {
                    override fun visitBase64(base64: BetaManagedAgentsBase64ImageSource) =
                        base64.validity()

                    override fun visitUrl(url: BetaManagedAgentsUrlImageSource) = url.validity()

                    override fun visitFile(file: BetaManagedAgentsFileImageSource) = file.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Source &&
                base64 == other.base64 &&
                url == other.url &&
                file == other.file
        }

        override fun hashCode(): Int = Objects.hash(base64, url, file)

        override fun toString(): String =
            when {
                base64 != null -> "Source{base64=$base64}"
                url != null -> "Source{url=$url}"
                file != null -> "Source{file=$file}"
                _json != null -> "Source{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Source")
            }

        companion object {

            /** Base64-encoded image data. */
            @JvmStatic
            fun ofBase64(base64: BetaManagedAgentsBase64ImageSource) = Source(base64 = base64)

            /** Image referenced by URL. */
            @JvmStatic fun ofUrl(url: BetaManagedAgentsUrlImageSource) = Source(url = url)

            /** Image referenced by file ID. */
            @JvmStatic fun ofFile(file: BetaManagedAgentsFileImageSource) = Source(file = file)
        }

        /** An interface that defines how to map each variant of [Source] to a value of type [T]. */
        interface Visitor<out T> {

            /** Base64-encoded image data. */
            fun visitBase64(base64: BetaManagedAgentsBase64ImageSource): T

            /** Image referenced by URL. */
            fun visitUrl(url: BetaManagedAgentsUrlImageSource): T

            /** Image referenced by file ID. */
            fun visitFile(file: BetaManagedAgentsFileImageSource): T

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
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsBase64ImageSource>(),
                            )
                            ?.let { Source(base64 = it, _json = json) } ?: Source(_json = json)
                    }
                    "url" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsUrlImageSource>(),
                            )
                            ?.let { Source(url = it, _json = json) } ?: Source(_json = json)
                    }
                    "file" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsFileImageSource>(),
                            )
                            ?.let { Source(file = it, _json = json) } ?: Source(_json = json)
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
                    value.file != null -> generator.writeObject(value.file)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Source")
                }
            }
        }
    }

    class Type @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val IMAGE = of("image")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            IMAGE
        }

        /**
         * An enum containing [Type]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Type] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            IMAGE,
            /** An enum member indicating that [Type] was instantiated with an unknown value. */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                IMAGE -> Value.IMAGE
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws AnthropicInvalidDataException if this class instance's value is a not a known
         *   member.
         */
        fun known(): Known =
            when (this) {
                IMAGE -> Known.IMAGE
                else -> throw AnthropicInvalidDataException("Unknown Type: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws AnthropicInvalidDataException if this class instance's value does not have the
         *   expected primitive type.
         */
        fun asString(): String =
            _value().asString().orElseThrow {
                AnthropicInvalidDataException("Value is not a String")
            }

        private var validated: Boolean = false

        fun validate(): Type = apply {
            if (validated) {
                return@apply
            }

            known()
            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: AnthropicInvalidDataException) {
                false
            }

        /**
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Type && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsImageBlock &&
            source == other.source &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(source, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsImageBlock{source=$source, type=$type, additionalProperties=$additionalProperties}"
}
