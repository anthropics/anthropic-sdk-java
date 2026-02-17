// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
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

class DocumentBlock
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val citations: JsonField<CitationsConfig>,
    private val source: JsonField<Source>,
    private val title: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("citations")
        @ExcludeMissing
        citations: JsonField<CitationsConfig> = JsonMissing.of(),
        @JsonProperty("source") @ExcludeMissing source: JsonField<Source> = JsonMissing.of(),
        @JsonProperty("title") @ExcludeMissing title: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(citations, source, title, type, mutableMapOf())

    /**
     * Citation configuration for the document
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun citations(): Optional<CitationsConfig> = citations.getOptional("citations")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun source(): Source = source.getRequired("source")

    /**
     * The title of the document
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun title(): Optional<String> = title.getOptional("title")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("document")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [citations].
     *
     * Unlike [citations], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("citations")
    @ExcludeMissing
    fun _citations(): JsonField<CitationsConfig> = citations

    /**
     * Returns the raw JSON value of [source].
     *
     * Unlike [source], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("source") @ExcludeMissing fun _source(): JsonField<Source> = source

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
         * Returns a mutable builder for constructing an instance of [DocumentBlock].
         *
         * The following fields are required:
         * ```java
         * .citations()
         * .source()
         * .title()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [DocumentBlock]. */
    class Builder internal constructor() {

        private var citations: JsonField<CitationsConfig>? = null
        private var source: JsonField<Source>? = null
        private var title: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("document")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(documentBlock: DocumentBlock) = apply {
            citations = documentBlock.citations
            source = documentBlock.source
            title = documentBlock.title
            type = documentBlock.type
            additionalProperties = documentBlock.additionalProperties.toMutableMap()
        }

        /** Citation configuration for the document */
        fun citations(citations: CitationsConfig?) = citations(JsonField.ofNullable(citations))

        /** Alias for calling [Builder.citations] with `citations.orElse(null)`. */
        fun citations(citations: Optional<CitationsConfig>) = citations(citations.getOrNull())

        /**
         * Sets [Builder.citations] to an arbitrary JSON value.
         *
         * You should usually call [Builder.citations] with a well-typed [CitationsConfig] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun citations(citations: JsonField<CitationsConfig>) = apply { this.citations = citations }

        fun source(source: Source) = source(JsonField.of(source))

        /**
         * Sets [Builder.source] to an arbitrary JSON value.
         *
         * You should usually call [Builder.source] with a well-typed [Source] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun source(source: JsonField<Source>) = apply { this.source = source }

        /** Alias for calling [source] with `Source.ofBase64(base64)`. */
        fun source(base64: Base64PdfSource) = source(Source.ofBase64(base64))

        /**
         * Alias for calling [source] with the following:
         * ```java
         * Base64PdfSource.builder()
         *     .data(data)
         *     .build()
         * ```
         */
        fun base64Source(data: String) = source(Base64PdfSource.builder().data(data).build())

        /** Alias for calling [source] with `Source.ofText(text)`. */
        fun source(text: PlainTextSource) = source(Source.ofText(text))

        /**
         * Alias for calling [source] with the following:
         * ```java
         * PlainTextSource.builder()
         *     .data(data)
         *     .build()
         * ```
         */
        fun textSource(data: String) = source(PlainTextSource.builder().data(data).build())

        /** The title of the document */
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

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("document")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

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
         * Returns an immutable instance of [DocumentBlock].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .citations()
         * .source()
         * .title()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): DocumentBlock =
            DocumentBlock(
                checkRequired("citations", citations),
                checkRequired("source", source),
                checkRequired("title", title),
                type,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): DocumentBlock = apply {
        if (validated) {
            return@apply
        }

        citations().ifPresent { it.validate() }
        source().validate()
        title()
        _type().let {
            if (it != JsonValue.from("document")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
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
        (citations.asKnown().getOrNull()?.validity() ?: 0) +
            (source.asKnown().getOrNull()?.validity() ?: 0) +
            (if (title.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("document")) 1 else 0 }

    @JsonDeserialize(using = Source.Deserializer::class)
    @JsonSerialize(using = Source.Serializer::class)
    class Source
    private constructor(
        private val base64: Base64PdfSource? = null,
        private val text: PlainTextSource? = null,
        private val _json: JsonValue? = null,
    ) {

        fun base64(): Optional<Base64PdfSource> = Optional.ofNullable(base64)

        fun text(): Optional<PlainTextSource> = Optional.ofNullable(text)

        fun isBase64(): Boolean = base64 != null

        fun isText(): Boolean = text != null

        fun asBase64(): Base64PdfSource = base64.getOrThrow("base64")

        fun asText(): PlainTextSource = text.getOrThrow("text")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T =
            when {
                base64 != null -> visitor.visitBase64(base64)
                text != null -> visitor.visitText(text)
                else -> visitor.unknown(_json)
            }

        private var validated: Boolean = false

        fun validate(): Source = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitBase64(base64: Base64PdfSource) {
                        base64.validate()
                    }

                    override fun visitText(text: PlainTextSource) {
                        text.validate()
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
                    override fun visitBase64(base64: Base64PdfSource) = base64.validity()

                    override fun visitText(text: PlainTextSource) = text.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Source && base64 == other.base64 && text == other.text
        }

        override fun hashCode(): Int = Objects.hash(base64, text)

        override fun toString(): String =
            when {
                base64 != null -> "Source{base64=$base64}"
                text != null -> "Source{text=$text}"
                _json != null -> "Source{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Source")
            }

        companion object {

            @JvmStatic fun ofBase64(base64: Base64PdfSource) = Source(base64 = base64)

            @JvmStatic fun ofText(text: PlainTextSource) = Source(text = text)
        }

        /** An interface that defines how to map each variant of [Source] to a value of type [T]. */
        interface Visitor<out T> {

            fun visitBase64(base64: Base64PdfSource): T

            fun visitText(text: PlainTextSource): T

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
                        return tryDeserialize(node, jacksonTypeRef<Base64PdfSource>())?.let {
                            Source(base64 = it, _json = json)
                        } ?: Source(_json = json)
                    }
                    "text" -> {
                        return tryDeserialize(node, jacksonTypeRef<PlainTextSource>())?.let {
                            Source(text = it, _json = json)
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
                    value.text != null -> generator.writeObject(value.text)
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

        return other is DocumentBlock &&
            citations == other.citations &&
            source == other.source &&
            title == other.title &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(citations, source, title, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "DocumentBlock{citations=$citations, source=$source, title=$title, type=$type, additionalProperties=$additionalProperties}"
}
