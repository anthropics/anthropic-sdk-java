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

/**
 * Document content, either specified directly as base64 data, as text, or as a reference via a URL.
 */
class BetaManagedAgentsDocumentBlock
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val source: JsonField<Source>,
    private val type: JsonField<Type>,
    private val context: JsonField<String>,
    private val title: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("source") @ExcludeMissing source: JsonField<Source> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonField<Type> = JsonMissing.of(),
        @JsonProperty("context") @ExcludeMissing context: JsonField<String> = JsonMissing.of(),
        @JsonProperty("title") @ExcludeMissing title: JsonField<String> = JsonMissing.of(),
    ) : this(source, type, context, title, mutableMapOf())

    /**
     * Union type for document source variants.
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
     * Additional context about the document for the model.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun context(): Optional<String> = context.getOptional("context")

    /**
     * The title of the document.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun title(): Optional<String> = title.getOptional("title")

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

    /**
     * Returns the raw JSON value of [context].
     *
     * Unlike [context], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("context") @ExcludeMissing fun _context(): JsonField<String> = context

    /**
     * Returns the raw JSON value of [title].
     *
     * Unlike [title], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("title") @ExcludeMissing fun _title(): JsonField<String> = title

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
         * Returns a mutable builder for constructing an instance of
         * [BetaManagedAgentsDocumentBlock].
         *
         * The following fields are required:
         * ```java
         * .source()
         * .type()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsDocumentBlock]. */
    class Builder internal constructor() {

        private var source: JsonField<Source>? = null
        private var type: JsonField<Type>? = null
        private var context: JsonField<String> = JsonMissing.of()
        private var title: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaManagedAgentsDocumentBlock: BetaManagedAgentsDocumentBlock) = apply {
            source = betaManagedAgentsDocumentBlock.source
            type = betaManagedAgentsDocumentBlock.type
            context = betaManagedAgentsDocumentBlock.context
            title = betaManagedAgentsDocumentBlock.title
            additionalProperties =
                betaManagedAgentsDocumentBlock.additionalProperties.toMutableMap()
        }

        /** Union type for document source variants. */
        fun source(source: Source) = source(JsonField.of(source))

        /**
         * Sets [Builder.source] to an arbitrary JSON value.
         *
         * You should usually call [Builder.source] with a well-typed [Source] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun source(source: JsonField<Source>) = apply { this.source = source }

        /** Alias for calling [source] with `Source.ofBase64(base64)`. */
        fun source(base64: BetaManagedAgentsBase64DocumentSource) = source(Source.ofBase64(base64))

        /** Alias for calling [source] with `Source.ofText(text)`. */
        fun source(text: BetaManagedAgentsPlainTextDocumentSource) = source(Source.ofText(text))

        /** Alias for calling [source] with `Source.ofUrl(url)`. */
        fun source(url: BetaManagedAgentsUrlDocumentSource) = source(Source.ofUrl(url))

        /**
         * Alias for calling [source] with the following:
         * ```java
         * BetaManagedAgentsUrlDocumentSource.builder()
         *     .type(BetaManagedAgentsUrlDocumentSource.Type.URL)
         *     .url(url)
         *     .build()
         * ```
         */
        fun urlSource(url: String) =
            source(
                BetaManagedAgentsUrlDocumentSource.builder()
                    .type(BetaManagedAgentsUrlDocumentSource.Type.URL)
                    .url(url)
                    .build()
            )

        /** Alias for calling [source] with `Source.ofFile(file)`. */
        fun source(file: BetaManagedAgentsFileDocumentSource) = source(Source.ofFile(file))

        /**
         * Alias for calling [source] with the following:
         * ```java
         * BetaManagedAgentsFileDocumentSource.builder()
         *     .type(BetaManagedAgentsFileDocumentSource.Type.FILE)
         *     .fileId(fileId)
         *     .build()
         * ```
         */
        fun fileSource(fileId: String) =
            source(
                BetaManagedAgentsFileDocumentSource.builder()
                    .type(BetaManagedAgentsFileDocumentSource.Type.FILE)
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

        /** Additional context about the document for the model. */
        fun context(context: String?) = context(JsonField.ofNullable(context))

        /** Alias for calling [Builder.context] with `context.orElse(null)`. */
        fun context(context: Optional<String>) = context(context.getOrNull())

        /**
         * Sets [Builder.context] to an arbitrary JSON value.
         *
         * You should usually call [Builder.context] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun context(context: JsonField<String>) = apply { this.context = context }

        /** The title of the document. */
        fun title(title: String?) = title(JsonField.ofNullable(title))

        /** Alias for calling [Builder.title] with `title.orElse(null)`. */
        fun title(title: Optional<String>) = title(title.getOrNull())

        /**
         * Sets [Builder.title] to an arbitrary JSON value.
         *
         * You should usually call [Builder.title] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun title(title: JsonField<String>) = apply { this.title = title }

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
         * Returns an immutable instance of [BetaManagedAgentsDocumentBlock].
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
        fun build(): BetaManagedAgentsDocumentBlock =
            BetaManagedAgentsDocumentBlock(
                checkRequired("source", source),
                checkRequired("type", type),
                context,
                title,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    /**
     * Validates that the types of all values in this object match their expected types recursively.
     *
     * This method is _not_ forwards compatible with new types from the API for existing fields.
     *
     * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
     *   expected type.
     */
    fun validate(): BetaManagedAgentsDocumentBlock = apply {
        if (validated) {
            return@apply
        }

        source().validate()
        type().validate()
        context()
        title()
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
            (type.asKnown().getOrNull()?.validity() ?: 0) +
            (if (context.asKnown().isPresent) 1 else 0) +
            (if (title.asKnown().isPresent) 1 else 0)

    /** Union type for document source variants. */
    @JsonDeserialize(using = Source.Deserializer::class)
    @JsonSerialize(using = Source.Serializer::class)
    class Source
    private constructor(
        private val base64: BetaManagedAgentsBase64DocumentSource? = null,
        private val text: BetaManagedAgentsPlainTextDocumentSource? = null,
        private val url: BetaManagedAgentsUrlDocumentSource? = null,
        private val file: BetaManagedAgentsFileDocumentSource? = null,
        private val _json: JsonValue? = null,
    ) {

        /** Base64-encoded document data. */
        fun base64(): Optional<BetaManagedAgentsBase64DocumentSource> = Optional.ofNullable(base64)

        /** Plain text document content. */
        fun text(): Optional<BetaManagedAgentsPlainTextDocumentSource> = Optional.ofNullable(text)

        /** Document referenced by URL. */
        fun url(): Optional<BetaManagedAgentsUrlDocumentSource> = Optional.ofNullable(url)

        /** Document referenced by file ID. */
        fun file(): Optional<BetaManagedAgentsFileDocumentSource> = Optional.ofNullable(file)

        fun isBase64(): Boolean = base64 != null

        fun isText(): Boolean = text != null

        fun isUrl(): Boolean = url != null

        fun isFile(): Boolean = file != null

        /** Base64-encoded document data. */
        fun asBase64(): BetaManagedAgentsBase64DocumentSource = base64.getOrThrow("base64")

        /** Plain text document content. */
        fun asText(): BetaManagedAgentsPlainTextDocumentSource = text.getOrThrow("text")

        /** Document referenced by URL. */
        fun asUrl(): BetaManagedAgentsUrlDocumentSource = url.getOrThrow("url")

        /** Document referenced by file ID. */
        fun asFile(): BetaManagedAgentsFileDocumentSource = file.getOrThrow("file")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        /**
         * Maps this instance's current variant to a value of type [T] using the given [visitor].
         *
         * Note that this method is _not_ forwards compatible with new variants from the API, unless
         * [visitor] overrides [Visitor.unknown]. To handle variants not known to this version of
         * the SDK gracefully, consider overriding [Visitor.unknown]:
         * ```java
         * import com.anthropic.core.JsonValue;
         * import java.util.Optional;
         *
         * Optional<String> result = source.accept(new Source.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitBase64(BetaManagedAgentsBase64DocumentSource base64) {
         *         return Optional.of(base64.toString());
         *     }
         *
         *     // ...
         *
         *     @Override
         *     public Optional<String> unknown(JsonValue json) {
         *         // Or inspect the `json`.
         *         return Optional.empty();
         *     }
         * });
         * ```
         *
         * @throws AnthropicInvalidDataException if [Visitor.unknown] is not overridden in [visitor]
         *   and the current variant is unknown.
         */
        fun <T> accept(visitor: Visitor<T>): T =
            when {
                base64 != null -> visitor.visitBase64(base64)
                text != null -> visitor.visitText(text)
                url != null -> visitor.visitUrl(url)
                file != null -> visitor.visitFile(file)
                else -> visitor.unknown(_json)
            }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
         */
        fun validate(): Source = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitBase64(base64: BetaManagedAgentsBase64DocumentSource) {
                        base64.validate()
                    }

                    override fun visitText(text: BetaManagedAgentsPlainTextDocumentSource) {
                        text.validate()
                    }

                    override fun visitUrl(url: BetaManagedAgentsUrlDocumentSource) {
                        url.validate()
                    }

                    override fun visitFile(file: BetaManagedAgentsFileDocumentSource) {
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
                    override fun visitBase64(base64: BetaManagedAgentsBase64DocumentSource) =
                        base64.validity()

                    override fun visitText(text: BetaManagedAgentsPlainTextDocumentSource) =
                        text.validity()

                    override fun visitUrl(url: BetaManagedAgentsUrlDocumentSource) = url.validity()

                    override fun visitFile(file: BetaManagedAgentsFileDocumentSource) =
                        file.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Source &&
                base64 == other.base64 &&
                text == other.text &&
                url == other.url &&
                file == other.file
        }

        override fun hashCode(): Int = Objects.hash(base64, text, url, file)

        override fun toString(): String =
            when {
                base64 != null -> "Source{base64=$base64}"
                text != null -> "Source{text=$text}"
                url != null -> "Source{url=$url}"
                file != null -> "Source{file=$file}"
                _json != null -> "Source{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Source")
            }

        companion object {

            /** Base64-encoded document data. */
            @JvmStatic
            fun ofBase64(base64: BetaManagedAgentsBase64DocumentSource) = Source(base64 = base64)

            /** Plain text document content. */
            @JvmStatic
            fun ofText(text: BetaManagedAgentsPlainTextDocumentSource) = Source(text = text)

            /** Document referenced by URL. */
            @JvmStatic fun ofUrl(url: BetaManagedAgentsUrlDocumentSource) = Source(url = url)

            /** Document referenced by file ID. */
            @JvmStatic fun ofFile(file: BetaManagedAgentsFileDocumentSource) = Source(file = file)
        }

        /** An interface that defines how to map each variant of [Source] to a value of type [T]. */
        interface Visitor<out T> {

            /** Base64-encoded document data. */
            fun visitBase64(base64: BetaManagedAgentsBase64DocumentSource): T

            /** Plain text document content. */
            fun visitText(text: BetaManagedAgentsPlainTextDocumentSource): T

            /** Document referenced by URL. */
            fun visitUrl(url: BetaManagedAgentsUrlDocumentSource): T

            /** Document referenced by file ID. */
            fun visitFile(file: BetaManagedAgentsFileDocumentSource): T

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
                                jacksonTypeRef<BetaManagedAgentsBase64DocumentSource>(),
                            )
                            ?.let { Source(base64 = it, _json = json) } ?: Source(_json = json)
                    }
                    "text" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsPlainTextDocumentSource>(),
                            )
                            ?.let { Source(text = it, _json = json) } ?: Source(_json = json)
                    }
                    "url" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsUrlDocumentSource>(),
                            )
                            ?.let { Source(url = it, _json = json) } ?: Source(_json = json)
                    }
                    "file" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsFileDocumentSource>(),
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
                    value.text != null -> generator.writeObject(value.text)
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

            @JvmField val DOCUMENT = of("document")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            DOCUMENT
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
            DOCUMENT,
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
                DOCUMENT -> Value.DOCUMENT
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
                DOCUMENT -> Known.DOCUMENT
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

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
         */
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

        return other is BetaManagedAgentsDocumentBlock &&
            source == other.source &&
            type == other.type &&
            context == other.context &&
            title == other.title &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(source, type, context, title, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsDocumentBlock{source=$source, type=$type, context=$context, title=$title, additionalProperties=$additionalProperties}"
}
