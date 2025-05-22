// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

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

class BetaBase64PdfBlock
private constructor(
    private val source: JsonField<Source>,
    private val type: JsonValue,
    private val cacheControl: JsonField<BetaCacheControlEphemeral>,
    private val citations: JsonField<BetaCitationsConfigParam>,
    private val context: JsonField<String>,
    private val title: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("source") @ExcludeMissing source: JsonField<Source> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("cache_control")
        @ExcludeMissing
        cacheControl: JsonField<BetaCacheControlEphemeral> = JsonMissing.of(),
        @JsonProperty("citations")
        @ExcludeMissing
        citations: JsonField<BetaCitationsConfigParam> = JsonMissing.of(),
        @JsonProperty("context") @ExcludeMissing context: JsonField<String> = JsonMissing.of(),
        @JsonProperty("title") @ExcludeMissing title: JsonField<String> = JsonMissing.of(),
    ) : this(source, type, cacheControl, citations, context, title, mutableMapOf())

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun source(): Source = source.getRequired("source")

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
     * Create a cache control breakpoint at this content block.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun cacheControl(): Optional<BetaCacheControlEphemeral> =
        cacheControl.getOptional("cache_control")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun citations(): Optional<BetaCitationsConfigParam> = citations.getOptional("citations")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun context(): Optional<String> = context.getOptional("context")

    /**
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
     * Returns the raw JSON value of [cacheControl].
     *
     * Unlike [cacheControl], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("cache_control")
    @ExcludeMissing
    fun _cacheControl(): JsonField<BetaCacheControlEphemeral> = cacheControl

    /**
     * Returns the raw JSON value of [citations].
     *
     * Unlike [citations], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("citations")
    @ExcludeMissing
    fun _citations(): JsonField<BetaCitationsConfigParam> = citations

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
         * Returns a mutable builder for constructing an instance of [BetaBase64PdfBlock].
         *
         * The following fields are required:
         * ```java
         * .source()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaBase64PdfBlock]. */
    class Builder internal constructor() {

        private var source: JsonField<Source>? = null
        private var type: JsonValue = JsonValue.from("document")
        private var cacheControl: JsonField<BetaCacheControlEphemeral> = JsonMissing.of()
        private var citations: JsonField<BetaCitationsConfigParam> = JsonMissing.of()
        private var context: JsonField<String> = JsonMissing.of()
        private var title: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaBase64PdfBlock: BetaBase64PdfBlock) = apply {
            source = betaBase64PdfBlock.source
            type = betaBase64PdfBlock.type
            cacheControl = betaBase64PdfBlock.cacheControl
            citations = betaBase64PdfBlock.citations
            context = betaBase64PdfBlock.context
            title = betaBase64PdfBlock.title
            additionalProperties = betaBase64PdfBlock.additionalProperties.toMutableMap()
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
        fun source(base64: BetaBase64PdfSource) = source(Source.ofBase64(base64))

        /**
         * Alias for calling [source] with the following:
         * ```java
         * BetaBase64PdfSource.builder()
         *     .data(data)
         *     .build()
         * ```
         */
        fun base64Source(data: String) = source(BetaBase64PdfSource.builder().data(data).build())

        /** Alias for calling [source] with `Source.ofText(text)`. */
        fun source(text: BetaPlainTextSource) = source(Source.ofText(text))

        /**
         * Alias for calling [source] with the following:
         * ```java
         * BetaPlainTextSource.builder()
         *     .data(data)
         *     .build()
         * ```
         */
        fun textSource(data: String) = source(BetaPlainTextSource.builder().data(data).build())

        /** Alias for calling [source] with `Source.ofContent(content)`. */
        fun source(content: BetaContentBlockSource) = source(Source.ofContent(content))

        /**
         * Alias for calling [source] with the following:
         * ```java
         * BetaContentBlockSource.builder()
         *     .content(content)
         *     .build()
         * ```
         */
        fun contentSource(content: BetaContentBlockSource.Content) =
            source(BetaContentBlockSource.builder().content(content).build())

        /**
         * Alias for calling [contentSource] with `BetaContentBlockSource.Content.ofString(string)`.
         */
        fun contentSource(string: String) =
            contentSource(BetaContentBlockSource.Content.ofString(string))

        /**
         * Alias for calling [contentSource] with
         * `BetaContentBlockSource.Content.ofBetaContentBlockSource(betaContentBlockSource)`.
         */
        fun contentSourceOfBetaContentBlockSource(
            betaContentBlockSource: List<BetaContentBlockSourceContent>
        ) =
            contentSource(
                BetaContentBlockSource.Content.ofBetaContentBlockSource(betaContentBlockSource)
            )

        /** Alias for calling [source] with `Source.ofUrl(url)`. */
        fun source(url: BetaUrlPdfSource) = source(Source.ofUrl(url))

        /**
         * Alias for calling [source] with the following:
         * ```java
         * BetaUrlPdfSource.builder()
         *     .url(url)
         *     .build()
         * ```
         */
        fun urlSource(url: String) = source(BetaUrlPdfSource.builder().url(url).build())

        /** Alias for calling [source] with `Source.ofFile(file)`. */
        fun source(file: BetaFileDocumentSource) = source(Source.ofFile(file))

        /**
         * Alias for calling [source] with the following:
         * ```java
         * BetaFileDocumentSource.builder()
         *     .fileId(fileId)
         *     .build()
         * ```
         */
        fun fileSource(fileId: String) =
            source(BetaFileDocumentSource.builder().fileId(fileId).build())

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

        /** Create a cache control breakpoint at this content block. */
        fun cacheControl(cacheControl: BetaCacheControlEphemeral?) =
            cacheControl(JsonField.ofNullable(cacheControl))

        /** Alias for calling [Builder.cacheControl] with `cacheControl.orElse(null)`. */
        fun cacheControl(cacheControl: Optional<BetaCacheControlEphemeral>) =
            cacheControl(cacheControl.getOrNull())

        /**
         * Sets [Builder.cacheControl] to an arbitrary JSON value.
         *
         * You should usually call [Builder.cacheControl] with a well-typed
         * [BetaCacheControlEphemeral] value instead. This method is primarily for setting the field
         * to an undocumented or not yet supported value.
         */
        fun cacheControl(cacheControl: JsonField<BetaCacheControlEphemeral>) = apply {
            this.cacheControl = cacheControl
        }

        fun citations(citations: BetaCitationsConfigParam) = citations(JsonField.of(citations))

        /**
         * Sets [Builder.citations] to an arbitrary JSON value.
         *
         * You should usually call [Builder.citations] with a well-typed [BetaCitationsConfigParam]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun citations(citations: JsonField<BetaCitationsConfigParam>) = apply {
            this.citations = citations
        }

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
         * Returns an immutable instance of [BetaBase64PdfBlock].
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
        fun build(): BetaBase64PdfBlock =
            BetaBase64PdfBlock(
                checkRequired("source", source),
                type,
                cacheControl,
                citations,
                context,
                title,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaBase64PdfBlock = apply {
        if (validated) {
            return@apply
        }

        source().validate()
        _type().let {
            if (it != JsonValue.from("document")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        cacheControl().ifPresent { it.validate() }
        citations().ifPresent { it.validate() }
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
            type.let { if (it == JsonValue.from("document")) 1 else 0 } +
            (cacheControl.asKnown().getOrNull()?.validity() ?: 0) +
            (citations.asKnown().getOrNull()?.validity() ?: 0) +
            (if (context.asKnown().isPresent) 1 else 0) +
            (if (title.asKnown().isPresent) 1 else 0)

    @JsonDeserialize(using = Source.Deserializer::class)
    @JsonSerialize(using = Source.Serializer::class)
    class Source
    private constructor(
        private val base64: BetaBase64PdfSource? = null,
        private val text: BetaPlainTextSource? = null,
        private val content: BetaContentBlockSource? = null,
        private val url: BetaUrlPdfSource? = null,
        private val file: BetaFileDocumentSource? = null,
        private val _json: JsonValue? = null,
    ) {

        fun base64(): Optional<BetaBase64PdfSource> = Optional.ofNullable(base64)

        fun text(): Optional<BetaPlainTextSource> = Optional.ofNullable(text)

        fun content(): Optional<BetaContentBlockSource> = Optional.ofNullable(content)

        fun url(): Optional<BetaUrlPdfSource> = Optional.ofNullable(url)

        fun file(): Optional<BetaFileDocumentSource> = Optional.ofNullable(file)

        fun isBase64(): Boolean = base64 != null

        fun isText(): Boolean = text != null

        fun isContent(): Boolean = content != null

        fun isUrl(): Boolean = url != null

        fun isFile(): Boolean = file != null

        fun asBase64(): BetaBase64PdfSource = base64.getOrThrow("base64")

        fun asText(): BetaPlainTextSource = text.getOrThrow("text")

        fun asContent(): BetaContentBlockSource = content.getOrThrow("content")

        fun asUrl(): BetaUrlPdfSource = url.getOrThrow("url")

        fun asFile(): BetaFileDocumentSource = file.getOrThrow("file")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T =
            when {
                base64 != null -> visitor.visitBase64(base64)
                text != null -> visitor.visitText(text)
                content != null -> visitor.visitContent(content)
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
                    override fun visitBase64(base64: BetaBase64PdfSource) {
                        base64.validate()
                    }

                    override fun visitText(text: BetaPlainTextSource) {
                        text.validate()
                    }

                    override fun visitContent(content: BetaContentBlockSource) {
                        content.validate()
                    }

                    override fun visitUrl(url: BetaUrlPdfSource) {
                        url.validate()
                    }

                    override fun visitFile(file: BetaFileDocumentSource) {
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
                    override fun visitBase64(base64: BetaBase64PdfSource) = base64.validity()

                    override fun visitText(text: BetaPlainTextSource) = text.validity()

                    override fun visitContent(content: BetaContentBlockSource) = content.validity()

                    override fun visitUrl(url: BetaUrlPdfSource) = url.validity()

                    override fun visitFile(file: BetaFileDocumentSource) = file.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Source && base64 == other.base64 && text == other.text && content == other.content && url == other.url && file == other.file /* spotless:on */
        }

        override fun hashCode(): Int = /* spotless:off */ Objects.hash(base64, text, content, url, file) /* spotless:on */

        override fun toString(): String =
            when {
                base64 != null -> "Source{base64=$base64}"
                text != null -> "Source{text=$text}"
                content != null -> "Source{content=$content}"
                url != null -> "Source{url=$url}"
                file != null -> "Source{file=$file}"
                _json != null -> "Source{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Source")
            }

        companion object {

            @JvmStatic fun ofBase64(base64: BetaBase64PdfSource) = Source(base64 = base64)

            @JvmStatic fun ofText(text: BetaPlainTextSource) = Source(text = text)

            @JvmStatic fun ofContent(content: BetaContentBlockSource) = Source(content = content)

            @JvmStatic fun ofUrl(url: BetaUrlPdfSource) = Source(url = url)

            @JvmStatic fun ofFile(file: BetaFileDocumentSource) = Source(file = file)
        }

        /** An interface that defines how to map each variant of [Source] to a value of type [T]. */
        interface Visitor<out T> {

            fun visitBase64(base64: BetaBase64PdfSource): T

            fun visitText(text: BetaPlainTextSource): T

            fun visitContent(content: BetaContentBlockSource): T

            fun visitUrl(url: BetaUrlPdfSource): T

            fun visitFile(file: BetaFileDocumentSource): T

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
                        return tryDeserialize(node, jacksonTypeRef<BetaBase64PdfSource>())?.let {
                            Source(base64 = it, _json = json)
                        } ?: Source(_json = json)
                    }
                    "text" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaPlainTextSource>())?.let {
                            Source(text = it, _json = json)
                        } ?: Source(_json = json)
                    }
                    "content" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaContentBlockSource>())?.let {
                            Source(content = it, _json = json)
                        } ?: Source(_json = json)
                    }
                    "url" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaUrlPdfSource>())?.let {
                            Source(url = it, _json = json)
                        } ?: Source(_json = json)
                    }
                    "file" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaFileDocumentSource>())?.let {
                            Source(file = it, _json = json)
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
                    value.content != null -> generator.writeObject(value.content)
                    value.url != null -> generator.writeObject(value.url)
                    value.file != null -> generator.writeObject(value.file)
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

        return /* spotless:off */ other is BetaBase64PdfBlock && source == other.source && type == other.type && cacheControl == other.cacheControl && citations == other.citations && context == other.context && title == other.title && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(source, type, cacheControl, citations, context, title, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaBase64PdfBlock{source=$source, type=$type, cacheControl=$cacheControl, citations=$citations, context=$context, title=$title, additionalProperties=$additionalProperties}"
}
