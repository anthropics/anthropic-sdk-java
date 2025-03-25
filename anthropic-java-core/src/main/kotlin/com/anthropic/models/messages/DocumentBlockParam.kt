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

class DocumentBlockParam
private constructor(
    private val source: JsonField<Source>,
    private val type: JsonValue,
    private val cacheControl: JsonField<CacheControlEphemeral>,
    private val citations: JsonField<CitationsConfigParam>,
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
        cacheControl: JsonField<CacheControlEphemeral> = JsonMissing.of(),
        @JsonProperty("citations")
        @ExcludeMissing
        citations: JsonField<CitationsConfigParam> = JsonMissing.of(),
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
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun cacheControl(): Optional<CacheControlEphemeral> =
        Optional.ofNullable(cacheControl.getNullable("cache_control"))

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun citations(): Optional<CitationsConfigParam> =
        Optional.ofNullable(citations.getNullable("citations"))

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun context(): Optional<String> = Optional.ofNullable(context.getNullable("context"))

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun title(): Optional<String> = Optional.ofNullable(title.getNullable("title"))

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

    /**
     * Returns the raw JSON value of [citations].
     *
     * Unlike [citations], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("citations")
    @ExcludeMissing
    fun _citations(): JsonField<CitationsConfigParam> = citations

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
         * Returns a mutable builder for constructing an instance of [DocumentBlockParam].
         *
         * The following fields are required:
         * ```java
         * .source()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [DocumentBlockParam]. */
    class Builder internal constructor() {

        private var source: JsonField<Source>? = null
        private var type: JsonValue = JsonValue.from("document")
        private var cacheControl: JsonField<CacheControlEphemeral> = JsonMissing.of()
        private var citations: JsonField<CitationsConfigParam> = JsonMissing.of()
        private var context: JsonField<String> = JsonMissing.of()
        private var title: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(documentBlockParam: DocumentBlockParam) = apply {
            source = documentBlockParam.source
            type = documentBlockParam.type
            cacheControl = documentBlockParam.cacheControl
            citations = documentBlockParam.citations
            context = documentBlockParam.context
            title = documentBlockParam.title
            additionalProperties = documentBlockParam.additionalProperties.toMutableMap()
        }

        fun source(source: Source) = source(JsonField.of(source))

        /**
         * Sets [Builder.source] to an arbitrary JSON value.
         *
         * You should usually call [Builder.source] with a well-typed [Source] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun source(source: JsonField<Source>) = apply { this.source = source }

        /** Alias for calling [source] with `Source.ofBase64Pdf(base64Pdf)`. */
        fun source(base64Pdf: Base64PdfSource) = source(Source.ofBase64Pdf(base64Pdf))

        /**
         * Alias for calling [source] with the following:
         * ```java
         * Base64PdfSource.builder()
         *     .data(data)
         *     .build()
         * ```
         */
        fun base64PdfSource(data: String) = source(Base64PdfSource.builder().data(data).build())

        /** Alias for calling [source] with `Source.ofPlainText(plainText)`. */
        fun source(plainText: PlainTextSource) = source(Source.ofPlainText(plainText))

        /**
         * Alias for calling [source] with the following:
         * ```java
         * PlainTextSource.builder()
         *     .data(data)
         *     .build()
         * ```
         */
        fun plainTextSource(data: String) = source(PlainTextSource.builder().data(data).build())

        /** Alias for calling [source] with `Source.ofContentBlock(contentBlock)`. */
        fun source(contentBlock: ContentBlockSource) = source(Source.ofContentBlock(contentBlock))

        /**
         * Alias for calling [source] with the following:
         * ```java
         * ContentBlockSource.builder()
         *     .content(content)
         *     .build()
         * ```
         */
        fun contentBlockSource(content: ContentBlockSource.Content) =
            source(ContentBlockSource.builder().content(content).build())

        /**
         * Alias for calling [contentBlockSource] with
         * `ContentBlockSource.Content.ofString(string)`.
         */
        fun contentBlockSource(string: String) =
            contentBlockSource(ContentBlockSource.Content.ofString(string))

        /**
         * Alias for calling [contentBlockSource] with
         * `ContentBlockSource.Content.ofBlockSource(blockSource)`.
         */
        fun contentBlockSourceOfBlockSource(blockSource: List<ContentBlockSourceContent>) =
            contentBlockSource(ContentBlockSource.Content.ofBlockSource(blockSource))

        /** Alias for calling [source] with `Source.ofUrlPdf(urlPdf)`. */
        fun source(urlPdf: UrlPdfSource) = source(Source.ofUrlPdf(urlPdf))

        /**
         * Alias for calling [source] with the following:
         * ```java
         * UrlPdfSource.builder()
         *     .url(url)
         *     .build()
         * ```
         */
        fun urlPdfSource(url: String) = source(UrlPdfSource.builder().url(url).build())

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

        fun citations(citations: CitationsConfigParam) = citations(JsonField.of(citations))

        /**
         * Sets [Builder.citations] to an arbitrary JSON value.
         *
         * You should usually call [Builder.citations] with a well-typed [CitationsConfigParam]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun citations(citations: JsonField<CitationsConfigParam>) = apply {
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
         * Returns an immutable instance of [DocumentBlockParam].
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
        fun build(): DocumentBlockParam =
            DocumentBlockParam(
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

    fun validate(): DocumentBlockParam = apply {
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

    @JsonDeserialize(using = Source.Deserializer::class)
    @JsonSerialize(using = Source.Serializer::class)
    class Source
    private constructor(
        private val base64Pdf: Base64PdfSource? = null,
        private val plainText: PlainTextSource? = null,
        private val contentBlock: ContentBlockSource? = null,
        private val urlPdf: UrlPdfSource? = null,
        private val _json: JsonValue? = null,
    ) {

        fun base64Pdf(): Optional<Base64PdfSource> = Optional.ofNullable(base64Pdf)

        fun plainText(): Optional<PlainTextSource> = Optional.ofNullable(plainText)

        fun contentBlock(): Optional<ContentBlockSource> = Optional.ofNullable(contentBlock)

        fun urlPdf(): Optional<UrlPdfSource> = Optional.ofNullable(urlPdf)

        fun isBase64Pdf(): Boolean = base64Pdf != null

        fun isPlainText(): Boolean = plainText != null

        fun isContentBlock(): Boolean = contentBlock != null

        fun isUrlPdf(): Boolean = urlPdf != null

        fun asBase64Pdf(): Base64PdfSource = base64Pdf.getOrThrow("base64Pdf")

        fun asPlainText(): PlainTextSource = plainText.getOrThrow("plainText")

        fun asContentBlock(): ContentBlockSource = contentBlock.getOrThrow("contentBlock")

        fun asUrlPdf(): UrlPdfSource = urlPdf.getOrThrow("urlPdf")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T {
            return when {
                base64Pdf != null -> visitor.visitBase64Pdf(base64Pdf)
                plainText != null -> visitor.visitPlainText(plainText)
                contentBlock != null -> visitor.visitContentBlock(contentBlock)
                urlPdf != null -> visitor.visitUrlPdf(urlPdf)
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
                    override fun visitBase64Pdf(base64Pdf: Base64PdfSource) {
                        base64Pdf.validate()
                    }

                    override fun visitPlainText(plainText: PlainTextSource) {
                        plainText.validate()
                    }

                    override fun visitContentBlock(contentBlock: ContentBlockSource) {
                        contentBlock.validate()
                    }

                    override fun visitUrlPdf(urlPdf: UrlPdfSource) {
                        urlPdf.validate()
                    }
                }
            )
            validated = true
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Source && base64Pdf == other.base64Pdf && plainText == other.plainText && contentBlock == other.contentBlock && urlPdf == other.urlPdf /* spotless:on */
        }

        override fun hashCode(): Int = /* spotless:off */ Objects.hash(base64Pdf, plainText, contentBlock, urlPdf) /* spotless:on */

        override fun toString(): String =
            when {
                base64Pdf != null -> "Source{base64Pdf=$base64Pdf}"
                plainText != null -> "Source{plainText=$plainText}"
                contentBlock != null -> "Source{contentBlock=$contentBlock}"
                urlPdf != null -> "Source{urlPdf=$urlPdf}"
                _json != null -> "Source{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Source")
            }

        companion object {

            @JvmStatic fun ofBase64Pdf(base64Pdf: Base64PdfSource) = Source(base64Pdf = base64Pdf)

            @JvmStatic fun ofPlainText(plainText: PlainTextSource) = Source(plainText = plainText)

            @JvmStatic
            fun ofContentBlock(contentBlock: ContentBlockSource) =
                Source(contentBlock = contentBlock)

            @JvmStatic fun ofUrlPdf(urlPdf: UrlPdfSource) = Source(urlPdf = urlPdf)
        }

        /** An interface that defines how to map each variant of [Source] to a value of type [T]. */
        interface Visitor<out T> {

            fun visitBase64Pdf(base64Pdf: Base64PdfSource): T

            fun visitPlainText(plainText: PlainTextSource): T

            fun visitContentBlock(contentBlock: ContentBlockSource): T

            fun visitUrlPdf(urlPdf: UrlPdfSource): T

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
                        tryDeserialize(node, jacksonTypeRef<Base64PdfSource>()) { it.validate() }
                            ?.let {
                                return Source(base64Pdf = it, _json = json)
                            }
                    }
                    "text" -> {
                        tryDeserialize(node, jacksonTypeRef<PlainTextSource>()) { it.validate() }
                            ?.let {
                                return Source(plainText = it, _json = json)
                            }
                    }
                    "content" -> {
                        tryDeserialize(node, jacksonTypeRef<ContentBlockSource>()) { it.validate() }
                            ?.let {
                                return Source(contentBlock = it, _json = json)
                            }
                    }
                    "url" -> {
                        tryDeserialize(node, jacksonTypeRef<UrlPdfSource>()) { it.validate() }
                            ?.let {
                                return Source(urlPdf = it, _json = json)
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
                    value.base64Pdf != null -> generator.writeObject(value.base64Pdf)
                    value.plainText != null -> generator.writeObject(value.plainText)
                    value.contentBlock != null -> generator.writeObject(value.contentBlock)
                    value.urlPdf != null -> generator.writeObject(value.urlPdf)
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

        return /* spotless:off */ other is DocumentBlockParam && source == other.source && type == other.type && cacheControl == other.cacheControl && citations == other.citations && context == other.context && title == other.title && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(source, type, cacheControl, citations, context, title, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "DocumentBlockParam{source=$source, type=$type, cacheControl=$cacheControl, citations=$citations, context=$context, title=$title, additionalProperties=$additionalProperties}"
}
